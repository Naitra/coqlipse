package coq.streams;
import java.io.IOException;
import java.io.InputStream;




public class CoqInputStream {

	InputStream input;
	
	public CoqInputStream(InputStream input){
		this.input=input;
	}
	
	//private int waitNext(){
	//	try {
	//		System.out.println("Waiting for coqtop input");
	//		input.mark(1);
	//		input.read();
	//		input.reset();
	//		return input.available();
	//	} catch (IOException e1) {
	//		// TODO Auto-generated catch block
	//		e1.printStackTrace();
	//		System.out.println("error");
	//		return -1;
	//	}
	//}
	
	public String read(){
		byte bytes[];
		//int available;
		String message="";		
		try {
			if (input.available()>0){
			//if ((available = waitNext()) > 0){
				bytes = new byte [input.available()];
				try {
					input.read(bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i=0;i<bytes.length;i++){
					message+=(char)bytes[i];	   
				}
				return message;
			}	
			else return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Reading coq ouput");
			e.printStackTrace();
			return "";
		}
	}
}
