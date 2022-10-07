import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.*;
import java.util.stream.Collectors;

public class ApplicationConfiguration {
    private static ApplicationConfiguration instance;
    private static final HashMap<String,String> config = new HashMap<>();

    public static synchronized ApplicationConfiguration getInstance() {
        if (instance == null)
            instance = new ApplicationConfiguration();

        return instance;
    }

    public HashMap<String, String> getConfig() {
        return config;
    }

    public String getValeur(String key) {
        return config.get(key);
    }

    public String[] getToutesLesValeurs() {
        String[] valeurs = new String[config.size()];

        var entrySet = config.entrySet();

        int compteur = 0;
        for (var entry : entrySet) {
            valeurs[compteur] = entry.getKey();
            compteur++;
        }

        return valeurs;
    }

    public void chargerConfig() throws mauvaiseSyntax, configurationVide {
        chargerConfig("application.properties");
    }

    public void chargerConfig(String confFile) throws mauvaiseSyntax, configurationVide {
        config.clear();

        InputStream inputStream = ApplicationConfiguration.class.getClassLoader().getResourceAsStream(confFile);

        String result;
        result = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .lines().collect(Collectors.joining("\n"));

        if (result.isEmpty()) {
            throw new configurationVide();
        } else {
            // split result en string[] à chaque retour de ligne
            for (String s: result.split("\n")) {
                // teste si le format "clé=valeur" est respecté
                final Pattern pattern = Pattern.compile("^[^=]+=[^=]+$", Pattern.MULTILINE);
                final Matcher matcher = pattern.matcher(s);
                int counter = 0;

                while (matcher.find())
                    counter++;

                if (counter != 1)
                    throw new mauvaiseSyntax();

                // converti la ligne en String[] en coupant au niveau de "="
                String[] line = s.split("=");

                // ajoute la configuration au hashmap
                config.put(line[0],line[1]);
            }
        }

    }

    public static class mauvaiseSyntax extends Exception {
        @Override
        public String toString() {
            return "Mauvaise syntaxe ! un seul signe égal par ligne est accepté, dans le format 'clé=valeur'";
        }
    }

    public static class configurationVide extends Exception {
        @Override
        public String toString() {
            return "Aucune paire de clé valeur n'a été trouvée !";
        }
    }
}
