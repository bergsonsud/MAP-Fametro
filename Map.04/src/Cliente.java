import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Cliente extends JFrame{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField destinatario;
	private Socket conexaoServidor;
	private JTextArea JTAmsg;	
	private PrintStream saida;
	private List<String> lines;
	private StringBuffer msg = new StringBuffer();
	private Boolean iniciado = false;	
	private Scanner s;

	public Cliente(){		

		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mensagem");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 59, 450, 16);
		contentPane.add(lblNewLabel);

		destinatario = new JTextField("127.0.0.1:12345");
		destinatario.setBounds(155, 25, 134, 28);
		contentPane.add(destinatario);
		destinatario.setColumns(10);

		JTAmsg = new JTextArea();
		JTAmsg.setBounds(12, 79, 408, 133);
		JTAmsg.setLineWrap(true);
		JTAmsg.setWrapStyleWord(true);
		contentPane.add(JTAmsg);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11,78,407,132);
		contentPane.add(scrollPane);		
		scrollPane.setViewportView(JTAmsg);

		JLabel lblDestinatrio = new JLabel("Destinatário");
		lblDestinatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestinatrio.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblDestinatrio.setBounds(0, 6, 450, 16);
		contentPane.add(lblDestinatrio);

		JButton btnEnviar = new JButton("Enviar");		
		btnEnviar.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				prepararMsg();

			}

			private void prepararMsg() {				
				lines = Arrays.asList(JTAmsg.getText().split("\n"));							
				for (String string : lines) {
					msg.append(string);					
				}				
				enviarMsg(msg.toString());
			}

			private void enviarMsg(String msg) {

				try {	

					while (!iniciado){
						iniciado = conectarServidor();
					}	

					PrintStream saida = new PrintStream(conexaoServidor.getOutputStream());
					saida.println(msg);
					System.out.println("Mensagem enviada!");

					s = new Scanner(conexaoServidor.getInputStream());


					JOptionPane.showMessageDialog(null, s.nextLine());
					iniciado = false;


				} catch (IOException e) {

					e.printStackTrace();
				}				
			}

		});
		btnEnviar.setBounds(156, 220, 117, 29);
		contentPane.add(btnEnviar);	
		this.setVisible(true);

	}

	public Boolean conectarServidor() {

		Boolean serverIniciado = false;

		if (destinatario.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Defina IP e Porta do Servidor");
		}else{
			try {

				String[] parts = destinatario.getText().split(":");

				if (parts.length == 2){
					conexaoServidor = new Socket(parts[0],Integer.parseInt(parts[1]));				
					System.out.println(">>Cliente conectado com Servidor<<");
					saida= new PrintStream(conexaoServidor.getOutputStream());
					serverIniciado = true;
				}else{
					JOptionPane.showMessageDialog(null, "IP ou Porta incorretos");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return serverIniciado;
	}


}
