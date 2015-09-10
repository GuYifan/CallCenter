package org.yifan.interview;

import org.yifan.interview.call.Call;
import org.yifan.interview.dispatcher.Dispatcher;
import org.yifan.interview.employee.Employee;
import org.yifan.interview.employee.Manager;
import org.yifan.interview.employee.CallHandler;
import org.yifan.interview.employee.Supervisor;

import java.util.ArrayList;

/**
 * Main program for call center
 * @author Yifan Gu
 */
public class App 
{
    public static void main( String[] args )
    {
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
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 18));
        calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 1));
        calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 5));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 13));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 6));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 3));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 15));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 18));
        calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 1));
        calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 5));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 13));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 6));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 3));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 15));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 18));
        calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 1));
        calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 5));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 13));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 6));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 3));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 15));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 18));
        calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 1));
        calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.HARD_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.MEDIUM_LEVEL, 2));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 5));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 13));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 6));
        calls.add(new Call(Call.DifficultyLevel.EASY_LEVEL, 3));

        for (Call c : calls) {
            CallHandler handler = dispatcher.findCallHandler(c);
            System.out.println(handler.role.toString() + " " + handler.id + " is answering the call.");
        }

    }
}
