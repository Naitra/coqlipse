package coq.definitions;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;

public interface CoqToken {
	
	public static final Token theorem = 
		new Token(new TextAttribute(
				CoqConstant.COLOR_THEOREM,null,SWT.BOLD));
	
	public static final Token tactic = 
		new Token(new TextAttribute(
				CoqConstant.COLOR_TACTIC,null,SWT.BOLD));
	
	public static final Token type = 
		new Token(new TextAttribute(
				CoqConstant.COLOR_TYPE,null,SWT.BOLD));
	
	public static final Token keyword = 
		new Token(new TextAttribute(
				CoqConstant.COLOR_KEYWORD,null,SWT.BOLD));
	
	public static final Token comment 
		= new Token(new TextAttribute(
				CoqConstant.COLOR_COMMENT,null,SWT.BOLD));
	
	public static final Token other 
		= new Token(new TextAttribute(
				CoqConstant.COLOR_OTHER,null,SWT.BOLD));
}
