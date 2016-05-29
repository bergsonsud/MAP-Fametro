import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Cliente extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField destinatario;
	private Socket conexaoServidor;
	private JTextArea msg;


	public Cliente() {
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
		
		destinatario = new JTextField();
		destinatario.setBounds(155, 25, 134, 28);
		contentPane.add(destinatario);
		destinatario.setColumns(10);
		
		msg = new JTextArea();
		msg.setBounds(12, 79, 420, 149);
		contentPane.add(msg);
		
		JLabel lblDestinatrio = new JLabel("Destinatário");
		lblDestinatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestinatrio.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblDestinatrio.setBounds(0, 6, 450, 16);
		contentPane.add(lblDestinatrio);
		
		JButton btnEnviar = new JButton("Enviar");
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMsg();
			}

			private void enviarMsg() {
				
				try {
					PrintStream saida = new PrintStream(conexaoServidor.getOutputStream());
					saida.println(msg.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnEnviar.setBounds(152, 239, 117, 29);
		contentPane.add(btnEnviar);
		this.setVisible(true);
		
		conectarServidor();
	}
	
	public void conectarServidor() {
		try {
			conexaoServidor = new Socket("127.0.0.1",12345);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
