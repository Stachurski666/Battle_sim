package org.example.army;

import org.example.battlefield.Battlefield;


/**
 * Abstrakcyjna klasa reprezentująca pojedynczego żołnierza.
 */
 public abstract class Soldier {
    private String id;
    private String symbol;
    private String armySymbol;
    private int xPosition;
    private int yPosition;
    private int health;
    private int attackDamage;

    /**
     * Konstruktor do tworzenia żołnierza.
     *
     * @param id            unikalny identyfikator żołnierza
     * @param symbol        symbol reprezentujący typ żołnierza
     * @param armySymbol    symbol reprezentujący armię żołnierza
     * @param health        punkty zdrowia żołnierza
     * @param attackDamage  obrażenia zadawane przez żołnierza
     * @param xPosition     współrzędna x pozycji żołnierza na polu bitwy
     * @param yPosition     współrzędna y pozycji żołnierza na polu bitwy
     */

    public Soldier(String id, String symbol, String armySymbol, int health, int attackDamage, int xPosition, int yPosition) {
        this.id = id;
        this.symbol = symbol;
        this.armySymbol = armySymbol;
        this.health = health;
        this.attackDamage = attackDamage;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // Gettery i settery
    /**
     * Pobierz unikalny identyfikator żołnierza.
     *
     * @return unikalny identyfikator żołnierza
     */
    public String getId() {
        return id;
    }

    /**
     * Pobierz symbol reprezentujący typ żołnierza.
     *
     * @return symbol reprezentujący typ żołnierza
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Pobierz symbol reprezentujący armię żołnierza.
     *
     * @return symbol reprezentujący armię żołnierza
     */
    public String getArmySymbol() {
        return armySymbol;
    }

    /**
     * Pobierz punkty zdrowia żołnierza.
     *
     * @return punkty zdrowia żołnierza
     */
    public int getHealth() {
        return health;
    }

    /**
     * Pobierz współrzędną x pozycji żołnierza na polu bitwy.
     *
     * @return współrzędna x pozycji żołnierza na polu bitwy
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Pobierz współrzędną y pozycji żołnierza na polu bitwy.
     *
     * @return współrzędna y pozycji żołnierza na polu bitwy
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Ustaw unikalny identyfikator żołnierza.
     *
     * @param id unikalny identyfikator żołnierza
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Ustaw symbol reprezentujący typ żołnierza.
     *
     * @param symbol symbol reprezentujący typ żołnierza
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Pobierz obrażenia zadawane przez żołnierza.
     *
     * @return obrażenia zadawane przez żołnierza
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Ustaw symbol reprezentujący armię żołnierza.
     *
     * @param armySymbol symbol reprezentujący armię żołnierza
     */
    public void setArmySymbol(String armySymbol) {
        this.armySymbol = armySymbol;
    }

    /**
     * Ustaw punkty zdrowia żołnierza.
     *
     * @param health punkty zdrowia żołnierza
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Ustaw współrzędną x pozycji żołnierza na polu bitwy.
     *
     * @param xPosition współrzędna x pozycji żołnierza na polu bitwy
     */
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Ustaw współrzędną y pozycji żołnierza na polu bitwy.
     *
     * @param yPosition współrzędna y pozycji żołnierza na polu bitwy
     */
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Ustaw pozycję żołnierza na polu bitwy.
     *
     * @param x współrzędna x pozycji żołnierza na polu bitwy
     * @param y współrzędna y pozycji żołnierza na polu bitwy
     */
    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    /**
     * Abstrakcyjna metoda reprezentująca atak na wrogiego żołnierza.
     *
     * @param enemy        wrogi żołnierz, który ma zostać zaatakowany
     * @param battlefield  pole bitwy, na którym odbywa się atak
     * @param enemyX       współrzędna x pozycji wrogiego żołnierza
     * @param enemyY       współrzędna y pozycji wrogiego żołnierza
     */
    public abstract void attack(Soldier enemy, Battlefield battlefield, int enemyX, int enemyY);

    /**
     * Metoda zmniejszająca zdrowie żołnierza po otrzymaniu obrażeń.
     *
     * @param amount      ilość obrażeń
     * @param battlefield pole bitwy
     * @param x           współrzędna x pozycji
     * @param y           współrzędna y pozycji
     */
    public void decreaseHealth(int amount, Battlefield battlefield, int x, int y) {
        setHealth(this.health - amount);
        if (this.health == 0) {
            battlefield.removeSoldierAt(x, y);
        }
    }

    /**
     * Metoda zwracająca reprezentację tekstową obiektu.
     *
     * @return reprezentacja tekstowa obiektu
     */
    public String toString() {
        return "Infantry{id='" + getId() + "', health=" + getHealth() + ", attackDamage=" + getAttackDamage() + ", armySymbol='" + getArmySymbol() + "'}";
    }


}
