package coq.definitions;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;


public interface CoqConstant {
	
	public static final Color COLOR_THEOREM =
		new Color(Display.getCurrent(),255,200,0);
	
	public static final Color COLOR_TACTIC =
		new Color(Display.getCurrent(),0,0,128);
	
	public static final Color COLOR_TYPE =
		new Color(Display.getCurrent(),0,128,0);
	
	public static final Color COLOR_KEYWORD =
		new Color(Display.getCurrent(),0,0,128);
	
	public static final Color COLOR_COMMENT =
		new Color(Display.getCurrent(),0,128,0);
	
	public static final Color COLOR_OTHER =
		new Color(Display.getCurrent(),0,0,0);
	
}
