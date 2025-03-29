// Fichier : src/test/java/CanardTest.java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TypeCanard.typeCanard;

public class CanardTest {
    private CanardEau canardEau;
    private CanardFeu canardFeu;

    @BeforeEach
    public void setUp() {
        canardEau = new CanardEau("Aqua", 100, 20, typeCanard.CanardEau);
        canardFeu = new CanardFeu("Flamme", 80, 25, typeCanard.CanardFeu);
    }

    @Test
    public void testAttaquerEauContreFeu() {
        canardEau.attaquer(canardFeu);
        assertEquals(50, canardFeu.getPointsDeVie()); // 80 - (20 * 1.5) = 50
    }
}