package coq.popupMenus;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import coq.definitions.MakeError;
import coq.plugin.CoqPlugin;

public class CoqProjectAction implements IObjectActionDelegate {
	
	IProject project;
	LinkedList<IFolder> folders = new LinkedList<IFolder>();
	LinkedList<IFile> files = new LinkedList<IFile>();
	
	String coqbins = 
		"COQBIN:=/opt/local/bin/ CAMLBIN:=/usr/local/bin/ CAMLP4BIN:=/usr/local/bin/";
	

	public CoqProjectAction() {
		// TODO Auto-generated constructor stub
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}
	
	public void getFilesAndFolders(IResource resource){
		LinkedList<IResource> resources = new LinkedList<IResource>();
		files.clear();
		folders.clear();
		resources.add(resource);
		while (resources.size()>0){
			IResource current = resources.removeFirst();
			if (current instanceof IWorkspaceRoot);
			if (current instanceof IProject){
				IResource members[];
				try {
					members = ((IProject) current).members();
					for (int i =0;i<members.length;i++)
						resources.add(members[i]);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (current instanceof IFolder){
				folders.add((IFolder) current);
				IResource members[];
				try {
					members = ((IFolder) current).members();
					for (int i =0;i<members.length;i++)
						resources.add(members[i]);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (current instanceof IFile)
				files.add((IFile) current);
		}
		this.files = files; 
	}
	
	public void runCommand(String command){
		System.out.println("Command "+command);
		try {
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			if (p.getInputStream().available()>0){
				byte tab[] = new byte[p.getInputStream().available()];
				p.getInputStream().read(tab);
				String message="";
				for (int i=0;i<tab.length;i++) message+=(char)tab[i];
				MakeError errors[] = MakeError.extract(message);
				if (errors!=null){
					for (int i = 0;i<errors.length;i++){
						String fMessage = 
							"File " + errors[i].filename+
							", Line " + errors[i].line+ 
							", Offset "+ errors[i].startOffset+"-"+
							errors[i].endOffset+
							errors[i].message;
						CoqPlugin.logError(fMessage);
					}
				}		
			}
			else CoqPlugin.logInfo("Build successful");
		} catch (IOException e) {
			CoqPlugin.logError("Makefile process error(1) ");
		} catch (InterruptedException e) {
			CoqPlugin.logError("Makefile process error(2) ");
		}
	}
	
	private void genMakefile(){
		IPath projectPath = project.getLocation();
		
		getFilesAndFolders(project);
		
		// Get coq files from the project
		String file_list = " ";
		for (int i=0;i<files.size();i++){
			if ("v".equals(files.get(i).getFileExtension())){
				IPath path = files.get(i).getFullPath();
				String coq_file = path.removeFirstSegments(1).toString();
				file_list += " "+coq_file;
			}
		}
		
		// Makefile 
		String makefile = projectPath.toString()+"/Makefile";
		
		//Delete old Makefile
		File oldMakefile = new File(makefile);
		if (oldMakefile.exists()) oldMakefile.delete();
		
		String parameters = " -no-install "+file_list +" -o " +makefile;
		String command = CoqPlugin.getCoqMakePath() + parameters;
		
		runCommand(command);
	}
	
	public void clean(){
		IPath projectPath = project.getLocation();
		String makefile = projectPath.toString()+"/Makefile";
		String directory = project.getLocation().toString();

		if (new File(makefile).exists()){
			String command = "make -s -i "+ coqbins + " clean -C "+directory;
			runCommand(command);
			CoqPlugin.logInfo("Project cleaned");
		}
		else CoqPlugin.logWarning("nothing to clean");
		
	}
	
	public void makeHTML(){
		IPath projectPath = project.getLocation();
		String makefile = projectPath.toString()+"/Makefile";
		String directory = project.getLocation().toString();

		if (!new File(makefile).exists()){
			genMakefile();
		};
		String command = "make -s -i "+ coqbins+ " gallinahtml -C "+directory;
		runCommand(command);
	}
	
	public void make(){
		IPath projectPath = project.getLocation();
		String makefile = projectPath.toString()+"/Makefile";
		String directory = project.getLocation().toString();
		assert(new File(makefile).exists());
		System.out.println("makefile exists");
		String command = "make -s -i "+coqbins + " -C "+directory;
		runCommand(command);
	}

	public void run(IAction action) {
		
		//String make = CoqPlugin.getCoqMakePath();
		
		if (action.getId().toString().equals("coq_make_action")){
			//System.out.println("Make");
			genMakefile();
			make();
		}
		else if (action.getId().toString().equals("coq_clean_action")){
			//System.out.println("Make clean");
			clean();
		}
		else if (action.getId().toString().equals("coq_doc_action")){
			//System.out.println("Make doc");
			makeHTML();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		 if (selection instanceof TreeSelection) {
			 TreeSelection sel = (TreeSelection) selection;
			 Object obj = sel.getFirstElement();
			 if (obj instanceof IProject) project = (IProject) obj;
			 else project = null;		 
			 action.setEnabled(true);
		 }
	}

}
