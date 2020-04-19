package com.boaglio.circuitbreaker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.boaglio.circuitbreaker.services.ServiceResponse;

@SpringBootTest
class TesteSemCircuitBreaker {

    static String SERVICE_SEM_NADA = "http://localhost:8080/sem-nada/pais/pop/";

    static String paisGrande   = "brasil";
    static String paisPequeno1 = "japao";
    static String paisPequeno2 = "italia";

    static int TOTAL_DE_ITERACOES = 5;

    @Test
    void chamadaServicosRapidos() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        double tempoTotal = 0;

        for (int contador = 1; contador <= TOTAL_DE_ITERACOES; contador++) {

            // pais pequeno 1
            ResponseEntity<ServiceResponse> response1 = testRestTemplate.getForEntity(SERVICE_SEM_NADA + paisPequeno1,
                    ServiceResponse.class);
            ServiceResponse respostasService1 = response1.getBody();
            System.out.println("[" + contador + "/" + TOTAL_DE_ITERACOES + "] - Resposta: "
                    + respostasService1.getMessage() + " - tempo gasto: " + respostasService1.getTimeElapsed()
                    + "ms ou " + respostasService1.getTimeElapsed() / 1000 + "s");

            tempoTotal += respostasService1.getTimeElapsed();

        }
        System.out.println(
                "Tempo total = " + tempoTotal + "ms ou " + tempoTotal / 1000 + "s ou " + tempoTotal / 60000 + "min");

    }

    @Test
    void chamadaTodosServicos() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        double tempoTotal = 0;

        for (int contador = 1; contador <= TOTAL_DE_ITERACOES; contador++) {

            // pais pequeno 1
            ResponseEntity<ServiceResponse> response1 = testRestTemplate.getForEntity(SERVICE_SEM_NADA + paisPequeno1,
                    ServiceResponse.class);
            ServiceResponse respostasService1 = response1.getBody();
            System.out.println("[" + contador + "/" + TOTAL_DE_ITERACOES + "] - Resposta: "
                    + respostasService1.getMessage() + " - tempo gasto: " + respostasService1.getTimeElapsed()
                    + "ms ou " + respostasService1.getTimeElapsed() / 1000 + "s");

            tempoTotal += respostasService1.getTimeElapsed();

            // pais pequeno 2
            ResponseEntity<ServiceResponse> response2 = testRestTemplate.getForEntity(SERVICE_SEM_NADA + paisPequeno2,
                    ServiceResponse.class);
            ServiceResponse respostasService2 = response2.getBody();
            System.out.println("[" + contador + "/" + TOTAL_DE_ITERACOES + "] - Resposta: "
                    + respostasService2.getMessage() + " - tempo gasto: " + respostasService2.getTimeElapsed()
                    + "ms ou " + respostasService2.getTimeElapsed() / 1000 + "s");

            tempoTotal += respostasService2.getTimeElapsed();

            // pais grande
            ResponseEntity<ServiceResponse> response3 = testRestTemplate.getForEntity(SERVICE_SEM_NADA + paisGrande,
                    ServiceResponse.class);
            ServiceResponse respostasService3 = response3.getBody();
            System.out.println("[" + contador + "/" + TOTAL_DE_ITERACOES + "] - Resposta: "
                    + respostasService3.getMessage() + " - tempo gasto: " + respostasService3.getTimeElapsed()
                    + "ms ou " + respostasService3.getTimeElapsed() / 1000 + "s");

            tempoTotal += respostasService3.getTimeElapsed();
        }
        System.out.println(
                "Tempo total = " + tempoTotal + "ms ou " + tempoTotal / 1000 + "s ou " + tempoTotal / 60000 + "min");

    }

}
