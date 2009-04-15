package coq.views;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;



public class CoqTopErrorView extends ViewPart {
	StyledText text;
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		Color color = new Color(Display.getCurrent(),240,255,240);
		text = new StyledText(parent,SWT.BORDER);
		text.setBackground(color);
	}

	public void setError(String str){
		Color color = new Color(Display.getCurrent(),255,240,240);
		text.setBackground(color);
		text.setText(str);
	}
	
	public void setContent(String str){
		Color color = new Color(Display.getCurrent(),240,255,240);
		text.setBackground(color);
		text.setText(str);
	}
	
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
