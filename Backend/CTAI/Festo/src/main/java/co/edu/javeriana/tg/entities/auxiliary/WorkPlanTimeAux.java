package co.edu.javeriana.tg.entities.auxiliary;

import java.util.List;

import co.edu.javeriana.tg.entities.Operation;

public class WorkPlanTimeAux {
    private Long transportTime;
    private List<Operation> operationsInvolved;

    public WorkPlanTimeAux(Long transportTime, List<Operation> operationsInvolved) {
        this.transportTime = transportTime;
        this.operationsInvolved = operationsInvolved;
    }

    public Long getTransportTime() {
        return transportTime;
    }

    public List<Operation> getOperationsInvolved() {
        return operationsInvolved;
    }
}
