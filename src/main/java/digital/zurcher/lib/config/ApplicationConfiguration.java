package digital.zurcher.lib.config;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConfiguration {
    private static ApplicationConfiguration instance;
    private Map<String,String> config = new HashMap<>();

    private ApplicationConfiguration () throws ProprietesPasTrouvee, ProprietesVide {
        chargerConfig();
    }

    public static synchronized ApplicationConfiguration getInstance() throws ProprietesPasTrouvee, ProprietesVide {
        if (instance == null)
            instance = new ApplicationConfiguration();

        return instance;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public String getValeur(String key) {
        return config.get(key);
    }

    private void chargerConfig() throws ProprietesVide, ProprietesPasTrouvee {
        config = ChargeurProprietes.charger("application.properties");
    }
}
