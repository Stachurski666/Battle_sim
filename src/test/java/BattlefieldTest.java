import org.example.army.Infantry;
import org.example.battlefield.Battlefield;
import org.example.battlefield.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class BattlefieldTest {
    @Test
    public void testPlaceSoldierAndObstacle() {
        // Tworzenie pola bitwy
        Battlefield battlefield = new Battlefield(10, 10);

        // Umieszczanie żołnierza na polu bitwy
        Infantry infantry = new Infantry("1", "1", 100, 10, 0, 0);
        battlefield.placeSoldier(infantry, 0, 0, "1");

        // Sprawdzanie, czy żołnierz został poprawnie umieszczony na polu bitwy
        Assertions.assertEquals(infantry, battlefield.getSoldierAt(0, 0, "1"));

        // Umieszczanie przeszkody na polu bitwy
        battlefield.addObstacle(5, 5);

        // Sprawdzanie, czy przeszkoda została poprawnie umieszczona na polu bitwy
        Assertions.assertTrue(battlefield.isOccupied(5, 5));
    }

    @Test
    public void testMoveSoldierByOne() {
        // Tworzenie pola bitwy
        Battlefield battlefield = new Battlefield(10, 10);

        // Umieszczanie żołnierza na polu bitwy
        Infantry infantry = new Infantry("1", "1", 100, 10, 0, 0);
        battlefield.placeSoldier(infantry, 0, 0, "1");

        // Przesunięcie żołnierza o jedno pole w prawo
        battlefield.moveSoldierByOne(0, 0, Direction.RIGHT, new HashSet<>());

        // Sprawdzenie, czy żołnierz został poprawnie przesunięty
        Assertions.assertNull(battlefield.getSoldierAt(0, 0, "1"));
        Assertions.assertEquals(infantry, battlefield.getSoldierAt(1, 0, "1"));
    }
}

