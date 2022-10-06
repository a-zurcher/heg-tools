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

    public void chargerConfig() throws mauvaiseSyntax {
        config.clear();

        String confFile = "application.properties";

        InputStream inputStream = ApplicationConfiguration.class.getClassLoader().getResourceAsStream(confFile);

        String result;
        result = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .lines().collect(Collectors.joining("\n"));

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

    public static class mauvaiseSyntax extends Exception {
        @Override
        public String toString() {
            return "Mauvaise syntaxe ! un seul signe égal par ligne est accepté, dans le format 'clé=valeur'";
        }
    }
}
