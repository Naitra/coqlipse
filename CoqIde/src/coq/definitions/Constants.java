package coq.definitions;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;


public interface Constants {
	
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
	

	public static final String[] DECLARATION_KEYWORD = new String[]{
		"Axiom", "Conjecture",
		"Parameter",  "Parameters",
		"Variable", "Variables",
		"Hypothesis", "Hypotheses"
	};
	
	public static final String [] INDUCTIVE_KEYWORD = new String[]{
		"Record", "Structure",
		"Inductive", "CoInductive"
	};
	
	public static final String [] STATEMENT_KEYWORD = new String[]{
		"Theorem", "Lemma", "Fact", "Remark", "Proposition",
		"Corollary", "Definition", "Let"
	};
	
	public static final String [] PROOF_KEYWORD = new String[]{
		"Proof", "Qed", "Defined", "Admitted"
	};
	
	public static final String [] FIX_KEYWORD = new String[]{
		"FixPoint", "CoFixPoint"
	};
	
	public static final String [] TERM_KEYWORD = new String[]{
		"_", "as", "at", "cofix", "else", "end",
		"exists", "exists2", "fix",	"for", "forall", "fun",
		"if", "IF", "in", "let", "match", "mod",
		"Prop",	"return", "Set", "then", "Type", "using",
		"where", "with", ":=", ":", "=>", "@", "%",
		"by", "fun", "->", "<->", "/\\","\\/","~",
	};
	
	public static final String [] SPECIAL_TOKENS = new String[]{
		"!", "%", "&", "&&", "(", "()", ")",
		"*", "+", "++",	",", "-", "->",	".",
		".(", "..",	"/", "/\\", ":", "::", ":<",
		":=", ":>",	";", "<", "<-",	"<->", "<:",
		"<=", "<>",	"=", "=>", "=_D", ">", ">->",
		">=", "?", "?=", "@", "[", "\\/", "]",
		"^", "{", "|", "|-", "||", "}",	"~"
	};
	
	public static final String [] AUX = new String[] {
		"Require", "Import", "Export","Open",
		"Print", "Set", "Unset", "Test",
		"Scope","Ltac","Section", "Module", "End",
		"Hint"
	};
	
	public static final String [] TACTICS = new String[] {
		"abstract",
		"absurd",
		"admit",
		"apply",
		"assert",
		"assumption",
		"auto",
		"autorewrite",
		
		"induction", "destruct","simpl","intro","intros",
		"try", "discriminate","auto","inversion","eauto",
		"left","right","fail","constructor","split",
	};
	
	

	public static final String COQ_MAKE_ACTION = "coq_make_action";
	public static final String COQ_CLEAN_ACTION = "coq_clean_action";
	public static final String COQ_DOC_ACTION = "coq_doc_action"; 
	
	
	public static final Token theorem = 
		new Token(new TextAttribute(
				Constants.COLOR_THEOREM,null,SWT.BOLD));
	
	public static final Token tactic = 
		new Token(new TextAttribute(
				Constants.COLOR_TACTIC,null,SWT.BOLD));
	
	public static final Token type = 
		new Token(new TextAttribute(
				Constants.COLOR_TYPE,null,SWT.BOLD));
	
	public static final Token keyword = 
		new Token(new TextAttribute(
				Constants.COLOR_KEYWORD,null,SWT.BOLD));
	
	public static final Token comment 
		= new Token(new TextAttribute(
				Constants.COLOR_COMMENT,null,SWT.BOLD));
	
	public static final Token other 
		= new Token(new TextAttribute(
				Constants.COLOR_OTHER,null,SWT.BOLD));
	
}
