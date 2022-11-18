package digital.zurcher.lib.config;

public class ProprietesPasTrouvee extends Exception {
    @Override
    public String toString() {
        return "Le fichier de configuration n'a pas été trouvé !";
    }
}
