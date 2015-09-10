package org.yifan.interview.dispatcher;

import org.yifan.interview.call.Call;
import org.yifan.interview.employee.Employee;
import org.yifan.interview.employee.Manager;
import org.yifan.interview.employee.CallHandler;
import org.yifan.interview.employee.Supervisor;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Dispatcher
 * @author Yifan Gu
 */
public class Dispatcher {

    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private Supervisor supervisor;
    private Manager manager;

    public Dispatcher(ArrayList<Employee> employees, Supervisor supervisor, Manager manager) {
        this.employees.addAll(employees);
        this.supervisor = supervisor;
        this.manager = manager;
    }

    public CallHandler findCallHandler(Call call) {
        CallHandler firstResponder = findAvailableEmployeeHandler(call);
        while(firstResponder == null) {
        	firstResponder = findAvailableEmployeeHandler(call);
        }

        CallHandler finalResponder = firstResponder.answerCall(call);

        return finalResponder;
    }

    private Employee findAvailableEmployeeHandler(Call call) {
        Employee ret = null;
        for(Employee e : employees) {
            if(e.isAvailable()) {
                ret = e;
                break;
            }
        }
        return ret;
    }

}
