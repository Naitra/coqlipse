package coq.streams;
import java.io.IOException;
import java.io.InputStream;

import coq.definitions.CoqState;



public class ErrorStream {
	InputStream input;

	public ErrorStream(InputStream input){
		this.input=input;
	}
	
	public CoqState read(){
		byte bytes[];	
		
		try {
			while (input.available()<=0){
					//System.out.println("Waiting for coqtop output");
					Thread.sleep(100);
			}
			bytes = new byte [input.available()];
			input.read(bytes);
			String message="";			
			for (int i=0;i<bytes.length;i++){
				message+=(char)bytes[i];	   
			}
			return new CoqState(message);
		} catch (IOException ioException) {
			ioException.printStackTrace();
			return null;
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
			return null;
		}
	}
}