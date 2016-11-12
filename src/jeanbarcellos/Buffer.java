package jeanbarcellos;

/**
 * Classe Buffer <br>
 *
 * Possui o conte�do, que � colocado pelo produtor, e um booleano que indica
 * quando o conte�do est� dispon�vel.
 *
 * @author Jean Barcellos <jeanbarcellos@hotmail.com>
 * @date 08/11/2016
 *
 * @package jeanbarcellos
 *
 */
public class Buffer {

    /**
     * Conte�do atual na Mem�ria
     */
    private String conteudo;

    /**
     * Flag paraverificar se est� produzindo
     */
    private boolean produzindo;

    /**
     * Conte�do a ser inserido na mem�ria
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
     * Retorna o conte�do da mem�ria
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
