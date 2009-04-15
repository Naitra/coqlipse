package coq.preferences;

import org.eclipse.core.internal.resources.File;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import coq.plugin.CoqPlugin;


public class CoqPathsWorkbenchPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {
	
	DirectoryFieldEditor fieldDir;
	FileFieldEditor fieldCoqc;
	FileFieldEditor fieldByte;
	FileFieldEditor fieldOpt;
	FileFieldEditor fieldMake;
	FileFieldEditor fieldDep;
	FileFieldEditor fieldDoc;

	public CoqPathsWorkbenchPreferencePage() {
		super(FieldEditorPreferencePage.GRID);
		this.setPreferenceStore(CoqPlugin.getInstance().getPreferenceStore());
		this.setDescription("Please fill in all fields with " +
				"the correct paths on your system");
		// TODO Auto-generated constructor stub
	}

	public CoqPathsWorkbenchPreferencePage(int style) {
		super(style);
		// TODO Auto-generated constructor stub
	}

	public CoqPathsWorkbenchPreferencePage(String title, int style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}

	public CoqPathsWorkbenchPreferencePage(String title, ImageDescriptor image,
			int style) {
		super(title, image, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createFieldEditors() {
		
		Group group;
		GridLayout gLayout;
		GridData gData;

		
		group = new Group(this.getFieldEditorParent(),SWT.NONE);
		group.setText("Coq binaries directory");
		gLayout = new GridLayout();
		gLayout.numColumns=2;
		gData = new GridData(SWT.FILL,SWT.FILL,true,false);
		gData.horizontalSpan=2;
		group.setLayoutData(gData);
		fieldDir = 
			new DirectoryFieldEditor(PreferenceConstants.COQ_GLOBAL_PATH,"binaries",group);
		this.addField(fieldDir);
		Button apply = new Button(this.getFieldEditorParent(),SWT.PUSH);
		apply.setText("Apply changes");		
		
		group = new Group(this.getFieldEditorParent(),SWT.NONE);
		group.setText("Coq compiler");
		gLayout = new GridLayout();
		gLayout.numColumns=3;
		gData = new GridData(SWT.FILL,SWT.FILL,true,false);
		gData.horizontalSpan=3;
		group.setLayoutData(gData);
		fieldCoqc = new FileFieldEditor(PreferenceConstants.COQC_PATH,"coqc",group);
		this.addField(fieldCoqc);
		
		group = new Group(this.getFieldEditorParent(),SWT.NONE);
		group.setText("Coq toplevel");
		gLayout = new GridLayout();
		gLayout.numColumns=3;
		gData = new GridData(SWT.FILL,SWT.FILL,true,false);
		gData.horizontalSpan=3;
		group.setLayoutData(gData);
		fieldByte = new FileFieldEditor(PreferenceConstants.COQTOP_BYTE_PATH, 
				"coqtop.byte", group);
		this.addField(fieldByte); 
		fieldOpt = new FileFieldEditor(PreferenceConstants.COQTOP_OPT_PATH, 
					"coqtop.opt", group);
		this.addField(fieldOpt);
	
		group = new Group(this.getFieldEditorParent(),SWT.NONE);
		group.setText("Coq tools");
		gLayout = new GridLayout();
		gLayout.numColumns=3;
		gData = new GridData(SWT.FILL,SWT.FILL,true,false);
		gData.horizontalSpan=3;
		group.setLayoutData(gData);
		fieldMake = new FileFieldEditor(PreferenceConstants.COQ_MAKEFILE_PATH, 
				"coq_makefile", group);
		this.addField(fieldMake);
		fieldDep = new FileFieldEditor(PreferenceConstants.COQDEP_PATH, 
				"coqdep", group);
		this.addField(fieldDep);
		fieldDoc = new FileFieldEditor(PreferenceConstants.COQDOC_PATH, 
			"coqdoc", group);
		this.addField(fieldOpt);
		
		apply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pathPrefix();
			}

		});
	
	}
	
	public void pathPrefix(){
		String path = fieldDir.getStringValue().trim();
		System.out.println("P :"+path);
		if (path.endsWith("/"))
			path = path.substring(0, path.length()-1);
		fieldCoqc.setStringValue(path+"/coqc");
		fieldByte.setStringValue(path+"/coqtop.byte");
		fieldOpt.setStringValue(path+"/coqtop.opt");
		fieldMake.setStringValue(path+"/coq_makefile");
		fieldDep.setStringValue(path+"/coqdep");
		fieldDoc.setStringValue(path+"/coqdoc");
		
	}

	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

}
