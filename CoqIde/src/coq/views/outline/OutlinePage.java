package coq.views.outline;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import coq.editors.CoqEditor;

public class OutlinePage extends ContentOutlinePage {
	
	CoqEditor editor;
	OutlineItem input;
	
	public OutlinePage(CoqEditor editor){
		super();
		this.editor = editor;
	}
	
	public void init(IPageSite pageSite){
		super.init(pageSite);
	}
	
	
	public void refresh(){
		IDocument document = editor.getDocumentProvider().
			getDocument(editor.getEditorInput());
		
		String key =("(Lemma|Axiom|Definition|Theorem|Variable|Parameter)");
		String str = "^\\s*"+key+"\\s*[[\\p{ASCII}&&[^\\\\.]][\\\\]]*";
		FindReplaceDocumentAdapter fr =
			new FindReplaceDocumentAdapter(document);
		IRegion region;
		int position = 0;
		input = new OutlineItem(OutlineItem.Type.Dummy,"Sum",0,0);
		try {
			
			while (position < document.getLength()){
				region = fr.find(position, str, true, false, false, true);
				if (region == null) break;
				String str2 = document.get(region.getOffset(), region.getLength()).trim();
				OutlineItem.Type type = OutlineItem.Type.Dummy; 
				if (str2.startsWith("Lemma")){
					type = OutlineItem.Type.Lemma;
					str2=str2.replaceFirst("Lemma", "");
				}
				else if (str2.startsWith("Axiom")){
					type = OutlineItem.Type.Axiom;
					str2=str2.replaceFirst("Axiom", "");
				}
				else if (str2.startsWith("Definition")) {
					type = OutlineItem.Type.Definition;
					str2=str2.replaceFirst("Definition", "");
				}
				else if (str2.startsWith("Parameter")) {
					type = OutlineItem.Type.Parameter;
					str2=str2.replaceFirst("Parameter", "");
				}
				else if (str2.startsWith("Theorem")) {
					type = OutlineItem.Type.Theorem;
					str2=str2.replaceFirst("Theorem", "");
				}
				else if (str2.startsWith("Variable")) {
					type = OutlineItem.Type.Variable;
					str2=str2.replaceFirst("Variable", "");
				}
				OutlineItem child = new OutlineItem(type,str2,
						region.getOffset(),region.getLength());
				input.addChil(child);
				position = region.getOffset()+region.getLength()+1;
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void createControl(Composite parent){
		super.createControl(parent);
		System.out.println("create");	
		TreeViewer viewer = this.getTreeViewer();
		viewer.setContentProvider(new ContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		this.refresh();
		this.addSelectionChangedListener(this);
		viewer.setInput(input);
		viewer.refresh();
		viewer.getTree().update();
	}
	
	public void selectionChanged(SelectionChangedEvent event) {
		//super.selectionChanged(event);
		System.out.println("Selection changed "+event.toString());
	}

}
