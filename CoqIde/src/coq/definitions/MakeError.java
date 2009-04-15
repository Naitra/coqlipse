package coq.definitions;

public class MakeError {
	public String filename;
	public int line;
	public int startOffset;
	public int endOffset;
	public String message;
	
	public MakeError(){
		
	}
	
	public static MakeError[] extract(String s){
		MakeError errors[] = null;
		String messages[] = s.split("(File|Error|,)");
		int nb = messages.length/4;
		if (nb > 0){
			errors = new MakeError[nb];
			for (int i=0;i<nb;i++){
				errors[i]=new MakeError();
				String file = messages[4*i+1].replaceAll("\"", "");
				int line = 
					Integer.parseInt(messages[4*i+2].replaceAll("[^0-9]", "").trim());
				String positions[] = 
					(messages[4*i+3].replaceAll("[^0-9|-]", "")).split("-");
				if (positions.length>=2){
					int start = Integer.parseInt(positions[0].trim());
					int end = Integer.parseInt(positions[1].trim());
		
					errors[i].filename=file;
					System.out.println("filename "+file);
					errors[i].line=line;
					errors[i].startOffset=start;
					errors[i].endOffset=end;
					errors[i].message=messages[4*i+4];
				} else return null;
			}
		}
		return errors;
	}
}
