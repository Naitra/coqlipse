package coq.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CoqWorkbenchPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	public CoqWorkbenchPreferencePage() {
		// TODO Auto-generated constructor stub
	}

	public CoqWorkbenchPreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public CoqWorkbenchPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		System.out.println("Preferences");
	}

}
