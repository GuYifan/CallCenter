package org.yifan.interview.dispatcher;

import org.yifan.interview.call.Call;
import org.yifan.interview.employee.Employee;
import org.yifan.interview.employee.Manager;
import org.yifan.interview.employee.CallHandler;
import org.yifan.interview.employee.Supervisor;

import java.util.ArrayList;

/**
 * Dispatcher represents call dispatcher.
 * 
 * @author Yifan Gu
 */
public class Dispatcher {

	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private Supervisor supervisor;
	private Manager manager;

	/**
	 * Constructor for Dispatcher.
	 * 
	 * @param employees
	 *            list of employees at the call center
	 * @param supervisor
	 *            the supervisor at the call center
	 * @param manager
	 *            the manager at the call center
	 */
	public Dispatcher(ArrayList<Employee> employees, Supervisor supervisor,
			Manager manager) {
		this.employees.addAll(employees);
		this.supervisor = supervisor;
		this.manager = manager;
	}

	/**
	 * Find a available employee and assign the incoming call to him.
	 * 
	 * @param call
	 *            incoming call
	 */
	public void findCallHandler(Call call) {
		CallHandler firstResponder = findAvailableEmployeeHandler(call);
		while (firstResponder == null) {
			firstResponder = findAvailableEmployeeHandler(call);
		}

		firstResponder.handleCall(call);
	}

	/**
	 * Find an available employee from the employees array. If no employee is
	 * available, returns null.
	 * 
	 * @param call
	 *            incoming call
	 */
	private Employee findAvailableEmployeeHandler(Call call) {
		Employee ret = null;
		for (Employee e : employees) {
			if (e.isAvailable()) {
				ret = e;
				break;
			}
		}
		return ret;
	}

}
