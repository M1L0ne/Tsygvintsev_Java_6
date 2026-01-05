package tsygvintsev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Управляет включением поля или класса в строковое представление.
 * <p>
 * Применяется к типам и полям. По умолчанию включено (YES).
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {

    /**
     * Режим включения в toString.
     */
    enum Mode { YES, NO }

    /**
     * Указывает, следует ли включать элемент в строковое представление.
     *
     * @return режим; по умолчанию YES
     */
    Mode value() default Mode.YES;
}