package coq.plugin;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public class CoqPluginImageRegistry{

	public static final String COQ_LEMMA = "Lemma.png";
	public static final String COQ_DEFINITION = "Definition.png";
	public static final String COQ_THEOREM = "Theorem.png";
	public static final String COQ_IMPORT = "Import.png";
	public static final String COQ_PARAMETER = "Parameter.png";
	public static final String COQ_AXIOM = "Axiom.png";
	public static final String COQ_VARIABLE = "Variable.png";
	
	public static Image getImage(String name){
		String iconsPath = "icons/";
		URL installUrl = CoqPlugin.getInstallURL();
		try {
			URL url = new URL(installUrl,iconsPath+name);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
			return imageDescriptor.createImage();
		} catch (MalformedURLException e) {
			CoqPlugin.logError("Can't find image");
			return ImageDescriptor.getMissingImageDescriptor().createImage();
			// TODO Auto-generated catch block
		}
	}
}
