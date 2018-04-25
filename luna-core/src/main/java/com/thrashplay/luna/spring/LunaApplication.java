package com.thrashplay.luna.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Sean Kleinjung
 */
@SpringBootApplication
@Import({LunaSpringConfig.class})
@Target(TYPE)
@Retention(RUNTIME)
public @interface LunaApplication {
}
