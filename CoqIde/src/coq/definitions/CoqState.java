package coq.definitions;

public class CoqState {
	
	public final int envStateNumber;
	public final int proofStateNumber;
	public final String proofName;
	public final String warning;
	public final String prompt;
	public final String[] currentProofs;
	
	public CoqState(String p){
		prompt=p;
		String tempWarning = "";
		
		String lines[] = p.split("\n");
		for (int i=0; i<=lines.length-2; i++)
			tempWarning+=lines[i];
		warning = tempWarning;
		String prompt=lines[lines.length-1];
		
		String promptParts[] = prompt.split("<");
		
		assert (promptParts.length>1) : "Ill-Formed Coq Prompt(1)";
		proofName=promptParts[0].trim();
		String state[] = promptParts[1].split("\\|");
		
		envStateNumber=Integer.parseInt(state[0].trim());
		proofStateNumber=Integer.parseInt(state[state.length-1].trim());
		if (state.length-2>0 && state[1].length()==0) {
			currentProofs = new String[0];
		}
		else if (state.length-2>0){
			currentProofs = new String[state.length-2];
			for (int i=1;i<state.length-1;i++)
				currentProofs[i-1]=state[i];
		}
		else currentProofs=new String[0];
	}
	
	public boolean inProofMode(){
		return (currentProofs.length>0);
	}
	
	@Override
	public String toString(){
		String str_env = "#env="+envStateNumber;
		String str_proof = "#proof="+proofStateNumber;
		String str_proofs = "current=";
		if (currentProofs.length>0)
			for (int i=0;i<currentProofs.length;i++)
				str_proofs+=" "+ currentProofs[i];
		else str_proofs=str_proofs+"none";
		return 
		"<"+str_env+"; "+str_proof+"; "+str_proofs+">";
	}
}
