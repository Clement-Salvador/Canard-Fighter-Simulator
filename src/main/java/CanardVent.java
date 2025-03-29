/**
 * Classe représentant un canard de type vent
 * 
 * Le canard de type vent possède une capacité spéciale appelée
 * Rafale, qui lui permet d'attaquer de plus en plus vite.
 * lorsqu'elle est activée. Cette capacité ne peut être utilisée qu'une seule
 * fois par combat.
 * 
 * Hérite de la classe abstraite {@link Canard}, qui définit les attributs
 * communs à tous les types de canards.
 * 
 */
public class CanardVent extends Canard {

    private int multiplicateur = 1;

    public CanardVent(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, pointsDeVie, pointsAttaque, TypeCanard.typeCanard.CanardVent);
    }

    /**
     * Capacité spéciale : Rafale.
     * Augmente la puissance d'attaque temporairement.
     */
    @Override
    public void activerCapaciteSpeciale() {
        if (!isCapaciteSpecialeUtilisee()) {
            multiplicateur = 2;
            System.out.println(getNom() + " se prépare avec Rafale ! Attaque x2 !");
            markCapaciteSpecialeAsUsed();
        } else {
            System.out.println(getNom() + " a déjà utilisé sa capacité spéciale !");
        }
    }

    /**
     * Redéfinition de la méthode attaquer pour prendre en compte le bonus
     * temporaire.
     * 
     * @param autreCanard : Cible de l’attaque.
     */
    @Override
    public void attaquer(Canard autreCanard) {
        int bonusAttaque = getPointsAttaque() * multiplicateur;
        int degats = (int) (bonusAttaque * TypeCanard.getMultiplicateur(getType(), autreCanard.getType()));
        autreCanard.subirDegats(degats);
        if (multiplicateur == 2) {
            multiplicateur = 3;
            System.out.println(getNom() + " passe en Attaque x3 !");
        } else {
            multiplicateur = 1;
        }
    }
}