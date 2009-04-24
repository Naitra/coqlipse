package coq.document;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WordRule;

import coq.definitions.Constants;

public class Scanner extends RuleBasedScanner {
	
	public Scanner(){
		
		//Creates a rule which, with the help of a word detector, 
		//will return the token associated with the detected word.
		
		WordRule rule = new WordRule(new WordDetector(), 
				Constants.other);
		
		for (String k: Constants.AUX){
			rule.addWord(k, Constants.keyword);
		}
		for (String k: Constants.DECLARATION_KEYWORD){
			rule.addWord(k, Constants.theorem);
		}
		
		for (String k: Constants.FIX_KEYWORD){
			rule.addWord(k, Constants.theorem);
		}
		
		for (String k: Constants.INDUCTIVE_KEYWORD){
			rule.addWord(k, Constants.theorem);
		}
		
		for (String k: Constants.PROOF_KEYWORD){
			rule.addWord(k, Constants.theorem);
		}
		
		for (String k: Constants.SPECIAL_TOKENS){
			rule.addWord(k, Constants.type);
		}
		
		for (String k: Constants.STATEMENT_KEYWORD){
			rule.addWord(k, Constants.theorem);
		}
		
		for (String k: Constants.TACTICS){
			rule.addWord(k, Constants.tactic);
		}
		
		for (String k: Constants.TERM_KEYWORD){
			rule.addWord(k, Constants.type);
		}
		
		//MultiLineRule rule1 = 
		//	new MultiLineRule("(*","*)",comment);
		//SingleLineRule rule2 = 
		//	new SingleLineRule("(*","*)",comment);
		IRule[] frules = new IRule[] {rule};
		setRules(frules);
		
		
	}
	
	
	
}
