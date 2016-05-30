import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private File selectedFile;
	


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
		

		
		destinatario = new JTextField();
		destinatario.setBounds(155, 25, 134, 28);
		contentPane.add(destinatario);
		destinatario.setColumns(10);
		
		JLabel lblDestinatrio = new JLabel("Destinat�rio");
		lblDestinatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestinatrio.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblDestinatrio.setBounds(0, 6, 450, 16);
		contentPane.add(lblDestinatrio);
		
		
		JButton btnEnviar = new JButton("Enviar");
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMsg(selectedFile);
			}

			private void enviarMsg(File selectedFile) {
				
				try {

					BufferedImage bimg = ImageIO.read(selectedFile); 
					
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			        ImageIO.write(bimg, "jpg", byteArrayOutputStream);

			        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
			        
			        OutputStream outputStream = conexaoServidor.getOutputStream();
			        outputStream.write(size);
			        outputStream.write(byteArrayOutputStream.toByteArray());
			        outputStream.flush();

					System.out.println("imagem enviada");
			        
				} catch (IOException e) {					
					e.printStackTrace();
				}
				
				
			}
		});
		btnEnviar.setBounds(152, 239, 117, 29);
		contentPane.add(btnEnviar);
		this.setVisible(true);
		
		JFileChooser chooser = new JFileChooser();
		int returnValue = chooser.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
	          anexo.setText(selectedFile.getName()+" anexado!");
	          conectarServidor();
	    }
		
		
	}
	
	public void conectarServidor() {
		try {
			conexaoServidor = new Socket("127.0.0.1",12345);
			System.out.println(">>Cliente conectado com Servidor<<");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
