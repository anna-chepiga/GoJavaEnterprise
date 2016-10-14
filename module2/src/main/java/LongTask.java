public class LongTask implements Task<Long> {
    private Long value;

    public LongTask(Long value) {
        this.value = value;
    }

    @Override
    public void execute() {
        value *= 3;
    }

    @Override
    public Long getResult() {
        return value;
    }
}