package Principal;

import java.util.Random;

public class Mensaje {

	private int valorInicial;
	private int valorFinal;
	
	public Mensaje(){
		
		Random r = new Random();
		valorInicial = r.nextInt(10000);
		System.out.println("Valor inicial del mensaje es " + valorInicial);
	}
	
	public int getValorInicial(){
		return this.valorInicial;
	}
	public int getValorFinal(){
		return this.valorFinal;
	}
}
