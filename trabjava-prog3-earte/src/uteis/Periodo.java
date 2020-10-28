package uteis;

import java.io.*;

public class Periodo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int ano;
	private String semestre;	
	
	public Periodo(){
        
    }
	
	public void setAno(int entrada) {
		this.ano = entrada;
	}	
	public int getAno() {
		return this.ano;
	}
	
	public void setSemestre(String entrada) {
		this.semestre = entrada;
	}
	public String getSemestre() {
		return this.semestre;
	}
	
	public String getNomePeriodo() {
		String p = String.format("%d/%s", ano, semestre);
		return p;
	}		
	
	public Periodo getPeriodo() {
		return this;
	}
}
