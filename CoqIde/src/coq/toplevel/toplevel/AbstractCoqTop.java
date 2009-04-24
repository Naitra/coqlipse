package coq.toplevel.toplevel;

import java.util.LinkedList;

import coq.definitions.CoqException;
import coq.definitions.CoqOutput;
import coq.definitions.ExtendedCoqState;

public class AbstractCoqTop {
	
	String folder = "";
	
	private CoqTop coqtop;
	
	private LinkedList<ExtendedCoqState> history=
		new LinkedList<ExtendedCoqState>();
	
	public LinkedList<ExtendedCoqState> getHistory(){
		return history;
	}
	
	public CoqOutput sendCommand(String string){
		return coqtop.writeCommand(string);
	}
	
	public AbstractCoqTop(String string) {
		this.folder = string;
		coqtop = new CoqTop(folder);
		history = new LinkedList<ExtendedCoqState>();
		CoqOutput output;
		try {
			output = coqtop.start();
			recordCoqState(new ExtendedCoqState(0,output.state));
		} catch (CoqException e) {
				e.printStackTrace();
		}
	}
	
	public int getCoqOffset(){
		assert (history.size() > 0) :"null history";
		return history.getFirst().offset;
	}
	
	// Perform one step backwards and return coq's output
	// If fails, returns null
	public CoqOutput backtrack(){
	if (history.size()>1){
		ExtendedCoqState current  = history.poll();
		ExtendedCoqState former = history.getFirst();
		// Check if we are at the beginning of a proof
		if (current.state.proofStateNumber==1){
			// if so, abort the current proof before backtracking
				coqtop.writeCommand("Abort.");
		}
		// backtrack to former state
		CoqOutput output = 
		coqtop.backtrack(former.state.envStateNumber, former.state.proofStateNumber);
		
		return output;
	}
	else return null;
	}
	
	

	public void recordCoqState(ExtendedCoqState coqState){
		history.addFirst(coqState);
	}
	
	public void removeLast() {
		int size=history.getFirst().state.currentProofs.length;
		assert (size>0);
		while(history.getFirst().state.currentProofs.length==size)
			history.remove();
	}
	
	
	public ExtendedCoqState getFormerCoqState(){
		history.removeFirst();
		ExtendedCoqState last = history.getFirst();
		return last;
	}
	
	public boolean makeProgress(ExtendedCoqState last2){
		ExtendedCoqState last = history.getFirst();
		return last2.state.envStateNumber!=last.state.envStateNumber;
	}
	
	public boolean exitProof(ExtendedCoqState next){
		ExtendedCoqState last = history.getFirst();
		return next.state.currentProofs.length<last.state.currentProofs.length;
	}
	
	public boolean inProofMode(){
		if (history.size()>0)
			return history.getFirst().state.inProofMode();
		else return false;
	}

	public void restart() {
		// TODO Auto-generated method stub
		coqtop=null;
		//getCoqTop();
		//refresh();
	}
	
	
}
