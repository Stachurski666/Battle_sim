package org.example.battlefield;

import org.example.army.Army;
import org.example.army.Medic;
import org.example.army.Soldier;

import java.util.*;


/**
 * Klasa reprezentująca pole bitwy.
 */
public class Battlefield {
    private String[][] grid;
    private Map<String, Soldier> soldiersById; // Mapa żołnierzy według id
    private Map<String, Soldier> soldiersByPosition; // Mapa żołnierzy według położenia (klucz: "x,y")
    private Map<String, String> positionById; // Mapa id obiektów według położenia (klucz: "x,y")
    private int width;
    private int height;
    private Army army1;
    private Army army2;

    /**
     * Konstruktor tworzący nowe pole bitwy o określonej szerokości i wysokości.
     * Inicjalizuje puste pole bitwy oraz dwie armie.
     *
     * @param width  szerokość pola bitwy
     * @param height wysokość pola bitwy
     */
    public Battlefield(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new String[height][width];
        initializeGrid();
        army1 = new Army();
        army2 = new Army();
        soldiersByPosition = new HashMap<>();
        soldiersById = new HashMap<>();
        positionById = new HashMap<>();
    }

    /**
     * Metoda zwracająca szerokość planszy.
     *
     * @return szerokość planszy
     */
    public int getWidth() {
        return width;
    }

    /**
     * Metoda zwracająca wysokość planszy.
     *
     * @return wysokość planszy
     */
    public int getHeight() {
        return height;
    }

    /**
     * Metoda zwracająca siatkę pola bitwy.
     *
     * @return siatka pola bitwy
     */
    public String[][] getGrid() {
        return grid;
    }

    /**
     * Inicjalizuje siatkę pola bitwy, wypełniając ją pustymi polami.
     */
    private void initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], ".."); // Wypełnienie pola kropkami (reprezentują puste pola)
        }
    }

    /**
     * Umieszcza żołnierza na określonej pozycji na planszy.
     *
     * @param soldier   żołnierz do umieszczenia
     * @param x         współrzędna x
     * @param y         współrzędna y
     * @param armySymbol symbol armii
     * @return true, jeśli żołnierz został pomyślnie umieszczony, false w przeciwnym razie
     */
    public boolean placeSoldier(Soldier soldier, int x, int y, String armySymbol) {
        if (isValidPosition(x, y) && !isCollision(x, y)) {
            String symbol = soldier.getSymbol();
            String fullSymbol = symbol + armySymbol;
            grid[y][x] = fullSymbol;
            String id = soldier.getId(); // Unikalny identyfikator żołnierza
            // Aktualizacja mapy żołnierzy według położenia
            soldiersByPosition.put(x + "," + y, soldier);
            // Aktualizacja mapy żołnierzy według ID
            soldiersById.put(soldier.getId(), soldier);
            // Aktualizacja mapy pozycji na ID
            positionById.put(x + "," + y, soldier.getId());
            if (armySymbol.equals("1")) {
                army1.addSoldier(soldier, id);
            } else if (armySymbol.equals("2")) {
                army2.addSoldier(soldier, id);
            }
            return true;
        }

        return false;
    }

    /**
     * Sprawdza, czy pozycja (x, y) jest poprawna w ramach planszy.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @return true, jeśli pozycja jest poprawna, false w przeciwnym razie
     */
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length;
    }

    /**
     * Sprawdza, czy na pozycji (x, y) znajduje się już inny obiekt.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @return true, jeśli na pozycji znajduje się inny obiekt, false w przeciwnym razie
     */
    private boolean isCollision(int x, int y) {
        return grid[y][x] != ".."; // Sprawdzenie, czy pole jest już zajęte przez innego żołnierza
    }

    /**
     * Dodaje przeszkodę na określonej pozycji.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public void addObstacle(int x, int y) {
        if (isValidPosition(x, y)) {
            grid[y][x] = "##"; // Oznaczenie przeszkody na planszy
        }
    }

    /**
     * Dodaje losowe przeszkody na planszy.
     *
     * @param obstaclesCount liczba przeszkód do dodania
     */
    public void addRandomObstacles(int obstaclesCount) {
        Random random = new Random();
        for (int i = 0; i < obstaclesCount; i++) {
            int x, y;
            do {
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while (isOccupied(x, y)); // Sprawdź, czy wybrane miejsce jest już zajęte przez żołnierza
            addObstacle(x, y);
        }
    }

    /**
     * Sprawdza, czy pozycja (x, y) jest zajęta przez przeszkodę lub żołnierza.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @return true, jeśli pozycja jest zajęta, false w przeciwnym razie
     */
    public boolean isOccupied(int x, int y) {
        return grid[y][x].equals("##") || !grid[y][x].equals(".."); // Sprawdź, czy miejsce jest zajęte przez przeszkodę lub żołnierza
    }

    /**
     * Sprawdza, czy na pozycji (x, y) znajduje się przeszkoda.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @return true, jeśli na pozycji znajduje się przeszkoda, false w przeciwnym razie
     */
    public boolean isObstacle(int x, int y) {
        return isValidPosition(x, y) && grid[y][x].equals("##"); // Sprawdzenie, czy na danej pozycji znajduje się przeszkoda
    }

    /**
     * Wyświetla aktualny stan pola bitwy.
     */
    public void display() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Przesuwa wszystkich żołnierzy na planszy w losowe kierunki.
     * Wyświetla stan planszy po każdym ruchu.
     */
    public void moveAllSoldiers() {
        Random random = new Random();
        Set<String> movedSoldiers = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String positionKey = j + "," + i;
                if (!grid[i][j].equals("..") && !movedSoldiers.contains(positionKey) && !isObstacle(j, i)) {
                    Direction randomDirection = getRandomDirection(random);
                    moveSoldierByOne(j, i, randomDirection, movedSoldiers);

                }
            }
        }
        display();
        interact();

    }

    /**
     * Zwraca losowy kierunek ruchu.
     *
     * @param random obiekt Random do losowania kierunku
     * @return losowy kierunek ruchu
     */
    private Direction getRandomDirection(Random random) {
        Direction[] directions = Direction.values();
        return directions[random.nextInt(directions.length)];
    }

    /**
     * Przesuwa żołnierza o jedno pole w określonym kierunku.
     *
     * @param currentX       aktualna współrzędna x
     * @param currentY       aktualna współrzędna y
     * @param direction      kierunek ruchu
     * @param movedSoldiers  zbiór żołnierzy, którzy już się poruszyli w tej turze
     */
    public void moveSoldierByOne(int currentX, int currentY, Direction direction, Set<String> movedSoldiers) {
        int newX = currentX;
        int newY = currentY;

        switch (direction) {
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
            case LEFT:
                newX--;
                break;
            case RIGHT:
                newX++;
                break;
        }

        if (isValidPosition(newX, newY) && !isCollision(newX, newY) && !isObstacle(newX, newY)) {
            String symbol = grid[currentY][currentX];
            grid[currentY][currentX] = "..";
            grid[newY][newX] = symbol;

            String currentPosKey = currentX + "," + currentY;
            String newPosKey = newX + "," + newY;

            String soldierId = positionById.remove(currentPosKey);
            positionById.put(newPosKey, soldierId);

            Soldier movedSoldier = soldiersByPosition.remove(currentPosKey);
            if (movedSoldier != null) {
                soldiersByPosition.put(newPosKey, movedSoldier);
                soldiersById.put(movedSoldier.getId(), movedSoldier);
                movedSoldiers.add(newPosKey);

                // Aktualizacja pozycji w obiekcie Soldier
                movedSoldier.setPosition(newX, newY);

                System.out.println("Soldier at position (" + currentX + ", " + currentY + ") moved to position (" + newX + ", " + newY + ")");
            }
        } else {
            movedSoldiers.add(currentX + "," + currentY);
        }
    }

    /**
     * Sprawdza interakcje między żołnierzami na planszy.
     */
    protected void interact() {
        // Iteruj po każdym polu na planszy
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Sprawdź, czy na danym polu znajduje się obiekt
                if (!grid[i][j].equals("..")) {
                    // Sprawdź, czy sąsiednie pola zawierają inne obiekty
                    for (Direction direction : Direction.values()) {
                        int neighborX = j + direction.getX();
                        int neighborY = i + direction.getY();
                        if (isValidPosition(neighborX, neighborY) && !grid[neighborY][neighborX].equals("..")) {
                            // Znaleziono kolizję - pobierz obiekty na tych polach
                            Soldier currentSoldier = getSoldierAt(j, i, grid[i][j].substring(1));
                            Soldier neighborSoldier = getSoldierAt(neighborX, neighborY, grid[neighborY][neighborX].substring(1));
                            // Wykonaj odpowiednią akcję w zależności od rodzaju obiektów
                            if (currentSoldier != null && neighborSoldier != null) {
                                // Wykonaj odpowiednią akcję w zależności od rodzaju obiektów
                                interactBetweenSoldiers(currentSoldier, neighborSoldier, j, i, neighborX, neighborY);
                                if (currentSoldier.getHealth() <= 0) {
                                    removeSoldierAt(j, i);
                                }
                                if (neighborSoldier.getHealth() <= 0) {
                                    removeSoldierAt(neighborX, neighborY);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Usuwa żołnierza z określonej pozycji.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public void removeSoldierAt(int x, int y) {
        if (isValidPosition(x, y)) {
            String positionKey = getPositionKey(x, y);
            Soldier removedSoldier = soldiersByPosition.get(positionKey);

            if (removedSoldier != null) {
                String id = removedSoldier.getId();
                if (id != null) {
                    // Usunięcie pozycji z mapy positionById
                    positionById.remove(id);
                    // Usunięcie żołnierza z mapy soldiersById
                    soldiersById.remove(id);
                    // Usunięcie żołnierza z mapy soldiersByPosition
                    soldiersByPosition.remove(positionKey);
                    // Ustaw puste pole na miejscu żołnierza
                    grid[y][x] = "..";
                }
            }
        }
    }

    /**
     * Wykonuje odpowiednią akcję w zależności od rodzaju obiektów.
     *
     * @param currentSoldier obecny żołnierz
     * @param neighborSoldier sąsiadujący żołnierz
     * @param currentX współrzędna x obecnego żołnierza
     * @param currentY współrzędna y obecnego żołnierza
     * @param neighborX współrzędna x sąsiadującego żołnierza
     * @param neighborY współrzędna y sąsiadującego żołnierza
     */
    private void interactBetweenSoldiers(Soldier currentSoldier, Soldier neighborSoldier, int currentX, int currentY, int neighborX, int neighborY) {
        if (currentSoldier.getHealth() > 0 && neighborSoldier.getHealth() > 0) {
            if (currentSoldier instanceof Medic && currentSoldier.getArmySymbol().equals(neighborSoldier.getArmySymbol())) {
                // Jeśli obiekt currentSoldier jest medykiem i należy do tej samej armii co neighborSoldier
                Medic medic = (Medic) currentSoldier;
                System.out.println("Soldier at position (" + currentX + ", " + currentY + ") [" + currentSoldier.getSymbol() + currentSoldier.getArmySymbol() + "] heals soldier at position (" + neighborX + ", " + neighborY + ") [" + neighborSoldier.getSymbol() + neighborSoldier.getArmySymbol() + "]");
                medic.heal(neighborSoldier);
                System.out.println("Health of soldier at position (" + neighborX + ", " + neighborY + ") after healing: " + neighborSoldier.getHealth());
            }
            if (!currentSoldier.getArmySymbol().equals(neighborSoldier.getArmySymbol())) {
                System.out.println("Soldier at position (" + currentX + ", " + currentY + ") [" + currentSoldier.getSymbol() + currentSoldier.getArmySymbol() + "] attacks soldier at position (" + neighborX + ", " + neighborY + ") [" + neighborSoldier.getSymbol() + neighborSoldier.getArmySymbol() + "]");
                currentSoldier.attack(neighborSoldier, this, neighborX, neighborY);
                System.out.println("Health of soldier at position (" + neighborX + ", " + neighborY + ") after attack: " + neighborSoldier.getHealth());
                if (neighborSoldier.getHealth() > 0) {
                    neighborSoldier.attack(currentSoldier, this, currentX, currentY);
                    System.out.println("Health of soldier at position (" + currentX + ", " + currentY + ") after counterattack: " + currentSoldier.getHealth());
                }

                if (currentSoldier.getHealth() <= 0) {
                    System.out.println("Soldier at position (" + currentX + ", " + currentY + ") [" + currentSoldier.getSymbol() + currentSoldier.getArmySymbol() + "] has been defeated.");
                    removeSoldierAt(currentX, currentY);
                } else {
                    updateMapsAfterHealthChange(currentSoldier);
                }
                if (neighborSoldier.getHealth() <= 0) {
                    System.out.println("Soldier at position (" + neighborX + ", " + neighborY + ") [" + neighborSoldier.getSymbol() + neighborSoldier.getArmySymbol() + "] has been defeated.");
                    removeSoldierAt(neighborX, neighborY);
                } else {
                    updateMapsAfterHealthChange(neighborSoldier);
                }
            }
        }
    }

    /**
     * Aktualizuje mapy po zmianie zdrowia żołnierza.
     *
     * @param soldier żołnierz, którego zdrowie uległo zmianie
     */
    public void updateMapsAfterHealthChange(Soldier soldier) {
        String id = soldier.getId();
        String positionKey = positionById.get(id);

        if (positionKey != null) {
            // Aktualizacja soldiersById
            soldiersById.put(id, soldier);
            // Aktualizacja soldiersByPosition
            soldiersByPosition.put(positionKey, soldier);
        }
    }

    /**
     * Zwraca klucz pozycji w formacie "x,y".
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @return klucz pozycji
     */
    private String getPositionKey(int x, int y) {
        return x + "," + y;
    }

    /**
     * Pobiera żołnierza znajdującego się na określonej pozycji.
     *
     * @param x          współrzędna x
     * @param y          współrzędna y
     * @param armySymbol symbol armii
     * @return żołnierz na danej pozycji
     */
    public Soldier getSoldierAt(int x, int y, String armySymbol) {
        if (isValidPosition(x, y)) {
            String positionKey = x + "," + y;
            String soldierId = positionById.get(positionKey); // Pobierz ID żołnierza na danej pozycji

            if (soldierId != null) {
                return soldiersById.get(soldierId); // Pobierz żołnierza na podstawie ID
            } else {
                return soldiersByPosition.get(positionKey); // Pobierz żołnierza na podstawie pozycji
            }
        }
        return null; // Brak aktywnego żołnierza na danej pozycji
    }

    /**
     * Wyświetla zdrowie żołnierzy na planszy.
     */
    public void displayHealth() {
        //displayArmies();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!grid[i][j].equals("..")) {
                    // Pobieranie ID żołnierza z mapy pozycji na ID
                    String soldierId = positionById.get(j + "," + i);
                    if (soldierId != null) {
                        // Pobieranie żołnierza z mapy żołnierzy według ID
                        Soldier soldier = soldiersById.get(soldierId);
                        if (soldier != null) {
                            System.out.println("Position: (" + j + ", " + i + ") - Symbol: " + grid[i][j] + ", Health: " + soldier.getHealth());
                        }
                    }
                }
            }
        }
    }

    /**
     * Zwraca armię 1.
     *
     * @return armia 1
     */
    public Army getArmy1() {
        return army1;
    }


    /**
     * Zwraca armię 2.
     *
     * @return armia 2
     */
    public Army getArmy2() {
        return army2;
    }
}
