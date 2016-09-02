package module_2;

import java.util.ArrayList;
import java.util.List;

public class NumberExecutor implements Executor<Number> {
    private List<Task<? extends Number>> tasks = new ArrayList<>();
    private ArrayList<Number> validResults = new ArrayList<>();
    private ArrayList<Number> invalidResults = new ArrayList<>();
    private boolean executeCalled = false;

    @Override
    public void addTask(Task<? extends Number> task) throws ExecuteCalledException {
        if (executeCalled) throw new ExecuteCalledException("execute method called");
        tasks.add(task);
    }

    @Override
    public void addTask(Task<? extends Number> task, Validator<Number> validator) throws ExecuteCalledException {
        if (executeCalled) throw new ExecuteCalledException("execute method called");

        task.execute();
        Number result = task.getResult();

        if (validator.isValid(result)) {
            validResults.add(result);
        } else {
            invalidResults.add(result);
        }
    }

    @Override
    public void execute() {
        Validator<Number> val = new NumberValidator();

        for (Task<? extends Number> task : tasks) {
            task.execute();
            Number result = task.getResult();

            if (val.isValid(result)) {
                validResults.add(result);
            } else {
                invalidResults.add(result);
            }
        }

        executeCalled = true;
    }

    @Override
    public List<? extends Number> getValidResults() throws ExecuteNotCalledException {
        if (!executeCalled) throw new ExecuteNotCalledException("execute method not called");
        return validResults;
    }

    @Override
    public List<? extends Number> getInvalidResults() throws ExecuteNotCalledException {
        if (!executeCalled) throw new ExecuteNotCalledException("execute method not called");
        return invalidResults;
    }
}