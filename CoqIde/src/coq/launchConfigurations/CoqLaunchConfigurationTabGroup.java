package coq.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;


public class CoqLaunchConfigurationTabGroup extends
		AbstractLaunchConfigurationTabGroup {

	public CoqLaunchConfigurationTabGroup() {
		// TODO Auto-generated constructor stub
	}

	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		// TODO Auto-generated method stub
		ILaunchConfigurationTab[] tab = 
			new ILaunchConfigurationTab[]{
				//new CommonTab(),
				new CoqTab()
			};
		this.setTabs(tab);
	}

}
