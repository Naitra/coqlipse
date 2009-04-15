package coq.toplevel;

import java.io.File;
import java.io.IOException;
import coq.definitions.CoqOutput;
import coq.definitions.CoqState;
import coq.exceptions.CoqException;
import coq.plugin.CoqPlugin;
import coq.streams.ErrorStream;
import coq.streams.CoqInputStream;
import coq.streams.CoqOutputStream;



public class CoqTop {

	private String coqtop = CoqPlugin.getCoqtopOpt();
	private String folder = "";
	private Process coqTopProcess;
	
	CoqInputStream coqInput;
	CoqOutputStream coqOutput;
	ErrorStream coqError;
	
	public CoqTop(String f){
		folder = f;
	}
	
	public boolean running(){
		return (coqTopProcess != null);
	}
	
	// Starts the top level
	// Return the first output if succeed, null otherwise
	
	public CoqOutput start() throws CoqException{
		
		if (new File(coqtop).exists())
			CoqPlugin.logInfo("found (coqtop) : " + coqtop);	
		else {
			CoqPlugin.logError("not found (coqtop) : " + coqtop);
			return null;
		}
		String command = coqtop + " -I "+ folder +" -emacs";
		
		try {
			coqTopProcess = Runtime.getRuntime().exec(command);
			coqOutput = new CoqOutputStream(coqTopProcess.getOutputStream());
			coqInput = new CoqInputStream(coqTopProcess.getInputStream());
			coqError = new ErrorStream(coqTopProcess.getErrorStream());
		} catch (IOException e) {
			String message = "Error starting coq toplevel " + coqtop + 
				" "+ e.toString();
			CoqPlugin.logError(message);
		}
		String message = "started (coqtop) : " + command;
		CoqPlugin.logInfo(message);
		return new CoqOutput(coqError.read(),coqInput.read());
	}
	
	public void restart() throws CoqException{
		if (running ()){
			terminate();
		}
		start();
		System.out.println("restart coq top level");
	}
	
	public void terminate(){
		if (coqTopProcess != null){
			coqTopProcess.destroy();
			coqTopProcess = null;
			coqOutput = null;
			coqInput = null;
			coqError = null;
		}
		else {
			System.out.println("No coq top level running");
		}
	}
	
	public CoqOutput abort(){
		return writeCommand("Abort.");
	}
	
	public CoqOutput backtrack(int e, int p){
		String command = "Backtrack "+ e+" "+ p +" "+ 0 + ".";
		return writeCommand(command);
	}
	
	public CoqOutput writeCommand(String msg){
		coqOutput.write(msg);
		CoqState state = coqError.read();
		String message = coqInput.read();
		return new CoqOutput(state,message);
	}
}
