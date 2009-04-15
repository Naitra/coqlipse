package coq.newWizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;


public class CoqNewWizard extends Wizard implements INewWizard {
	private CoqNewWizardPage page;
	private ISelection selection;
	public CoqNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		page = new CoqNewWizardPage(selection);
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		return false;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

}
