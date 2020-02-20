package Principal;

import java.util.Random;

public class Mensaje {

	/**
	 * Valor inicial aleatorio del mensaje
	 */
	private int valorInicial;
	/**
	 * Valor final del mensaje, debería ser valorInicial++ después de que sea atendido
	 */
	private int valorFinal;
	/**
	 * Id del mensaje
	 */
	private int id;
	
	/**
	 * Constructor de la clase mensaje
	 * @param pId id del mensaje
	 */
	public Mensaje(int pId){
		this.id = pId;
		Random r = new Random();
		valorInicial = r.nextInt(10000);
		System.out.println("Valor inicial del mensaje es " + valorInicial);
	}
	
	/**
	 * @return valor inicial del mensaje
	 */
	public int getValorInicial(){
		return this.valorInicial;
	}
	/**
	 * @return valor final del mensaje
	 */
	public int getValorFinal(){
		return this.valorFinal;
	}
	/**
	 * Suma 1 al valor del mensaje y lo guarda en ValorFinal
	 */
	public void setValorFinal(){
		valorFinal = valorInicial++;
	}
	/**
	 * @return el id del mensaje
	 */
	public int getId(){
		return this.id;
	}
}

