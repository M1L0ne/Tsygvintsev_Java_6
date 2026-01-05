package tsygvintsev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, указывающая класс по умолчанию.
 * <p>
 * Может применяться к типам (классам) и полям.
 * Обязательно содержит ссылку на класс через {@code value()}.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
    /**
     * Указывает класс по умолчанию.
     *
     * @return класс, используемый по умолчанию
     */
    Class<?> value();
}
