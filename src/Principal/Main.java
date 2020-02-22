package Principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Clase principal del caso. Responsable de leer los parametros en archivo configuracion
 * @author d.galindo
 * @author Violeta Rodríguez (vl.rodriguez10)
 *
 */
public class Main {

	/**
	 * Ruta del archivo de configuracion
	 */
	private final static String RUTA_CONFIG = "config.txt";

	/**
	 * Cadena de caracteres del numero de mensajes de los clientes
	 */

	private static String numMensajes;
	/**
	 * Numero de clientes a crear
	 */
	private static int numClientes;
	/**
	 * Numero de servidores a crear
	 */
	private static int numServidores;
	/**
	 * Tamaño del buffer (cuantos mensajes puede leer maximo)
	 */
	private static int tamBuffer;

	/**
	 * Lsita cono los mensajes que debe mandar cada cliente
	 */

	private static String[] mensajesClientes;

	/**
	 * Lista con los clientes
	 */

	private static Cliente[] clientes;

	/**
	 * Lista con los servidores
	 */

	private static Servidor[] servidores;

	/**
	 * Main de la app, lee el archivo config y crea los servidores, clientes y buffer
	 * @param args
	 */
	public static void main(String[] args){



		try {
			FileReader r = new FileReader(RUTA_CONFIG);
			BufferedReader bf = new BufferedReader(r);

			try{
				String[] data;
				String linea=bf.readLine();
				while(linea != null){
					data = linea.split("=");
					if(data[0].equals("numClientes"))
					{
						numClientes = Integer.parseInt(data[1]);
						mensajesClientes = new String[numClientes];
						clientes = new Cliente[numClientes];

					}
					if(data[0].equals("numMensajes"))
					{
						numMensajes =data[1];
						mensajesClientes=numMensajes.split(",");
					}
					if(data[0].equals("numServidores"))
					{
						numServidores = Integer.parseInt(data[1]);
						servidores = new Servidor[numServidores];
					}
					if(data[0].equals("tamBuffer"))
					{
						tamBuffer = Integer.parseInt(data[1]);
					}

					linea = bf.readLine();
				}
				System.out.println("Numero de mensaje por cliente " + numMensajes);
				System.out.println("Numero de clientes " + numClientes);
				System.out.println("Numero de servidores " + numServidores);
				System.out.println("Tamaño buffer " + tamBuffer);


				Buffer buff = new Buffer(tamBuffer, numClientes);

				//Creación de los clintes
				for(int j=0; j<numClientes;j++)
				{
					clientes[j]=new Cliente(j, buff, Integer.parseInt(mensajesClientes[j]));
					clientes[j].start();
				}

				for(int j=0; j<numServidores;j++)
				{
					servidores[j]=new Servidor(buff);
					servidores[j].start();
				}
				

			}
			catch(Exception e){
				e.printStackTrace();
			}







		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
