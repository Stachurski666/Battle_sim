package org.example.soldierfactory;

import org.example.army.Artillery;
import org.example.army.Soldier;

import java.util.Random;

/**
 * Klasa ArtilleryFactory implementuje interfejs SoldierFactory.
 * Jest odpowiedzialna za tworzenie losowych obiektów typu Artillery (artyleria).
 */
public class ArtilleryFactory implements SoldierFactory {

    /**
     * Publiczny konstruktor fabryki artylerii.
     * Tworzy instancję klasy ArtilleryFactory.
     */
    public ArtilleryFactory() {
        // Konstruktor jest publiczny, aby można było tworzyć instancje klasy z zewnątrz.
    }

    /**
     * Metoda createRandomSoldier tworzy losowego żołnierza artylerii
     * z określonym identyfikatorem i symbolem armii.
     *
     * @param id          Unikalny identyfikator żołnierza.
     * @param armySymbol  Symbol identyfikujący armię, do której należy żołnierz.
     * @return Nowo utworzony obiekt klasy Artillery.
     */
    @Override
    public Soldier createRandomSoldier(String id, String armySymbol) {
        Random random = new Random();
        // Losowe wygenerowanie wartości zdrowia, obrażeń i pozycji
        int randomHealth = random.nextInt(100) + 1; // Dodaje 1, aby uniknąć wartości 0
        int randomAttackDamage = random.nextInt(51);
        int randomXPosition = random.nextInt(11);
        int randomYPosition = random.nextInt(11);
        // Utworzenie i zwrócenie nowego obiektu klasy Artillery
        return new Artillery(id, armySymbol, randomHealth, randomAttackDamage, randomXPosition, randomYPosition);
    }
}
