package coq.launchConfigurations;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CoqTab extends AbstractLaunchConfigurationTab {

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite container = new Composite(parent,SWT.NONE);
		setControl(container);
		
		GridLayout layout = new GridLayout();
		layout.numColumns=2;
		container.setLayout(layout);
		
		Label label = new Label(container, SWT.NULL);
		label.setText("Coqc");
		Text text = new Text(container,SWT.NULL);
		text.setText("/opt/local/bin/coqc");
		
		createVerticalSpacer(container, 2);
		
		Button button1 = new Button(container,SWT.RADIO);
		button1.setText("native code");
		Button button2 = new Button(container,SWT.RADIO);
		button2.setText("byte code");
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "Coq configuration";
	}

	public void initializeFrom(ILaunchConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub

	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub

	}

}
