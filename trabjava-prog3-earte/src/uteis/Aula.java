package uteis;
import java.io.*;
import java.util.*;


public class Aula extends Atividade implements Serializable {
	private Data dataAula = new Data();
	
	public Aula() {
		
	}
	
	public void setDataAula(String data, String horario) {
		dataAula.setData(data);
		dataAula.setHorario(horario);		
	}
	
	public void registerAula() {
		System.out.println("Favor informar data da atividade (dd/MM/aaaa):");
		String data_aula;
		data_aula = leitor.next();
		                    			
		System.out.println("Favor informar horário da atividade (hh:mm):");
		String horario_aula;
		horario_aula = leitor.next();
		
		setDataAula(data_aula, horario_aula);
		
		System.out.println("\nNova aula registrada\n");
		setSincrona(true);
	}
}
