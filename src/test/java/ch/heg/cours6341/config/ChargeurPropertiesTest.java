package ch.heg.cours6341.config;

import org.junit.Test;

public class ChargeurPropertiesTest {
    @Test (expected = ProprietesMauvaiseSyntaxe.class)
    public void testProprieteMauvaiseSyntaxe() throws ProprietesMauvaiseSyntaxe, ProprietesVide, ProprietesPasTrouvee {
        ChargeurProprietes.charger("application_mauvaise_syntaxe.properties");
    }

    @Test (expected = ProprietesPasTrouvee.class)
    public void testProprietesFichierInexistant() throws ProprietesMauvaiseSyntaxe, ProprietesVide, ProprietesPasTrouvee {
        ChargeurProprietes.charger("fichierInexistant.properties");
    }

    @Test (expected = ProprietesVide.class)
    public void testProprietesFichierVide() throws ProprietesMauvaiseSyntaxe, ProprietesVide, ProprietesPasTrouvee {
        ChargeurProprietes.charger("application_vide.properties");
    }
}
