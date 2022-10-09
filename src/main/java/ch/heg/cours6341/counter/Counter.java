package ch.heg.cours6341.counter;

public class Counter {

    private static Counter INSTANCE = new Counter();
    private long value;

    private Counter() {
        value=0;
    }

    public static Counter getInstance() {
        return INSTANCE;
    }

    public void inc() {
        value++;
    }

    public long getValue() {
        return value;
    }

    public void reset() {
        this.value = 0;
    }
}