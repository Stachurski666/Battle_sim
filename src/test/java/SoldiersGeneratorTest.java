import org.example.army.Infantry;
import org.example.army.Soldier;
import org.example.battlefield.Battlefield;
import org.example.soldiergenerator.SoldiersGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SoldiersGeneratorTest {

    @Test
    public void testPutRandomSoldiers() {
        // Tworzenie pola bitwy
        Battlefield battlefield = new Battlefield(10, 10);
        // Tworzenie generatora
        SoldiersGenerator generator = new SoldiersGenerator(battlefield);
        // Umieszczanie 5 losowych żołnierzy na polu bitwy dla armii 1
        generator.putRandomSoldiers(5, "1");

        // Sprawdzanie, czy faktycznie umieszczono 5 żołnierzy dla armii 1
        Assertions.assertEquals(5, countSoldiersOnBattlefield(battlefield, "1"));
    }

    private int countSoldiersOnBattlefield(Battlefield battlefield, String armySymbol) {
        int count = 0;
        for (int i = 0; i < battlefield.getWidth(); i++) {
            for (int j = 0; j < battlefield.getHeight(); j++) {
                Soldier soldier = battlefield.getSoldierAt(i, j,"1");
                if (soldier != null && soldier.getArmySymbol().equals(armySymbol)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void testPlaceSoldier() {
        // Tworzenie pola bitwy
        Battlefield battlefield = new Battlefield(10, 10);
        // Tworzenie żołnierza
        Soldier soldier = new Infantry("1", "1", 100, 10, 0, 0);
        // Umieszczanie żołnierza na polu bitwy
        battlefield.placeSoldier(soldier, 0, 0, "1");

        // Pobieranie żołnierza z pola bitwy na podstawie pozycji
        Soldier retrievedSoldier = battlefield.getSoldierAt(0, 0, "1");

        // Sprawdzanie, czy żołnierz został umieszczony na właściwej pozycji
        Assertions.assertEquals(soldier, retrievedSoldier);
    }
}