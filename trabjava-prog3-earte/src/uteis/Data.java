package uteis;
import java.io.Serializable;
import java.util.*;


public class Data implements Serializable{
	private String data;
	private String horario;
	private Calendar c = Calendar.getInstance();

	
	public Data() {
		
	}
	
	public void setData(String data) {		
		this.data = data;
		String[] aux = data.split("/");
		
		int dia = java.lang.Integer.parseInt(aux[0]);
		int mes = java.lang.Integer.parseInt(aux[1]);
		int ano = java.lang.Integer.parseInt(aux[2]);
		this.c.set(Calendar.DAY_OF_MONTH, dia);
		this.c.set(Calendar.MONTH, mes);
		this.c.set(Calendar.YEAR, ano);		
	}
	
	public void setHorario(String horario) {	
		this.horario = horario;
		String[] aux2 = horario.split(":");
		
		int horas = java.lang.Integer.parseInt(aux2[0]);
		int minutos = java.lang.Integer.parseInt(aux2[1]);
		this.c.set(Calendar.HOUR_OF_DAY, horas);
		this.c.set(Calendar.MINUTE, minutos);		
	}
}
