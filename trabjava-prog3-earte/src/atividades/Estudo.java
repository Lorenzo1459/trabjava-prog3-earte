package atividades;
import java.io.*;
import java.util.*;

public class Estudo extends Atividade implements Serializable {
	private List<String> materiais = new ArrayList<String>(); 
	
	public Estudo() {
		
	}
	
	public void putMaterial(String estudo) {
		materiais.add(estudo);
	}
	
	public void registerEstudo(String material) {
		//COLOCAR NOME,URL SEPARADOS
		super.setTipoAtividade("E");
		String nomeMaterial = material;					
		putMaterial(nomeMaterial);   
		setSincrona(false);
		System.out.println("Novo estudo registrado");
	}
}
