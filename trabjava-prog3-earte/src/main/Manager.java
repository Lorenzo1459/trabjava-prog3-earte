package main;

import java.util.*;

import atividades.*;
import custom_exceptions.InvallidReferenceException;
import custom_exceptions.RepeatedRegisterException;
import uteis.Disciplina;
import uteis.Docente;
import uteis.Estudante;
import uteis.Periodo;

import java.io.*;
import java.lang.Long;

import csv.Leitor_csv;
import csv.Relatorios;

public class Manager implements Serializable {
	
	private static final Throwable Throwable = null;
	private int serializingSignal = 0;	
	private Map<String, Periodo> periodos = new HashMap<String, Periodo>();
	private Map<String, Docente> docentes = new HashMap<String, Docente>();
	private Map<Long , Estudante> estudantes = new HashMap<Long, Estudante>();
	private Map<String, Disciplina> disciplinas = new HashMap<String, Disciplina>();

	public Manager() {
		
	}
	
	public int getSignal() {
		return this.serializingSignal;
	}	
	public void setSignal(Integer i) {
		this.serializingSignal = i;
	}
	
	public void putPeriodos(String formato, Periodo p) {
		this.periodos.put(formato, p);
	}
	public int existePeriodo(Map<String, Periodo> mapa, String s) {
		for(Map.Entry<String, Periodo> aux : mapa.entrySet()) {
			if(aux.getKey().equals(s)) {								       
				return 1;
			}
        }		
		return 0;
	}	
	
	public void putDocentes(String login, Docente d) {
		this.docentes.put(login, d);
	}
	public int existeDocente(Map<String, Docente> mapa, String s) {
		for(Map.Entry<String, Docente> aux : mapa.entrySet()) {
			if(aux.getKey().equals(s)) {								       
				return 1;
			}
        }
		return 0;
	}
	
	public void putEstudantes(Long mat, Estudante e) {
		this.estudantes.put(mat, e);
	}
	public int existeEstudante(Map<Long, Estudante> mapa, Long s) {
		for(Map.Entry<Long, Estudante> aux : mapa.entrySet()) {
			if(java.lang.Long.compare(aux.getKey(), s) == 0) {		
				return 1;
			}
        }
		return 0;
	}
	
	public void putDisciplinas(String formato, Disciplina dis) {		
		dis.getDocenteDisc().putDisciplinaDocente(dis);
		this.disciplinas.put(formato, dis);
	}
	public int existeDisciplina(Map<String, Disciplina> mapa, String s) {
		for(Map.Entry<String, Disciplina> aux : mapa.entrySet()) {
			if(aux.getKey().equals(s)) {								       
				return 1;
			}
        }
		return 0;
	}	 					
	
	public void DisplayMenu(String opcao, String arquivo) {		
			Leitor_csv leitor = new Leitor_csv();		
			File handle_error = new File("out/output.txt");
            switch(opcao){
                case "-p": // CADASTRO DE PERIODOS
                	List<String> lista_periodos = leitor.lerPeriodosCSV(arquivo);
                    try {
                    	
                        for(Map.Entry<String, Periodo> aux : periodos.entrySet()) {
                        	System.out.println(aux.getKey());                    	
                        }
                        
                        for(int i = 0; i < leitor.getNumPeriodos(); i++)   {
                        	Periodo p = new Periodo();                        
                        	String[] periodo = lista_periodos.get(i).split(";");       
                        	int ano = Integer.parseInt(periodo[0]);                        	
                            p.setAno(ano);                            
                            
                            String semestre = periodo[1];
                            if(semestre.length() > 1)
                            	throw new InputMismatchException(semestre);                            
                            p.setSemestre(semestre);                           
                            String ref = String.format("%d/%s", ano, semestre);                                 
                            if(this.existePeriodo(periodos, ref) == 1)
                        		throw new RepeatedRegisterException("Cadastro repetido: " + ref, Throwable);                        	
                            else                             
                            	putPeriodos(ref, p);
                        }                        	                                                                                                    
                        System.out.println("Periodos cadastrados."); 
                    }
                    catch(Exception e) {
                    	try {
                            PrintWriter pw = new PrintWriter(new FileOutputStream(handle_error));
                            pw.println("" + e);
                            pw.flush();
                            pw.close();
                            System.exit(1);
                        } 
                        catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    	System.out.println("Erro : " + e);                       	                    	
                    };
                    break;
                case "-d":	 //CADASTRO DE DOCENTES
                	try {
                		List<String> lista_docentes = leitor.lerDocentesCSV(arquivo);
                		
                		
                            for(Map.Entry<String, Docente> aux : docentes.entrySet()) 
                            	System.out.println(aux.getKey());      
                            
                            for(int i = 0; i < leitor.getNumDocentes(); i++) {
                            	Docente d = new Docente();                           	
                            	String[] docente = lista_docentes.get(i).split(";");
                            	String login = docente[0];
                            	d.setLogin(login);                        	                            	
                            	String nomecompleto = docente[1];                           	
                            	d.setNomeDocente(nomecompleto);                        	                            	
                            	String paginaweb = docente[2];
                            	d.setPaginaWeb(paginaweb);                        	
                            	if(this.existeDocente(docentes, login) == 1) 
                            		throw new RepeatedRegisterException("Cadastro repetido: " + login, Throwable);                        	
                            	else                    	
                            		putDocentes(login, d);
                		}                		                        	                                               
                            System.out.println("Docentes cadastrados."); 
                	}
                	catch(Exception e) {
                		try {
                            PrintWriter pw = new PrintWriter(new FileOutputStream(handle_error));
                            pw.println("" + e);
                            pw.flush();
                            pw.close();
                            System.exit(1);
                        } 
                        catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                		System.out.println("Erro : " + e);                       	                    	
                	}                    
                    break;
                case "-o": // CADASTRO DE DISCIPLINAS
                	try {
                		List<String> lista_disciplinas = leitor.lerDisciplinasCSV(arquivo);
                		
                        for(Map.Entry<String, Disciplina> aux : disciplinas.entrySet()) 
                        	System.out.println(aux.getKey());                    	                                                                                               
                        	
                        for(int i = 0; i < leitor.getNumDisciplinas(); i++) {
                        	Disciplina dis = new Disciplina();
                    		
                        	String[] disciplina =  lista_disciplinas.get(i).split(";");
                        	
                        	String cod = disciplina[1];                	
                        	dis.setCodigo(cod);    
                        	
                        	String nomeDisciplina = disciplina[2];                        	                           
                        	dis.setNomediscip(nomeDisciplina);  
                        	                        	
                        	String periodo = disciplina[0];
                        	if(this.existePeriodo(periodos, periodo) == 0) 
                        		throw new InvallidReferenceException("Referencia invalida Periodo: " + periodo, Throwable);                            	
                        	Periodo p = periodos.get(periodo);
                        	dis.setPeriodoDisc(p);                            	
                        	
                        	String docente = disciplina[3];
                        	if(this.existeDocente(docentes, docente) == 0) 
                        		throw new InvallidReferenceException("Referencia invalida Docente: " + docente, Throwable);                            	
                        	Docente d = docentes.get(docente);
                        	dis.setDocenteDisc(d);                            	
                        	String format = String.format("%s-%s", cod, periodo);                            	
                        	if(this.existeDisciplina(disciplinas, format) == 1) 
                        		throw new RepeatedRegisterException("Cadastro repetido: " + format, Throwable);                            	
                        	else                    	                        		
                        		putDisciplinas(format, dis);       
                    		}
                        System.out.println("Disciplinas cadastradas."); 
                        } 
                    	catch(Exception e) {
                    		try {
                                PrintWriter pw = new PrintWriter(new FileOutputStream(handle_error));
                                pw.println("" + e);
                                pw.flush();
                                pw.close();
                                System.exit(1);
                            } 
                            catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                    		System.out.println("Erro :" + e);                    		
                    	}                    	                                                  		                  	                                                                                                      	                  
                    break;
                case "-e": // CADASTRO DE ALUNOS
                	try {
                		List<String> lista_estudantes = leitor.lerEstudantesCSV(arquivo);
                		
                		
                        for(Map.Entry<Long, Estudante> aux : estudantes.entrySet()) 
                        	System.out.println(aux.getKey());                    	                        
                        
                        for(int i = 0; i < leitor.getNumEstudantes(); i++) {
                        	String[] estudante =  lista_estudantes.get(i).split(";");
                        	
                         	Estudante e = new Estudante();                        	
                        	
                        	long matricula = Long.parseLong(estudante[0]);
                        	e.setMatricula(matricula);                        	
                        	
                        	String nomeEstudante = estudante[1];
                        	
                        	e.setNomeEstudante(nomeEstudante);                        	
                        	if(this.existeEstudante(estudantes, matricula) == 1) 
                        		throw new RepeatedRegisterException("Cadastro repetido: " + matricula, Throwable);                        	
                        	else                     	                    	
                        		putEstudantes(matricula, e);      
                        }
                        System.out.println("Alunos cadastrados."); 
                    }
                	catch(Exception e) {
                		try {
                            PrintWriter pw = new PrintWriter(new FileOutputStream(handle_error));
                            pw.println("" + e);
                            pw.flush();
                            pw.close();
                            System.exit(1);
                        } 
                        catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                		System.out.println("Erro : " + e);                       	                    	
                	}                                                                  
                    break;
                case "-m":             // MATRICULAS DE ALUNOS EM DISCIPLINAS
                	try {
                		List<String> lista_MatEstuds = leitor.lerMatEstudsCSV(arquivo);
                		
                		for(int i = 0; i < leitor.getNumMatEstuds(); i++) {
                			String[] matEstud =  lista_MatEstuds.get(i).split(";");
                			
                			           		
                    		long matricula = Long.parseLong(matEstud[1]);
                    		if(this.existeEstudante(estudantes, matricula) == 0) 
                    			throw new InvallidReferenceException("Referencia invalida: " + matricula, Throwable);                    	
                    		Estudante e = estudantes.get(matricula);     
                    		
                            
                            String codigodisc = matEstud[0];
                            if(this.existeDisciplina(disciplinas, codigodisc) == 0) 
                            	throw new InvallidReferenceException("Referencia invalida: " + codigodisc, Throwable);                    	
                            Disciplina d = disciplinas.get(codigodisc);     
                            
                            if(d.jaMatriculado(d.getAlunosList(), matricula) == 1){
                            	String msgERRO = String.format("Matricula repetida estudante: %s em %s", matricula, codigodisc);
                        		throw new RepeatedRegisterException(msgERRO , Throwable);
                        	}
                        	else                     	                    	
                        		d.putEstudantesDisc(matricula, e,d);                    	                                               
                            //d.imprimeEstudantes();
                    		}
                		System.out.println("Matriculas de alunos concluidas.");         
                		}
                    	catch(Exception erro){
                    		try {
                                PrintWriter pw = new PrintWriter(new FileOutputStream(handle_error));
                                pw.println("" + erro);
                                pw.flush();
                                pw.close();
                                System.exit(1);
                            } 
                            catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                    		System.out.println("Erro : " + erro);                		
                    	}                		                		                		                                       
                    break;
                case "-a": // REGISTRO DE ATIVIDADES
                	try { 
                		List<String> lista_Ativs = leitor.lerAtivsCSV(arquivo);
                		System.out.println("Registro de Atividades");
                		for(int i = 0; i < leitor.getNumAtivs(); i++) {
                			String[] linha_ativs =  lista_Ativs.get(i).split(";");               			
                        	Atividade ativ = new Atividade(){};                        	
                        	ativ = ativ.newAtividade(linha_ativs, ativ);   
                        	String codigodisc = linha_ativs[0];
                            if(this.existeDisciplina(disciplinas, codigodisc) == 0) 
                            	throw new InvallidReferenceException("Referencia invalida: " + codigodisc, Throwable);                        	
                            Disciplina d = disciplinas.get(codigodisc);                                                                            
                            d.putAtividade(ativ);
                        
                		}
                		System.out.println("Atividades Registradas.");
                	}
                	catch(Exception erro){
                		try {
                            PrintWriter pw = new PrintWriter(new FileOutputStream(handle_error));
                            pw.println("" + erro);
                            pw.flush();
                            pw.close();
                            System.exit(1);
                        } 
                        catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                		System.out.println("Erro : " + erro);                		
                	}                		               			                	
                    break;
                case "-n": // AVALIAÇAO DE ATIVIDADES POR ALUNOS
                	try {
                		List<String> lista_Avaliacoes = leitor.lerAvaliacoesCSV(arquivo);
                		for(int i = 0; i < leitor.getNumAvaliacoes(); i++) {
                			String[] avaliacoes =  lista_Avaliacoes.get(i).split(";");                   		                   		
                        	long matricula = Long.parseLong(avaliacoes[1]);
                        	if(this.existeEstudante(estudantes, matricula) == 0) 
                        		throw new InvallidReferenceException("Referencia invalida: " + matricula, Throwable);                    	                    	
                        	
                        	String codigodisc = avaliacoes[0];
                            if(this.existeDisciplina(disciplinas, codigodisc) == 0) 
                            	throw new InvallidReferenceException("Referencia invalida: " + codigodisc, Throwable);                    	
                            Disciplina d = disciplinas.get(codigodisc);                              
                            
                            Integer num = Integer.parseInt(avaliacoes[2]);
                            Atividade a = d.getAtivsList().get(num);                        
                            
                            String notastring = avaliacoes[3].replace(',', '.');
                            float notafloat = Float.parseFloat(notastring);        
                            if(a.existeAvaliacao(matricula, num) == 1) 
                            	throw new RepeatedRegisterException("Avaliacao repetida : " + matricula + " para atividade " + num + " de " + codigodisc, Throwable);                    	                        
                            a.putAvaliacao(matricula, notafloat);
                    		}
                		System.out.println("Avaliacoes de alunos Registradas.");
                		}catch(Exception erro){
                			try {
                                PrintWriter pw = new PrintWriter(new FileOutputStream(handle_error));
                                pw.println("" + erro);
                                pw.flush();
                                pw.close();
                                System.exit(1);
                            } 
                            catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                    		System.out.println("Erro : " + erro);                		
                    	}  
                	
                	if(principal.get_write_only_status() == true) {                		
                		Relatorios relatorio = new Relatorios();
                		File file = new File("1-visao-geral.csv");
                		if(file.exists())
                			file.delete();
                		File file2 = new File("2-docentes.csv");	
                		if(file2.exists())
                			file2.delete();
                		File file3 = new File("3-estudantes.csv");
                		if(file3.exists())
                			file3.delete();
                		File file4 = new File("4-disciplinas.csv");
                		if(file4.exists())
                			file4.delete();
                		
                		for(Map.Entry<String, Periodo> aux : periodos.entrySet()){
                			relatorio.relatorioDisc(disciplinas,aux.getValue().getNomePeriodo(), file);      		
                		}
                		System.out.printf("Relatório %c1-visao-geral.csv%c completo.\n", 34, 34);
                		
                		
                		relatorio.relatorioDocente(docentes, file2);
                		System.out.printf("Relatório %c2-docentes.csv%c completo.\n", 34, 34);
                		
                		
                		relatorio.relatorioEstudante(estudantes, file3);
                		System.out.printf("Relatório %c3-estudantes.csv%c completo.\n", 34, 34);
                		
                		for(Map.Entry<String, Docente> aux : docentes.entrySet()) {
                			relatorio.relatorioDiscDocente(aux.getValue(), disciplinas, file4);
                		}
                		System.out.printf("Relatório %c4-disciplinas.csv%c completo.\n", 34, 34);
                	}
                	
                    break;                
                default:
                    System.out.println("Favor informar uma opcao valida.");
                    break;
            }                        
	}
}
	
