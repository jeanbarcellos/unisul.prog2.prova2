package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Jean Barcellos <jeanbarcellos@hotmail.com>
 */
public class Principal {

    /**
     * Execução do programa
     *
     * @param args
     */
    public static void main(String[] args) {

        // Arquivos de Entrada e Saída
        String arquivoEntrada = "conteudo/prova2.txt";
        String arquivoSaidaSql = "conteudo/bq/prova2_sql_bq.sql";
        String arquivoSaidaCandidatos = "conteudo/bq/prova2_lista_candidados_bq.txt";

        // Memória compartilhada
        BlockingQueue<String> memoriaCompartilhada = new ArrayBlockingQueue<String>(1000);

        Produtor produtor = new Produtor(memoriaCompartilhada, arquivoEntrada);
        Consumidor consumidor = new Consumidor(memoriaCompartilhada, arquivoSaidaSql, arquivoSaidaCandidatos);

        System.out.println("Iniciando ...");

        produtor.start();
        consumidor.start();

    }

}
