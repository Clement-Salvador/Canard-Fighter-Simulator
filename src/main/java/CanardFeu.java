/**
 * Représente un canard de type Feu.
 * Sa capacité spéciale inflige des dégâts supplémentaires et applique l'effet brûlure,
 * qui inflige 10 dégâts supplémentaires au tour suivant.
 */
public class CanardFeu extends Canard {

    private boolean brulureActive = false;

    public CanardFeu(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, pointsDeVie, pointsAttaque, TypeCanard.typeCanard.CanardFeu);
    }

    /**
     * Capacité spéciale : Flamme brûlante.
     * Inflige des dégâts immédiats et applique un effet de brûlure pour un tour.
     */
    @Override
    public void activerCapaciteSpeciale() {
        if (!isCapaciteSpecialeUtilisee()) {
            System.out.println(getNom() + " lance Flamme brûlante !");
            brulureActive = true;
            markCapaciteSpecialeAsUsed();
        } else {
            System.out.println(getNom() + " a déjà utilisé sa capacité spéciale !");
        }
    }

    /**
     * Applique l'effet de brûlure si actif, inflige 10 dégâts au canard ciblé.
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
