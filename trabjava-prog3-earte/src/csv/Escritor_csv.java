package csv;
import java.io.*;
import java.math.*;
import java.util.Map;

import uteis.Disciplina;

public class Escritor_csv {
	
	public Escritor_csv() {
		
	}
	
	public void visao_geral(String periodo, String codigo, String nome, String docente, String email, Integer numEstud, Integer numAtivs, File file) {		
		int existe = 0;			
		if(file.exists()) {
			existe = 1;
		}
		try {
			PrintWriter printwriter = new PrintWriter(new FileOutputStream(file,true));
			String cabecalho = String.format("Periodo;Codigo Disciplina;Disciplina;Docente Respons�vel;E-mail Docente;Qtd. Estudantes;Qtd. Atividades");
			String format = String.format("%s;%s;%s;%s;%s;%d;%d", periodo, codigo, nome, docente, email, numEstud, numAtivs);
			if(existe == 0)
				printwriter.println(cabecalho);
			printwriter.println(format);
			printwriter.flush();			
			printwriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public void docentes(String nome, Integer num_disc, Integer num_periodos_dif, Float mediaAtiv_Disc,Float percSinc, Float percAssinc, Float mediaNotas, File file2) {		
		int existe = 0;
			
		if(file2.exists()) {
			existe = 1;
		}
		try {
			PrintWriter printwriter = new PrintWriter(new FileOutputStream(file2,true));
			String cabecalho = String.format("Docente;Qtd. Disciplinas;Qtd. Periodos;M�dia Atividades/Disciplina;%c S�ncronas;%c Ass�ncronas;M�dia de Notas", 37, 37);
			String format = String.format("%s;%d;%d;%.1f;%d%c;%d%c;%.1f", nome, num_disc, num_periodos_dif, mediaAtiv_Disc, Math.round(percSinc), 37, Math.round(percAssinc), 37, mediaNotas);
			if(existe == 0)
				printwriter.println(cabecalho);
			printwriter.println(format);
			printwriter.flush();			
			printwriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public void estudantes(Long mat, String nome, Float mediaDisc_per, Float mediaAv_disc, Float mediaNotas, File file3) {		
		int existe = 0;
				
		if(file3.exists()) {
			existe = 1;
		}
		try {
			PrintWriter printwriter = new PrintWriter(new FileOutputStream(file3,true));
			String cabecalho = String.format("Matr�cula;Nome;M�dia Disciplinas/Per�odo;M�dia Avalia��es/Disciplina;M�dia Notas Avalia��es");
			String format = String.format("%d;%s;%.1f;%.1f;%.1f", mat, nome, mediaDisc_per, mediaAv_disc, mediaNotas);
			if(existe == 0)
				printwriter.println(cabecalho);
			printwriter.println(format);
			printwriter.flush();			
			printwriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public void disciplinas(String login, String per, String codigo, String nome, float perSinc,float perAssinc, File file4) {
		int existe = 0;			
		if(file4.exists()) {
			existe = 1;
		}
		try {
			PrintWriter printwriter = new PrintWriter(new FileOutputStream(file4,true));
			String cabecalho = String.format("Docente;Per�odo;C�digo;Nome;Qtd. Atividades;%c S�ncronas;%c Ass�ncronas;CH;Datas Avalia��es",37,37);
			String format = String.format("%s;%s;%s;%s;%d%c;%d%c", login, per, codigo, nome, Math.round(perSinc),37,Math.round(perAssinc),37);
			if(existe == 0)
				printwriter.println(cabecalho);
			printwriter.println(format);
			printwriter.flush();			
			printwriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
