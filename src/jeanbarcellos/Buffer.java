package jeanbarcellos;

/**
 * Classe Buffer <br>
 *
 * Possui o conteúdo, que é colocado pelo produtor, e um booleano que indica
 * quando o conteúdo está disponível.
 *
 * @author Jean Barcellos <jeanbarcellos@hotmail.com>
 * @date 08/11/2016
 *
 * @package jeanbarcellos
 *
 */
public class Buffer {

    /**
     * Conteúdo atual na Memória
     */
    private String conteudo;

    /**
     * Flag paraverificar se está produzindo
     */
    private boolean produzindo;

    /**
     * Conteúdo a ser inserido na memória
     *
     * @param linha
     */
    public synchronized void set(String linha) {

        while (this.produzindo == true) {
            try {
                System.out.println("Produtor esperando.");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.conteudo = linha;

        System.out.println("Produtor inseriu " + conteudo);

        this.produzindo = true;

        notifyAll();
    }

    /**
     * Retorna o conteúdo da memória
     *
     * @return String
     */
    public synchronized String get() {

        while (this.produzindo == false) {
            try {
                System.out.println("Consumidor esperado!");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Consumidor  consumiu: " + conteudo);

        this.produzindo = false;

        notifyAll();

        return this.conteudo;
    }
}
