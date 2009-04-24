package coq.document;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WordRule;

import coq.definitions.Constants;;



public class CommentScanner extends RuleBasedScanner {
	
	public CommentScanner(){
		
		WordRule rule = new WordRule(new CommentDetector(), 
			Constants.comment);
		MultiLineRule rule2 = 
			new MultiLineRule("(*","*)",Constants.comment);
		IRule[] frules = new IRule[] {rule,rule2};
		setRules(frules);
	}
}
