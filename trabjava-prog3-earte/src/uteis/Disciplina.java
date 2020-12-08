package uteis;
import java.io.*;
import java.util.*;

import atividades.Atividade;

public class Disciplina implements Serializable {
	private String codigo;
	private String nomediscip;
	private Periodo p;
	private Docente prof;
	private float numSincronas = 0;
	private float numAssincronas = 0;
	private float totalAtivs = numSincronas + numAssincronas;
	private Integer seq = 0;
	private Map<Long , Estudante> alunosDisc = new HashMap<Long, Estudante>();
	private Map<Integer, Atividade> ativsDisc = new HashMap<Integer, Atividade>();
	private int numEstudantes = 0;	
	
    public Disciplina(){
        
    }     
    
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigo() {
		return this.codigo;
	}		
	
	public void setNomediscip(String nomediscip) {
		this.nomediscip = nomediscip;
	}
	public String getNomediscip() {
		return this.nomediscip;
	}
	
	public Periodo getPeriodoDisc() {
		return this.p.getPeriodo();
	}	
	
	public void setPeriodoDisc(Periodo per) {
		this.p = per;
	}
	
	public void setDocenteDisc(Docente d) {
		d.setNumDisc();
		this.prof = d;
	}
	public Docente getDocenteDisc() {
		return this.prof;
	}
	
	
	public void putEstudantesDisc(Long mat, Estudante e, Disciplina d) {
		numEstudantes++;
		e.increaseDisc();
		e.addNomesPeriodos(this.getPeriodoDisc());
		alunosDisc.put(mat, e);
		e.putDisciplinaEstudante(d);
	}
	
	public int getNumEstudantes() {
		return this.numEstudantes;
	}
	public int getNumAtividades() {
		return this.seq;
	}
	
	public void imprimeEstudantes() {
		System.out.println(this.nomediscip);
		for(Map.Entry<Long, Estudante> aux : alunosDisc.entrySet()) {
        	System.out.println(aux.getKey());                    	
        }
	}
	
	public void putAtividade(Atividade a) {
		prof.setNumAtiv();
		seq++;
		if(a.getSincrona() == true)
			this.numSincronas++;
		else if(a.getSincrona() == false)
			this.numAssincronas++;			
		a.setNum(seq);
		ativsDisc.put(seq, a);
	}
	
	public Map<Integer, Atividade> getAtivsList() {
		return ativsDisc;
	}
	
	public Map<Long , Estudante> getAlunosList(){
		return alunosDisc;
	}
	
	public int jaMatriculado(Map<Long, Estudante> mapa, Long s) {
		for(Map.Entry<Long, Estudante> aux : mapa.entrySet()) {
			if(java.lang.Long.compare(aux.getKey(), s) == 0) {	
				System.out.println("Estudante já matriculado");
				System.out.println("Matricula = "+ aux.getKey());
				return 1;
			}
        }
		return 0;
	}
	
	public float getNumAssincDisc() {
		return numAssincronas;
	}
	public float getNumSincDisc() {
		return numSincronas;
	}
	
	public float getPerSinc() {
		float total = numAssincronas + numSincronas;
		if(total != 0)
			return (numSincronas/total)*100;
		return 0;
	}
	public float getPerAssinc() {
		float total = numAssincronas + numSincronas;
		if(total != 0)
			return (numAssincronas/total)*100;
		return 0;
	}
	
	public float getSomaAtivsDisc() {
		float soma = 0;
		for(Map.Entry<Integer, Atividade> aux : ativsDisc.entrySet())
			soma += aux.getValue().getSomaNotas();
		return soma;
	}
	
	public int getQtAtivsDisc() {
		int qtAtivs = 0;
		for(Map.Entry<Integer, Atividade> aux : ativsDisc.entrySet())
		{
			if(aux.getValue().getqtNotas() != 0)
				qtAtivs += aux.getValue().getqtNotas();
		}
		return qtAtivs;
	}
	
	public int getAvaliacoesEstudante(Estudante e) {
		int cont = 0;
		for(Map.Entry<Integer, Atividade> aux : ativsDisc.entrySet())
		{
			cont += aux.getValue().getQtNotasAluno(e);
		}
		return cont;
	}
	
	public float somaNotasAtivs(Estudante e) {
		float soma = 0;
		for(Map.Entry<Integer, Atividade> aux : ativsDisc.entrySet())
		{
			soma += aux.getValue().somaNotasAluno(e);
		}
		return soma;
	}
}
