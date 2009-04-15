package coq.scanners;

import org.eclipse.jface.text.rules.IWordDetector;

public class CoqWordDetector implements IWordDetector {

	public boolean isWordPart(char c) {
		return Character.isLetter(c);
	}

	public boolean isWordStart(char c) {
		return Character.isLetterOrDigit(c) || c=='_';
	}

}
