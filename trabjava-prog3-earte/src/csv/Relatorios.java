package csv;
import java.io.*;
import java.util.*;

import main.principal;
import uteis.Disciplina;
import uteis.Docente;
import uteis.Estudante;

public class Relatorios implements Serializable{
	
	public Relatorios() {
		
	}
	
	public void relatorioDisc(Map<String, Disciplina> mapa, String s, File file) {
			Escritor_csv writer = new Escritor_csv();						
			//A-Z Disciplinas
			
			for(Map.Entry<String, Disciplina> aux : mapa.entrySet()) {
				if(aux.getValue().getPeriodoDisc().getNomePeriodo().equals(s)) {
					String periodo = aux.getValue().getPeriodoDisc().getNomePeriodo();
					String cod = aux.getValue().getCodigo();					
					String nome = aux.getValue().getNomediscip();
					String docente = aux.getValue().getDocenteDisc().getNomeDocente();
					String email = aux.getValue().getDocenteDisc().getLogin() + "@ufes.br";
					Integer num_estud = aux.getValue().getNumEstudantes();
					Integer num_ativs = aux.getValue().getNumAtividades();
										
					writer.visao_geral(periodo, cod, nome, docente, email, num_estud, num_ativs, file);
				}			
			}	
	}
	
	public void relatorioDocente(Map<String, Docente> mapa, File file2) {	
			Escritor_csv writer = new Escritor_csv();
			//Z-A nome docente
			
			for(Map.Entry<String, Docente> aux : mapa.entrySet()) {		
				String nome = aux.getValue().getNomeDocente();
				Integer num_disc = aux.getValue().getNumDisc();
				Integer num_periodos_dif = aux.getValue().contaPeriodosDif();
				Float percentSinc = aux.getValue().percentSinc();
				Float percentAssinc = aux.getValue().percentAssinc();
				Float mediaAtiv_Disc = aux.getValue().getAtivPorDisc();
				Float mediaNotas = aux.getValue().getMediaNotasDocente();
								
				writer.docentes(nome, num_disc, num_periodos_dif, mediaAtiv_Disc, percentSinc, percentAssinc, mediaNotas, file2);
			}			
	}	
	
	public void relatorioEstudante(Map<Long, Estudante> mapa, File file3) {
		Escritor_csv writer = new Escritor_csv();
		//99-0 avaliações && A-Z nome
		
		for(Map.Entry<Long, Estudante> aux : mapa.entrySet()) {
			Long mat = aux.getValue().getMatricula();
			String nome = aux.getValue().getNomeEstudante();
			Float mediaDisc_per = aux.getValue().mediaDiscPeriodo();
			Float mediaAv_disc = aux.getValue().getQtAvalicaoPorDisc(aux.getValue());
			Float mediaNotas = aux.getValue().mediaNotasAluno(aux.getValue());
						
			writer.estudantes(mat, nome, mediaDisc_per, mediaAv_disc, mediaNotas, file3);
		}
	}
	
	public void relatorioDiscDocente(Docente d,Map<String, Disciplina> mapa, File file4) {
		Escritor_csv writer = new Escritor_csv();
		for(Map.Entry<String, Disciplina> aux : mapa.entrySet()) {
			if(aux.getValue().getDocenteDisc().getLogin().equals(d.getLogin()))
			{
				String docente = aux.getValue().getDocenteDisc().getLogin();
				String periodo = aux.getValue().getPeriodoDisc().getNomePeriodo();
				String codigo = aux.getValue().getCodigo();
				String disc = aux.getValue().getNomediscip();
				float percentSinc = aux.getValue().getPerSinc();
				float percentAssinc = aux.getValue().getPerAssinc();
				
				writer.disciplinas(docente,periodo,codigo,disc,percentSinc,percentAssinc,file4);
			}
		}
	}
	
}
