package digital.zurcher.lib.config;

public class ProprietesMauvaiseSyntaxe extends Exception {
    @Override
    public String toString() {
        return "Mauvaise syntaxe ! un seul signe égal par ligne est accepté, dans le format 'clé=valeur'";
    }
}
