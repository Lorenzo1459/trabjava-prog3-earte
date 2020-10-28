package uteis;
import java.io.*;
import java.util.*;

public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager m = new Manager();
		Scanner leitor = new Scanner(System.in);
		
		m.DisplayMenu();
		if(m.getSignal() == 1) { // salvar
			try {
				System.out.println("Com qual nome deseja salvar o arquivo:");
				String entrada = leitor.next();
				FileOutputStream fileOutput = new FileOutputStream(entrada);
				ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
				objOutput.writeObject(m);
				objOutput.close();
				System.out.println("Salvo com sucesso.");
				m.DisplayMenu();
			}catch(Exception e) {
				System.out.println("Erro de I/O ");
				m.DisplayMenu();
			}
			
		}
		
		if(m.getSignal() == 2) { //carregar
			try {					
				System.out.println("Favor informar o arquivo a ser carregado:");
				String entrada = leitor.next();
				FileInputStream fileInput = new FileInputStream(entrada);
				ObjectInputStream objInput = new ObjectInputStream(fileInput);
				m = (Manager) objInput.readObject();
				System.out.println("Carregado com sucesso.");			
				objInput.close();
				m.DisplayMenu();
			}catch(Exception e) {
				System.out.println("Erro de I/O");
				m.DisplayMenu();
			}
		}
		
		
		
	}

}
