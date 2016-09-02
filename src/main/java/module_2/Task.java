package module_2;

public interface Task<T> {
    void execute();
    T getResult();
}