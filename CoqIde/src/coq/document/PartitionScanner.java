package coq.document;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;



public class PartitionScanner extends RuleBasedPartitionScanner {
	
	public final static String COQ_COMMENT = "__coq_comment";
	
	public PartitionScanner() {

		IToken coqComment = new Token(COQ_COMMENT);
		
		IPredicateRule[] rules = new IPredicateRule[]{
			new MultiLineRule("(*", "*)", coqComment)
		};
		

		setPredicateRules(rules);
	}
}
