package org.yifan.interview.employee;

import org.yifan.interview.call.Call;

/**
 * @author Yifan Gu
 */
public class Supervisor extends CallHandler {

    private static Supervisor supervisorInstance;
    private Manager manager;

    private Supervisor (int id, Manager manager) {
        super(id, Role.SUPERVISOR);
        setManager(manager);
    }

    public static Supervisor getSupervisor(int id, Manager manager) {
        if (supervisorInstance == null) {
            supervisorInstance = new Supervisor(id, manager);
        }
        return supervisorInstance;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    private CallHandler transferToManager(Call call) {
        while(getManager().isAvailable() == false) {}
        return getManager().answerCall(call);
    }

    @Override
    public CallHandler handleCall() {
        CallHandler ret = this;
        if(call.difficultyLevel == Call.DifficultyLevel.MEDIUM_LEVEL) {
            call.setAnsweredTime(this);
        } else {
            ret = transferToManager(call);
            finishCall();
        }
        return ret;
    }
}
