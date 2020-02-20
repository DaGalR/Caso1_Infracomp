package Principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Clase principal del caso. Responsable de leer los parametros en archivo configuracion
 * @author d.galindo
 *
 */
public class Main {

	/**
	 * Ruta del archivo de configuracion
	 */
	private final static String RUTA_CONFIG = "config.txt";
	/**
	 * Numero de mensajes que cada cliente debe enviar
	 */
	private static int numMensajes;
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
				int i = 0;
				while(linea != null){
					data = linea.split("=");
					switch(i){
					case(0):
						numMensajes = Integer.parseInt(data[1]);
					case(1):
						numClientes = Integer.parseInt(data[1]);
					case(2):
						numServidores = Integer.parseInt(data[1]);
					case(3):
						tamBuffer = Integer.parseInt(data[1]);
					}
					i++;
					linea = bf.readLine();
				}
				
				System.out.println("Numero de mensaje por cliente " + numMensajes);
				System.out.println("Numero de clientes " + numClientes);
				System.out.println("Numero de servidores " + numServidores);
				System.out.println("Tamanio buffer " + tamBuffer);
				
				
				//for(int j=0;)
				
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
