package coq.views.toplevel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;


public class CoqTopView extends ViewPart {
	
	StyledText text;
	@Override
	public void createPartControl(Composite parent) {
		Display display = Display.getCurrent();
		
		//java.awt.Font[] fonts =
		//	GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		//for (int i=0;i<fonts.length;i++)
		//	System.out.println("#Font: "+fonts[i].getFontName());
		Color color = new Color(display,240,240,255);
		Font font = new Font(display, "Courier", 12, SWT.NORMAL);
		text = new StyledText(parent,SWT.BORDER);
		text.setBackground(color);
		text.setFont(font);
	}

	@Override
	public void setFocus() {
	
	}
	
	public void setContent(String str){
		text.setText(str);
	}
}
