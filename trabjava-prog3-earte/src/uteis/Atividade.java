package uteis;
import java.io.*;
import java.util.*;

public class Atividade implements Serializable {
	Scanner leitor = new Scanner(System.in);
	
	private String nomeAtiv;
	private boolean ehSincrona;
	private String tipodeAtiv;	
	private Disciplina d;
	private int numero;
	private Map<Long, Float> avaliacoes = new HashMap<Long, Float>();
	//private Map<String, String> materiais = new HashMap<String, String>(); 
	//private int qtdAlunos;
	//private String conteudoAtiv;
	//private Data dataAtiv = new Data();
	//private int cargaHoraria;
	
	public Atividade() {
		
	}
	
	public void setNum(int n) {
		this.numero = n;
	}
	
	public void putAvaliacao(Long matricula, Float nota) {
		avaliacoes.put(matricula, nota);
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
	
	public boolean getSincronia() {
		return ehSincrona;
	}
	public void setSincrona(boolean b) {
		this.ehSincrona = b;
	}
	
	public void newAtividade() {
		System.out.println("\nInforme o nome da atividade:\n");
    	String nomeAtividade = leitor.nextLine();
    	//nomeAtividade = leitor.nextLine();
    	setNomeAtiv(nomeAtividade);
    	
    	System.out.println("\nInforme o tipo da atividade:\n");
    	String tipoAtividade = leitor.next();            	
    	setTipoAtiv(tipoAtividade);
	}
	
	public void setTipoAtiv(String entrada) {
		this.tipodeAtiv = entrada;
		switch(tipodeAtiv.toLowerCase()) {
		case "aula":                    			
			Aula aula = new Aula();
			aula.registerAula();	
			break;
		case "estudo":
			Estudo estudo = new Estudo();
			estudo.registerEstudo();			
			break;
		case "trabalho":
			Trabalho trabalho = new Trabalho();
			trabalho.registerTrabalho();
			break;
		case "prova":
			Prova prova = new Prova();
			prova.registerProva();
			break;
		default:
			break;
		}
	}
	public String getTipoAtiv() {
		return this.tipodeAtiv;		
	}
}
