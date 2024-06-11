package org.example.soldierfactory;

import org.example.army.Medic;
import org.example.army.Soldier;

import java.util.Random;

/**
 * Klasa MedicFactory implementuje interfejs SoldierFactory.
 * Jest odpowiedzialna za tworzenie losowych obiektów typu Medic (sanitariusz).
 */
public class MedicFactory implements SoldierFactory {

    /**
     * Publiczny konstruktor fabryki medyków.
     * Tworzy instancję klasy MedicFactory.
     */
    public MedicFactory() {
        // Konstruktor jest publiczny, aby można było tworzyć instancje klasy z zewnątrz.
    }

    /**
     * Metoda createRandomSoldier tworzy losowego sanitariusza
     * z określonym identyfikatorem i symbolem armii.
     *
     * @param id          Unikalny identyfikator żołnierza.
     * @param armySymbol  Symbol identyfikujący armię, do której należy żołnierz.
     * @return Nowo utworzony obiekt klasy Medic.
     */
    @Override
    public Soldier createRandomSoldier(String id, String armySymbol) {
        Random random = new Random();
        // Losowe wygenerowanie wartości zdrowia, obrażeń, ilości uzdrowienia i pozycji
        int randomHealth = random.nextInt(100) + 1; // Dodaje 1, aby uniknąć wartości 0
        int randomAttackDamage = random.nextInt(51);
        int randomHealAmount = random.nextInt(21);
        int randomXPosition = random.nextInt(11);
        int randomYPosition = random.nextInt(11);
        // Utworzenie i zwrócenie nowego obiektu klasy Medic
        return new Medic(id, armySymbol, randomHealth, randomAttackDamage, randomHealAmount, randomXPosition, randomYPosition);
    }
}
