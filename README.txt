Design Overview.
1. Three types of objects.
    a. Call
        * Represents each incoming call. It has associated difficulty level and length.
    b. Dispatcher
        * Call dispatcher. It assign calls to available employees if there is any. Wait
        until there is one if there is none at the moment.
    c. CallHandler
        i.  Employee
        * Answers call if he can, otherwise transfer the call to supervisor.
        ii. Supervisor
        * Has a queue of calls. Try to answer the first call in queue, if he can't 
        answer it then transfer it to the manager.
        iii.Manager 
        * Has a queue of calls. Try to answer all calls in the queue from the head 
        to the tail of the queue.     
2. How it works.
    a. Dispatcher only assigns calls to employees directly but not to supervisor or manager.
    b. Employee decides if the call should be redirected to supervisor but an employee never
    forwards the call to the manager directly.
    c. Supervisor decides if the call should be forwarded to the manager. supervisor has a queue,
    and he always handles the earliest call first.
    d. Manager answers all calls forwarded by the supervisor. He has a queue too.
    e. Since the call handling is triggered by incoming call, when there is no more incoming 
    calls, the supervisor and the manager need to clean up all remaining calls in their queues.
3. Notes.
    a. It follows real world logic. It can't transfer call directly to the supervisor or the 
    manager because in real world the dispatcher won't know the difficulty of the call and 
    only call handlers do.
    b. OPTIMIZATION: It implements queues for supervisor and manager so that employees can
    just forward the call to supervisor's queue and  employees don't have to hold the call 
    and wait for the supervisor or manager to be available. Employees can continue to handle 
    calls.
    c. Singleton pattern ensures that only one manager and one supervisor can be created.