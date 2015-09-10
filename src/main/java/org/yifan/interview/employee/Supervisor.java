package org.yifan.interview.employee;

import java.util.Stack;

import org.yifan.interview.call.Call;

/**
 * Superevisor represents the only supervisor in the call center
 * 
 * @author Yifan Gu
 */
public class Supervisor extends CallHandler {

	// Since there's only one supervisor so make it static.
	private static Supervisor supervisorInstance;
	private Manager manager;
	// A stack which represents a queue of incoming calls.
	private Stack<Call> mainQueueOfCalls = new Stack<Call>();
	// A stack which is used when it looks for next available call to answer.
	private Stack<Call> tmpStackfCalls = new Stack<Call>();

	/**
	 * Private constructor for Supervisor. It's private because of the singleton
	 * pattern.
	 * 
	 * @param id
	 *            ID
	 * @param manager
	 *            manager
	 */
	private Supervisor(int id, Manager manager) {
		super(id, Role.SUPERVISOR);
		setManager(manager);
	}

	/**
	 * Returns the only instance of the Supervisor.If there is no create one.
	 * Since there is only one supervisor in the call center, so it implements
	 * singleton pattern.
	 * 
	 * @param id
	 * @param manager
	 */
	public static Supervisor getSupervisor(int id, Manager manager) {
		if (supervisorInstance == null) {
			supervisorInstance = new Supervisor(id, manager);
		}
		return supervisorInstance;
	}

	/**
	 * Setter for manager.
	 * 
	 * @param manager
	 *            manager
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Getter for ,amager
	 * 
	 * @return manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * Put an incoming call in queue and set assignee to this supervisor
	 * temporarily.
	 * 
	 * @param incomingCall
	 *            an incoming call
	 */
	private void putInQueue(Call incomingCall) {
		mainQueueOfCalls.add(incomingCall);
		incomingCall.setAssignee(this);
	}

	/**
	 * Put an incoming call in the queue first. Then check if he's available to
	 * handle the next call.
	 * 
	 * @param incomingCall
	 *            an incoming call
	 */
	@Override
	public void handleCall(Call incomingCall) {
		putInQueue(incomingCall);
		if (this.isAvailable()) {
			handleNextCallInLine();
		}
	}

	/**
	 * Mark the start of answering a call by supervisor.
	 * 
	 * @param nextCall
	 *            a call
	 */
	private void answer(Call nextCall) {
		this.call = nextCall;
		isAvailable = false;
		nextCall.setAnsweredTime(this);
	}

	/**
	 * Transfer the call to the manager.
	 * 
	 * @param nextCall
	 *            a call
	 */
	private void transferToManager(Call nextCall) {
		getManager().handleCall(nextCall);
		finishCall();
	}

	/**
	 * Find the next call in the queue to answer. If the call can't be answered
	 * by supervisor, transfer it to the manager.
	 */
	private void handleNextCallInLine() {
		Call nextCallToHandle = null;
		popQueueToStack();
		nextCallToHandle = tmpStackfCalls.pop();
		// if can't be handled, transfer to manager
		while (!canIHandleIt(nextCallToHandle)) {
			transferToManager(nextCallToHandle);
			// if no more calls in the queue
			if (tmpStackfCalls.isEmpty()) {
				nextCallToHandle = null;
				break;
			}
			// iterate to next call
			nextCallToHandle = tmpStackfCalls.pop();
		}
		// if finds a call to answer
		if (nextCallToHandle != null) {
			answer(nextCallToHandle);
		}
		// put all calls back to main queue
		popStackBackToQueue();
	}

	/**
	 * Pops all calls in main queue to temporary stack.
	 */
	private void popQueueToStack() {
		while (!mainQueueOfCalls.isEmpty()) {
			tmpStackfCalls.add(mainQueueOfCalls.pop());
		}
	}

	/**
	 * Pops all calls in temporary stack back to main queue.
	 */
	private void popStackBackToQueue() {
		while (!tmpStackfCalls.isEmpty()) {
			mainQueueOfCalls.add(tmpStackfCalls.pop());
		}
	}

	/**
	 * At the end of day, when call center stops receiving more calls, it needs
	 * to cleanup all calls in the queue. Since the only thing triggers the
	 * supervisor to handler the call is another incoming call, so this is
	 * needed when it needs to clean up the queue but no more incoming calls is
	 * made.
	 */
	public void handlingRemainingCalls() {
		while (!mainQueueOfCalls.isEmpty()) {
			handleNextCallInLine();
		}
	}
}
