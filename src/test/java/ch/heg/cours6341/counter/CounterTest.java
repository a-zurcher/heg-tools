package ch.heg.cours6341.counter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CounterTest {
    @Before
    public void init(){
        Counter.getInstance().reset();
    }

    @Test
    public void testInc() {
        Counter instance = Counter.getInstance();
        instance.inc();
        Assert.assertEquals(instance.getValue(), 1);
    }

    @Test
    public void testConstructeur() {
        Counter instance = Counter.getInstance();
        Assert.assertEquals(instance.getValue(), 0);
    }

    @Test
    public void testSingleton() {
        Counter instance1= Counter.getInstance();
        Counter instance2= Counter.getInstance();
        Assert.assertEquals(instance1, instance2);
    }
}