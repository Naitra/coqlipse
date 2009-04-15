package coq.editors;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.presentation.PresentationReconciler;


public class CoqPresentationReconciler extends PresentationReconciler {
	
	public CoqPresentationReconciler(){
		super();
	}
	@Override
	public void install(ITextViewer viewer){
		super.install(viewer);
;	}
	
}
