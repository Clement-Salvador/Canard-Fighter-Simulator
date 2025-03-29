/**
 * Classe représentant un canard de type Eau
 * 
 * Le canard de type Eau possède une capacité spéciale appelée
 * Régénération Aquatique, qui lui permet de régénérer 20
 * points de vie
 * lorsqu'elle est activée. Cette capacité ne peut être utilisée qu'une seule
 * fois par combat.
 * 
 * Hérite de la classe abstraite {@link Canard}, qui définit les attributs
 * communs à tous les types de canards.
 *  
 */

public class CanardEau extends Canard {

    public CanardEau(String nom, int pointsDeVie, int pointsAttaque) {
        super(nom, pointsDeVie, pointsAttaque,TypeCanard.typeCanard.CanardEau);
    }

    public void activerCapaciteSpeciale() {
        if (!isCapaciteSpecialeUtilisee()) {
            System.out.println(getNom() + " active sa capacité spéciale : Régénération Aquatique !");
            // Récupère 20PV.
            int recuperation = 20;
            setPointsDeVie(getPointsDeVie() + recuperation);
            markCapaciteSpecialeAsUsed(); // Marquer que la capacité spéciale a été utilisée.
        } else {
            System.out.println("La capacité spéciale de " + getNom() + " a déjà été utilisée !");
        }
    }
}
