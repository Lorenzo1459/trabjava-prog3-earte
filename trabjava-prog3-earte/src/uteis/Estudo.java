package uteis;
import java.io.*;
import java.util.*;

public class Estudo extends Atividade implements Serializable {
	private Map<String, String> materiais = new HashMap<String, String>(); 
	
	public Estudo() {
		
	}
	
	public void putMaterial(String nome, String url) {
		materiais.put(nome, url);
	}
	
	public void registerEstudo() {
		System.out.println("\nNovo estudo registrado\n");
		setSincrona(false);
		int option = 1;
		String nomeMaterial;
		String urlMaterial;
		while(option == 1) {
			System.out.println("1 - Cadastrar um novo material.");
			System.out.println("2 - Sair.");
			option = leitor.nextInt();
			if(option == 2) break;
			System.out.println("Informe o nome do Material");
			nomeMaterial = leitor.nextLine();
			nomeMaterial = leitor.nextLine();
			System.out.println("Informe o url do Material");
			urlMaterial = leitor.next();
			
			putMaterial(nomeMaterial, urlMaterial);                                    
		}		
	}
	

}
