public class CanardEau extends Canard {

    public CanardEau(String nom, int pointsDeVie, int pointsAttaque, TypeCanard.typeCanard type) {
        super(nom, pointsDeVie, pointsAttaque, type.CanardEau);
    }

    public void activerCapaciteSpeciale() {
        if (!isCapaciteSpecialeUtilisee()) {
            System.out.println(getNom() + " active sa capacité spéciale : Régénération Aquatique !");
            // Calcul de la récupération : 20% des points de vie actuels.
            int recuperation = (int) (getPointsDeVie() * 0.2);
            setPointsDeVie(getPointsDeVie() + recuperation);
            markCapaciteSpecialeAsUsed(); // Marquer que la capacité spéciale a été utilisée.
        } else {
            System.out.println("La capacité spéciale de " + getNom() + " a déjà été utilisée !");
        }
    }
}
