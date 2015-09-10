package org.yifan.interview.employee;

import org.yifan.interview.call.Call;

/**
 * Call handler
 * @author Yifan Gu
 */
public abstract class CallHandler {
    public enum Role {
        MANAGER("Manger"),
        SUPERVISOR("Supervisor"),
        EMPLOYEE("Employee");

        private final String role;

        Role(String role) {
            this.role = role;
        }

        String getRole() {
            return role;
        }
    }

    public int id;
    protected boolean isAvailable = true;
    public Role role;
    protected Call call;

    public CallHandler(int id, Role role) {
        this.id = id;
        this.role = role;
    }

    public boolean isAvailable() {
        if(!isAvailable) {
            if(call == null) {
                throw new RuntimeException("Employee " + id + "is not answering but busy...");
            }
            call.isEnded();
        }
        return isAvailable;
    }

    public CallHandler answerCall(Call call) {
        this.call = call;
        isAvailable = false;
        return handleCall();
    }

    public void finishCall() {
        this.call = null;
        isAvailable = true;
    }

    public Call getCall(){
        return call;
    }

    public abstract CallHandler handleCall();
}
