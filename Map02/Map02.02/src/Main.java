import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		JFileChooser c = new JFileChooser();
		c.showOpenDialog(c);
		File file = c.getSelectedFile();
		
		try {
		    FileReader fr = new FileReader(file);
		    BufferedReader bw = new BufferedReader(fr); 
		    System.out.println(bw.readLine());		        
		    bw.close();
		    fr.close();
		}
		catch (Exception exc) {
		   System.out.println(exc);
		}
		
		
	}

}
