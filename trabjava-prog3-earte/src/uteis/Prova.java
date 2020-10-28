package uteis;
import java.io.*;
import java.util.*;

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
	
	public void registerProva() {
		System.out.println("Favor informar data da atividade (dd/MM/aaaa):");
		String data_prova;
		data_prova = leitor.next();
		
		System.out.println("Favor informar horário da atividade (hh:mm):");
		String horario_prova;
		horario_prova = leitor.next();
		
		setDataProva(data_prova, horario_prova);
		
		System.out.println("\nNova prova registrada\n");
		setSincrona(true);
		System.out.println("\nInforme o conteudo que sera cobrado\n");
		String conteudo = leitor.nextLine();
		conteudo = leitor.nextLine();
		setConteudoProva(conteudo);
	}

}
