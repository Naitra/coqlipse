package coq.popupMenus;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import coq.plugin.CoqPlugin;


public class CoqFileAction implements IObjectActionDelegate {

	//private Shell shell;
	IProject project;
	IFile file;
	
	/**
	 * Constructor for Action1.
	 */
	public CoqFileAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		//shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		assert (file != null) : "Null file";
		String fileName = file.getLocation().toString();
		String command = "/opt/local/bin/coqc "+ fileName;
		
		//IConsoleView consoleView;
		//MessageConsole console = new MessageConsole("Compiler",null);
		//CoqConsole console2 = new CoqConsole();
		//IWorkbenchPage page = 
		//	PlatformUI.getWorkbench().getActiveWorkbenchWindow().
		//	getActivePage();
		//String CONSOLE_VIEW_ID = IConsoleConstants.ID_CONSOLE_VIEW;
		
		try {
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			if (p.getInputStream().available()>0){
				byte tab[] = new byte[p.getInputStream().available()];
				p.getInputStream().read(tab);
				String message="";
				for (int i=0;i<tab.length;i++) message+=(char)tab[i];
			}
			else {
				CoqPlugin.logInfo(
				"Successfull compilation of "+file.getName());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//try {
		//	 ConsolePlugin plugin = ConsolePlugin.getDefault();
		 //    IConsoleManager conMan = plugin.getConsoleManager();
		 //    conMan.addConsoles(new IConsole[]{console2});
		//	MessageConsoleStream out = console.newMessageStream();
		//	out.println("Hello");
		//	consoleView = 
		//		(IConsoleView) page.showView(CONSOLE_VIEW_ID);
		//	consoleView.display(console);
		//	console.activate();
		//	System.out.println(consoleView.getTitle());
		//	System.out.println("Found console view");
		//} catch (PartInitException e1) {
		//	// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
		
		//Process compiler;
		// Get current selection
		//PlatformUI.getWorkbench().getActiveWorkbenchWindow().
		//	getSelectionService().
		//ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().
		//	getSelectionService().getSelection();
		//try {
		//compiler = Runtime.getRuntime().exec("pwd");
		//	CoqOutputStream output = new CoqOutputStream(compiler.getOutputStream());
		//CoqInputStream input = new CoqInputStream(compiler.getInputStream());
		//	ErrorStream error = new ErrorStream(compiler.getErrorStream());
		//	System.out.println(error.read());
		//while(true){
		//System.out.println(input.read());
		//}
		//
		//} catch (IOException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//} 
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		 if (selection instanceof TreeSelection) {
			 TreeSelection sel = (TreeSelection) selection;
			 Object obj = sel.getFirstElement();
			 if (obj instanceof IFile) file = (IFile) obj;
			 else file = null;
			 action.setEnabled(true);
		 }
	}
}
