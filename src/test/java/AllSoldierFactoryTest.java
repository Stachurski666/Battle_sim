import org.example.army.Artillery;
import org.example.army.Medic;
import org.example.army.Rifleman;
import org.example.army.Cavalry;
import org.example.army.Infantry;
import org.example.army.Soldier;
import org.example.soldierfactory.ArtilleryFactory;
import org.example.soldierfactory.MedicFactory;
import org.example.soldierfactory.RiflemanFactory;
import org.example.soldierfactory.CavalryFactory;
import org.example.soldierfactory.InfantryFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AllSoldierFactoryTest {
    @Test
    public void testMedicFactoryCreatesValidSoldier() {
        MedicFactory medicFactory = new MedicFactory();
        Soldier medic = medicFactory.createRandomSoldier("1", "M");

        assertNotNull(medic);
        assertTrue(medic instanceof Medic);
        assertEquals("1", medic.getId());
        assertEquals("M", medic.getArmySymbol());
        assertTrue(medic.getHealth() > 0 && medic.getHealth() <= 100, "Zdrowie powinno być między 1 a 100");
        assertTrue(medic.getAttackDamage() >= 0 && medic.getAttackDamage() <= 50, "Atak powinien być między 0 a 50");
    }

    @Test
    public void testRiflemanFactoryCreatesValidSoldier() {
        RiflemanFactory riflemanFactory = new RiflemanFactory();
        Soldier rifleman = riflemanFactory.createRandomSoldier("2", "R");

        assertNotNull(rifleman);
        assertTrue(rifleman instanceof Rifleman);
        assertEquals("2", rifleman.getId());
        assertEquals("R", rifleman.getArmySymbol());
        assertTrue(rifleman.getHealth() >= 0 && rifleman.getHealth() <= 100, "Zdrowie powinno być między 1 a 100");
        assertTrue(rifleman.getAttackDamage() >= 0 && rifleman.getAttackDamage() <= 50, "Atak powinien być między 0 a 50");
    }

    @Test
    public void testCavalryFactoryCreatesValidSoldier() {
        CavalryFactory cavalryFactory = new CavalryFactory();
        Soldier cavalry = cavalryFactory.createRandomSoldier("3", "C");

        assertNotNull(cavalry);
        assertTrue(cavalry instanceof Cavalry);
        assertEquals("3", cavalry.getId());
        assertEquals("C", cavalry.getArmySymbol());
        assertTrue(cavalry.getHealth() >= 0 && cavalry.getHealth() <= 100, "Zdrowie powinno być między 1 a 100");
        assertTrue(cavalry.getAttackDamage() >= 0 && cavalry.getAttackDamage() <= 50, "Atak powinien być między 0 a 50");
    }

    @Test
    public void testArtilleryFactoryCreatesValidSoldier() {
        ArtilleryFactory artilleryFactory = new ArtilleryFactory();
        Soldier artillery = artilleryFactory.createRandomSoldier("4", "A");

        assertNotNull(artillery);
        assertTrue(artillery instanceof Artillery);
        assertEquals("4", artillery.getId());
        assertEquals("A", artillery.getArmySymbol());
        assertTrue(artillery.getHealth() >= 0 && artillery.getHealth() <= 100, "Zdrowie powinno być między 1 a 100");
        assertTrue(artillery.getAttackDamage() >= 0 && artillery.getAttackDamage() <= 50, "Atak powinien być między 0 a 50");
    }

    @Test
    public void testInfantryFactoryCreatesValidSoldier() {
        InfantryFactory infantryFactory = new InfantryFactory();
        Soldier infantry = infantryFactory.createRandomSoldier("5", "I");

        assertNotNull(infantry);
        assertTrue(infantry instanceof Infantry);
        assertEquals("5", infantry.getId());
        assertEquals("I", infantry.getArmySymbol());
        assertTrue(infantry.getHealth() >= 0 && infantry.getHealth() <= 100, "Zdrowie powinno być między 1 a 100");
        assertTrue(infantry.getAttackDamage() >= 0 && infantry.getAttackDamage() <= 50, "Atak powinien być między 0 a 50");
    }
}
