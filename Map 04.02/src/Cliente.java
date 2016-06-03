import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Cliente extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField destinatario;
	private Socket conexaoServidor;
	private JLabel anexo;
	private File arquivo = null;
	private JFileChooser chooser = new JFileChooser();
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Scanner s;
	private Boolean iniciado;


	public Cliente() {

		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		anexo = new JLabel("");
		anexo.setHorizontalAlignment(SwingConstants.CENTER);
		anexo.setBounds(0, 59, 450, 16);
		contentPane.add(anexo);

		destinatario = new JTextField("127.0.0.1:12345");
		destinatario.setBounds(155, 25, 134, 28);
		contentPane.add(destinatario);
		destinatario.setColumns(10);

		JLabel lblDestinatrio = new JLabel("Destinatario");
		lblDestinatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestinatrio.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblDestinatrio.setBounds(0, 6, 450, 16);
		contentPane.add(lblDestinatrio);	

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				if (arquivo != null){
					enviarMsg(arquivo);
				}else{
					JOptionPane.showMessageDialog(null, "Selecione alguma imagem para envio!");
					validarImagem();
				}
			}

			private void enviarMsg(File selectedFile) {		


				try {
					iniciado = conectarServidor();

					if (!iniciado) {
						return;
					}		

					out = new ObjectOutputStream(conexaoServidor.getOutputStream());
					in = new ObjectInputStream(conexaoServidor.getInputStream());


					out.writeObject(selectedFile);
					out.flush();

					s = new Scanner(conexaoServidor.getInputStream());

					while(s.hasNextLine()){			
						JOptionPane.showMessageDialog(null, s.nextLine());
					}

					out.close();
					in.close();



				} catch (UnknownHostException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}


			}
		});
		btnEnviar.setBounds(237, 165, 117, 29);
		contentPane.add(btnEnviar);

		JButton btnAnexar = new JButton("Anexar");
		btnAnexar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validarImagem();
			}
		});

		btnAnexar.setBounds(86, 165, 117, 29);
		contentPane.add(btnAnexar);
		this.setVisible(true);	
	}

	private void validarImagem() {		
		int returnValue = chooser.showOpenDialog(null);		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			arquivo = chooser.getSelectedFile();
			anexo.setText(arquivo.getName()+" anexado!");          
		}

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
