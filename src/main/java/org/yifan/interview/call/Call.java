package org.yifan.interview.call;

import org.yifan.interview.employee.CallHandler;

/**
 * Call class represents a call
 * 
 * @author Yifan Gu
 */
public class Call {
	/**
	 * Difficulty levels for calls
	 */
	public enum DifficultyLevel {
		/**
		 * Easy calls that can be answered by any employees
		 */
		EASY_LEVEL,
		/**
		 * Medium level calls that should be answered by the supervisor
		 */
		MEDIUM_LEVEL,
		/**
		 * Touch calls that only can be answered by manager
		 */
		HARD_LEVEL;
	}

	public final DifficultyLevel difficultyLevel;
	private CallHandler assignee;
	// expected length of this call
	private long length;
	// the time stamp when this call is answered
	private long answeredTime;
	private boolean isOver = false;

	/**
	 * Constructor for Call
	 * 
	 * @param difficultyLevel
	 *            difficulty level
	 * @param length
	 *            expected length of the call
	 */
	public Call(DifficultyLevel difficultyLevel, long length) {
		this.difficultyLevel = difficultyLevel;
		this.length = length;
	}

	/**
	 * Set assignee to the call handler.
	 * 
	 * @param assignee
	 *            the call handler
	 */
	public void setAssignee(CallHandler assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the call handler
	 */
	public CallHandler getAssignee() {
		return assignee;
	}

	/**
	 * Mark the start of this call being answered.
	 * 
	 * @param person
	 *            the actual call handler who answers the call
	 */
	public void setAnsweredTime(CallHandler person) {
		answeredTime = new java.util.Date().getTime();
		setAssignee(person);
	}

	/**
	 * Indicates if the call is ended.
	 * 
	 * @return true if call is over and false otherwise
	 */
	public boolean isEnded() {
		if ((new java.util.Date().getTime() - answeredTime) / 1000 >= length) {
			isOver = true;
			assignee.finishCall();
		}
		return isOver;
	}
}
