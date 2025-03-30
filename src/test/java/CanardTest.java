import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CanardTest {
    private CanardEau canardEau;
    private CanardFeu canardFeu;
    private CanardGlace canardGlace;
    private CanardVent canardVent;

    @BeforeEach
    public void setUp() {
        // Initialisation avec 100 PV et 20 PA pour tous
        canardEau = new CanardEau("Canarrosé", 100, 20);
        canardFeu = new CanardFeu("Canardant", 100, 20);
        canardGlace = new CanardGlace("Canartic", 100, 20);
        canardVent = new CanardVent("Canarafale", 100, 20);
    }

    // ------------------------------------------
    // Tests pour la méthode attaquer()
    // ------------------------------------------
    @Test
    public void testAttaquerEauContreFeu() {
        canardEau.attaquer(canardFeu); // Eau > Feu (x1.5)
        assertEquals(70, canardFeu.getPointsDeVie()); // 100 - (20 * 1.5) = 70
    }

    @Test
    public void testAttaquerFeuContreGlace() {
        canardFeu.attaquer(canardGlace); // Feu > Glace (x1.5)
        assertEquals(70, canardGlace.getPointsDeVie()); // 100 - (20 * 1.5) = 70
    }

    // ------------------------------------------
    // Tests pour la méthode subirDegats()
    // ------------------------------------------
    @Test
    public void testSubirDegatsNonMortel() {
        canardEau.subirDegats(30);
        assertEquals(70, canardEau.getPointsDeVie());
        assertFalse(canardEau.estKO());
    }

    @Test
    public void testSubirDegatsMortel() {
        canardEau.subirDegats(100);
        assertEquals(0, canardEau.getPointsDeVie());
        assertTrue(canardEau.estKO());
    }

    // ------------------------------------------
    // Tests pour les capacités spéciales
    // ------------------------------------------
    @Test
    public void testCapaciteSpecialeCanardEau() {
        canardEau.activerCapaciteSpeciale(canardEau); // +20 PV
        assertEquals(120, canardEau.getPointsDeVie());
        assertTrue(canardEau.isCapaciteSpecialeUtilisee());
    }

    @Test
    public void testCapaciteSpecialeCanardFeu() {
        canardFeu.activerCapaciteSpeciale(canardEau); // Active la brûlure
        canardFeu.attaquer(canardEau); // Applique la brûlure
        assertEquals(80, canardEau.getPointsDeVie()); // 100 - (20 * 1.0) = 80
    }

    // ------------------------------------------
    // Tests pour les interactions de types
    // ------------------------------------------
    @Test
    public void testVentContreGlace() {
        canardVent.attaquer(canardGlace); // Vent > Glace (x1.5)
        assertEquals(70, canardGlace.getPointsDeVie()); // 100 - (20 * 1.5) = 70
    }

    @Test
    public void testGlaceContreFeu() {
        canardGlace.attaquer(canardFeu); // Glace > Feu (x0.5)
        assertEquals(90, canardFeu.getPointsDeVie()); // 100 - (20 * 0.5) = 90
    }
}
