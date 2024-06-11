package org.example.battlefield;

/**
 * Wymienna reprezentująca kierunki ruchu na polu bitwy.
 * Każdy kierunek ma skojarzone wartości deltaX i deltaY, które określają zmianę współrzędnych x i y.
 */
public enum Direction {
    /**
     * Kierunek w górę, odpowiadający zmniejszeniu współrzędnej y.
     */
    UP(0, -1),

    /**
     * Kierunek w dół, odpowiadający zwiększeniu współrzędnej y.
     */
    DOWN(0, 1),

    /**
     * Kierunek w lewo, odpowiadający zmniejszeniu współrzędnej x.
     */
    LEFT(-1, 0),

    /**
     * Kierunek w prawo, odpowiadający zwiększeniu współrzędnej x.
     */
    RIGHT(1, 0);

    private final int deltaX;
    private final int deltaY;

    /**
     * Konstruktor dla enuma Direction z określoną zmianą współrzędnych x i y.
     *
     * @param deltaX Zmiana współrzędnej x.
     * @param deltaY Zmiana współrzędnej y.
     */
    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Zwraca zmianę współrzędnej x dla tego kierunku.
     *
     * @return Zmiana współrzędnej x.
     */
    public int getX() {
        return deltaX;
    }

    /**
     * Zwraca zmianę współrzędnej y dla tego kierunku.
     *
     * @return Zmiana współrzędnej y.
     */
    public int getY() {
        return deltaY;
    }
}
