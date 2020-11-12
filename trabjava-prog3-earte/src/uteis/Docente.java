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
	private HashSet<String> semDuplicata = new HashSet<String>();
		
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
	
	public int contaPeriodosDif() {
		for(Map.Entry<String, Disciplina> aux : disciplinasDoDocente.entrySet()) {
			semDuplicata.add(aux.getValue().getPeriodoDisc().getNomePeriodo());		
		}
		return semDuplicata.size();
	}
	
	public float getMediaNotasDocente() {
		float soma = 0;
		int cont = 0;
		for(Map.Entry<String, Disciplina> aux : disciplinasDoDocente.entrySet()) {
			soma += aux.getValue().getSomaAtivsDisc();
			cont += aux.getValue().getQtAtivsDisc();
		}
		if(cont !=0)
			return (float)(soma/=cont);
		else
			return 0;

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
	
	public float percentSinc() {
		float numSinc = 0;
		float numAssinc = 0;
		for(Map.Entry<String, Disciplina> aux : disciplinasDoDocente.entrySet()) {
			numAssinc += aux.getValue().getNumAssincDisc();
			numSinc += aux.getValue().getNumSincDisc();			
		}
		float totalAtivs = numAssinc + numSinc;
		if(totalAtivs != 0)
		{		
			return numSinc/totalAtivs * 100;
		}
		else
		{
			return numSinc;
		}
	}
	
	public float percentAssinc() {
		float numSinc = 0;
		float numAssinc = 0;
		for(Map.Entry<String, Disciplina> aux : disciplinasDoDocente.entrySet()) {
			numAssinc += aux.getValue().getNumAssincDisc();
			numSinc += aux.getValue().getNumSincDisc();			
		}
		float totalAtivs = numAssinc + numSinc;
		if(totalAtivs != 0)
		{		
			return numAssinc/totalAtivs * 100;
		}
		else
		{
			return numAssinc;
		}
	}
}
