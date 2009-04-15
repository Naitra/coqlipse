package coq.plugin;

import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import coq.preferences.PreferenceConstants;

public class CoqPlugin extends AbstractUIPlugin {

	private static CoqPlugin instance = null;
	private static IPreferenceStore preferenceStore = null;
	
	// Plugins basic methods
	
	public CoqPlugin() {
		super();
		if (instance != null) logError("Coq plugin already loaded");
		else {
			instance = this;
			//
			//logInfo("Coq plugin loaded");
		}
	}
	
	public void start(BundleContext context) throws Exception{
		super.start(context);
	}
	
	public void stop(BundleContext context) throws Exception{
		super.stop(context);
	}
	
	// Plugins logs
	
	
	public static void logInfo(String message){
		instance.getLog().log(new Status(IStatus.INFO,"coq",0,message,null));
	}
	
	public static void logWarning(String message){
		instance.getLog().log(new Status(IStatus.WARNING,"coq",0,message,null));
	}

	public static void logError(String message){
		instance.getLog().log(new Status(IStatus.ERROR,"coq",0,message,null));
	}
	
	// this plugin (pattern singleton)
	
	public static CoqPlugin getInstance(){
		return instance;
	}
	
	// Plugins paths
	
	public static String getCoqcPath(){
		preferenceStore = instance.getPreferenceStore();
		return 
		preferenceStore.getString(PreferenceConstants.COQC_PATH).trim();
	}
	
	public static String getCoqtopByte(){
		preferenceStore = instance.getPreferenceStore();
		return 
		preferenceStore.getString(PreferenceConstants.COQTOP_BYTE_PATH).trim();
	}
	
	public static String getCoqtopOpt(){
		preferenceStore = instance.getPreferenceStore();
		return 
		preferenceStore.getString(PreferenceConstants.COQTOP_OPT_PATH).trim();
	}
	
	public static String getCoqDoc(){
		preferenceStore = instance.getPreferenceStore();
		return 
		preferenceStore.getString(PreferenceConstants.COQDOC_PATH).trim();
	}
	
	public static String getCoqDep(){
		preferenceStore = instance.getPreferenceStore();
		return 
		preferenceStore.getString(PreferenceConstants.COQDEP_PATH).trim();
	}
	
	public static String getCoqMakePath(){
		preferenceStore = instance.getPreferenceStore();
		return
		preferenceStore.getString(PreferenceConstants.COQ_MAKEFILE_PATH).trim();
	}
	
	public static URL getInstallURL() {
		return instance.getBundle().getEntry("/");
	}
}
