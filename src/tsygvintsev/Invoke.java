package tsygvintsev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, помечающая метод для автоматического вызова.
 * <p>
 * Может применяться только к методам.
 * Сохраняется в runtime для обработки через Reflection API.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invoke {
}
