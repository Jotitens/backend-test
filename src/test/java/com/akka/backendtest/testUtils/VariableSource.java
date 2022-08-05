package com.akka.backendtest.testUtils;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

/**
 * Annotation used to provide arguments on Integration Test
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(VariableArgumentsProvider.class)
public @interface VariableSource {
    /**
     * Name of the static variable
     */
    String value();
}
