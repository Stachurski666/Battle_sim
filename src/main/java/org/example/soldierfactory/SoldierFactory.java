package org.example.soldierfactory;

import org.example.army.Soldier;

/**
 * Interfejs SoldierFactory definiuje metodę do tworzenia losowych żołnierzy.
 * Implementujące ten interfejs fabryki są odpowiedzialne za tworzenie różnych typów żołnierzy.
 */
public interface SoldierFactory {
    /**
     * Metoda createRandomSoldier służy do tworzenia losowych żołnierzy z określonym identyfikatorem
     * i symbolem armii.
     *
     * @param id          Unikalny identyfikator żołnierza.
     * @param armySymbol  Symbol identyfikujący armię, do której należy żołnierz.
     * @return Nowo utworzony obiekt klasy Soldier.
     */
    Soldier createRandomSoldier(String id, String armySymbol);
}
