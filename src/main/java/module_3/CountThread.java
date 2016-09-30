package module_3;

public class CountThread extends Thread {
    private String name;
    private Semaphore semaphore;
    private CommonResourceCounter counter;

    public CountThread(String name, Semaphore semaphore, CommonResourceCounter counter) {
        this.name = name;
        this.semaphore = semaphore;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " awaits permission");
            semaphore.acquire();
            System.out.println(name + " gets permission");

            counter.value = 1;

            for (int i = 0; i < 5; i++) {
                System.out.println(name + " changes value to " + counter.value);
                counter.value++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(name + " releases permission");
            semaphore.release();
        }
    }
}