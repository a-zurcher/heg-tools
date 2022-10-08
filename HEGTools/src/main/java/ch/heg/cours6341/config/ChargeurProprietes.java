package ch.heg.cours6341.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChargeurProprietes {
    public static Map<String, String> charger(String file) throws ProprietesPasTrouvee, ProprietesVide, ProprietesMauvaiseSyntaxe {
        HashMap<String, String> proprietes = new HashMap<>();

        InputStream inputStream = ChargeurProprietes.class.getClassLoader().getResourceAsStream(file);

        if (inputStream == null)
            throw new ProprietesPasTrouvee();

        String result;
        result = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .lines().collect(Collectors.joining("\n"));

        if (result.isEmpty()) {
            throw new ProprietesVide();
        } else {
            // split result en string[] à chaque retour de ligne
            for (String s : result.split("\n")) {
                // teste si le format "clé=valeur" est respecté
                final Pattern pattern = Pattern.compile("^[^=]+=[^=]+$", Pattern.MULTILINE);
                final Matcher matcher = pattern.matcher(s);
                int counter = 0;

                while (matcher.find())
                    counter++;

                if (counter != 1)
                    throw new ProprietesMauvaiseSyntaxe();

                // converti la ligne en String[] en coupant au niveau de "="
                String[] line = s.split("=");

                // ajoute la configuration au hashmap
                proprietes.put(line[0], line[1]);
            }
        }

        return proprietes;
    }
}
