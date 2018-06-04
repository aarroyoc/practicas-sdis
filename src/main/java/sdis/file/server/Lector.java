package sdis.file.server;

import java.io.*;
import sdis.file.client.FileReceptor;

public class Lector implements Runnable {

	private final BufferedReader br;
	private final FileReceptor receptor;

	public Lector(String nombre, FileReceptor receptor) throws FileNotFoundException{
		this.br = new BufferedReader(new FileReader(nombre));
		this.receptor = receptor;
	}

	@Override
	public void run(){
		while(true){
			try{
				String line = br.readLine();
				if(line != null){
					receptor.recibeLinea(line);
				}else{
					Thread.sleep(1000);
				}
			}catch(Exception e){
				
			}
		}
	}
}
