import java.io.IOException;

public class Main {

	private Servidor servidor;

	public static void main(String[] args) throws IOException {
		new Main();
	}

	public Main() throws IOException {
		servidor = new Servidor();	

		Thread threadServidor = new Thread(servidor);
		threadServidor.start();
		
		new Cliente();
	}
}
