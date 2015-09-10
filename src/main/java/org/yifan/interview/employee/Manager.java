package org.yifan.interview.employee;

import java.util.LinkedList;
import java.util.Queue;

import org.yifan.interview.call.Call;

/**
 * Manager represents the manager at call center.
 * 
 * @author Yifan Gu
 */
public class Manager extends CallHandler {

	// Since there's only one manager so make it static.
	private static Manager managerInstance;
	// A queue of incoming calls.
	private Queue<Call> managerQueueOfCalls = new LinkedList<Call>();

	/**
	 * Private constructor for Manager. It's private because of the singleton
	 * pattern.
	 * 
	 * @param id
	 *            ID
	 */
	private Manager(int id) {
		super(id, Role.MANAGER);
	}

	/**
	 * Returns the only instance of a Manager.If there is no create one. Since
	 * there is only one manager in the call center, so it implements singleton
	 * pattern.
	 * 
	 * @param id
	 *            ID
	 */
	public static Manager getMangaer(int id) {
		if (managerInstance == null) {
			managerInstance = new Manager(id);
		}
		return managerInstance;
	}

	/**
	 * Handles an incoming call. If the manager is busy at the moment, put the
	 * call into queue. If the manager is available, find the first call in the
	 * queue and answer it.
	 * 
	 * @param incomingCall
	 *            an incoming call
	 */
	@Override
	public void handleCall(Call incomingCall) {
		managerQueueOfCalls.add(incomingCall);
		incomingCall.setAssignee(this);
		if (isAvailable()) {
			handleNextCallInLine();
		}
	}

	/**
	 * Find the first call in the queue and answer it.
	 */
	private void handleNextCallInLine() {
		call = managerQueueOfCalls.remove();
		if (call == null)
			return;
		isAvailable = false;
		call.setAnsweredTime(this);
	}

	/**
	 * At the end of day, when call center stops receiving more calls, it needs
	 * to cleanup all calls in the queue. Since the only thing triggers the
	 * manager to handler the call is another incoming call, so this is needed
	 * when it needs to clean up the queue but no more incoming calls is made.
	 */
	public void handleRemainingCalls() {
		while (!managerQueueOfCalls.isEmpty()) {
			handleNextCallInLine();
		}
	}
}
