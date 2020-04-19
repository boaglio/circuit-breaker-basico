package com.boaglio.circuitbreaker.services.semnada;

import java.time.Duration;
import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.circuitbreaker.repository.DatabaseMock;
import com.boaglio.circuitbreaker.services.ServiceResponse;

@RequestMapping("/sem-nada")
@RestController
public class ConsultaPaises {

    @GetMapping("/pais/pop/{pais}")
    public ServiceResponse calculaPopulacao(@PathVariable String pais) {

        String status;
        String message;

        Instant start = Instant.now();

        String populacao = DatabaseMock.buscaPopulacao(pais);

        if (populacao != null) {
            status = "Ok";
            message = pais + " - population: " + populacao;
        } else {
            status = "Erro";
            message = pais + " sem cadastro";
        }

        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();

        ServiceResponse resposta = new ServiceResponse(message, status, timeElapsed);

        System.out.println("Sem nada: " + resposta);

        return resposta;

    }

}
