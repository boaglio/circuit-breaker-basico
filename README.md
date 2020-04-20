# Circuit Breaker simplificado

Esse projeto é uma implementação pura em Java do padrão Circuit Breaker com o objetivo didático de entender o seu comportamento.

A parte de serviços, acessos e banco de dados é mockada, para uso do padrão em produção considere o uso do *Hystrix*. 


## Descrição dos arquivos do projeto

<pre>
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── boaglio
    │   │           └── circuitbreaker
    │   │               ├── CircuitBreakerBasicoApplication.java  - classe inicial do Spring Boot
    │   │               ├── repository
    │   │               │   └── DatabaseMock.java - banco de dados simulado
    │   │               └── services
    │   │                   ├── circuitbreaker
    │   │                   │   ├── CircuitBreaker.java - implementação do padrão Circuit Breaker
    │   │                   │   ├── ConsultaPaisesComCircuitBreaker.java - serviço com Circuit Breaker
    │   │                   │   └── Posicao.java - enum com as posições do circuito
    │   │                   ├── semnada
    │   │                   │   └── ConsultaPaises.java - serviço sem Circuit Breaker
    │   │                   └── ServiceResponse.java - POJO da resposta padrão do serviço
    └── test
        └── java
            └── com
                └── boaglio
                    └── circuitbreaker
                        ├── TesteComCircuitBreaker.java - teste dos serviços com circuit breaker
                        └── TesteSemCircuitBreaker.java - teste dos serviços com circuit breaker
</pre>

## Referências

* [CircuitBreaker - Martin Fowler](https://martinfowler.com/bliki/CircuitBreaker.html)
* [Netflix Hystrix](https://github.com/Netflix/Hystrix/) 
