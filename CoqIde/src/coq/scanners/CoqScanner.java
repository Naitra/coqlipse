package coq.scanners;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WordRule;

import coq.definitions.CoqItem;
import coq.definitions.CoqToken;


public class CoqScanner extends RuleBasedScanner {
	
	public CoqScanner(){
		
		//Creates a rule which, with the help of a word detector, 
		//will return the token associated with the detected word.
		
		WordRule rule = new WordRule(new CoqWordDetector(), 
				CoqToken.other);
		
		for (String k: CoqItem.AUX){
			rule.addWord(k, CoqToken.keyword);
		}
		for (String k: CoqItem.DECLARATION_KEYWORD){
			rule.addWord(k, CoqToken.theorem);
		}
		
		for (String k: CoqItem.FIX_KEYWORD){
			rule.addWord(k, CoqToken.theorem);
		}
		
		for (String k: CoqItem.INDUCTIVE_KEYWORD){
			rule.addWord(k, CoqToken.theorem);
		}
		
		for (String k: CoqItem.PROOF_KEYWORD){
			rule.addWord(k, CoqToken.theorem);
		}
		
		for (String k: CoqItem.SPECIAL_TOKENS){
			rule.addWord(k, CoqToken.type);
		}
		
		for (String k: CoqItem.STATEMENT_KEYWORD){
			rule.addWord(k, CoqToken.theorem);
		}
		
		for (String k: CoqItem.TACTICS){
			rule.addWord(k, CoqToken.tactic);
		}
		
		for (String k: CoqItem.TERM_KEYWORD){
			rule.addWord(k, CoqToken.type);
		}
		
		//MultiLineRule rule1 = 
		//	new MultiLineRule("(*","*)",comment);
		//SingleLineRule rule2 = 
		//	new SingleLineRule("(*","*)",comment);
		IRule[] frules = new IRule[] {rule};
		setRules(frules);
		
		
	}
	
	
	
}
