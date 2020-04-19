package com.boaglio.circuitbreaker.services.circuitbreaker;

/**
 * Adaptacao simplificada de:
 * 
 * https://martinfowler.com/bliki/CircuitBreaker.html
 * 
 * @author Fernando Boaglio
 */
public class CircuitBreaker {

    public static final int INVOCATION_TIMEOUT = 2;
    static final int        LIMITE_FALHA       = 5;
    static int              falhas             = 0;
    static Posicao          estado             = Posicao.FECHADO;

    public static void passouLimite() {
        falhas += 1;
        estado = Posicao.ABERTO;
    }

    public static void normalizou() {
        falhas = 0;
        estado = Posicao.FECHADO;
    }

    public static Posicao call() {

        System.out.println("Verificando circuito...");
        if (falhas > LIMITE_FALHA) {

            System.out.println("- passou o limite, vamos tentar novamente...");
            normalizou();

        } else {
            System.out.println("- falhas = " + falhas);
        }

        switch (estado) {
            case ABERTO:
                System.out.println("- Circuito aberto =(");
                falhas++;
                break;
            case FECHADO:
                System.out.println("- Circuito fechado =)");
                break;
        }
        return estado;

    }

}
