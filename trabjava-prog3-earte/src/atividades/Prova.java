package atividades;
import java.io.*;
import java.util.*;

import uteis.Data;

public class Prova extends Atividade implements Serializable {
	private Data dataProva = new Data();
	private String conteudoProva;
	//private Map<Long, Float> avaliacoes = new HashMap<Long, Float>();
	
	public Prova() {
		
	}
	
	public void setDataProva(String data, String horario) {
		dataProva.setData(data);
		dataProva.setHorario(horario);
	}
	
	public void setConteudoProva(String conteudo) {
		this.conteudoProva = conteudo;
	}
	public String getConteudoProva() {
		return this.conteudoProva;
	}
	
	public void registerProva(String data, String horario, String cont) {
		super.setTipoAtividade("P");
		String data_prova = data;
		String horario_prova = horario;		
		setDataProva(data_prova, horario_prova);
		String conteudo = cont;
		setConteudoProva(conteudo);
		setSincrona(true);
		System.out.println("Nova prova registrada");
	}

}
