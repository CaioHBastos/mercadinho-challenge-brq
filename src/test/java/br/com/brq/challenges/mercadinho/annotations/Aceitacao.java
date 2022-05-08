package br.com.brq.challenges.mercadinho.annotations;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Tag("aceitacao")
public @interface Aceitacao {
}
