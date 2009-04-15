package coq.consoles;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.part.IPageBookViewPage;


public class CoqConsole implements IConsole {

	public void addPropertyChangeListener(IPropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	public IPageBookViewPage createPage(IConsoleView view) {
		return null;
	}

	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		
		return "Compilation";
	}

	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removePropertyChangeListener(IPropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

}
