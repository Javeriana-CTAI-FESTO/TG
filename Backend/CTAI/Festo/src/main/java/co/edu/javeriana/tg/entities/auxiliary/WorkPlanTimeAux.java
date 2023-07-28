package co.edu.javeriana.tg.entities.auxiliary;

import java.util.List;

public class WorkPlanTimeAux {
    private Long transportTime;
    private List<Long> operationsInvolvedIds;

    public WorkPlanTimeAux(Long transportTime, List<Long> operationsInvolved) {
        this.transportTime = transportTime;
        this.operationsInvolvedIds = operationsInvolved;
    }

    public Long getTransportTime() {
        return transportTime;
    }

    public List<Long> getOperationsInvolved() {
        return operationsInvolvedIds;
    }
}
