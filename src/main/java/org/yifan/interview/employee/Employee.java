package org.yifan.interview.employee;

import org.yifan.interview.call.Call;

/**
 * @author Yifan Gu
 */
public class Employee extends CallHandler {

    private Supervisor supervisor;
    private Manager manager;

    public Employee(int id, Supervisor supvisor, Manager manager) {
        super(id, Role.EMPLOYEE);
        setSupervisor(supvisor);
        setManager(manager);
    }

    public void setSupervisor(Supervisor supvisor) {
        this.supervisor = supvisor;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    private CallHandler transferToSupervisor(Call call) {
        while(getSupervisor().isAvailable() == false) {}
        return getSupervisor().answerCall(call);
    }

    @Override
    public CallHandler handleCall() {
        CallHandler ret = this;
        if(call.difficultyLevel == Call.DifficultyLevel.EASY_LEVEL) {
            call.setAnsweredTime(this);
        } else {
            ret = transferToSupervisor(call);
            finishCall();
        }
        return ret;
    }
}
