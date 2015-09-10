package org.yifan.interview.employee;

import org.yifan.interview.call.Call;

/**
 * CallHandler represents an Employee, Supervisor or Manager.
 * 
 * @author Yifan Gu
 */
public abstract class CallHandler {
	/**
	 * Roles for CallHandler
	 */
	public enum Role {
		/**
		 * Manager role
		 */
		MANAGER,
		/**
		 * Supervisor role
		 */
		SUPERVISOR,
		/**
		 * Employee role
		 */
		EMPLOYEE;
	}

	public int id;
	protected boolean isAvailable = true;
	public Role role;
	// The Call that this handler is actively handling
	protected Call call;

	/**
	 * Constructor for CallHandler.
	 * 
	 * @param id
	 *            ID of this CallHandler
	 * @param role
	 *            Role of this CallHandler
	 */
	public CallHandler(int id, Role role) {
		this.id = id;
		this.role = role;
	}

	/**
	 * Returns true if the current CallHandler is available to answer calls.
	 * 
	 * @return true if this CallHandler is availabe to answer, false otherwise
	 */
	public boolean isAvailable() {
		if (!isAvailable) {
			if (call == null) {
				throw new RuntimeException("Employee " + id
						+ "is not answering but busy...");
			}
			call.isEnded();
		}
		return isAvailable;
	}

	/**
	 * It is called when a CallHandler finishes with one call.
	 */
	public void finishCall() {
		this.call = null;
		isAvailable = true;
	}

	/**
	 * Getter for call.
	 */
	public Call getCall() {
		return call;
	}

	/**
	 * Check if this CallHandler is able to handle an incoming call
	 * 
	 * @param call
	 *            incoming call
	 */
	protected boolean canIHandleIt(Call call) {
		if (call == null) {
			return false;
		} else if (call.difficultyLevel == Call.DifficultyLevel.EASY_LEVEL) {
			return true;
		} else if (role == Role.MANAGER) {
			return true;
		} else if (role == Role.SUPERVISOR
				&& call.difficultyLevel == Call.DifficultyLevel.MEDIUM_LEVEL) {
			return true;
		}

		return false;
	}

	/**
	 * Abstract method to handle incoming calls differently based on Role.
	 * 
	 * @param incomingCall
	 *            incoming call
	 */
	public abstract void handleCall(Call incomingCall);
}
