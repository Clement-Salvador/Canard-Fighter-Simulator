import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principale de l'application Canard Fighter Simulator.
 * Gère la création de canards, le lancement de combats, et l'interaction
 * utilisateur via console.
 */
public class Main {

    /**
     * Point d'entrée du programme.
     * Affiche un menu et gère les choix utilisateur.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Canard> canards = new ArrayList<>();

        System.out.println("Bienvenue dans Canard Fighter Simulator");

        while (true) {
            System.out.println("\nMenu principal :");
            System.out.println("1. Créer un canard");
            System.out.println("2. Lancer une bataille");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    Canard nouveau = creerCanard(scanner);
                    if (nouveau != null) {
                        canards.add(nouveau);
                        System.out.println("Canard " + nouveau.getNom() + " créé avec succès !");
                    }
                    break;
                case 2:
                    if (canards.size() < 2) {
                        System.out.println("Vous devez créer au moins deux canards !");
                    } else {
                        lancerCombat(canards.get(0), canards.get(1), scanner);
                    }
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    /**
     * Crée un canard à partir des choix utilisateur.
     * 
     * @param scanner Scanner pour lire les entrées.
     * @return Un objet Canard instancié selon le type.
     */
    public static Canard creerCanard(Scanner scanner) {
        System.out.print("Nom du canard : ");
        String nom = scanner.nextLine();

        System.out.print("Points de vie : ");
        int pv = scanner.nextInt();

        System.out.print("Points d'attaque : ");
        int pa = scanner.nextInt();

        System.out.println("Choisir le type de canard :");
        System.out.println("1. Eau");
        System.out.println("2. Feu");
        System.out.println("3. Glace");
        System.out.println("4. Vent");
        int type = scanner.nextInt();
        scanner.nextLine(); // vider le buffer

        switch (type) {
            case 1:
                return new CanardEau(nom, pv, pa);
            case 2:
                return new CanardFeu(nom, pv, pa);
            case 3:
                return new CanardGlace(nom, pv, pa);
            case 4:
                return new CanardVent(nom, pv, pa);
            default:
                System.out.println("Type invalide.");
                return null;
        }
    }

    /**
     * Lance un combat entre deux canards.
     * 
     * @param c1      Premier canard.
     * @param c2      Deuxième canard.
     * @param scanner Scanner pour l'interaction utilisateur.
     */
    public static void lancerCombat(Canard c1, Canard c2, Scanner scanner) {
        int tour = 1;
        System.out.println("\n=== Début du combat : " + c1.getNom() + " VS " + c2.getNom() + " ===");

        while (!c1.estKO() && !c2.estKO()) {
            System.out.println("\n--- Tour " + tour + " ---");

            if (!(c2 instanceof CanardGlace) || !((CanardGlace) c2).estCibleGelee(c1)) {
                tourCanard(c1, c2, scanner);
            } else {
                System.out.println(c1.getNom() + " est gelé et perd son tour !");
            }

            if (c2.estKO())
                break;

            if (!(c1 instanceof CanardGlace) || !((CanardGlace) c1).estCibleGelee(c2)) {
                tourCanard(c2, c1, scanner);
            } else {
                System.out.println(c2.getNom() + " est gelé et perd son tour !");
            }

            afficherPV(c1, c2);
            tour++;
        }

        System.out.println("\n=== Fin du combat ===");
        if (c1.estKO()) {
            System.out.println(c1.getNom() + " est KO ! Victoire de " + c2.getNom() + " !");
        } else {
            System.out.println(c2.getNom() + " est KO ! Victoire de " + c1.getNom() + " !");
        }
    }

    /**
     * Gère le tour d’un canard (attaque ou capacité spéciale).
     * 
     * @param attaquant Le canard qui joue.
     * @param defenseur Le canard ciblé.
     * @param scanner   Pour les entrées utilisateur.
     */
    public static void tourCanard(Canard attaquant, Canard defenseur, Scanner scanner) {
        System.out.println("\n" + attaquant.getNom() + " (" + attaquant.getType() + ") - Que veux-tu faire ?");
        System.out.println("1. Attaquer (-5 PE)");
        System.out.println("2. Utiliser capacité spéciale (-10 PE)");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                if (attaquant.consommerPE(5)) {
                    attaquant.attaquer(defenseur);
                    if (attaquant instanceof CanardFeu) {
                        ((CanardFeu) attaquant).appliquerEffetBrulure(defenseur);
                    }
                }
                break;
            case 2:
                if (attaquant.consommerPE(10)) {
                    attaquant.activerCapaciteSpeciale(defenseur);
                }
                break;
            default:
                System.out.println("Action invalide. Attaque par défaut.");
                if (attaquant.consommerPE(5)) {
                    attaquant.attaquer(defenseur);
                }
        }
    }

    /**
     * Affiche les PV restants des deux canards.
     * 
     * @param c1 Le premier canard.
     * @param c2 Le second canard.
     */
    public static void afficherPV(Canard c1, Canard c2) {
        System.out.println("\nÉtat du combat :");
        System.out.println(c1.getNom() + " : " + c1.getPointsDeVie() + " PV | " + c1.getPointsEnergie() + " PE");
        System.out.println(c2.getNom() + " : " + c2.getPointsDeVie() + " PV | " + c2.getPointsEnergie() + " PE");
    }
}
