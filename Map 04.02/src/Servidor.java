import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class Servidor extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JPanel contentPane;

	private ImageImplement imagem = null;

	public Servidor(){
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);		
		this.setVisible(true);
	}



	@Override
	public void run() {
		System.out.println(">>Iniciando servidor");
		Socket cliente = null;		
		ObjectOutputStream out = null;
		ObjectInputStream in = null;

		while(true){
			try {

				ServerSocket servidor = new ServerSocket(12345);
				System.out.println(">>Servidor online");
				cliente = servidor.accept();	
				System.out.println(">>IP Cliente " +
						cliente.getInetAddress().getHostAddress());
				out = new ObjectOutputStream(cliente.getOutputStream());
				in = new ObjectInputStream(cliente.getInputStream());


				File file = (File) in.readObject();
				Image image = ImageIO.read(file);	
				System.out.println(">>Imagem recebida, renderizando!");
				imagem = new ImageImplement(image);

				this.getContentPane().removeAll();
				contentPane.add(imagem);
				contentPane.repaint();

				PrintStream saida = new PrintStream(cliente.getOutputStream());
				saida.println("Imagem recebida");

				out.close();
				in.close();

				servidor.close();

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

	}
}
