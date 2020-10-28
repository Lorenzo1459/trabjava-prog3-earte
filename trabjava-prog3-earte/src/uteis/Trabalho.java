package uteis;
import java.io.*;
import java.util.*;

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
	
	public void registerTrabalho() {
		System.out.println("Favor informar data da atividade (dd/MM/aaaa):");
		String data_trab;
		data_trab = leitor.next();
		setDataTrabalho(data_trab);		
		
		System.out.println("Favor informar a carga horaria estimada para a atividade: (horas)");
		int cargaHor = leitor.nextInt();
		setCargaHoraria(cargaHor);
		
		System.out.println("\nNovo trabalho registrado\n");
		setSincrona(false);
		System.out.println("\nInforme o numero de integrantes do grupo\n");
		int qtdAluno = leitor.nextInt();
		setQtdAlunos(qtdAluno);
	}

}
