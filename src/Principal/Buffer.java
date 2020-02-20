package Principal;

import java.util.ArrayList;

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
	 * Metodo constructor del buffer
	 * @param pTamBuffer tamaño del buffer
	 */
	public Buffer(int pTamBuffer){
		this.tamBuffer = pTamBuffer;
		this.mensajes = new ArrayList<>();
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

				mensajes.add(pMsj);
				return true;
			}
		}

	}
	
	/**
	 * Metodo que verifica si el servidor no tiene mensajes por atender
	 * @return true si no hay mensajes, false de lo contrario
	 */
	public boolean vacio(){
		return mensajes.isEmpty();
	}
	
	/**
	 * Metodo que retorna un mensaje que debe ser atendido por un servidor, cuando el servidor lo requiera.
	 * @return Mensaje de los que envió algun cliente
	 */
	public Mensaje retirarMensaje(){
		return mensajes.remove(mensajes.size() - 1);
	}
	
	/**
	 * Metodo que resta en uno la cantidad de clientes, para indicar que ya se atendio a un cliente. 
	 */
	public void clienteSalir(){
		this.numClientes--;
	}
}