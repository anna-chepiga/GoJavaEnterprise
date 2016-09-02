package module_2;

public interface Validator<T> {
    boolean isValid(T value);
}