package org.example.round;

import org.example.battlefield.Battlefield;

/**
 * Klasa Round reprezentuje pojedynczą rundę symulacji na polu bitwy.
 * Odpowiada za wykonywanie ruchów wszystkich żołnierzy oraz wyświetlanie stanu pola bitwy po zakończeniu rundy.
 */
public class Round {
    private Battlefield battlefield;

    /**
     * Konstruktor tworzy nowy obiekt rundy z podanym polem bitwy.
     *
     * @param battlefield Pole bitwy, na którym ma być przeprowadzona runda.
     */
    public Round(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    /**
     * Metoda executeRound() wykonuje rundę symulacji na polu bitwy.
     * Wykonuje ruchy wszystkich żołnierzy oraz wyświetla stan pola bitwy po zakończeniu rundy.
     */
    public void executeRound() {
        battlefield.moveAllSoldiers();
        battlefield.display();
    }


}
