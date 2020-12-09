package main;
import java.io.*;
import java.util.*;

public class principal implements Serializable{
	static boolean write_only = false;
	public static void main(String[] args) {	
		//File handle_error = new File("out/output.txt");

		// Determina configurações regionais.
		Locale.setDefault(new Locale("pt", "BR"));
		
		String arq_periodos = null;
		String arq_docentes = null;
		String arq_oferta_disc = null;
		String arq_estudantes = null;
		String arq_mat_estuds = null;
		String arq_ativs = null;
		String arq_avaliacoes = null;
		
		Manager m = new Manager();
		
//		Scanner leitor = new Scanner(System.in);		
		
		try {
			boolean read_only = false, write_only = false;

			// Processa os parâmetros da linha de comando.
			for (int i = 0; i < args.length; i++) {
				// Procura pela opção -p, que especifica o arquivo de periodos.
				if ("-p".equals(args[i]) && args.length > i + 1)
					arq_periodos = args[i + 1];

				// Procura pela opção -d, que especifica o arquivo de docentes.
				else if ("-d".equals(args[i]) && args.length > i + 1)
					arq_docentes = args[i + 1];

				// Procura pela opção -o, que especifica o arquivo de disciplinas (oferta).
				else if ("-o".equals(args[i]) && args.length > i + 1)
					arq_oferta_disc = args[i + 1];

				// Procura pela opção -e, que especifica o arquivo de estudantes.
				else if ("-e".equals(args[i]) && args.length > i + 1)
					arq_estudantes = args[i + 1];

				// Procura pela opção -m, que especifica o arquivo de matrículas.
				else if ("-m".equals(args[i]) && args.length > i + 1)
					arq_mat_estuds = args[i + 1];

				// Procura pela opção -a, que especifica o arquivo de atividades.
				else if ("-a".equals(args[i]) && args.length > i + 1)
					arq_ativs = args[i + 1];

				// Procura pela opção -n, que especifica o arquivo de avaliações de atividades (notas).
				else if ("-n".equals(args[i]) && args.length > i + 1)
					arq_avaliacoes = args[i + 1];

				// Procura pela opções --read-only e --write-only, que indicam o uso de serialização.
				else if ("--read-only".equals(args[i]))
					read_only = true;
				else if ("--write-only".equals(args[i]))
					write_only = true;
			}			


			if(! write_only) {
				
				m.DisplayMenu("-p", arq_periodos);
				m.DisplayMenu("-d", arq_docentes);
				m.DisplayMenu("-o", arq_oferta_disc);
				m.DisplayMenu("-e", arq_estudantes);
				m.DisplayMenu("-m", arq_mat_estuds);
				m.DisplayMenu("-a", arq_ativs);
				m.DisplayMenu("-n", arq_avaliacoes);	
				
				if(read_only == true) {
					try {														      
						FileOutputStream fileOutput = new FileOutputStream("dados.dat");
						ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);		
						
						objOutput.writeObject(m);
						objOutput.flush();
						objOutput.close();		
						fileOutput.close();
//						System.out.println("Salvo com sucesso.");			
					}catch(Exception e) {
						e.printStackTrace();					
					}
				}				
			}
			else {
				try {							
					FileInputStream fileInput = new FileInputStream("dados.dat");					
					ObjectInputStream objInput = new ObjectInputStream(fileInput);					
					m =  (Manager) objInput.readObject();																
					//m.set_write_only_status(true);					
					objInput.close();											
				}catch(Exception e) {
					e.printStackTrace();					
				}
			}

			// Gera os relatórios.
			if (! read_only) m.DisplayMenu("--write-only", null);
			
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
	
}
