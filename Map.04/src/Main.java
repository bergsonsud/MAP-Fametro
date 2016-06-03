
public class Main {

	public static void main(String[] args) {
		Servidor servidor = new Servidor();

		Thread threadServidor = new Thread(servidor);
		threadServidor.start();

		new Cliente();

	}

}
