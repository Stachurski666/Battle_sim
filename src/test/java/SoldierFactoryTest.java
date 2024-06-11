import org.example.army.*;
import org.example.soldierfactory.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoldierFactoryTest {

    @Test
    void infantryFactoryCreatesInfantrySoldier() {
        SoldierFactory factory = new InfantryFactory();
        Soldier soldier = factory.createRandomSoldier("1", "1");
        assertTrue(soldier instanceof Infantry);
    }

    @Test
    void cavalryFactoryCreatesCavalrySoldier() {
        SoldierFactory factory = new CavalryFactory();
        Soldier soldier = factory.createRandomSoldier("2", "2");
        assertTrue(soldier instanceof Cavalry);
    }

    @Test
    void artilleryFactoryCreatesArtillerySoldier() {
        SoldierFactory factory = new ArtilleryFactory();
        Soldier soldier = factory.createRandomSoldier("3", "1");
        assertTrue(soldier instanceof Artillery);
    }

    @Test
    void medicFactoryCreatesMedicSoldier() {
        SoldierFactory factory = new MedicFactory();
        Soldier soldier = factory.createRandomSoldier("4", "2");
        assertTrue(soldier instanceof Medic);
    }

    @Test
    void riflemanFactoryCreatesRiflemanSoldier() {
        SoldierFactory factory = new RiflemanFactory();
        Soldier soldier = factory.createRandomSoldier("5", "1");
        assertTrue(soldier instanceof Rifleman);
    }
}
