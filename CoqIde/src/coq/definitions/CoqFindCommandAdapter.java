package coq.definitions;

import java.util.LinkedList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

public class CoqFindCommandAdapter {
	
	IDocument document;
	
	public CoqFindCommandAdapter(IDocument document){
		this.document=document;
	}
	
	public IRegion findTerm(int offset) throws BadLocationException{
		IRegion next;
		FindReplaceDocumentAdapter finder =
			new FindReplaceDocumentAdapter(document);
		return finder.find(offset, ".", true, true, false, false);
	}
	
	public IRegion getNextCommand(int offset) throws BadLocationException{
		FindReplaceDocumentAdapter finder =
			new FindReplaceDocumentAdapter(document);
		IRegion next = findTerm(offset);
		if (next != null)
			while ( (next.getOffset()+1<document.getLength())
					&& (document.getChar(next.getOffset()+1)=='('))
				next = findTerm(next.getOffset()+1);
		return next;
		
	}
	
	public IRegion[] getAllCommand(){
		LinkedList<IRegion> commands = new LinkedList<IRegion>();
		int offset = 0;
		try {
			while (offset < document.getLength()){
				IRegion next = getNextCommand(offset);
				if (next==null) break;
				commands.add(next);
				offset = next.getOffset()+1;
			} 
		}catch (BadLocationException e){ 
			return null;
		}
		if (commands.size()>0)
			return (IRegion []) commands.toArray();
		else return null;
	}
}