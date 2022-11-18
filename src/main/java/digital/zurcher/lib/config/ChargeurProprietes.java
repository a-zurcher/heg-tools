package digital.zurcher.lib.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChargeurProprietes {
    public static Map<String, String> charger(String file) throws ProprietesPasTrouvee, ProprietesVide {
        HashMap<String, String> proprietes = new HashMap<>();

        String result;
        try (InputStream inputStream = ChargeurProprietes.class.getClassLoader().getResourceAsStream(file)) {

            if (inputStream == null)
                throw new ProprietesPasTrouvee();

            result = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))
                    .lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (result.isEmpty()) {
            throw new ProprietesVide();
        } else {
            // split result en string[] à chaque retour de ligne
            for (String s : result.split("\n")) {
                // converti la ligne en String[] en coupant au niveau de "="
                String[] line = s.split("=", 2);

                // ajoute la configuration au hashmap
                proprietes.put(line[0], line[1]);
            }
        }

        return proprietes;
    }
}
