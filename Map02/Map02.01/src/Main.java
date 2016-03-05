import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub



		
		File writeFile = new File("C:/saida.txt");
		System.out.println("Escreva algo:");
		String entrada = new Scanner(System.in).nextLine();

		try {
			FileWriter fw = new FileWriter(writeFile);
			fw.write(entrada);
			fw.close();
		}
		catch (Exception exc) {
			System.out.println(exc);
		}



	}

}
