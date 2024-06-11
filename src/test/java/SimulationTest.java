import org.example.battlefield.Simulation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SimulationTest {

    @Test
    public void testExecuteSimulationWithRound() {

        // Tworzenie obiektu do testowanej symulacji
        Simulation simulation = new Simulation(10, 10, 2, 3, 3); // Wysokość, szerokość, liczba żołnierzy armii 1, liczba żołnierzy armii 2, liczba przeszkód

        // Wywołanie metody executeSimulation() z określoną liczbą rund
        try {
            simulation.executeSimulation(1);
        } catch (Exception e) {
            Assertions.fail("Metoda executeSimulation() rzuciła wyjątek: " + e.getMessage());
        }

        // Sprawdzenie, czy pliki tekstowe zapisujące stan bitwy zostały utworzone
        File armyFile = new File("armies_state.txt");
        File gridFile = new File("board_state.txt");
        Assertions.assertTrue(armyFile.exists() && gridFile.exists());
    }

}