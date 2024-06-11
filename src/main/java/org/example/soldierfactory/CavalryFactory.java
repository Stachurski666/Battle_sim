package org.example.soldierfactory;

import org.example.army.Cavalry;
import org.example.army.Soldier;

import java.util.Random;

/**
 * Klasa CavalryFactory implementuje interfejs SoldierFactory.
 * Jest odpowiedzialna za tworzenie losowych obiektów typu Cavalry (kawaleria).
 */
public class CavalryFactory implements SoldierFactory {

    /**
     * Publiczny konstruktor fabryki kawalerii.
     * Tworzy instancję klasy CavalryFactory.
     */
    public CavalryFactory() {
        // Konstruktor jest publiczny, aby można było tworzyć instancje klasy z zewnątrz.
    }

    /**
     * Metoda createRandomSoldier tworzy losowego żołnierza kawalerii
     * z określonym identyfikatorem i symbolem armii.
     *
     * @param id          Unikalny identyfikator żołnierza.
     * @param armySymbol  Symbol identyfikujący armię, do której należy żołnierz.
     * @return Nowo utworzony obiekt klasy Cavalry.
     */
    @Override
    public Soldier createRandomSoldier(String id, String armySymbol) {
        Random random = new Random();
        // Losowe wygenerowanie wartości zdrowia, obrażeń i pozycji
        int randomHealth = random.nextInt(100) + 1; // Dodaje 1, aby uniknąć wartości 0
        int randomAttackDamage = random.nextInt(51);
        int randomXPosition = random.nextInt(11);
        int randomYPosition = random.nextInt(11);
        // Utworzenie i zwrócenie nowego obiektu klasy Cavalry
        return new Cavalry(id, armySymbol, randomHealth, randomAttackDamage, randomXPosition, randomYPosition);
    }
}
