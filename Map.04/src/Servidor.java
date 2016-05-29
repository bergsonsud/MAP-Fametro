import java.awt.BorderLayout;
import java.awt.TextArea;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Servidor extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ServerSocket servidor;
	private JTextArea msg;
	private Scanner s;


	public Servidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setVisible(true);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Mensagem");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 28, 450, 16);
		contentPane.add(label);
		
		msg = new JTextArea();
		msg.setBounds(15, 48, 420, 164);
		contentPane.add(msg);		
	}

	@Override
	public void run() {
		iniciarServidor();
	}

	public void iniciarServidor() {
		try {
			servidor = new ServerSocket(12345);
		    Socket clienteConexao = servidor.accept();
		    
		    s = new Scanner(clienteConexao.getInputStream());
		    	
		   	while (s.hasNext()) {
			    JOptionPane.showMessageDialog(null, "Mensagem recebida!");
		    	msg.setText(msg.getText().concat(s.nextLine() + "\n"));		    
	    	}
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
