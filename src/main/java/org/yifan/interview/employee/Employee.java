package org.yifan.interview.employee;

import org.yifan.interview.call.Call;

/**
 * Employee represents and employee at call center.
 * 
 * @author Yifan Gu
 */
public class Employee extends CallHandler {

	private Supervisor supervisor;
	private Manager manager;

	/**
	 * Constructor for Employee.
	 * 
	 * @param id
	 *            ID for this employee
	 * @param supervisor
	 *            this employee's supervisor
	 * @param manager
	 *            this employee's manager
	 */
	public Employee(int id, Supervisor supvisor, Manager manager) {
		super(id, Role.EMPLOYEE);
		setSupervisor(supvisor);
		setManager(manager);
	}

	/**
	 * Setter for supervisor.
	 * 
	 * @param his
	 *            supervisor
	 */
	public void setSupervisor(Supervisor supvisor) {
		this.supervisor = supvisor;
	}

	/**
	 * Getter for supervisor.
	 * 
	 * @return supervisor
	 */
	public Supervisor getSupervisor() {
		return supervisor;
	}

	/**
	 * Setter for manager
	 * 
	 * @param manager
	 *            his manager
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Getter for manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * Transfer an incoming call to his supervisor.
	 * 
	 * @param incomingCall
	 *            an incoming call
	 */
	private void transferToSupervisor(Call incomingCall) {
		getSupervisor().handleCall(incomingCall);
	}

	/**
	 * Handles an incoming call. If this call is easy enough to be answered by
	 * an employee, then this employee will answer it, otherwise it will be
	 * transfered to his supervisor.
	 * 
	 * @param incomingCall
	 *            an incoming call
	 */
	@Override
	public void handleCall(Call incomingCall) {
		this.call = incomingCall;
		isAvailable = false;
		// Take the call if it's easy otherwise transfer to supervisor
		if (canIHandleIt(incomingCall)) {
			incomingCall.setAnsweredTime(this);
		} else {
			transferToSupervisor(incomingCall);
			finishCall();
		}
	}
}
