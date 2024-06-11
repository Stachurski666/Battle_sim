package org.example.army;

import org.example.battlefield.Battlefield;


/**
 * Klasa reprezentująca strzelca.
 */
public class Rifleman extends Soldier {

    /**
     * Konstruktor klasy Rifleman.
     *
     * @param id            unikalny identyfikator żołnierza
     * @param armySymbol    symbol reprezentujący armię żołnierza
     * @param health        punkty zdrowia żołnierza
     * @param attackDamage  obrażenia zadawane przez żołnierza
     * @param xPosition     współrzędna x pozycji żołnierza na polu bitwy
     * @param yPosition     współrzędna y pozycji żołnierza na polu bitwy
     */
    public Rifleman(String id, String armySymbol, int health, int attackDamage, int xPosition, int yPosition) {
        super(id, "R", armySymbol, health, attackDamage, xPosition, yPosition);
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
        int damage = getAttackDamage() + 5; // Dodatkowe 5 obrażeń dla strzelca
        enemy.decreaseHealth(damage, battlefield, enemyX, enemyY);
        System.out.println("Rifleman attacks for " + damage + " damage!");
        if (enemy.getHealth() <= 0) {
            battlefield.removeSoldierAt(enemyX, enemyY);
        }
    }


    /**
     * Metoda toString() zwracająca reprezentację obiektu Rifleman w formie tekstowej.
     *
     * @return tekstowa reprezentacja obiektu Rifleman
     */
    @Override
    public String toString() {
        return "Rifleman{id='" + getId() + "', health=" + getHealth() + ", attackDamage=" + getAttackDamage() + ", armySymbol='" + getArmySymbol() + "'}";
    }
}
