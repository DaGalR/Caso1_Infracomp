package Principal;

public class Servidor extends Thread {

	/**
	 * Instancia del buffer
	 */
	private Buffer buffer;

	/**
	 * Identificador del servidor
	 */
	private int id;

	/**
	 * Guarda el último mensaje
	 */
	private Mensaje msj;

	/**
	 * Constructor del servidor
	 * @param pBuff
	 */
	public Servidor(Buffer pBuff, int pId){
		this.buffer = pBuff;
		this.id=pId;

	}

	/**
	 * Metodo principal del servidor
	 */
	public void run(){


		while(buffer.darClientes()>0){

			while(!buffer.retirarMensaje(this))
			{
				if(buffer.darClientes()==0)
				{
					break;
				}
				yield();
			}
			if(buffer.darClientes()==0)
			{
				break;
			}
			synchronized(msj){

				System.out.println("SERVIDOR "+id+": recibió " +msj.getId() + " con valor inical " + msj.getValorInicial());
				msj.setValorFinal();
				msj.notify();
				System.out.println("SERVIDOR "+id+": respondió "  + msj.getId()+ " con valor final " + msj.getValorFinal());

			}
		}
		System.out.println("SERVIDOR "+ id +": se apaga");
	}

	public void recibirMsj(Mensaje pMsj)
	{
		this.msj= pMsj;
	}
}
