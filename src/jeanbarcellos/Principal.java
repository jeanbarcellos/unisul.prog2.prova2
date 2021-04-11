package jeanbarcellos;

/**
 * Classe principal
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
        String arquivoSaidaSql = "conteudo/prova2_sql.sql";
        String arquivoSaidaCandidatos = "conteudo/prova2_lista_candidatos.txt";

        // Mem?ria compartilhada
        Buffer memoriaCompartilhada = new Buffer();

        Produtor produtor = new Produtor(memoriaCompartilhada, arquivoEntrada);
        Consumidor consumidor = new Consumidor(memoriaCompartilhada, arquivoSaidaSql, arquivoSaidaCandidatos);

        System.out.println("Iniciando ...");

        produtor.start();
        consumidor.start();

    }

}
