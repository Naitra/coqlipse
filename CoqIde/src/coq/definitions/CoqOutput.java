package coq.definitions;


public class CoqOutput {
	public final CoqState state;
	public final String message;
	public CoqOutput(CoqState s, String m){
		state=s;
		message=m;
	}
	
}
