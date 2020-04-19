package com.boaglio.circuitbreaker.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class DatabaseMock {

    private static final int PROCESSO_LENTO  = 100;
    private static final int PROCESSO_RAPIDO = 1;

    static Map<String, String> mapPaises = Map.of("brasil", "210.147.125", "japao", "126.440.000", "italia",
            "60.665.551");

    public static synchronized String buscaPopulacao(String pais) {

        System.out.println("- inicio busca por " + pais);

        int tempoDeProcessamento = PROCESSO_RAPIDO;
        if (pais.equalsIgnoreCase("brasil")) {
            tempoDeProcessamento = PROCESSO_LENTO;
        }

        try {
            Thread.sleep(tempoDeProcessamento * 1000);
        } catch (InterruptedException e) {
        }

        System.out.println("- fim busca por " + pais);

        return mapPaises.get(pais.toLowerCase());
    }

}
