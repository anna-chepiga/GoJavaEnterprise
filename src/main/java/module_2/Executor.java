package module_2;

import java.util.List;

public interface Executor<T> {
    void addTask(Task<? extends T> task) throws ExecuteCalledException;

    void addTask(Task<? extends T> task, Validator<T> validator) throws ExecuteCalledException;

    void execute();

    List<? extends T> getValidResults() throws ExecuteNotCalledException;

    List<? extends T> getInvalidResults() throws ExecuteNotCalledException;

    List<Task<? extends Number>> getTasks();
}