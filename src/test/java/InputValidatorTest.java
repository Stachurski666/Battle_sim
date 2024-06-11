import org.example.input.InputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputValidatorTest {

    @Test
    public void testGetValidInput() {
        String input = "10\n"; // Symulacja wprowadzenia liczby 10 przez użytkownika
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InputValidator validator = new InputValidator();
        int validInput = validator.getValidInput("Podaj liczbę naturalną:");
        Assertions.assertEquals(10, validInput);
        validator.close();
    }

    @Test
    public void testValidateArmyAndObstacleCount() {
        InputValidator validator = new InputValidator();
        boolean validCount = validator.validateArmyAndObstacleCount(5, 5, 10, 10, 5);
        Assertions.assertTrue(validCount);

        boolean invalidCount = validator.validateArmyAndObstacleCount(5, 5, 20, 20, 10);
        Assertions.assertFalse(invalidCount);
        validator.close();
    }
}