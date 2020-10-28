package uteis;

import java.io.*;
import java.util.*;

public class Estudante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long matricula;
	private String nome;
	private float numDisc = 0;
	private ArrayList<String> p = new ArrayList<>();
	
	
	public Estudante(){
        
    }
	
	public void increaseDisc() {
		this.numDisc++;
	}
	public void addNomesPeriodos(Periodo per) {		
		String nome = per.getNomePeriodo();
		if( !p.contains(nome.toLowerCase()) )
			p.add(nome.toLowerCase());		
	}	
	
	public float mediaDiscPeriodo() {		
		return numDisc/p.size();
	}
	
	public void setMatricula(long entrada) {
		this.matricula = entrada;
	}
	public long getMatricula() {
		return this.matricula;
	}
	
	public void setNomeEstudante(String entrada) {
		this.nome = entrada;
	}
	public String getNomeEstudante() {
		return this.nome;
	}
		
}
