import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Servidor extends JFrame implements Runnable {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ServerSocket servidor;
	private Container c;
	public Servidor() {
		
		
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		c = getContentPane();
		this.setVisible(true);
	}

	@Override
	public void run() {		
			iniciarServidor();		
	}

	@SuppressWarnings("resource")
	public void iniciarServidor() {
		try {
			servidor = new ServerSocket(12345);
			System.out.println(">>Servidor Iniciado<<");
		    Socket clienteConexao = servidor.accept();
		    System.out.println(">>Servidor conectado com Cliente<<");

		    
		    InputStream inputStream = clienteConexao.getInputStream();

		    byte[] sizeAr = new byte[4];
	        inputStream.read(sizeAr);
	        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

	        byte[] imageAr = new byte[size];
	        inputStream.read(imageAr);
	        
	        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

	        System.out.println(">>Imagem recebida<<");       
                        
    		ImageImplement panel = new ImageImplement(image);
   			contentPane.add(panel);
   			contentPane.repaint();
                          
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}






}
