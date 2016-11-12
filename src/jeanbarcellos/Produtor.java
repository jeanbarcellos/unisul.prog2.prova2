package jeanbarcellos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Classe Produtor
 *
 * @author Jean Barcellos <jeanbarcellos@hotmail.com>
 * @date 08/11/2016
 *
 * @package jeanbarcellos
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
    private Buffer memoria;

    /**
     * Construtor
     *
     * @param arquivo Path do arquivo a ser lido
     * @param memoria Memória/Buffer
     */
    public Produtor(Buffer memoria, String arquivo) {
        this.memoria = memoria;
        this.arquivo = arquivo;
    }

    /**
     * Faz a leitura do arquivo
     */
    private void lerArquivo() {
        InputStream leitorByte = null;
        InputStreamReader leitorCaracter = null;
        BufferedReader leitorPalavras = null;

        try {
            leitorByte = new FileInputStream(this.arquivo);
            leitorCaracter = new InputStreamReader(leitorByte);
            leitorPalavras = new BufferedReader(leitorCaracter);

            String linha = leitorPalavras.readLine();

            while (linha != null) {
                memoria.set(linha);

                linha = leitorPalavras.readLine();
            }
            memoria.set("DONE");

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
        this.lerArquivo();
    }
}
