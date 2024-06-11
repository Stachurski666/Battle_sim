package org.example.army;

import org.example.army.Soldier;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa reprezentująca armię, która przechowuje kolekcję żołnierzy.
 */
public class Army {
    private Map<String, Soldier> soldiers;

    /**
     * Konstruktor tworzący nową instancję klasy Army.
     * Inicjalizuje pustą kolekcję żołnierzy.
     */
    public Army() {
        soldiers = new HashMap<>();
    }

    /**
     * Dodaje żołnierza do armii.
     *
     * @param soldier żołnierz do dodania
     * @param id      unikalny identyfikator żołnierza
     */
    public void addSoldier(Soldier soldier, String id) {
        soldiers.put(id, soldier); // Użyj unikalnego identyfikatora jako klucza
    }

    /**
     * Zwraca mapę żołnierzy w armii.
     *
     * @return mapa żołnierzy, gdzie kluczem jest unikalny identyfikator żołnierza
     */
    public Map<String, Soldier> getSoldiers() {
        return soldiers;
    }

    /**
     * Zwraca liczbę aktywnych żołnierzy w armii (tych, którzy mają więcej niż 0 punktów zdrowia).
     *
     * @return liczba aktywnych żołnierzy
     */
    public int getActiveSoldiersCount() {
        int count = 0;
        for (Soldier soldier : soldiers.values()) {
            if (soldier.getHealth() > 0) {
                count++;
            }
        }
        return count;
    }
}
