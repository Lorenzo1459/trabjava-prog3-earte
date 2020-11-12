package uteis;

import java.io.*;
import java.util.*;

public class Estudante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long matricula;
	private String nome;
	private float numDisc = 0;
	private ArrayList<String> p = new ArrayList<>();
	private Map<String, Disciplina> disciplinasDoEstudante = new HashMap<String, Disciplina>();
	int qtAtivs = 0;
	
	
	public Estudante(){
        
    }
	
	public void putDisciplinaEstudante(Disciplina d) {
		disciplinasDoEstudante.put(d.getCodigo(), d);
	}
	
	public float getQtAvalicaoPorDisc(Estudante e) {
		qtAtivs = 0;
		for(Map.Entry<String, Disciplina> aux : disciplinasDoEstudante.entrySet()) {
			qtAtivs += aux.getValue().getAvaliacoesEstudante(e);
		}
		return (float)qtAtivs/numDisc;
	}
	
	public float mediaNotasAluno(Estudante e) {
		float soma = 0;
		for(Map.Entry<String, Disciplina> aux : disciplinasDoEstudante.entrySet()) {
			soma += aux.getValue().somaNotasAtivs(e);
		}
		if(qtAtivs != 0)
			return (float)soma/qtAtivs;
		return 0;
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
