/**
 * La classe abstraite Canard représente un canard.
 * Chaque canard possède un nom, des points de vie, des points d'attaque, et un
 * type
 * (eau, feu, vent ou glace). Cette classe fournit des méthodes pour gérer les
 * interactions entre les canards, comme attaquer, subir des dégâts, et vérifier
 * si un canard est hors combat (KO).
 * 
 * Attributs :
 * - nom : Le nom du canard.
 * - pointsDeVie : Les points de vie du canard. Si ce nombre atteint 0, le
 * canard est KO.
 * - pointsAttaque : Les points d'attaque du canard, utilisés pour infliger des
 * dégâts.
 * - type : Le type du canard, qui influence les dégâts infligés en fonction des
 * interactions entre types (eau, feu, vent, glace).
 * 
 * Constructeur :
 * - Canard(String nom, int pointsDeVie, int pointsAttaque,
 * TypeCanard.typeCanard type) :
 * Initialise un canard avec un nom, des points de vie, des points d'attaque, et
 * un type.
 */
public abstract class Canard {
    private String nom;
    private int pointsDeVie;
    private int pointsAttaque;
    private TypeCanard.typeCanard type;
    private boolean capaciteSpecialeUtilisee = false; // Pour s'assurer que la capacité spéciale n'est utilisée qu'une
                                                      // seule fois par combat
    private int pointsEnergie = 50;


    /**
     * Constructreur
     * 
     * @param nom           : Nom du canard
     * @param pointsDeVie   : Points du vie du canard
     * @param pointsAttaque : Points d'attaque du canard
     * @param type          : Type du canard (eau, feu, vent ou glace).
     */
    public Canard(String nom, int pointsDeVie, int pointsAttaque, TypeCanard.typeCanard type) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.pointsAttaque = pointsAttaque;
        this.type = type;
    }

    public TypeCanard.typeCanard getType() {
        return this.type;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPointsDeVie() {
        return this.pointsDeVie;
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public int getPointsAttaque() {
        return this.pointsAttaque;
    }

    public void setPointsAttaque(int pointsAttaque) {
        this.pointsAttaque = pointsAttaque;
    }

    public int getPointsEnergie() {
        return pointsEnergie;
    }

    public void setPointsEnergie(int pointsEnergie) {
        this.pointsEnergie = pointsEnergie;
    }


    /**
     * attaquer(Canard autreCanard) : Permet à un canard d'attaquer un autre
     * canard.
     * Les dégâts infligés sont calculés en fonction des points d'attaque et du
     * multiplicateur basé sur les types des deux canards disponible dans
     * TypeCanard.java
     * 
     * @param autreCanard : Canard attaqué
     */
    public void attaquer(Canard autreCanard) {
    int coutAttaque = 5;
    if (!consommerPE(coutAttaque)) return;

    double multiplicateurType = TypeCanard.getMultiplicateur(this.type, autreCanard.getType());

    // 10% de chance critique
    boolean critique = Math.random() < 0.10;
    double multiplicateurCritique = critique ? 2.0 : 1.0;

    int degats = (int) (this.pointsAttaque * multiplicateurType * multiplicateurCritique);
    autreCanard.subirDegats(degats);

    System.out.println(getNom() + " attaque " + autreCanard.getNom() + 
        (critique ? " avec un COUP CRITIQUE !" : "") + " (-" + degats + " PV)");
    }


    /**
     * subirDegats(int degats) : Réduit les points de vie du canard en fonction
     * des
     * dégâts reçus. Si les points de vie deviennent négatifs, ils sont ramenés à 0.
     * 
     * @param degats : Dégats infligés au canard.
     * @return : Return point de vie pour traitement par la méthode estKO
     */
    public int subirDegats(int degats) {
        this.pointsDeVie -= degats;
        if (this.pointsDeVie < 0) {
            this.pointsDeVie = 0;
        }
        return this.pointsDeVie;
    }

    /**
     * estKO() : Vérifie si le canard est hors combat (points de vie <= 0). *
     * 
     * @return
     */
    public boolean estKO() {
        return this.pointsDeVie <= 0;
    }

    /**
     * Vérifie si la capacité spéciale a déjà été utilisée durant le combat.
     * 
     * @return true si la capacité spéciale a été activée, sinon false.
     */
    public boolean isCapaciteSpecialeUtilisee() {
        return this.capaciteSpecialeUtilisee;
    }

    /**
     * Marque la capacité spéciale comme utilisée.
     */

    protected void markCapaciteSpecialeAsUsed() {
        this.capaciteSpecialeUtilisee = true;
    }

    /**
     * activerCapaciteSpeciale() : Méthode abstraite qui devra être implémentée par
     * chaque sous-classe pour définir le comportement de la capacité spéciale.
     */
    public abstract void activerCapaciteSpeciale();

    /**
     * Consomme un certain nombre de points d'énergie. Retourne true si suffisant.
     */
    public boolean consommerPE(int cout) {
        if (pointsEnergie >= cout) {
            pointsEnergie -= cout;
            return true;
    } else {
        System.out.println(getNom() + " n’a pas assez de PE !");
        return false;
    }
}

}
