package br.com.brq.challenges.mercadinho.usecase.service.utils;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class MercadinhoListServiceUtils<T> {

    public static <T> int indexOf(List<T> list, Predicate<? super T> predicate) {
        long noMatchPrefix = list.stream().takeWhile(predicate.negate()).count();
        return noMatchPrefix == list.size() ? -1 : (int) noMatchPrefix;
    }
}
