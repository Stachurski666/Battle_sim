package org.example.simulation;

import org.example.battlefield.Simulation;
import org.example.input.InputValidator;

/**
 * Klasa Main jest punktem wejścia dla symulacji bitwy. Pobiera parametry symulacji od użytkownika
 * za pomocą obiektu klasy InputValidator, a następnie tworzy obiekt klasy Simulation, aby przeprowadzić
 * symulację z podanymi parametrami.
 */
public class Main {
    /**
     * Konstruktor klasy Main.
     * Nie są wykonywane żadne operacje inicjalizacyjne, więc nie są wymagane dodatkowe komentarze.
     */
    public Main() {
        // Konstruktor domyślny bez dodatkowych operacji.
    }
    /**
     * Metoda main jest punktem wejścia dla programu. Pobiera parametry symulacji od użytkownika,
     * tworzy obiekt klasy Simulation i uruchamia symulację.
     *
     * @param args Argumenty wiersza poleceń (nie są używane w tej aplikacji).
     */
    public static void main(String[] args) {
        int width, height, army1Soldiers, army2Soldiers, rounds, obstacles;

        InputValidator inputValidator = new InputValidator();

        // Pobieranie parametrów symulacji od użytkownika
        width = inputValidator.getValidInput("Podaj szerokość pola bitwy:");
        height = inputValidator.getValidInput("Podaj wysokość pola bitwy:");
        army1Soldiers = inputValidator.getValidInput("Podaj liczbę żołnierzy dla armii 1:");
        army2Soldiers = inputValidator.getValidInput("Podaj liczbę żołnierzy dla armii 2:");
        obstacles = inputValidator.getValidInput("Podaj liczbę przeszkód:");

        // Walidacja, czy suma żołnierzy z obu armii i przeszkód nie przekracza rozmiaru planszy
        while (!inputValidator.validateArmyAndObstacleCount(width, height, army1Soldiers, army2Soldiers, obstacles)) {
            System.out.println("Suma żołnierzy z obu armii oraz przeszkody przekraczają rozmiar planszy. Podaj wartości ponownie.");
            army1Soldiers = inputValidator.getValidInput("Podaj liczbę żołnierzy dla armii 1:");
            army2Soldiers = inputValidator.getValidInput("Podaj liczbę żołnierzy dla armii 2:");
            obstacles = inputValidator.getValidInput("Podaj liczbę przeszkód:");
        }

        // Pobieranie liczby tur lub wybór trwania symulacji do zwycięstwa jednej z armii
        rounds = inputValidator.getValidInput("Podaj liczbę tur (jeśli chcesz by symulacja trwała do wygranej jednej z armii wprowadź 0):");

        inputValidator.close();

        // Tworzenie i uruchamianie symulacji
        Simulation symulacja = new Simulation(width, height, army1Soldiers, army2Soldiers, obstacles);
        symulacja.executeSimulation(rounds);
    }
}

