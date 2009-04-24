package coq.definitions;

import coq.toplevel.toplevel.CoqState;


public class ExtendedCoqState{
	public int offset;
	public CoqState state;
	public ExtendedCoqState(int o,CoqState state){
		offset=o;
		this.state=state;
	}
	
	@Override
	public String toString(){
		return ":: "+ offset+ " "+state.toString();
	}
}
