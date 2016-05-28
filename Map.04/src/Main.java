import java.io.IOException;

public class Main {

	private Cliente cliente;
	private static Main window;
	private Servidor servidor;

	public static void main(String[] args) throws IOException {
		window = new Main();
	}

	public Main() throws IOException {
		servidor = new Servidor();	

		Thread threadServidor = new Thread(servidor);
		threadServidor.start();
		
		cliente = new Cliente();
	}
}
