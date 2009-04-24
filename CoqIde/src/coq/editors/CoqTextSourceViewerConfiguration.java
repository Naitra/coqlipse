package coq.editors;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

import coq.document.CommentScanner;
import coq.document.PartitionScanner;
import coq.document.Scanner;



public class CoqTextSourceViewerConfiguration 
	extends TextSourceViewerConfiguration{
	
	private Scanner coqscanner;
	private CoqTextDoubleClickStrategy doubleClickStrategy;
	
	//public CoqTextSourceViewerConfiguration(){
		
	//}
	
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			PartitionScanner.COQ_COMMENT};
	}
	
	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy(
			ISourceViewer sourceViewer, String contentType){
		if (doubleClickStrategy==null){
			doubleClickStrategy = new CoqTextDoubleClickStrategy();
		}		
		return doubleClickStrategy;
		
	}
	
	public Scanner getCoqScanner(){
		if (coqscanner==null){
			coqscanner=new Scanner();
		}
		return coqscanner;
	}
	
	// Returns the presentation reconciler ready to be used 
	// with the given source viewer.

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer){
		ContentAssistant contentAssistant = new ContentAssistant();
		contentAssistant.setContentAssistProcessor(
				new CoqContentAssistant(), 
				IDocument.DEFAULT_CONTENT_TYPE);
		contentAssistant.enableAutoActivation(true);
		contentAssistant.setAutoActivationDelay(500);
		contentAssistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
		return contentAssistant;	
	}
	
	@Override
	public IPresentationReconciler getPresentationReconciler(
				ISourceViewer sourceViewer){
		PresentationReconciler reconciler=
			new CoqPresentationReconciler();
		//reconciler.setDocumentPartitioning(
		//		getConfiguredDocumentPartitioning(sourceViewer));
		
		DefaultDamagerRepairer dr=
			new DefaultDamagerRepairer(getCoqScanner());
		
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		
		DefaultDamagerRepairer dr2=
			new DefaultDamagerRepairer(new CommentScanner());
		reconciler.setDamager(dr2, PartitionScanner.COQ_COMMENT);
		reconciler.setRepairer(dr2, PartitionScanner.COQ_COMMENT);
		
		return reconciler;
	}
	
	
		
}
