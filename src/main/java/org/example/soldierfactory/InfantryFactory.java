package org.example.soldierfactory;

import org.example.army.Infantry;
import org.example.army.Soldier;

import java.util.Random;

/**
 * Klasa InfantryFactory implementuje interfejs SoldierFactory.
 * Jest odpowiedzialna za tworzenie losowych obiektów typu Infantry (piechota).
 */
public class InfantryFactory implements SoldierFactory {

    /**
     * Publiczny konstruktor fabryki piechoty.
     * Tworzy instancję klasy InfantryFactory.
     */
    public InfantryFactory() {
        // Konstruktor jest publiczny, aby można było tworzyć instancje klasy z zewnątrz.
    }

    /**
     * Metoda createRandomSoldier tworzy losowego żołnierza piechoty
     * z określonym identyfikatorem i symbolem armii.
     *
     * @param id          Unikalny identyfikator żołnierza.
     * @param armySymbol  Symbol identyfikujący armię, do której należy żołnierz.
     * @return Nowo utworzony obiekt klasy Infantry.
     */
    @Override
    public Soldier createRandomSoldier(String id, String armySymbol) {
        Random random = new Random();
        // Losowe wygenerowanie wartości zdrowia, obrażeń i pozycji
        int randomHealth = random.nextInt(100) + 1; // Dodaje 1, aby uniknąć wartości 0
        int randomAttackDamage = random.nextInt(51);
        int randomXPosition = random.nextInt(11);
        int randomYPosition = random.nextInt(11);
        // Utworzenie i zwrócenie nowego obiektu klasy Infantry
        return new Infantry(id, armySymbol, randomHealth, randomAttackDamage, randomXPosition, randomYPosition);
    }
}
