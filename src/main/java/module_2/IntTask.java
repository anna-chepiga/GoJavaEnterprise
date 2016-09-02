package module_2;

public class IntTask implements Task<Integer> {
    private Integer value;

    public IntTask(Integer value) {
        this.value = value;
    }

    @Override
    public void execute() {
        value *= 2;
    }

    @Override
    public Integer getResult() {
        return value;
    }
}