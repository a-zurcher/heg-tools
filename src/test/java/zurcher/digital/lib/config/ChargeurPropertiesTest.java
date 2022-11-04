package zurcher.digital.lib.config;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChargeurPropertiesTest {
    @Test
    public void testProprietesFichierInexistant() {
        Assertions.assertThrows(ProprietesPasTrouvee.class, () -> ChargeurProprietes.charger("fichierInexistant.properties"));
    }

    @Test
    public void testProprietesFichierVide() {
        Assertions.assertThrows(ProprietesVide.class, () -> ChargeurProprietes.charger("application_vide.properties"));
    }
}
