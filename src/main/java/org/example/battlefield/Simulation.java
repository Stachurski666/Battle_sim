package org.example.battlefield;

import org.example.army.Army;
import org.example.army.Soldier;
import org.example.round.Round;
import org.example.soldiergenerator.SoldiersGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Klasa reprezentująca symulację bitwy na polu bitwy.
 * Umożliwia wykonanie symulacji z określoną liczbą tur oraz zapisanie stanu bitwy po każdej turze do plików tekstowych.
 */
public class Simulation {
    private Battlefield battlefield;
    private SoldiersGenerator soldiersGenerator;
    private Round round;

    private int obstaclesCount;

    /**
     * Konstruktor klasy Simulation.
     *
     * @param width          Szerokość pola bitwy.
     * @param height         Wysokość pola bitwy.
     * @param army1Soldiers  Liczba żołnierzy armii 1.
     * @param army2Soldiers  Liczba żołnierzy armii 2.
     * @param obstaclesCount Liczba przeszkód na polu bitwy.
     */
    public Simulation(int width, int height, int army1Soldiers, int army2Soldiers, int obstaclesCount) {
        battlefield = new Battlefield(width, height);
        this.obstaclesCount = obstaclesCount;
        soldiersGenerator = new SoldiersGenerator(battlefield);

        soldiersGenerator.putRandomSoldiers(army1Soldiers, "1");
        soldiersGenerator.putRandomSoldiers(army2Soldiers, "2");


        // Dodanie przeszkód
        battlefield.addRandomObstacles(obstaclesCount);

        round = new Round(battlefield);
    }

    /**
     * Metoda wykonująca symulację bitwy przez określoną liczbę tur.
     * Zapisuje stan bitwy po każdej turze do plików tekstowych.
     *
     * @param rounds Liczba tur do wykonania. Jeśli 0, symulacja trwa do momentu, gdy tylko jedna armia pozostanie.
     */
    public void executeSimulation(int rounds) {
        try (FileWriter armyWriter = new FileWriter("armies_state.txt");
             FileWriter gridWriter = new FileWriter("board_state.txt")) {
            // Zapisywanie stanu na początku symulacji
            armyWriter.write("Initial State:\n");
            int initialArmy1Count = battlefield.getArmy1().getActiveSoldiersCount();
            int initialArmy2Count = battlefield.getArmy2().getActiveSoldiersCount();
            saveBattlefieldState(armyWriter, initialArmy1Count, initialArmy2Count);
            armyWriter.write("\n");

            gridWriter.write("Initial State:\n");
            saveGridState(gridWriter);
            gridWriter.write("\n");

            battlefield.displayHealth();
            battlefield.display();

            int currentRound = 0;
            int previousArmy1Count = battlefield.getArmy1().getSoldiers().size();
            int previousArmy2Count = battlefield.getArmy2().getSoldiers().size();

            while ((rounds == 0 && !isOneArmyLeft()) || (rounds > 0 && currentRound < rounds)) {
                System.out.println("Tura " + (currentRound + 1));
                round.executeRound();
                battlefield.displayHealth();
                System.out.println();

                // Zapisywanie stanu po każdej turze
                armyWriter.write("State after round " + (currentRound + 1) + ":\n");
                saveBattlefieldState(armyWriter, previousArmy1Count, previousArmy2Count);
                armyWriter.write("\n");

                gridWriter.write("State after round " + (currentRound + 1) + ":\n");
                saveGridState(gridWriter);
                gridWriter.write("\n");

                previousArmy1Count = battlefield.getArmy1().getActiveSoldiersCount();
                previousArmy2Count = battlefield.getArmy2().getActiveSoldiersCount();

                currentRound++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sprawdza, czy pozostała tylko jedna armia na polu bitwy.
     *
     * @return true, jeśli pozostała tylko jedna armia; false w przeciwnym razie.
     */
    private boolean isOneArmyLeft() {
        boolean army1Present = false;
        boolean army2Present = false;

        for (String[] row : battlefield.getGrid()) {
            for (String cell : row) {
                if (!cell.equals("..")) {
                    if (cell.endsWith("1")) {
                        army1Present = true;
                    } else if (cell.endsWith("2")) {
                        army2Present = true;
                    }
                }

                if (army1Present && army2Present) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Zapisuje stan bitwy do pliku tekstowego, włączając liczbę pozostałych żołnierzy w obu armiach oraz informacje o każdym z nich.
     *
     * @param writer              Obiekt FileWriter do zapisu stanu bitwy.
     * @param previousArmy1Count Liczba żołnierzy armii 1 przed turą.
     * @param previousArmy2Count Liczba żołnierzy armii 2 przed turą.
     * @throws IOException Jeśli wystąpi problem podczas zapisu do pliku.
     */
    private void saveBattlefieldState(FileWriter writer, int previousArmy1Count, int previousArmy2Count) throws IOException {
        int currentArmy1Count = saveArmyState(battlefield.getArmy1(), writer);
        int currentArmy2Count = saveArmyState(battlefield.getArmy2(), writer);

        writer.write("Army 1 - Remaining Soldiers: " + currentArmy1Count + ", Soldiers Lost This Round: " + (previousArmy1Count - currentArmy1Count) + "\n");
        writer.write("Army 2 - Remaining Soldiers: " + currentArmy2Count + ", Soldiers Lost This Round: " + (previousArmy2Count - currentArmy2Count) + "\n");
    }

    /**
     * Zapisuje stan armii do pliku tekstowego, włączając informacje o każdym żołnierzu.
     *
     * @param army   Armia, której stan ma zostać zapisany.
     * @param writer Obiekt FileWriter do zapisu stanu armii.
     * @return Liczba pozostałych aktywnych żołnierzy w armii.
     * @throws IOException Jeśli wystąpi problem podczas zapisu do pliku.
     */
    private int saveArmyState(Army army, FileWriter writer) throws IOException {
        Map<String, Soldier> soldiers = army.getSoldiers();
        int activeSoldiersCount = 0;

        for (Map.Entry<String, Soldier> entry : soldiers.entrySet()) {
            Soldier soldier = entry.getValue();
            String line = "Soldier: " + soldier.getSymbol() + soldier.getArmySymbol() + ", ";
            if (soldier.getHealth() > 0) {
                line += "Position: (" + soldier.getXPosition() + "," + soldier.getYPosition() + "), ";
                activeSoldiersCount++;
            }
            line += "Health: " + soldier.getHealth() + (soldier.getHealth() <= 0 ? " (Defeated)" : "") + ", Damage: "+soldier.getAttackDamage()+"\n";
            writer.write(line);
        }

        return activeSoldiersCount;
    }

    /**
     * Zapisuje stan pola bitwy do pliku tekstowego.
     *
     * @param writer Obiekt FileWriter do zapisu stanu pola bitwy.
     * @throws IOException Jeśli wystąpi problem podczas zapisu do pliku.
     */
    private void saveGridState(FileWriter writer) throws IOException {
        String[][] grid = battlefield.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                writer.write(grid[i][j] + " ");
            }
            writer.write("\n");
        }
        writer.write("\n");
    }


}
