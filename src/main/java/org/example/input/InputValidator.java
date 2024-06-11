package org.example.input;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Klasa InputValidator służy do walidacji danych wprowadzanych przez użytkownika.
 */
public class InputValidator {
    private Scanner scanner;

    /**
     * Konstruktor tworzący nowy obiekt InputValidator i inicjujący obiekt Scanner dla wejścia standardowego.
     */
    public InputValidator() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Pobiera od użytkownika poprawne dane wejściowe w postaci liczby całkowitej.
     *
     * @param prompt Komunikat zachęcający do wprowadzenia danych.
     * @return Poprawne dane wejściowe jako liczba całkowita.
     */
    public int getValidInput(String prompt) {
        int input = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println(prompt);
            try {
                input = scanner.nextInt();
                if (input < 0) {
                    throw new InputMismatchException("Wartość musi być nieujemna.");
                }
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Nieprawidłowe dane. Proszę wprowadzić liczbę naturalną.");
                scanner.next(); // Wyczyść nieprawidłowe dane
            }
        }
        return input;
    }

    /**
     * Zamyka obiekt Scanner używany do pobierania danych wejściowych od użytkownika.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Sprawdza, czy suma żołnierzy i przeszkód dla obu armii nie przekracza liczby dostępnych komórek na polu bitwy.
     *
     * @param width          Szerokość pola bitwy.
     * @param height         Wysokość pola bitwy.
     * @param army1Soldiers  Liczba żołnierzy w armii 1.
     * @param army2Soldiers  Liczba żołnierzy w armii 2.
     * @param obstacles      Liczba przeszkód na polu bitwy.
     * @return True, jeśli suma żołnierzy i przeszkód nie przekracza liczby dostępnych komórek; w przeciwnym razie false.
     */
    public boolean validateArmyAndObstacleCount(int width, int height, int army1Soldiers, int army2Soldiers, int obstacles) {
        int totalCells = width * height;
        return (army1Soldiers + army2Soldiers + obstacles) <= totalCells;
    }
}
