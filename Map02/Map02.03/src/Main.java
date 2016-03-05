import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFileChooser c = new JFileChooser();
		c.showOpenDialog(c);
		File file = c.getSelectedFile();
		
		
		try {
			String line;
		    FileReader fr = new FileReader(file);
		    File f = new File("C:/saida.txt");
		    
		    BufferedReader br = new BufferedReader(fr);
		    FileWriter fw = new FileWriter(f);
		    
			while ((line = br.readLine()) != null) {
				fw.append(line);
				fw.append(System.lineSeparator());				
			}
		    
		    br.close();
		    fr.close();
		    fw.close();
		}
		catch (Exception exc) {
		   System.out.println(exc);
		}

	}

}
