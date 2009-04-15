package coq.editors;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;


public class CoqContentAssistant implements IContentAssistProcessor {

	String proposals[] = new String[]{"toto"};
	
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		ICompletionProposal result[] = new ICompletionProposal[proposals.length];
		for (int i=0;i<proposals.length;i++){
			result[i] = new CompletionProposal(proposals[i],offset,0,proposals[i].length()); 
		}
		return result;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return new char[] {'?'};
	}

	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return new char[] {'u'};
	}

	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
