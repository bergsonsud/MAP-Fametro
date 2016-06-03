import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Servidor extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private ServerSocket servidor;
	private Scanner s;
	private JPanel contentPane;
	private JTextArea msg;


	public Servidor(){
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);


		JLabel label = new JLabel("Mensagem");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 11, 434, 16);
		contentPane.add(label);

		msg = new JTextArea();
		msg.setBounds(10, 33, 414, 215);
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		contentPane.add(msg);	

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9,32,413,214);
		contentPane.add(scrollPane);		
		scrollPane.setViewportView(msg);

		this.setVisible(true);
	}

	public void iniciaServidor(){

		while(true){	
			try {

				servidor = new ServerSocket(12345);
				System.out.println(">>Servidor Iniciado<<");		

				Socket clienteConexao = servidor.accept();
				s = new Scanner(clienteConexao.getInputStream());
				PrintStream saida = new PrintStream(clienteConexao.getOutputStream());

				msg.setText(s.nextLine()+"\n");


				saida.println("Mensagem recebida");
				servidor.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}


	@Override
	public void run() {
		iniciaServidor();		
	}

}
