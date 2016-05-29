import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Servidor extends JFrame implements Runnable {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ServerSocket servidor;
	public Servidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
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
		    Socket clienteConexao = servidor.accept();
		    
		    new Scanner(clienteConexao.getInputStream());
		    
		   	
		   		
		   		JOptionPane.showMessageDialog(null, "Mensagem recebida!");
		   		
		   		BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(clienteConexao.getInputStream()));
                System.out.println("Image received!!!!"); 
                
                JPanel panel = new JPanel();
                panel.setSize(500,640);
                panel.setBackground(Color.CYAN);  
                ImageIcon icon = new ImageIcon(img);  
                JLabel label = new JLabel();  
                label.setIcon(icon);  
                panel.add(label); 
                contentPane.add(label); 
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
