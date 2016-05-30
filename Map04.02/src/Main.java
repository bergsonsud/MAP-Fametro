import java.io.IOException;

public class Main {

	private Servidor servidor;

	public static void main(String[] args) {
		try {
			new Main();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Main() throws IOException {
		servidor = new Servidor();	

		Thread threadServidor = new Thread(servidor);
		threadServidor.start();
		
		new Cliente();
	}
}
