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
	 * Metodo principal del cliente.  Indica lo que debe hacer el Thread Cliente una vez inicie.
	 */
	public void run()
	{
		for(int j = 0; j<consultas;j++){

			Mensaje mensj = new Mensaje(id);
			
			while(buffer.guardarMensaje(mensj) == false){
				
				yield();
			}

			synchronized(mensj){
				
				try{
					
					System.out.println("Mensaje con id " + id +" esperando respuesta del servidor");
					mensj.wait();
					
				}
				catch(InterruptedException i){
					i.printStackTrace();
				}

			}
			id++;
		}
		buffer.clienteSalir();
	}
}
