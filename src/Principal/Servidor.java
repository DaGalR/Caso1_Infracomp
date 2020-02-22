package Principal;

public class Servidor extends Thread {

	/**
	 * Instancia del buffer
	 */
	private Buffer buffer;

	/**
	 * Constructor del servidor
	 * @param pBuff
	 */
	public Servidor(Buffer pBuff){
		this.buffer = pBuff;

	}

	/**
	 * Metodo principal del servidor
	 */
	public void run(){


		while(buffer.darClientes()>0){
			
			while(buffer.vacio()){
				yield();
			}
			Mensaje recibido = buffer.retirarMensaje();
			synchronized(recibido){
				System.out.println("Servidor resivió mensaje de id " + recibido.getId() + " con valor inical " + recibido.getValorInicial());
				recibido.setValorFinal();
				recibido.notify();
				System.out.println("Servidor respondio mensaje de id " + recibido.getId()+ " con valor final " + recibido.getValorFinal());

			}
		}
	}
}
