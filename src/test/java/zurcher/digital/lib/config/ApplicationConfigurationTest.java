package zurcher.digital.lib.config;

import org.junit.Assert;
import org.junit.Test;

public class ApplicationConfigurationTest {
    @Test
    public void testGetValeur() throws ProprietesMauvaiseSyntaxe, ProprietesVide, ProprietesPasTrouvee {
        ApplicationConfiguration instance = ApplicationConfiguration.getInstance();

        Assert.assertEquals(instance.getValeur("key1"), "value1");
        Assert.assertEquals(instance.getValeur("key2"), "value2");
        Assert.assertEquals(instance.getValeur("key3"), "value3");
    }

    @Test
    public void testSingleton() throws ProprietesMauvaiseSyntaxe, ProprietesVide, ProprietesPasTrouvee {
        ApplicationConfiguration instance1= ApplicationConfiguration.getInstance();
        ApplicationConfiguration instance2= ApplicationConfiguration.getInstance();
        Assert.assertEquals(instance1, instance2);
    }
}
