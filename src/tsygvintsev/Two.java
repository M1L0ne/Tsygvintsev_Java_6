package tsygvintsev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация с двумя обязательными параметрами: строкой и числом.
 * <p>
 * Применяется только к типам (классам, интерфейсам и т.д.).
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Two {

    /**
     * Первое значение — строка.
     *
     * @return непустая строка
     */
    String first();

    /**
     * Второе значение — целое число.
     *
     * @return целое число
     */
    int second();
}