import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

public class ApplicationConfigurationTest {
    @Test
    public void testRetourneHashMap() throws ApplicationConfiguration.mauvaiseSyntax, ApplicationConfiguration.configurationVide {
        ApplicationConfiguration instance = ApplicationConfiguration.getInstance();
        instance.chargerConfig();

        Assert.assertEquals(instance.getConfig().getClass(), HashMap.class);
    }

    @Test (expected = ApplicationConfiguration.mauvaiseSyntax.class)
    public void testConfigMauvaiseSyntaxe() throws ApplicationConfiguration.mauvaiseSyntax, ApplicationConfiguration.configurationVide {
        ApplicationConfiguration instance = ApplicationConfiguration.getInstance();
        instance.chargerConfig("application_mauvaise_syntaxe.properties");
    }

    @Test (expected = NullPointerException.class)
    public void testConfigFileNotFound() throws ApplicationConfiguration.mauvaiseSyntax, ApplicationConfiguration.configurationVide {
        ApplicationConfiguration instance = ApplicationConfiguration.getInstance();
        instance.chargerConfig("fichierInexistant.properties");
    }

    @Test (expected = ApplicationConfiguration.configurationVide.class)
    public void testConfigVide() throws ApplicationConfiguration.mauvaiseSyntax, ApplicationConfiguration.configurationVide {
        ApplicationConfiguration instance = ApplicationConfiguration.getInstance();
        instance.chargerConfig("application_vide.properties");
    }

    @Test
    public void testGetValeur() throws ApplicationConfiguration.mauvaiseSyntax, ApplicationConfiguration.configurationVide {
        ApplicationConfiguration instance = ApplicationConfiguration.getInstance();
        instance.chargerConfig("application_test.properties");

        Assert.assertEquals(instance.getValeur("key1"), "value1");
        Assert.assertEquals(instance.getValeur("key2"), "value2");
        Assert.assertEquals(instance.getValeur("key3"), "value3");
    }

    @Test
    public void testSingleton() {
        ApplicationConfiguration instance1= ApplicationConfiguration.getInstance();
        ApplicationConfiguration instance2= ApplicationConfiguration.getInstance();
        Assert.assertEquals(instance1, instance2);
    }
}
