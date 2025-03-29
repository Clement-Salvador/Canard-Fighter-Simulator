/**
 * Classe représentant un canard de type glace
 * 
 * Le canard de type glace possède une capacité spéciale appelée
 * Souffle gelé, qui lui permet d'appliquer l'effet gelé, l'adversaire est donc
 * gelé au prochain cours
 * lorsqu'elle est activée. Cette capacité ne peut être utilisée qu'une seule
 * fois par combat.
 * 
 * Hérite de la classe abstraite {@link Canard}, qui définit les attributs
 * communs à tous les types de canards.
 * 
 */
public class CanardGlace extends Canard {

    private boolean cibleGelee = false;

    public CanardGlace(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, pointsDeVie, pointsAttaque, TypeCanard.typeCanard.CanardGlace);
    }

    /**
     * Capacité spéciale : Souffle gelé.
     * Gèle l'adversaire pendant un tour.
     */
    @Override
    public void activerCapaciteSpeciale() {
        if (!isCapaciteSpecialeUtilisee()) {
            System.out.println(getNom() + " utilise Souffle gelé !");
            cibleGelee = true;
            markCapaciteSpecialeAsUsed();
        } else {
            System.out.println(getNom() + " a déjà utilisé sa capacité spéciale !");
        }
    }

    /**
     * Vérifie si l'adversaire est gelé.
     * 
     * @return true si la cible est gelée, sinon false.
     */
    public boolean isCibleGelee() {
        boolean result = cibleGelee;
        cibleGelee = false; // effet pour un tour seulement
        return result;
    }
}
