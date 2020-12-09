package csv;
import java.io.*;
import java.util.*;
import uteis.*;

public class Leitor_csv implements Serializable{
	private int num_periodos = 0;
	private int num_docentes = 0;
	private int num_ofertas_disc = 0;
	private int num_estudantes = 0;
	private int num_mat_estuds = 0;
	private int num_ativs = 0;
	private int num_avaliacoes = 0;
	
	public Leitor_csv() {
		
	}	
	
	public List<String> lerPeriodosCSV(String arquivoCSV) {				
		String leitor;
		List<String> lista_periodos_csv = new ArrayList<String>();
		BufferedReader buffer;		
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV),"UTF-8"));
			String cabecalho =  buffer.readLine();
			while((leitor = buffer.readLine()) != null) {
				this.num_periodos++;				
				lista_periodos_csv.add(leitor);
			}			
		}
		catch(FileNotFoundException e) {
			System.out.println("" + e.getMessage());
		} 
		catch (IOException e) {			
			System.out.println("" + e.getMessage());
		}
		return lista_periodos_csv;
	}	
	public int getNumPeriodos() {
		return this.num_periodos;
	}
	
	public List<String> lerDocentesCSV(String arquivoCSV) {				
		String leitor;
		List<String> lista_docentes_csv = new ArrayList<String>();
		BufferedReader buffer;		
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV),"UTF-8"));
			String cabecalho =  buffer.readLine();
			while((leitor = buffer.readLine()) != null) {
				this.num_docentes++;
				
				lista_docentes_csv.add(leitor);
			}			
		}
		catch(FileNotFoundException e) {
			System.out.println("" + e.getMessage());
		} 
		catch (IOException e) {			
			System.out.println("" + e.getMessage());
		}
		return lista_docentes_csv;
	}	
	public int getNumDocentes() {
		return this.num_docentes;
	}
	
	public List<String> lerDisciplinasCSV(String arquivoCSV) {				
		String leitor;
		List<String> lista_disciplinas_csv = new ArrayList<String>();
		BufferedReader buffer;		
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV),"UTF-8"));
			String cabecalho =  buffer.readLine();
			while((leitor = buffer.readLine()) != null) {
				this.num_ofertas_disc++;
				
				lista_disciplinas_csv.add(leitor);
			}			
		}
		catch(FileNotFoundException e) {
			System.out.println("" + e.getMessage());
		} 
		catch (IOException e) {			
			System.out.println("" + e.getMessage());
		}
		return lista_disciplinas_csv;
	}	
	public int getNumDisciplinas() {
		return this.num_ofertas_disc;
	}
	
	public List<String> lerEstudantesCSV(String arquivoCSV) {				
		String leitor;
		List<String> lista_estudantes_csv = new ArrayList<String>();
		BufferedReader buffer;		
		try {
			buffer = new BufferedReader(new FileReader(arquivoCSV));
			String cabecalho =  buffer.readLine();
			while((leitor = buffer.readLine()) != null) {
				this.num_estudantes++;
				
				lista_estudantes_csv.add(leitor);
			}			
		}
		catch(FileNotFoundException e) {
			System.out.println("" + e.getMessage());
		} 
		catch (IOException e) {			
			System.out.println("" + e.getMessage());
		}
		return lista_estudantes_csv;
	}	
	public int getNumEstudantes() {
		return this.num_estudantes;
	}
	
	public List<String> lerMatEstudsCSV(String arquivoCSV) {				
		String leitor;
		List<String> lista_MatEstuds_csv = new ArrayList<String>();
		BufferedReader buffer;		
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV),"UTF-8"));
			String cabecalho =  buffer.readLine();
			while((leitor = buffer.readLine()) != null) {
				this.num_mat_estuds++;			
				lista_MatEstuds_csv.add(leitor);
			}			
		}
		catch(FileNotFoundException e) {
			System.out.println("" + e.getMessage());
		} 
		catch (IOException e) {			
			System.out.println("" + e.getMessage());
		}
		return lista_MatEstuds_csv;
	}	
	public int getNumMatEstuds() {
		return this.num_mat_estuds;
	}
	
	public List<String> lerAtivsCSV(String arquivoCSV) {				
		String leitor;
		List<String> lista_Ativs_csv = new ArrayList<String>();
		BufferedReader buffer;		
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV),"UTF-8"));
			String cabecalho =  buffer.readLine();
			while((leitor = buffer.readLine()) != null) {
				this.num_ativs++;
				lista_Ativs_csv.add(leitor);
			}			
		}
		catch(FileNotFoundException e) {
			System.out.println("" + e.getMessage());
		} 
		catch (IOException e) {			
			System.out.println("" + e.getMessage());
		}
		return lista_Ativs_csv;
	}	
	public int getNumAtivs() {
		return this.num_ativs;
	}
	
	public List<String> lerAvaliacoesCSV(String arquivoCSV) {			
		String leitor;
		List<String> lista_avaliacoes_csv = new ArrayList<String>();
		BufferedReader buffer;		
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV),"UTF-8"));
			String cabecalho =  buffer.readLine();
			while((leitor = buffer.readLine()) != null) {
				this.num_avaliacoes++;				
				lista_avaliacoes_csv.add(leitor);
			}			
		}
		catch(FileNotFoundException e) {
			System.out.println("" + e.getMessage());
		} 
		catch (IOException e) {			
			System.out.println("" + e.getMessage());
		}
		return lista_avaliacoes_csv;
	}	
	public int getNumAvaliacoes() {
		return this.num_avaliacoes;
	}
}
