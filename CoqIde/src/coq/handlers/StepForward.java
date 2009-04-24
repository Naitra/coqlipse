package coq.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;


import coq.definitions.CoqFindCommandAdapter;
import coq.definitions.CoqOutput;
import coq.definitions.ExtendedCoqState;
import coq.editors.CoqEditor;
import coq.plugin.CoqPlugin;
import coq.toplevel.toplevel.*;
import coq.views.toplevel.*;
import coq.views.toplevel.CoqTopView;


public class StepForward extends AbstractHandler{

	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchPage page;
		IEditorPart editor;
		CoqTopErrorView coqTopErrorView;
		CoqTopView coqTopView;

		page = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
		editor = page.getActiveEditor();
		
		try {
			coqTopErrorView = (CoqTopErrorView) page.showView("CoqTopErrorView");
			coqTopView = (CoqTopView)page.showView("CoqTopView");
		} catch (PartInitException e2) {
			CoqPlugin.logWarning("Can't find view");
			return null;
		}	
		
		if (!(editor instanceof CoqEditor)) return null;
		try {
			CoqEditor coqEditor = (CoqEditor) editor;
			IDocument document = coqEditor.getDocumentProvider().
				getDocument(coqEditor.getEditorInput());
			
			// Insert a new line at the end of the file
			if (document.getChar(document.getLength()-1)!='\n')
				document.set(document.get()+"\n");
			
			AbstractCoqTop coqtop = coqEditor.getCoqTop();
			int offset = coqtop.getCoqOffset();
				
			IRegion nextCommandDelimiter = 
				(new CoqFindCommandAdapter(document)).getNextCommand(offset);

			if (nextCommandDelimiter==null){
				coqTopErrorView.setError("I can't see any command to proceed"); 
				return null;
			}
			coqEditor.setHighlightRange(
					nextCommandDelimiter.getOffset()+1, 1, true);
			// Length of next command
			int nextCommandLength = 
				nextCommandDelimiter.getOffset() - offset+1;
			//System.out.println("Command :<" + document.get(offset, nextCommandLength)+">");
			// Extract command
			String command = 
				document.get(offset, nextCommandLength).trim();
			assert	(command.charAt(command.length()-1)=='.');
			// Send command to top level
			CoqOutput output = coqtop.sendCommand(command);
			// Create new state
			ExtendedCoqState last = 
				new ExtendedCoqState(nextCommandDelimiter.getOffset()+1,
						output.state);
			// Check if the top level has made progress
			if (coqEditor.getCoqTop().makeProgress(last)){
				// If we conclude a proof then remove it from history
				if (coqEditor.getCoqTop().exitProof(last))
					coqEditor.getCoqTop().removeLast();
				// Record the new state
				coqEditor.getCoqTop().recordCoqState(last);
				// Show warning if exists
				if (output.state.warning.length()>0)
					coqTopErrorView.setContent(last.state.warning);
				// Show top level message
				coqTopView.setContent(output.message);
				// Refresh page
				coqEditor.refresh();
			} else // if not show the error message
				coqTopErrorView.setError(output.message);
		} catch (BadLocationException e1) {
			CoqPlugin.logError("Bad Location");
		}
		return null;
	}
}
