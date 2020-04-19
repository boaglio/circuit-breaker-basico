package com.boaglio.circuitbreaker.services.circuitbreaker;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.circuitbreaker.repository.DatabaseMock;
import com.boaglio.circuitbreaker.services.ServiceResponse;

@RequestMapping("/circuitbreaker")
@RestController
public class ConsultaPaisesComCircuitBreaker {

    @GetMapping("/pais/pop/{pais}")
    public ServiceResponse calculaPopulacao(@PathVariable String pais) {

        String status;
        String message;
        String populacao = null;
        boolean circuitoAberto = false;

        Instant start = Instant.now();

        // String populacao = database.buscaPopulacao(pais);

        ExecutorService executor = Executors.newCachedThreadPool();
        Callable<String> task = new Callable<String>() {
            public String call() {
                return DatabaseMock.buscaPopulacao(pais);
            }
        };
        Future<String> future = executor.submit(task);
        try {

            Posicao posicaoDoCircuito = CircuitBreaker.call();

            switch (posicaoDoCircuito) {
                case ABERTO:

                    circuitoAberto = true;

                    break;

                case FECHADO:

                    populacao = future.get(CircuitBreaker.INVOCATION_TIMEOUT, TimeUnit.SECONDS);

                    break;
            }

        } catch (TimeoutException ex) {

            System.out.println("=== Estourou o tempo limite ===");
            CircuitBreaker.passouLimite();
            circuitoAberto = true;

        } catch (InterruptedException | ExecutionException e2) {
            System.out.println("Outro erro ");
        } finally {
            future.cancel(true);
        }

        // monta mensagem
        if (populacao != null) {
            status = "Ok";
            message = pais + " - population: " + populacao;
        } else if (circuitoAberto) {
            status = "Erro Grave";
            message = " Sistema indisponível, tente mais tarde =(";
        } else {
            status = "Erro";
            message = pais + " sem cadastro";
        }

        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();

        ServiceResponse resposta = new ServiceResponse(message, status, timeElapsed);

        System.out.println("circuit breaker: " + resposta);

        return resposta;

    }

}
