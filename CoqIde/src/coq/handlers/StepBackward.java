package coq.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;


import coq.definitions.CoqOutput;
import coq.editors.CoqEditor;
import coq.plugin.CoqPlugin;
import coq.views.CoqTopErrorView;
import coq.views.CoqTopView;

public class StepBackward extends AbstractHandler{ 


	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		IWorkbenchPage page = window.getActivePage();
		CoqEditor textEditor = (CoqEditor) page.getActiveEditor();
		
		CoqOutput output = textEditor.getCoqTop().backtrack();
		textEditor.setHighlightRange(
				textEditor.getCoqTop().getCoqOffset(), 1, true);
		
		CoqTopView coqTopView;
		coqTopView = (CoqTopView) page.findView("CoqTopView");
		if (coqTopView == null){
			try {
				coqTopView = (CoqTopView) page.showView("CoqTopView");
			} catch (PartInitException e) {
				CoqPlugin.logError("Can't initialize coqTopView");
				e.printStackTrace();
			}
		}
		if (output!=null){
			coqTopView.setContent(output.message);
		}
		else try {
				CoqTopErrorView coqTopErrorView =
					(CoqTopErrorView) page.showView("CoqTopErrorView");
				coqTopErrorView.setError("Nothing to backtrack");
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		textEditor.refresh();
		return null;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {
	}

}
