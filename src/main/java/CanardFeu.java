/**
 * Classe représentant un canard de type Feu
 * 
 * Le canard de type Feu possède une capacité spéciale appelée
 * Brûlure, qui lui permet d'infliger des dégats + 10 pv au prochain tour (en
 * appliquant l'effet brulure)
 * lorsqu'elle est activée. Cette capacité ne peut être utilisée qu'une seule
 * fois par combat.
 * 
 * Hérite de la classe abstraite {@link Canard}, qui définit les attributs
 * communs à tous les types de canards.
 * 
 */
public class CanardFeu extends Canard {

    private boolean brulureActive = false;

    public CanardFeu(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, pointsDeVie, pointsAttaque, TypeCanard.typeCanard.CanardFeu);
    }

    /**
     * Capacité spéciale : Brulure.
     * Inflige des dégâts immédiats et applique un effet de brûlure pour un tour.
     */
    @Override
    public void activerCapaciteSpeciale() {
        if (!isCapaciteSpecialeUtilisee()) {
            System.out.println(getNom() + " applique brûlure !");
            brulureActive = true;
            markCapaciteSpecialeAsUsed();
        } else {
            System.out.println(getNom() + " a déjà utilisé sa capacité spéciale !");
        }
    }

    /**
     * Applique l'effet de brûlure si actif, inflige 10 dégâts au canard ciblé.
     * 
     * @param cible Le canard ciblé par l'effet brûlure.
     */
    public void appliquerEffetBrulure(Canard cible) {
        if (brulureActive) {
            System.out.println(cible.getNom() + " subit une brûlure ! -10 PV");
            cible.subirDegats(10);
            brulureActive = false; // effet pour un seul tour
        }
    }
}
