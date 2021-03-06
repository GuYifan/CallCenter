package org.yifan.interview;

import org.yifan.interview.call.Call;
import org.yifan.interview.dispatcher.Dispatcher;
import org.yifan.interview.employee.Employee;
import org.yifan.interview.employee.Manager;
import org.yifan.interview.employee.Supervisor;

import java.util.ArrayList;

/**
 * Main program for call center. It's a demo that creates 3 employees, 1
 * supervisor and 1 manager. There is 10 calls and it demonstrates how
 * findCallHandler method in Dispatcher class works.
 * 
 * @author Yifan Gu
 */
public class App {
	public static void main(String[] args) {
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

		// Create a list of calls
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

		// expected results of callhandlers should be 2, 3, 1, 0, 0, 1, 4, 4, 2, 3 for 
		// the first call to the last
		for (Call c : calls) {
			System.out.println("This call is assigned to "
					+ c.getAssignee().role.toString() + " "
					+ c.getAssignee().id);
		}

	}
}
