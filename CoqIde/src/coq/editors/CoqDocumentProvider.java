package coq.editors;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

import coq.scanners.CoqPartitionScanner;

public class CoqDocumentProvider extends FileDocumentProvider{

	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new CoqPartitionScanner(),
					new String[] {
						CoqPartitionScanner.COQ_COMMENT});
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		document.addDocumentListener(new CoqDocumentListener());
		return document;
	}
}
