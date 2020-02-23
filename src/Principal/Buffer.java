package Principal;

import java.util.ArrayList;
import java.util.Random;

public class Buffer {

	/**
	 * Numero maximo de mensajes que pueden guardarse
	 */
	private int tamBuffer;
	/**
	 * Numero de clientes
	 */
	public int numClientes;
	/**
	 * Arreglo de mensajes recibidos
	 */
	private ArrayList<Mensaje> mensajes;
	
	/**
	 * Contado de mansje atendidos, para verificar que esta fucnionado bien y todos los mensajes fuerona atendidos
	 */
	private int msjsAtendidos;

	/**
	 * Metodo constructor del buffer
	 * @param pTamBuffer tamaño del buffer
	 */
	public Buffer(int pTamBuffer, int pNumCientes){
		this.tamBuffer = pTamBuffer;
		this.mensajes = new ArrayList<>();
		this.numClientes= pNumCientes;
		msjsAtendidos=0;
	}

	/**
	 * Metodo que guarda mensaje recibido de un cliente. 
	 * @param pMsj mensaje a agregar a lista
	 * @return Retorna false si no puede agregar el mensaje, true de lo contrario
	 */
	public boolean guardarMensaje(Mensaje pMsj){

		synchronized(this){

			if(mensajes.size() == tamBuffer){
				return false;

			}
			else{
				
				System.out.println("BUFFER: agrega mensaje "+ pMsj.getId());
				mensajes.add(pMsj);
				return true;
			}
		}

	}

	/**
	 * Metodo que verifica si el servidor tiene mensajes por atender
	 * @return true si no hay mensajes para atender, false de lo contrario
	 */
	public boolean vacio(){
		synchronized (this) 
		{
			return mensajes.isEmpty();
		}
	}

	/**
	 * Metodo que retorna un mensaje que debe ser atendido por un servidor, cuando el servidor lo requiera.
	 * @return Mensaje de los que envió algun cliente
	 */
	public boolean retirarMensaje(Servidor srv){
		synchronized(this)
		{
			if(!this.vacio())
			{
				Random r = new Random();
				int pos = r.nextInt(mensajes.size());
				srv.recibirMsj(mensajes.remove(pos));
				msjsAtendidos++;
				return true;
			}

			return false;
		}
	}

	/**
	 * Metodo que resta en uno la cantidad de clientes, para indicar que ya se atendio a un cliente. 
	 */
	public void clienteSalir(){

		this.numClientes--;
	}

	/**
	 * @return da el numero de clientes
	 */
	public int darClientes(){
		return this.numClientes;
	}
	
	/**
	 * @return retorna el numero de mensajes atendidos
	 */
	public int darMsjsAntendidos()
	{
		return msjsAtendidos;
	}
}