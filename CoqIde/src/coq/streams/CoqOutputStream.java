package coq.streams;
import java.io.IOException;
import java.io.OutputStream;


public class CoqOutputStream {
	
	OutputStream output;
	
	public CoqOutputStream(OutputStream out){
		output = out;
	}

	public void write(String msg){
		byte bytes[] = (msg+"\n").getBytes();
		try {
			output.write(bytes);
			output.flush();			
		} catch (IOException e) {
			System.out.println("writeError");
			e.printStackTrace();
		}
	}
}
