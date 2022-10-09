package ch.heg.cours6341.config;

public class ProprietesVide extends Exception {
    @Override
    public String toString() {
        return "Aucune paire de clé valeur n'a été trouvée !";
    }
}
