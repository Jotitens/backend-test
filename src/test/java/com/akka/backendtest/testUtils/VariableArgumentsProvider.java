package com.akka.backendtest.testUtils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class VariableArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<VariableSource> {

    private String variableName;

    @Override
    public void accept(VariableSource variableSource) {
        variableName = variableSource.value();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return context.getTestClass()
                .map(this::getField)
                .map(this::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Failed to load test arguments"));

    }

    private Field getField(Class<?> clazz) {
        try {
            return clazz.getDeclaredField(variableName);
        } catch (Exception e) {
            return null;
        }
    }

    private Stream<Arguments> getValue(Field field){
        Object value = null;
        try {
            value = field.get(null);
        } catch (Exception ignore) {}

        return value == null ? null : (Stream<Arguments>) value;
    }
}
