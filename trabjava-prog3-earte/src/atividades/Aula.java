package atividades;
import java.io.*;
import java.util.*;

import uteis.Data;


public class Aula extends Atividade implements Serializable {
	private Data dataAula = new Data();
	//private String tipo = "A";
	
	public Aula() {
		
	}
	
	public void setDataAula(String data, String horario) {
		dataAula.setData(data);
		dataAula.setHorario(horario);		
	}
	
	public void registerAula(String data, String horario) {
		super.setTipoAtividade("A");
		String data_aula = data;
		String horario_aula = horario;		
		setDataAula(data_aula, horario_aula);		
		setSincrona(true);
		//System.out.println("Nova aula registrada");
	}
}
