package Principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

	private final static String RUTA_CONFIG = "config.txt";
	private static int numMensajes;
	private static int numClientes;
	private static int numServidores;
	private static int tamBuffer;

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
