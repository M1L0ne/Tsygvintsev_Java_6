package tsygvintsev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для проверки корректности работы аннотации {@link Invoke}
 * и механизма автоматического вызова методов.
 */
public class InvokeJUnitTest {

    private InvokeTask instance;

    /**
     * Создаёт новый экземпляр InvokeTask перед каждым тестом.
     */
    @BeforeEach
    public void setUp() {
        instance = new InvokeTask();
    }

    /**
     * Проверяет, что методы с @Invoke выполняются и изменяют состояние объекта.
     *
     * @throws Exception если Reflection API не сработал
     */
    @Test
    public void testInvokeMethods_ExecuteAndChangeState() throws Exception {
        assertFalse(instance.isInitialized(), "Объект не должен быть инициализирован изначально");
        assertEquals(0, instance.getValidationCount(), "Счётчик валидаций должен быть 0");

        InvokeTask.runInvokeMethods(instance);

        assertTrue(instance.isInitialized(), "Метод init() должен установить флаг initialized");
        assertEquals(1, instance.getValidationCount(), "Метод validate() должен увеличить счётчик на 1");
    }

    /**
     * Проверяем, что мы действительно используем Reflection API.
     */
    @Test
    public void testReflectionFindsInvokeMethods() {
        long count = java.util.Arrays.stream(InvokeTask.class.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Invoke.class))
                .count();

        assertEquals(2, count, "Должно быть ровно 2 метода с @Invoke");
    }
}