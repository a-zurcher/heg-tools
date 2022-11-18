package digital.zurcher.lib.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationConfigurationTest {
    @Test
    public void testGetValeur() throws ProprietesMauvaiseSyntaxe, ProprietesVide, ProprietesPasTrouvee {
        ApplicationConfiguration instance = ApplicationConfiguration.getInstance();

        Assertions.assertEquals(instance.getValeur("key1"), "value1");
        Assertions.assertEquals(instance.getValeur("key2"), "value2");
        Assertions.assertEquals(instance.getValeur("url"), "https://google.com&q=test");
    }

    @Test
    public void testSingleton() throws ProprietesMauvaiseSyntaxe, ProprietesVide, ProprietesPasTrouvee {
        ApplicationConfiguration instance1= ApplicationConfiguration.getInstance();
        ApplicationConfiguration instance2= ApplicationConfiguration.getInstance();
        Assertions.assertEquals(instance1, instance2);
    }
}
