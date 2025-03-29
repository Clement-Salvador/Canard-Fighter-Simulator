public class CanardEau extends Canard {

    public CanardEau(String nom, int pointsDeVie, int pointsAttaque, TypeCanard.typeCanard type) {
        super(nom, pointsDeVie, pointsAttaque, type);
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
