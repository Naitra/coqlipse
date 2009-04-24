package coq.views.outline;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import coq.plugin.CoqPluginImageRegistry;

public class LabelProvider implements ILabelProvider{

	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		//System.out.println("getImage");
		//Image image = new Image(null, null);
		//Image image = ImageDescriptor.getMissingImageDescriptor()
		Image image=null;
		if (element instanceof OutlineItem){
			if (((OutlineItem) element).type == OutlineItem.Type.Lemma)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_LEMMA);
			if (((OutlineItem) element).type == OutlineItem.Type.Axiom)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_AXIOM);
			if (((OutlineItem) element).type == OutlineItem.Type.Definition)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_DEFINITION);
			if (((OutlineItem) element).type == OutlineItem.Type.Parameter)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_PARAMETER);
			if (((OutlineItem) element).type == OutlineItem.Type.Theorem)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_THEOREM);
			if (((OutlineItem) element).type == OutlineItem.Type.Variable)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_VARIABLE);
		}
		return image;
	}

	public String getText(Object element) {
		// TODO Auto-generated method stub
		//System.out.println(element.getClass().toString());
		if (element instanceof OutlineItem)
			return ((OutlineItem) element).name;
		else return "undefined";	
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		//System.out.println("addListener");
		
	}

	public void dispose() {
		//System.out.println("dispose");
		// TODO Auto-generated method stub
		
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		//System.out.println("isLabelProperty");
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		System.out.println("remove Listener");
		// TODO Auto-generated method stub
		
	}

}
