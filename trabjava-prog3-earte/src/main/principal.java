package main;
import java.io.*;
import java.util.*;

public class principal {
	static boolean write_only = false;
	public static void main(String[] args) {	
		//File handle_error = new File("out/output.txt");
		
		String arq_periodos = null;
		String arq_docentes = null;
		String arq_oferta_disc = null;
		String arq_estudantes = null;
		String arq_mat_estuds = null;
		String arq_ativs = null;
		String arq_avaliacoes = null;
		
		Manager m = new Manager();
		
		Scanner leitor = new Scanner(System.in);
		set_write_only_status(true);
		
		try {
			for(int i = 0; i < args.length; i = i + 2) {
				switch(args[i]) {
				case "-p":
					arq_periodos = String.format("%s", args[i+1]);
					
					break;
				case "-d":
					arq_docentes = String.format("%s", args[i+1]);
					
					break;
				case "-o":
					arq_oferta_disc = String.format("%s", args[i+1]);
					
					break;
				case "-e":
					arq_estudantes = String.format("%s", args[i+1]);
					
					break;
				case "-m":
					arq_mat_estuds = String.format("%s", args[i+1]);
					
					break;
				case "-a":
					arq_ativs = String.format("%s", args[i+1]);
					
					break;
				case "-n":
					arq_avaliacoes = String.format("%s", args[i+1]);
					
					break;
				case "--read-only":
					try {					
						FileOutputStream fileOutput = new FileOutputStream("dados.dat");
						ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
						objOutput.writeObject(m);
						objOutput.close();
						set_write_only_status(false);						
						//System.out.println("Salvo com sucesso.");			
					}catch(Exception e) {
						System.out.println("Erro de I/O ");					
					}
					break;
				case "--write-only":
					try {															
						FileInputStream fileInput = new FileInputStream("dados.dat");
						ObjectInputStream objInput = new ObjectInputStream(fileInput);
						m = (Manager) objInput.readObject();
						//System.out.println("Carregado com sucesso.");	
						objInput.close();					
					}catch(Exception e) {
						System.out.println("Erro de I/O");					
					}
					break;
				default:
					break;
				}
			}	
			m.DisplayMenu("-p", arq_periodos);
			m.DisplayMenu("-d", arq_docentes);
			m.DisplayMenu("-o", arq_oferta_disc);
			m.DisplayMenu("-e", arq_estudantes);
			m.DisplayMenu("-m", arq_mat_estuds);
			m.DisplayMenu("-a", arq_ativs);
			m.DisplayMenu("-n", arq_avaliacoes);
			
		}catch(Exception e) {
			//try {
				PrintWriter pw = new PrintWriter(System.out); // new PrintWriter(new FileOutputStream(handle_error));
				//e.printStackTrace();
                pw.println("Erro de I/O.");
                pw.flush();
                pw.close();
                System.exit(1);
            //} 
            //catch (FileNotFoundException e1) { e1.printStackTrace(); }
		}
	
	}
	public static void set_write_only_status(boolean b) {
		write_only = b;
	}
	
	public static boolean get_write_only_status() {
		return principal.write_only;
	}
}
