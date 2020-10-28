package uteis;

import java.util.*;

import custom_exceptions.InvallidReferenceException;
import custom_exceptions.RepeatedRegisterException;

import java.io.*;
import java.lang.Long;

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
//		System.out.println("Periodo nao existente");
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
//		System.out.println("Docente nao existente");
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
//		System.out.println("Estudante nao existente");
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
//		System.out.println("Disciplina nao existente");
		return 0;
	}	 	
	public void relatorioDisc(Map<String, Disciplina> mapa, String s) {
		System.out.println("----------------------------------------------");
		for(Map.Entry<String, Disciplina> aux : mapa.entrySet()) {
			if(aux.getValue().getPeriodoDisc().getNomePeriodo().equals(s)) {
				System.out.println("Código: " + aux.getValue().getCodigo());
				System.out.println("Nome: " + aux.getValue().getNomediscip());
				System.out.println("Docente: " + aux.getValue().getDocenteDisc().getNomeDocente());
				System.out.println("Email institucional: " + aux.getValue().getDocenteDisc().getLogin());
				System.out.println("Número de Estudantes Matriculados: " + aux.getValue().getNumEstudantes());
				System.out.println("Número de Atividades propostas: " + aux.getValue().getNumAtividades());
				System.out.println("----------------------------------------------");
			}
        }		
	}	
	
	public void relatorioDocente(Map<String, Docente> mapa) {	
		//mapa = new TreeMap<String, Docente>(Collections.reverseOrder());
		System.out.println("----------------------------------------------");
		for(Map.Entry<String, Docente> aux : mapa.entrySet()) {		
			System.out.println("Nome: " + aux.getValue().getNomeDocente());
			System.out.println("Numero de disc ministradas: " + aux.getValue().getNumDisc());			
			System.out.println("Numero de periodos diferentes com disciplinas associadas:");
			aux.getValue().printPeriodosDiscDocente();
			System.out.println("Média Ativ/Disc: " + aux.getValue().getAtivPorDisc());
			System.out.println("----------------------------------------------");
		}
	}	
	public void relatorioEstudante(Map<Long, Estudante> mapa) {
		for(Map.Entry<Long, Estudante> aux : mapa.entrySet()) {
			System.out.println("Matricula: " + aux.getValue().getMatricula());
			System.out.println("Nome: " + aux.getValue().getNomeEstudante());
			
		}
	}
		
	
	public void DisplayMenu() {
		Scanner leitor = new Scanner(System.in);
        int opcao;
        this.serializingSignal = 0;
		do{
			
	        
            System.out.println("Menu principal.");
            System.out.println("1 - Cadastro de periodos");
            System.out.println("2 - Cadastro de docentes");
            System.out.println("3 - Cadastro de disciplinas");
            System.out.println("4 - Cadastro de estudantes");
            System.out.println("5 - Matricula de estudantes em disciplinas");
            System.out.println("6 - Cadastro de atividade de disciplina");
            System.out.println("7 - Avaliacao de atividade por parte de estudante");
            System.out.println("8 - Relatorios (parcialmente)");
            System.out.println("9 - Salvar");
            System.out.println("10 - Carregar");
            System.out.println("11 - Sair");
            System.out.println("Por favor, escolha uma opcao:");
            
            opcao = leitor.nextInt();
           
            switch(opcao){
                case 1:
                    int escolha;
                    try {
                    	System.out.println("Periodos ja cadastrados:"); 
                        for(Map.Entry<String, Periodo> aux : periodos.entrySet()) {
                        	System.out.println(aux.getKey());                    	
                        }
                        System.out.println("Voce gostaria de:");
                        System.out.println("1 - Cadastrar um novo item.");
                        System.out.println("2 - Retornar ao menu principal.");                    
                        escolha = leitor.nextInt();
                        if(escolha == 1){
                        	Periodo p = new Periodo();
                        	
                            System.out.println("Favor informar o ano:");
                            int ano = leitor.nextInt();
                            p.setAno(ano);
                            
                            System.out.println("Favor informar o semestre:");
                            String semestre = leitor.next();
                            if(semestre.length() > 1)
                            	throw new InputMismatchException(semestre);
                            
                            p.setSemestre(semestre);
                            
                            String ref = String.format("%d/%s", ano, semestre);     
                            
                            if(this.existePeriodo(periodos, ref) == 1) {
                        		throw new RepeatedRegisterException("Cadastro repetido: " + ref, Throwable);
                        	}
                            else {                            
                            	putPeriodos(ref, p);
                            }
                        }
                        if(escolha == 2)
                            break;
                        break;
                    }
                    catch(Exception e) {
                    	System.out.println("Erro : " + e);                       	
                    	DisplayMenu();
                    };
                    
                case 2:
                	try {
                		System.out.println("Docentes ja cadastrados:");  
                        for(Map.Entry<String, Docente> aux : docentes.entrySet()) {
                        	System.out.println(aux.getKey());                    	
                        }
                        System.out.println("Voce gostaria de:");
                        System.out.println("1 - Cadastrar um novo item.");
                        System.out.println("2 - Retornar ao menu principal.");
                        escolha = leitor.nextInt();
                        if(escolha == 1){
                        	Docente d = new Docente();   
                        	
                        	System.out.println("Favor informar o login:");
                        	String login = leitor.next();
                        	d.setLogin(login);
                        	
                        	System.out.println("Favor informar o nome completo:");
                        	String nomecompleto = leitor.nextLine();
                        	nomecompleto = leitor.nextLine();
                        	d.setNomeDocente(nomecompleto);
                        	
                        	System.out.println("Favor informar o endereço da pagina web:");
                        	String paginaweb = leitor.next();
                        	d.setPaginaWeb(paginaweb);
                        	
                        	if(this.existeDocente(docentes, login) == 1) {
                        		throw new RepeatedRegisterException("Cadastro repetido: " + login, Throwable);
                        	}
                        	else {                    	
                        		putDocentes(login, d);
                        	}
                        }
                        if(escolha == 2)
                            break;
                	}
                	catch(Exception e) {
                		System.out.println("Erro : " + e);                       	
                    	DisplayMenu();
                	}
                    
                    break;
                case 3:
                	try {
                		System.out.println("Disciplinas ja cadastradas:");  
                        for(Map.Entry<String, Disciplina> aux : disciplinas.entrySet()) {
                        	System.out.println(aux.getKey());                    	
                        }
                        System.out.println("Voce gostaria de:");
                        System.out.println("1 - Cadastrar um novo item.");
                        System.out.println("2 - Retornar ao menu principal.");
                        escolha = leitor.nextInt();
                        if(escolha == 1){
                        	try {
                        		Disciplina dis = new Disciplina();
                        		
                            	System.out.println("Favor informar o código da disciplina:");
                            	String cod = leitor.next();                            	
                            	dis.setCodigo(cod);
                            	
                            	System.out.println("Favor informar o nome da disciplina:");
                            	String nomeDisciplina = leitor.nextLine();
                            	nomeDisciplina = leitor.nextLine();                            	
                            	dis.setNomediscip(nomeDisciplina);
                            	
                            	System.out.println("Favor informar o periodo:");
                            	String periodo = leitor.next();
                            	if(this.existePeriodo(periodos, periodo) == 0) {
                            		throw new InvallidReferenceException("Referencia invalida: " + periodo, Throwable);
                            	}
                            	Periodo p = periodos.get(periodo);
                            	dis.setPeriodoDisc(p);
                            	
                            	System.out.println("Favor informar o docente responsável (login institucional):");
                            	String docente = leitor.next();
                            	if(this.existeDocente(docentes, docente) == 0) {
                            		throw new InvallidReferenceException("Referencia invalida: " + docente, Throwable);
                            	}
                            	Docente d = docentes.get(docente);
                            	dis.setDocenteDisc(d);
                            	
                            	String format = String.format("%s-%s", cod, periodo);
                            	
                            	if(this.existeDisciplina(disciplinas, format) == 1) {
                            		throw new RepeatedRegisterException("Cadastro repetido: " + format, Throwable);
                            	}
                            	else {                    	                        		
                            		putDisciplinas(format, dis);
                            	}
                            	
                        	} 
                        	catch(Exception e) {
                        		System.out.println("Erro :" + e);
                        	}                    	                    	                    
                        }
                            
                        if(escolha == 2)
                            break;
                	}catch(Exception e) {
                		System.out.println("Erro : " + e);                       	
                    	DisplayMenu();
                	}
                    
                    break;
                case 4:
                	try {
                		System.out.println("Alunos ja cadastrados:"); 
                        for(Map.Entry<Long, Estudante> aux : estudantes.entrySet()) {
                        	System.out.println(aux.getKey());                    	
                        }
                        System.out.println("Voce gostaria de:");
                        System.out.println("1 - Cadastrar um novo item.");
                        System.out.println("2 - Retornar ao menu principal.");
                        escolha = leitor.nextInt();
                        if(escolha == 1){
                        	Estudante e = new Estudante();
                        	
                        	System.out.println("Favor informar a matricula:");
                        	long matricula = leitor.nextLong();
                        	e.setMatricula(matricula);
                        	
                        	System.out.println("Favor informar o nome completo:");
                        	String nomeEstudante = leitor.nextLine();
                        	nomeEstudante = leitor.nextLine();
                        	e.setNomeEstudante(nomeEstudante);
                        	
                        	if(this.existeEstudante(estudantes, matricula) == 1) {
                        		throw new RepeatedRegisterException("Cadastro repetido: " + matricula, Throwable);
                        	}
                        	else {                    	                    	
                        		putEstudantes(matricula, e);
                        	}
                        	
                        }
                        if(escolha == 2)
                            break;
                	}catch(Exception e) {
                		System.out.println("Erro : " + e);                       	
                    	DisplayMenu();
                	}
                    
                    break;
                case 5:             
                	try {
                		System.out.println("\nInforme o estudante a ser matriculado:\n");                		
                		long matricula = leitor.nextLong();
                		if(this.existeEstudante(estudantes, matricula) == 0) {
                			throw new InvallidReferenceException("Referencia invalida: " + matricula, Throwable);
                    	}
                		Estudante e = estudantes.get(matricula);
                		
                        System.out.println("\nInforme a disciplina desejada:\n");
                        String codigodisc = leitor.next();
                        if(this.existeDisciplina(disciplinas, codigodisc) == 0) {
                        	throw new InvallidReferenceException("Referencia invalida: " + codigodisc, Throwable);
                    	}
                        Disciplina d = disciplinas.get(codigodisc);
                        
                        if(this.existeEstudante(estudantes, matricula) == 1) {
                        	String msgERRO = String.format("Matricula repetida estudante: %s em %s", matricula, codigodisc);
                    		throw new RepeatedRegisterException(msgERRO , Throwable);
                    	}
                    	else {                    	                    	
                    		putEstudantes(matricula, e);
                    	}
                                                
                        d.imprimeEstudantes();
                	}
                	catch(Exception erro){
                		System.out.println("Erro : " + erro);
                		DisplayMenu();
                	}                                       
                    break;
                case 6:
                	try {
                		System.out.println("Voce gostaria de:");
                    	System.out.println("1 - Cadastrar um novo item.");
                        System.out.println("2 - Retornar ao menu principal.");
                        escolha = leitor.nextInt();
                        if(escolha == 1) {
                        	Atividade ativ = new Atividade();                      	
                        	ativ.newAtividade();
                        	
                        	System.out.println("\nInforme a disciplina referente a atividade:\n");
                        	String codigodisc = leitor.next();
                            if(this.existeDisciplina(disciplinas, codigodisc) == 0) {
                            	throw new InvallidReferenceException("Referencia invalida: " + codigodisc, Throwable);
                        	}
                            Disciplina d = disciplinas.get(codigodisc);                                                
                            
                            d.putAtividade(ativ);
                        	
                        }
                        if(escolha == 2)
                        	break;
                	}catch(Exception erro){
                		System.out.println("Erro : " + erro);
                		DisplayMenu();
                	}
                	
                    break;
                case 7:
                	try {
                		System.out.println("Informe a matricula:");
                    	long matricula = leitor.nextLong();
                    	if(this.existeEstudante(estudantes, matricula) == 0) {
                    		throw new InvallidReferenceException("Referencia invalida: " + matricula, Throwable);
                    	}
                    	
                    	System.out.println("\nInforme a disciplina:\n");
                    	String codigodisc = leitor.next();
                        if(this.existeDisciplina(disciplinas, codigodisc) == 0) {
                        	throw new InvallidReferenceException("Referencia invalida: " + codigodisc, Throwable);
                    	}
                        Disciplina d = disciplinas.get(codigodisc);      
                        
                        System.out.println("\nInforme o numero da atividade:\n");
                        Integer num = leitor.nextInt();
                        Atividade a = d.getAtivsList().get(num);
                        
                        System.out.println("Informe a nota desejada:");
                        float nota = leitor.nextFloat();
                        
                        if(a.existeAvaliacao(matricula, num) == 1) {
                        	throw new RepeatedRegisterException("Avaliacao repetida : " + matricula + " para atividade " + num + " de " + codigodisc, Throwable);
                    	}
                        
                        a.putAvaliacao(matricula, nota);
                	}catch(Exception erro){
                		System.out.println("Erro : " + erro);
                		DisplayMenu();
                	}
                	
                    break;
                case 8:
                	try {
                		System.out.println("Selecione o relatório a ser exibido:");
                        System.out.println("1 - Visão geral do perído acadêmico:");
                        System.out.println("2 - Estatísticas dos docentes:");
                        System.out.println("3 - Estatísticas dos estudantes:");
                        System.out.println("4 - Estatísticas das disciplinas de um docente:");
                        int opt = leitor.nextInt();
                        switch(opt) {
                        case 1:
                        	System.out.println("Favor informar um período cadastrado:");
                        	String p = leitor.next();
                        	relatorioDisc(disciplinas, p);   
                        	break;
                        case 2:
                        	relatorioDocente(docentes);
                        	break;
    					case 3:
    						relatorioEstudante(estudantes);
    						break;
    					case 4:
                        	
                        	break;
                        default:
                        	break;
                        }
                	}catch(Exception erro){
                		System.out.println("Erro : " + erro);
                		DisplayMenu();
                	}                    
                    break;  
                case 9:
                	try {
                        this.serializingSignal = 1; 
                	}catch(Exception e) {
                		System.out.println("Erro : " + e);
                		DisplayMenu();
                	}                                      
                    break; 
                case 10:
                	try {
                		System.out.println("carregar");
                        this.serializingSignal = 2;
                	}catch(Exception e) {
                		System.out.println("Erro : " + e);
                		DisplayMenu();
                	}                    
                    break;  
                case 11:
                	try {
                		System.out.println("fim.");
                        System.exit(1); 
                	}catch(Exception e) {
                		System.out.println("Erro : " + e);
                		DisplayMenu();
                	}                     
                default:
                    System.out.println("Favor informar uma opcao valida.");
                    break;
            }    
            if(this.serializingSignal == 1 || this.serializingSignal == 2)
            	break;
        }while(opcao != 8);		
	}
}
	
