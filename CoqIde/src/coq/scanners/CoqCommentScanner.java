package coq.scanners;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WordRule;

import coq.definitions.CoqToken;



public class CoqCommentScanner extends RuleBasedScanner {
	
	public CoqCommentScanner(){
		
		WordRule rule = new WordRule(new CoqCommentDetector(), 
			CoqToken.comment);
		MultiLineRule rule2 = 
			new MultiLineRule("(*","*)",CoqToken.comment);
		IRule[] frules = new IRule[] {rule,rule2};
		setRules(frules);
	}
}
