import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Digite um número: ");
		int num = new Scanner(System.in).nextInt();


		try {

			File f = new File("C:/numeros.txt");
			FileWriter fw = new FileWriter(f);

			String decimal = Integer.toString( num );
			String binario = Integer.toString( num, 2 );
			String octal = Integer.toString( num, 8 );
			String hexa = Integer.toString( num, 16 );

			fw.write("Número escolhido para conversão: "+String.valueOf(num));
			fw.append(System.lineSeparator());	
			fw.append(System.lineSeparator());	

			fw.write("Decimal: "+String.valueOf(decimal));
			fw.append(System.lineSeparator());					
			fw.write("Octal: "+octal);
			fw.append(System.lineSeparator());	
			fw.write("Binario: "+binario);
			fw.append(System.lineSeparator());
			fw.write("Hexadecimal: "+hexa);
			fw.close();
		}
		catch (Exception exc) {
			System.out.println(exc);
		}

	}

}
