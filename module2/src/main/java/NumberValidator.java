public class NumberValidator implements Validator<Number>{
    @Override
    public boolean isValid(Number value) {
        return value.doubleValue() > 30;
    }
}
