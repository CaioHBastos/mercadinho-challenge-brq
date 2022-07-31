package br.com.brq.challenges.mercadinho.usecase.service.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class MercadinhoServiceUtils {

    public MercadinhoServiceUtils() {
    }

    public static String gerarData() {
        return OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).toString();
    }
}
