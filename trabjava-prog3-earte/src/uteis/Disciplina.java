package uteis;
import java.io.*;
import java.util.*;

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
	
	
	public void putEstudantes(Long mat, Estudante e) {
		numEstudantes++;
		e.increaseDisc();
		e.addNomesPeriodos(this.getPeriodoDisc());
		alunosDisc.put(mat, e);
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
		if(a.getSincronia()==false)
			this.numAssincronas++;
		if(a.getSincronia()==true)
			this.numSincronas++;
		a.setNum(seq);
		ativsDisc.put(seq, a);
	}
	
	public Map<Integer, Atividade> getAtivsList() {
		return ativsDisc;
	}
	
	public void percentAssinc() {
		float percent = numAssincronas/totalAtivs;
		percent*=100;
		System.out.println("Percentual de atividades Síncronas = " + (100f-percent));
		System.out.println("Percentual de atividades Assíncronas = " + percent);
	}
}
