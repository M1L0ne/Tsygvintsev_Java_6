package tsygvintsev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для проверки корректности работы аннотации {@link Validate}
 * и обработчика в классе {@link ValidateJUnitTest}.
 */
public class ValidateJUnitTest {

    /**
     * Проверяет, что метод корректно извлекает классы из аннотации @Validate.
     */
    @Test
    public void testGetValidationClasses_ReturnsCorrectArray() {
        Class<?>[] result = ValidateTask.getValidationClasses(ValidateTask.class);

        assertArrayEquals(
                new Class<?>[]{String.class, Integer.class, ValidateTask.class},
                result,
                "Массив классов не совпадает с ожидаемым"
        );
    }

    /**
     * Проверяет, что при пустом массиве в @Validate выбрасывается исключение.
     */
    @Test
    public void testGetValidationClasses_EmptyArray_ThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ValidateTask.getValidationClasses(EmptyValidate.class),
                "Ожидалось исключение при пустом массиве"
        );

        assertTrue(
                exception.getMessage().contains("пуст"),
                "Сообщение об ошибке должно указывать на пустой массив"
        );
    }

    // Вспомогательный класс с пустым @Validate (для теста)
    @Validate({})
    private static class EmptyValidate {}
}
