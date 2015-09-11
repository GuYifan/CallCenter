package org.yifan.interview;

import java.util.*;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.yifan.interview.call.Call;
import org.yifan.interview.dispatcher.Dispatcher;
import org.yifan.interview.employee.Employee;
import org.yifan.interview.employee.Manager;
import org.yifan.interview.employee.Supervisor;

/**
 * Unit test for findCallHandler method in Dispatcher.
 * 
 * @author Yifan Gu
 */
public class FindCallHandlerTest {

	@Test
	public void testFindCallHandler() {
		// Initialize 1 manager, 1 supervisor and 3 employees
		Manager manager = Manager.getMangaer(0);
		Supervisor supervisor = Supervisor.getSupervisor(1, manager);
		Employee employeeOne = new Employee(2, supervisor, manager);
		Employee employeeTwo = new Employee(3, supervisor, manager);
		Employee employeeThree = new Employee(4, supervisor, manager);
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(employeeOne);
		employees.add(employeeTwo);
		employees.add(employeeThree);
		// Initialize dispatcher
		Dispatcher dispatcher = new Dispatcher(employees, supervisor, manager);

		// Create a list of calls. Length of time is in seconds.
		ArrayList<Call> calls = new ArrayList<Call>();
		calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 15));
		calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 17));
		calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 2));
		calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
		calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
		calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 2));
		calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 7));
		calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 13));
		calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 6));
		calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 3));

		// Let the dispatcher to dispatch all calls
		for (Call c : calls) {
			dispatcher.findCallHandler(c);
		}

		// Since the handler is only triggered by an incoming call
		// it needs to clean up queued calls by answering all queued calls
		supervisor.handlingRemainingCalls();
		manager.handleRemainingCalls();
		
		// Expected results which is the order of call handler that should 
		// answer the corresponding call
		int[] expectedResults = {2, 3, 1, 0, 0, 1, 4, 4, 2, 3};

		// expected results of callhandlers should be 2, 3, 1, 0, 0, 1, 4, 4, 2, 3 for 
		// the first call to the last
		for (Call c : calls) {
			int idx = calls.indexOf(c);
			assertTrue("Call # " + idx + " should be " +
					"answered by " + expectedResults[idx] + 
					" but actually anwsered by " + c.getAssignee().id,
					c.getAssignee().id == expectedResults[idx]);
		}
	}
}
