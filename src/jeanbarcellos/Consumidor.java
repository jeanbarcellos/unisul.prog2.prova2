package jeanbarcellos;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe Consumidor
 *
 * @author Jean Barcellos <jeanbarcellos@hotmail.com>
 * @date 08/11/2016
 *
 * @package jeanbarcellos
 *
 */
public class Consumidor extends Thread {

    /**
     * Caminho do arquivo com instruções SQL a serem gerados
     */
    private final String arquivo;

    /**
     * Carmiho do arquivo com a lista de candidatos a ser gerada
     */
    private final String arquivoCandidato;

    /**
     * Memória
     */
    private Buffer memoria;

    /**
     * Lista de Candidatos
     */
    private List<String> candidatos;

    /**
     * Construtor
     *
     * @param memoria Memória/Buffer
     * @param arquivoSql Caminho para o SQL a ser gerado
     * @param arquivoCandidato Caminho para a lista de candidatos a ser gerada
     */
    public Consumidor(Buffer memoria, String arquivoSql, String arquivoCandidato) {
        this.memoria = memoria;
        this.arquivo = arquivoSql;
        this.arquivoCandidato = arquivoCandidato;
        this.candidatos = new ArrayList<String>();
    }

    /**
     * Faz a escrita do arquivo de saída
     */
    private void escreverArquivo() {
        OutputStream escritorByte = null;
        OutputStreamWriter esctitorCaracter = null;
        BufferedWriter escritorPalavras = null;

        try {
            escritorByte = new FileOutputStream(this.arquivo);
            esctitorCaracter = new OutputStreamWriter(escritorByte);
            escritorPalavras = new BufferedWriter(esctitorCaracter);

            String linha = "";
            String sql = "";

            // Eqnquanto não receber a notificação fica processando
            while (!((linha = memoria.get()).equals("DONE"))) {

                // Adiciona nome a lista
                this.adicionarCandidato(linha);

                // Gera instrução SQL
                sql = this.gerarInsert(linha);
                escritorPalavras.write(sql);
                escritorPalavras.newLine();
            }
            escritorPalavras.flush();

            // Ordena a lista de candidatos
            this.ordenarListaCandidados();

            // Escrever Candidatos
            this.escreverCandidatos();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * Gerar Instrução SQL
     *
     * @param linha String contendo a linha do arquivo a ser feito o split
     * @return String
     */
    private String gerarInsert(String linha) {
        String[] coluna = linha.split(";");

        String sql = ""
                + "INSERT INTO vestibular "
                + "(id, nome, sexo, cidade, estado, acertos, classificacao) "
                + "VALUES "
                + "("
                + "" + coluna[0] + ", "
                + "'" + coluna[1] + "', "
                + "'" + coluna[2] + "', "
                + "'" + coluna[3] + "', "
                + "'" + coluna[4] + "', "
                + "" + coluna[5] + ", "
                + "" + coluna[6] + ""
                + ");";

        return sql;
    }

    /**
     * Gravar o nome do Candidato na lista
     *
     * @param linha
     */
    private void adicionarCandidato(String linha) {
        String[] coluna = linha.split(";");
        String candidato = coluna[1];

        if (!candidatos.contains(candidato)) {
            this.candidatos.add(candidato);
        }
    }

    /**
     * Ordena a lista de Candidatos
     */
    private void ordenarListaCandidados() {
        Collections.sort(this.candidatos);
    }

    /**
     * Escreve os candidatos em um arquivo
     */
    private void escreverCandidatos() {
        OutputStream escritorByte = null;
        OutputStreamWriter esctitorCaracter = null;
        BufferedWriter escritorPalavras = null;

        try {
            escritorByte = new FileOutputStream(this.arquivoCandidato);
            esctitorCaracter = new OutputStreamWriter(escritorByte);
            escritorPalavras = new BufferedWriter(esctitorCaracter);

            for (int i = 0; i < candidatos.size(); i++) {
                escritorPalavras.write(candidatos.get(i));
                escritorPalavras.newLine();
            }
            escritorPalavras.flush();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void run() {
        this.escreverArquivo();
    }
}
