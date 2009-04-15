package coq.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.StatusLineContributionItem;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import coq.definitions.ExtendedCoqState;
import coq.plugin.CoqPlugin;
import coq.toplevel.AbstractCoqTop;
import coq.views.CoqContentOutlinePage;
import coq.views.InfoView;

public class CoqEditor extends AbstractTextEditor{
	
	CoqContentOutlinePage coqContentOutlinePage;
	
	CoqDocumentProvider documentProvider;
	private AbstractCoqTop abstractCoqTop;
	
	StatusLineContributionItem item;
	StatusLineContributionItem positionItem;
	
	
	// If changed since saved and when saved
	public void firePropertyChange(int property){
		super.firePropertyChange(property);
		// recompile ?
	}
	
	@Override
	public Object getAdapter(Class required){
		if (IContentOutlinePage.class.equals(required)){
			if (coqContentOutlinePage == null){
				coqContentOutlinePage = new CoqContentOutlinePage(this);
			}
			return coqContentOutlinePage;
		}
		else return super.getAdapter(required);
	}
	
	public void refresh(){
		positionItem.setText(getCursorPosition());
		
		InfoView infoView;
		infoView = (InfoView) this.getSite().getPage().findView("InfoView");
		if (infoView == null)
			try {
				infoView = (InfoView) this.getSite().getPage().showView("InfoView");
			} catch (PartInitException e) {
				CoqPlugin.logError("Can't get info view");
			}
		assert (infoView != null) : "Can't initialize info view";
		infoView.clear();
		for (int i=0;i<getCoqTop().getHistory().size();i++){
			ExtendedCoqState coqState = abstractCoqTop.getHistory().get(i);
			infoView.addContent(coqState.offset, coqState.state.envStateNumber, 
					coqState.state.proofStateNumber, coqState.state.currentProofs);
		}
		
		setMarked();
	}
	
	public AbstractCoqTop getCoqTop(){
		if (abstractCoqTop == null){
			IFile file = ((FileEditorInput) getEditorInput()).getFile();
			IProject project = file.getProject();
			abstractCoqTop = 
				new AbstractCoqTop(project.getLocation().toString());
		}
		return abstractCoqTop;
	}

	public void setMarked(){
		Color white = new Color(Display.getCurrent(),255,255,255);
		Color color = new Color(Display.getCurrent(),150,150,255);
		IDocument document = getSourceViewer().getDocument();
		StyledText textWidget = getSourceViewer().getTextWidget();
		int offset = abstractCoqTop.getCoqOffset();
		try {
			textWidget.setLineBackground(0,document.getNumberOfLines(),white);
			if (abstractCoqTop.getHistory().size()>1)
			textWidget.setLineBackground(0,document.getLineOfOffset(offset)+1,color);
			textWidget.redraw();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}
	
	public void checkReadOnly(){
		try {
			int offset = getSourceViewer().getDocument().
				getLineOffset(getCurrentLine())+getCurrentRow();
			if (offset>0 && offset<abstractCoqTop.getCoqOffset()+1){
				this.getSourceViewer().setEditable(false);
			}
			else this.getSourceViewer().setEditable(true);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	
	// Auxiliary Methods
	
	public int getCurrentLine(){
		String position[] = (this.getCursorPosition()).split(":");
		return Integer.parseInt(position[0].trim())-1;
	}
	
	public int getCurrentRow(){
		String position[] = (this.getCursorPosition()).split(":");
		return Integer.parseInt(position[1].trim())-1;
	}
	// TextEditor Methods 
	
	@Override
	// crŽe l'interface graphique de l'Žditeur
	public void createPartControl(Composite parent){
		// add listeners to parents here
		super.createPartControl(parent);
	}
	
	@Override
	// initialise le contenu
	public void init(IEditorSite site, IEditorInput input){
		documentProvider = new CoqDocumentProvider();
		
		setSourceViewerConfiguration(
				new CoqTextSourceViewerConfiguration());
		setDocumentProvider(documentProvider);
		try {
			super.init(site, input);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		IActionBars bars = site.getActionBars();
		item = new StatusLineContributionItem("Cont");
		positionItem = new StatusLineContributionItem("Position");
		bars.getStatusLineManager().add(item);
		bars.getStatusLineManager().add(positionItem);

	}
	
	@Override
	public boolean isEditable(){
		return true;
	}
	
	@Override
	public void handleCursorPositionChanged(){
		super.handleCursorPositionChanged();
		refresh();
		checkReadOnly();
	}
	
	@Override
	// Handle external change
	public void handleEditorInputChanged(){
		super.handleEditorInputChanged();
	}
}
