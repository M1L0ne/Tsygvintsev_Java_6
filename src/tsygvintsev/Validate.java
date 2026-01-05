package tsygvintsev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Указывает, какие классы должны участвовать в валидации.
 * <p>
 * Применяется к типам и другим аннотациям.
 * Содержит массив классов для проверки.
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

    /**
     * Массив классов, подлежащих валидации.
     *
     * @return непустой массив классов
     */
    Class<?>[] value();
}