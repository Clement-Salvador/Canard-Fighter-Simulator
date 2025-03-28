public abstract class Canard {
    private String nom;
    private int pointsDeVie;
    private int pointsAttaque;
    private TypeCanard.typeCanard type;

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

    // TODO : attaquer(Canard autreCanard)
    public static void attaquer(Canard autreCanard){

    }


    // TODO : subirDegats(int degats)
    public int subirDegats(int degats){
        return 0;
    }
    
    public boolean estKO() {
        return this.pointsDeVie <= 0;
    }

}
