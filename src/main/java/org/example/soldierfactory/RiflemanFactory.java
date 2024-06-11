package org.example.soldierfactory;

import org.example.army.Rifleman;
import org.example.army.Soldier;

import java.util.Random;

/**
 * Klasa RiflemanFactory implementuje interfejs SoldierFactory.
 * Jest odpowiedzialna za tworzenie losowych obiektów typu Rifleman (strzelec).
 */
public class RiflemanFactory implements SoldierFactory {

    /**
     * Publiczny konstruktor fabryki strzelców.
     * Tworzy instancję klasy RiflemanFactory.
     */
    public RiflemanFactory() {
        // Konstruktor jest publiczny, aby można było tworzyć instancje klasy z zewnątrz.
    }

    /**
     * Metoda createRandomSoldier tworzy losowego strzelca
     * z określonym identyfikatorem i symbolem armii.
     *
     * @param id          Unikalny identyfikator żołnierza.
     * @param armySymbol  Symbol identyfikujący armię, do której należy żołnierz.
     * @return Nowo utworzony obiekt klasy Rifleman.
     */
    @Override
    public Soldier createRandomSoldier(String id, String armySymbol) {
        Random random = new Random();
        // Losowe wygenerowanie wartości zdrowia, obrażeń i pozycji
        int randomHealth = random.nextInt(101);
        int randomAttackDamage = random.nextInt(51);
        int randomXPosition = random.nextInt(11);
        int randomYPosition = random.nextInt(11);
        // Utworzenie i zwrócenie nowego obiektu klasy Rifleman
        return new Rifleman(id, armySymbol, randomHealth, randomAttackDamage, randomXPosition, randomYPosition);
    }
}
