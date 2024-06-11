package org.example.army;

import org.example.battlefield.Battlefield;

/**
 * Klasa reprezentująca medyka.
 */
public class Medic extends Soldier {
    private int healAmount;

    /**
     * Konstruktor klasy Medic.
     *
     * @param id            unikalny identyfikator żołnierza
     * @param armySymbol    symbol reprezentujący armię żołnierza
     * @param health        punkty zdrowia żołnierza
     * @param attackDamage  obrażenia zadawane przez żołnierza
     * @param healAmount    ilość zdrowia przywracana przez medyka
     * @param xPosition     współrzędna x pozycji żołnierza na polu bitwy
     * @param yPosition     współrzędna y pozycji żołnierza na polu bitwy
     */

    public Medic(String id, String armySymbol, int health, int attackDamage, int healAmount, int xPosition, int yPosition) {
        super(id, "M", armySymbol, health, attackDamage, xPosition, yPosition);
        this.healAmount = healAmount;
    }

    /**
     * Metoda lecząca sojusznicze jednostki.
     *
     * @param ally sojuszniczy żołnierz, który ma zostać wyleczony
     */
    public void heal(Soldier ally) {
        int newHealth = ally.getHealth() + healAmount;
        ally.setHealth(newHealth);
        System.out.println("Medic heals " + ally.getClass().getSimpleName() + " for " + healAmount + " health points!");
    }

    /**
     * Metoda atakująca wrogie jednostki.
     *
     * @param enemy        wrogi żołnierz, który ma zostać zaatakowany
     * @param battlefield  pole bitwy, na którym odbywa się atak
     * @param enemyX       współrzędna x pozycji wrogiego żołnierza
     * @param enemyY       współrzędna y pozycji wrogiego żołnierza
     */
    @Override
    public void attack(Soldier enemy, Battlefield battlefield, int enemyX, int enemyY) {
        int damage = getAttackDamage() / 2; // Obrażenia medyka zmniejszone o połowę
        enemy.decreaseHealth(damage, battlefield, enemyX, enemyY);
        System.out.println("Medic attacks for " + damage + " damage!");
        if (enemy.getHealth() <= 0) {
            battlefield.removeSoldierAt(enemyX, enemyY);
        }
    }

    /**
     * Metoda toString() zwracająca reprezentację obiektu Medic w formie tekstowej.
     *
     * @return tekstowa reprezentacja obiektu Medic
     */
    @Override
    public String toString() {
        return "Medic{id='" + getId() + "', health=" + getHealth() + ", attackDamage=" + getAttackDamage() + ", armySymbol='" + getArmySymbol() + "'}";
    }
}
