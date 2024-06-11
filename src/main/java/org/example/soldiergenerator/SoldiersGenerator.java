package org.example.soldiergenerator;

import org.example.army.Soldier;
import org.example.battlefield.Battlefield;
import org.example.soldierfactory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Klasa SoldiersGenerator jest odpowiedzialna za generowanie i umieszczanie losowych żołnierzy na polu bitwy.
 * Wykorzystuje fabryki żołnierzy do tworzenia różnych typów jednostek oraz losuje ich pozycje na podstawie dostępnych miejsc na polu bitwy.
 */
public class SoldiersGenerator {
    private Battlefield battlefield;
    private SoldierFactory[] soldierFactories;

    /**
     * Konstruktor tworzy obiekt generatora żołnierzy z podanym polem bitwy.
     *
     * @param battlefield Pole bitwy, na którym mają być umieszczeni żołnierze.
     */
    public SoldiersGenerator(Battlefield battlefield) {
        this.battlefield = battlefield;
        this.soldierFactories = new SoldierFactory[]{new InfantryFactory(), new CavalryFactory(), new ArtilleryFactory(), new RiflemanFactory(), new MedicFactory()};
    }

    /**
     * Metoda putRandomSoldiers generuje i umieszcza losową liczbę żołnierzy na polu bitwy dla określonego symbolu armii.
     *
     * @param count      Liczba żołnierzy do wygenerowania i umieszczenia.
     * @param armySymbol Symbol armii, do której należeć będą wygenerowani żołnierze.
     */
    public void putRandomSoldiers(int count, String armySymbol) {
        Random random = new Random();
        List<int[]> possiblePositions = new ArrayList<>();
        for (int i = 0; i < battlefield.getWidth(); i++) {
            for (int j = 0; j < battlefield.getHeight(); j++) {
                possiblePositions.add(new int[]{i, j});
            }
        }

        int soldiersPlaced = 0;
        while (soldiersPlaced < count && !possiblePositions.isEmpty()) {
            int[] randomPosition = possiblePositions.remove(random.nextInt(possiblePositions.size()));
            int randomX = randomPosition[0];
            int randomY = randomPosition[1];
            SoldierFactory randomFactory = soldierFactories[random.nextInt(soldierFactories.length)];
            String id = UUID.randomUUID().toString(); // Generowanie unikalnego identyfikatora
            Soldier randomSoldier = randomFactory.createRandomSoldier(id, armySymbol); // Fabryka nadal generuje identyfikator

            // Nadpisanie wygenerowanych XPosition i YPosition
            randomSoldier.setXPosition(randomX);
            randomSoldier.setYPosition(randomY);

            if (battlefield.placeSoldier(randomSoldier, randomX, randomY, armySymbol)) {
                soldiersPlaced++;
            }
        }

        if (soldiersPlaced < count) {
            System.out.println("Not enough space to place all soldiers. Placed " + soldiersPlaced + " out of " + count);
        }
    }
}
