package Principal;

public class Cliente extends Thread {

	/**
	 * Instancia del Buffer
	 */
	private Buffer buffer;
	/**
	 *Numero de consultas que debe hacer este cliente
	 */
	private int consultas;
	/**
	 * Id de este cliente
	 */
	private int id;

	/**
	 * Constructor del cliente
	 * @param pId id del cliente
	 * @param pBuff buffer donde se guardaran los mensajes escritos
	 * @param pConsultas cantidad de consultas que debe realizar este cliente
	 */
	public Cliente(int pId,Buffer pBuff, int pConsultas){
		this.id=pId;
		this.buffer = pBuff;
		this.consultas = pConsultas;
	}

	/**
	 * Metodo principal del cliente
	 */
	public void run(){
		for(int j = 0; j<consultas;j++){

			Mensaje mensj = new Mensaje(id);
			id++;
			while(buffer.guardarMensaje(mensj) == false){
				yield();
			}

			synchronized(mensj){
				try{
					mensj.wait();
					System.out.println("Esperando respuesta del servidor");
				}
				catch(InterruptedException i){
					i.printStackTrace();
				}

			}
		}
		buffer.clienteSalir();
	}
}
