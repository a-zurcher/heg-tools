package ch.heg.cours6341.config;

public class ProprietesPasTrouvee extends Exception {
    @Override
    public String toString() {
        return "Le fichier de configuration n'a pas été trouvé !";
    }
}
