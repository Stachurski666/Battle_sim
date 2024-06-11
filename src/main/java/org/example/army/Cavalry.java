package org.example.army;

import org.example.battlefield.Battlefield;

/**
 * Klasa reprezentująca jazdę.
 */
public class Cavalry extends Soldier {

    /**
     * Konstruktor klasy Cavalry.
     *
     * @param id            unikalny identyfikator żołnierza
     * @param armySymbol    symbol reprezentujący armię żołnierza
     * @param health        punkty zdrowia żołnierza
     * @param attackDamage  obrażenia zadawane przez żołnierza
     * @param xPosition     współrzędna x pozycji żołnierza na polu bitwy
     * @param yPosition     współrzędna y pozycji żołnierza na polu bitwy
     */
    public Cavalry(String id, String armySymbol, int health, int attackDamage, int xPosition, int yPosition) {
        super(id, "C", armySymbol, health, attackDamage, xPosition, yPosition);
    }

    /**
     * Metoda ataku na wrogiego żołnierza.
     *
     * @param enemy        wrogi żołnierz, który ma zostać zaatakowany
     * @param battlefield  pole bitwy, na którym odbywa się atak
     * @param enemyX       współrzędna x pozycji wrogiego żołnierza
     * @param enemyY       współrzędna y pozycji wrogiego żołnierza
     */
    @Override
    public void attack(Soldier enemy, Battlefield battlefield, int enemyX, int enemyY) {
        int damage = getAttackDamage() * 2; // Podwójne obrażenia dla konnego
        enemy.decreaseHealth(damage, battlefield, enemyX, enemyY);
        System.out.println("Cavalry attacks for " + damage + " damage!");
        if (enemy.getHealth() <= 0) {
            battlefield.removeSoldierAt(enemyX, enemyY);
        }
    }

    /**
     * Metoda toString() zwracająca reprezentację obiektu Cavalry w formie tekstowej.
     *
     * @return tekstowa reprezentacja obiektu Cavalry
     */
    @Override
    public String toString() {
        return "Cavalry{id='" + getId() + "', health=" + getHealth() + ", attackDamage=" + getAttackDamage() + ", armySymbol='" + getArmySymbol() + "'}";
    }
}
