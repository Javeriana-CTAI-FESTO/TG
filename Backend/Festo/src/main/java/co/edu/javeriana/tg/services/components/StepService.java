package co.edu.javeriana.tg.services.components;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.auxiliary.WorkPlanTimeAux;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.managed.Step;
import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.entities.managed.StepParameter;
import co.edu.javeriana.tg.repositories.interfaces.FinishedStepRepository;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.StepParameterDefinitionRepository;
import co.edu.javeriana.tg.repositories.interfaces.StepParameterRepository;
import co.edu.javeriana.tg.repositories.interfaces.StepRepository;

@Component
@Transactional
public class StepService {

    private final StepDefinitionRepository stepDefinitionRepository;

    private final StepParameterDefinitionRepository stepParameterDefinitionRepository;

    private final StepParameterRepository stepParameterRepository;

    private final StepRepository stepRepository;

    private final FinishedStepRepository finishedStepRepository;

    private final WorkPlanService workPlanService;

    private final OperationService operationService;

    public StepService(StepDefinitionRepository stepDefinitionRepository, WorkPlanService workPlanService,
            OperationService operationService, StepRepository stepRepository,
            StepParameterDefinitionRepository stepParameterDefinitionRepository,
            StepParameterRepository stepParameterRepository, FinishedStepRepository finishedStepRepository) {
        this.stepDefinitionRepository = stepDefinitionRepository;
        this.workPlanService = workPlanService;
        this.operationService = operationService;
        this.stepRepository = stepRepository;
        this.stepParameterDefinitionRepository = stepParameterDefinitionRepository;
        this.stepParameterRepository = stepParameterRepository;
        this.finishedStepRepository = finishedStepRepository;
    }

    public List<StepDefinitionDTO> getAll() {
        return stepDefinitionRepository.findAll().stream()
                .map(stepDefinition -> new StepDefinitionDTO(stepDefinition,
                        workPlanService.getWorkplanById(stepDefinition.getWorkPlan()),
                        operationService.get(stepDefinition.getOperation())))
                .collect(Collectors.toList());
    }

    public WorkPlanTimeAux getWorkPlanTime(Long workPlanNumber) {
        List<Long> operations = new ArrayList<>();
        List<StepDefinition> steps = stepDefinitionRepository.findByWorkPlan(workPlanNumber);
        steps.forEach(step -> operations.add(step.getOperation()));
        return new WorkPlanTimeAux(steps.stream().mapToLong(StepDefinition::getTransportTime).sum(), operations);
    }

    public Long getWorkPlanOperationsCount(Long workPlanNumber) {
        return stepDefinitionRepository.countByWorkPlan(workPlanNumber);
    }

    public List<StepDefinitionDTO> stepsByWorkplan(Long workPlanNumber) {
        return stepDefinitionRepository.findByWorkPlan(workPlanNumber).stream()
                .map(stepDefinition -> new StepDefinitionDTO(stepDefinition,
                        workPlanService.getWorkplanById(stepDefinition.getWorkPlan()),
                        operationService.get(stepDefinition.getOperation())))
                .collect(Collectors.toList());
    }

    public StepDefinitionDTO firstStepByWorkplan(Long workPlanNumber) {
        try {
            StepDefinition first = stepDefinitionRepository.findFirstByWorkPlan(workPlanNumber);
            return new StepDefinitionDTO(first, workPlanService.getWorkplanById(first.getWorkPlan()),
                    operationService.get(first.getOperation()));
        } catch (Exception e) {

        }
        return null;
    }

    public void saveStep(Step step) {
        try {
            stepRepository.save(step);
            stepParameterDefinitionRepository
                    .findByRelatedWorkPlanAndStep(step.getWorkPlanNumber(), step.getStepNumber())
                    .forEach(stepParameterDefinition -> {
                        StepParameter stepParameter = new StepParameter(stepParameterDefinition.getRelatedWorkPlan(),
                                stepParameterDefinition.getStep(), step.getOrderNumber(), step.getOrderPosition(),
                                stepParameterDefinition.getParameterNumber(), stepParameterDefinition.getParameter());
                        stepParameterRepository.save(stepParameter);
                    });
        } catch (Exception e) {
        }
    }

    public Long getWorkTimeForMachine(Long resource) {
        try {
            return stepRepository.findByResource(resource).stream().filter(step -> step.getRealEnd() != null)
                    .filter(step -> step.getRealStart() != null).mapToLong(step -> Duration
                            .between(step.getRealStart().toInstant(), step.getRealEnd().toInstant()).toSeconds())
                    .sum()
                    + finishedStepRepository.findByResource(resource).stream().filter(step -> step.getRealEnd() != null)
                            .filter(step -> step.getRealStart() != null).mapToLong(step -> Duration
                                    .between(step.getRealStart().toInstant(), step.getRealEnd().toInstant())
                                    .toSeconds())
                            .sum();
        } catch (Exception e) {
            return 1l;
        }
    }

    public Long getPlannedWorkTimeForMachine(Long resource) {
        try {
            return stepRepository.findByResource(resource).stream().filter(step -> step.getPlannedEnd() != null)
                    .filter(step -> step.getPlannedStart() != null).mapToLong(step -> Duration
                            .between(step.getPlannedStart().toInstant(), step.getPlannedEnd().toInstant()).toSeconds())
                    .sum()
                    + finishedStepRepository.findByResource(resource).stream()
                            .filter(step -> step.getPlannedEnd() != null)
                            .filter(step -> step.getPlannedStart() != null).mapToLong(step -> Duration
                                    .between(step.getPlannedStart().toInstant(), step.getPlannedEnd().toInstant())
                                    .toSeconds())
                            .sum();
        } catch (Exception e) {
            return 1l;
        }
    }

    public double getIdealWorkTimeForMachine(Long resource) {
        try {
            Double timeA = stepRepository.findByResource(resource).stream()
                    .filter(step -> step.getPlannedEnd() != null)
                    .filter(step -> step.getPlannedStart() != null).mapToLong(step -> Duration
                            .between(step.getPlannedStart().toInstant(), step.getPlannedEnd().toInstant())
                            .toSeconds())
                    .average().getAsDouble(),
                    timeB = finishedStepRepository.findByResource(resource).stream()
                            .filter(step -> step.getPlannedEnd() != null)
                            .filter(step -> step.getPlannedStart() != null).mapToLong(step -> Duration
                                    .between(step.getPlannedStart().toInstant(), step.getPlannedEnd().toInstant())
                                    .toSeconds())
                            .average().getAsDouble();
            return timeA + timeB;
        } catch (Exception e) {
            return 1l;
        }
    }

    public Long getTotalCountForMachine(Long resource) {
        try {
            return stepRepository.findByResource(resource).stream().filter(step -> step.getRealEnd() != null)
                    .filter(step -> step.getRealStart() != null).count()
                    + finishedStepRepository.findByResource(resource).stream().filter(step -> step.getRealEnd() != null)
                            .filter(step -> step.getRealStart() != null).count();
        } catch (Exception e) {
            return 1l;
        }
    }
}
