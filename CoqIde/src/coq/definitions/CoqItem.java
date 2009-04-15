package coq.definitions;

public interface CoqItem {

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
	
}
