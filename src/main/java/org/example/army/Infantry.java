package org.example.army;

import org.example.battlefield.Battlefield;

/**
 * Klasa reprezentująca piechotę.
 */
public class Infantry extends Soldier {

    /**
     * Konstruktor klasy Infantry.
     *
     * @param id            unikalny identyfikator żołnierza
     * @param armySymbol    symbol reprezentujący armię żołnierza
     * @param health        punkty zdrowia żołnierza
     * @param attackDamage  obrażenia zadawane przez żołnierza
     * @param xPosition     współrzędna x pozycji żołnierza na polu bitwy
     * @param yPosition     współrzędna y pozycji żołnierza na polu bitwy
     */
    public Infantry(String id, String armySymbol, int health, int attackDamage, int xPosition, int yPosition) {
        super(id, "I", armySymbol, health, attackDamage, xPosition, yPosition); // Przypisanie symbolu "I" i symbolu armii "1" dla piechoty
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
        int damage = getAttackDamage();
        System.out.println("Infantry attacks for " + damage + " damage!");
        //System.out.println("Health before attack: " + enemy.getHealth());
        enemy.setHealth(enemy.getHealth() - damage);
        //System.out.println("Health after attack: " + enemy.getHealth());
        battlefield.updateMapsAfterHealthChange(enemy);
        if (enemy.getHealth() <= 0) {
            battlefield.removeSoldierAt(enemyX, enemyY);
        }
    }

    /**
     * Metoda toString() zwracająca reprezentację obiektu Infantry w formie tekstowej.
     *
     * @return tekstowa reprezentacja obiektu Infantry
     */
    @Override
    public String toString() {
        return "Infantry{id='" + getId() + "', health=" + getHealth() + ", attackDamage=" + getAttackDamage() + ", armySymbol='" + getArmySymbol() + "'}";
    }
}
