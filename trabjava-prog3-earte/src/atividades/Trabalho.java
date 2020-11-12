package atividades;
import java.io.*;
import java.util.*;

import uteis.Data;

public class Trabalho extends Atividade implements Serializable {
	//private Map<Long, Float> avaliacoes = new HashMap<Long, Float>();
	private Data dataTrab = new Data();
	private int qtdAlunos;
	private int cargaHoraria;
	
	public Trabalho() {
		
	}
	
	public void setQtdAlunos(int qtd) {
		this.qtdAlunos = qtd;
	}
	
	public int getQtdAlunos() {
		return this.qtdAlunos;
	}
	
	public void setDataTrabalho(String data) {
		dataTrab.setData(data);
	}
	
	public void setCargaHoraria(int n) {
		this.cargaHoraria = n;
	}
	
	public void registerTrabalho(String data, String cargahor, String qtdaluno) {
		super.setTipoAtividade("T");
		String data_trab = data;
		setDataTrabalho(data_trab);	
		int cargaHor = Integer.parseInt(cargahor);
		setCargaHoraria(cargaHor);
		int qtdAluno = Integer.parseInt(qtdaluno);
		setQtdAlunos(qtdAluno);
		setSincrona(false);
		System.out.println("Novo trabalho registrado");
	}

}
