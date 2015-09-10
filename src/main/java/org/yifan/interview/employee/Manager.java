package org.yifan.interview.employee;

import org.yifan.interview.call.Call;

/**
 * @author Yifan Gu
 */
public class Manager extends CallHandler {

    private static Manager managerInstance;

    private Manager (int id) {
        super(id, Role.MANAGER);
    }

    public static Manager getMangaer(int id) {
        if (managerInstance == null) {
            managerInstance = new Manager(id);
        }
        return managerInstance;
    }

    @Override
    public Manager handleCall() {
        call.setAnsweredTime(this);
        return this;
    }
}
