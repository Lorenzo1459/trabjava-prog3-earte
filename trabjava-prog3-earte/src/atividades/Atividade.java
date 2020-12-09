package atividades;
import java.io.*;
import java.util.*;

import uteis.Disciplina;
import uteis.Estudante;

public abstract class Atividade implements Serializable {

	static Scanner leitor = new Scanner(System.in);
	
	private String nomeAtiv;
	private boolean ehSincrona;
	private String tipodeAtiv;	
	private Disciplina d;
	private int numero;
	private Map<Long, Float> avaliacoes = new HashMap<Long, Float>();	
	private float somaNotas = 0;
	private int qtNotas = 0;
	
	public Atividade() {
		
	}
	
	public void setTipoAtividade(String s) {
		this.tipodeAtiv = s;
	}
	
	public void setNum(int n) {
		this.numero = n;
	}
	
	public void putAvaliacao(Long matricula, Float nota) {
//		if(this.tipodeAtiv.equalsIgnoreCase("T")
//				|| this.tipodeAtiv.equalsIgnoreCase("P")) {
//			avaliacoes.put(matricula, nota);		
//		}
//		else if(this.tipodeAtiv.equalsIgnoreCase("E"))
//			System.out.println("Não é possível avaliar essa atividade, visto que ESTUDO não é uma atividade avaliativa.");
//		else if(this.tipodeAtiv.equalsIgnoreCase("A"))
//			System.out.println("Não é possível avaliar essa atividade, visto que AULA não é uma atividade avaliativa.");
		avaliacoes.put(matricula, nota);	
		somaNotas += nota;
		qtNotas++;
	}
	
	public float getSomaNotas() {
		return somaNotas;
	}
	
	public int getqtNotas() {
		return qtNotas;
	}
	
	public int getQtNotasAluno(Estudante e) {
		int cont = 0;
		for(Map.Entry<Long, Float> aux : avaliacoes.entrySet())
		{
			if(java.lang.Long.compare(aux.getKey(), e.getMatricula())==0)
				cont++;
		}
		return cont;
	}
	
	public float somaNotasAluno(Estudante e) {
		float soma = 0;
		for(Map.Entry<Long, Float> aux : avaliacoes.entrySet())
		{
			if(java.lang.Long.compare(aux.getKey(), e.getMatricula())==0)
				soma += aux.getValue().floatValue();
		}
		return soma;
	}
	
	public int getQtAvaliacoes() {
		return 0;
	}
	
	public void showAvaliacoes() {
		for(Map.Entry<Long, Float> aux : avaliacoes.entrySet()) {
        	System.out.println(aux.getKey());                    	
        }
	}
	
	public int existeAvaliacao(Long matricula, int ativ) {
		for(Map.Entry<Long, Float> aux : avaliacoes.entrySet()) {
			if(java.lang.Long.compare(aux.getKey(), matricula) == 0) {								       
				if(ativ == numero) {
					return 1;
				}
			}
        }
//		System.out.println("Estudante nao existente");
		return 0;
	}	

	public String getNomeAtiv() {
		return nomeAtiv;
	}
	public void setNomeAtiv(String nomeAtiv) {
		this.nomeAtiv = nomeAtiv;
	}
	
	public boolean getSincrona() {
		return ehSincrona;
	}
	public void setSincrona(boolean b) {
		this.ehSincrona = b;
	}
	
	public Atividade newAtividade(String[] ativs, Atividade a) {	
    	String nomeAtividade = ativs[1];    	
    	setNomeAtiv(nomeAtividade);    	     	
    	a = setAtiv(ativs, a);
    	return a;
	}	
	
	public Atividade setAtiv(String[] linha_ativs, Atividade a) {		
		this.tipodeAtiv = linha_ativs[2];
		switch(tipodeAtiv) {
		case "A":	
			a = new Aula();
			((Aula) a).registerAula(linha_ativs[3], linha_ativs[4]);
			break;
		case "E":
			a = new Estudo();
			((Estudo) a).registerEstudo(linha_ativs[5]);
			break;
		case "T":
			a = new Trabalho();
			((Trabalho) a).registerTrabalho(linha_ativs[3],linha_ativs[7], linha_ativs[6]);
			break;
		case "P":
			a = new Prova();
			((Prova) a).registerProva(linha_ativs[3], linha_ativs[4], linha_ativs[5]);
			break;
		default:
			break;
		}
		return a;
	}
	public String getTipoAtiv() {
		return this.tipodeAtiv;		
	}
}
