import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Socket conexaoServidor;

	/**
	 * Create the frame.
	 */
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
		
		textField = new JTextField();
		textField.setBounds(155, 25, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JTextArea msg = new JTextArea();
		msg.setBounds(15, 79, 420, 164);
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
		btnEnviar.setBounds(152, 245, 117, 29);
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
