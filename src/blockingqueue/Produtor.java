package blockingqueue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

/**
 * Classe Produtor
 *
 * @author Jean Barcellos <jeanbarcellos@hotmail.com>
 * @date 14/11/2016
 *
 * @package blockingqueue
 *
 */
public class Produtor extends Thread {

    /**
     * Caminho do arquivo a ser lido
     */
    private String arquivo;

    /**
     * Memória/Buffer
     */
//    private Buffer memoria;
    protected BlockingQueue<String> memoria = null;

    /**
     * Construtor
     *
     * @param arquivo Path do arquivo a ser lido
     * @param memoria Memória/Buffer
     */
    public Produtor(BlockingQueue<String> memoria, String arquivo) {
        this.memoria = memoria;
        this.arquivo = arquivo;
    }

    /**
     * Faz a leitura do arquivo
     */
    private void lerArquivo() throws InterruptedException {
        InputStream leitorByte = null;
        InputStreamReader leitorCaracter = null;
        BufferedReader leitorPalavras = null;

        try {
            leitorByte = new FileInputStream(this.arquivo);
            leitorCaracter = new InputStreamReader(leitorByte);
            leitorPalavras = new BufferedReader(leitorCaracter);

            String linha = leitorPalavras.readLine();

            while (linha != null) {
                System.out.println(linha);
                memoria.put(linha);

                linha = leitorPalavras.readLine();
            }
            memoria.put("DONE");

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (leitorByte != null) {
                    leitorByte.close();
                }
                if (leitorCaracter != null) {
                    leitorCaracter.close();
                }
                if (leitorPalavras != null) {
                    leitorPalavras.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void run() {
        
        try {
            this.lerArquivo();
            
            memoria.put("DONE");
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
        
    }
}
