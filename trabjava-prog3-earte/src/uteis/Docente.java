package uteis;

import java.io.*;
import java.util.*;


public class Docente implements Serializable {
	private String login;
	private String nome;
	private String paginaWeb;
	private int numDisc = 0;
	private int numAtiv = 0;
	private float mediaAtivPorDisc = 0;
	
	private Map<String, Disciplina> disciplinasDoDocente = new HashMap<String, Disciplina>();
	
	
	public Docente(){
        
    }
	
	public void putDisciplinaDocente(Disciplina d) {
		disciplinasDoDocente.put(d.getCodigo(), d);
	}
	
	public void printPeriodosDiscDocente() {		
		for(Map.Entry<String, Disciplina> aux : disciplinasDoDocente.entrySet()) {
			System.out.println(aux.getValue().getPeriodoDisc().getNomePeriodo());
		}
	}
	
	public void setLogin(String entrada) {
		this.login = entrada;
	}
	public String getLogin() {
		return this.login;
	}
	
	public void setNomeDocente(String entrada) {
		this.nome = entrada;
	}
	public String getNomeDocente() {
		return this.nome;
	}
	
	public void setPaginaWeb(String entrada) {
		this.paginaWeb = entrada;
	}
	public String getPaginaWeb() {
		return this.paginaWeb;
	}
	
	public int setNumDisc() {
		return this.numDisc++;
	}
	
	public int getNumDisc() {
		return this.numDisc;
	}
	
	public int setNumAtiv() {
		return this.numAtiv++;
	}
	
	public int getNumAtiv() {
		return this.numAtiv;
	}
	
	public float getAtivPorDisc() {
		return (float)numAtiv/numDisc;
	}
}
