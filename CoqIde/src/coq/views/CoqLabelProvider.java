package coq.views;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import coq.plugin.CoqPluginImageRegistry;

public class CoqLabelProvider implements ILabelProvider{

	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		//System.out.println("getImage");
		//Image image = new Image(null, null);
		//Image image = ImageDescriptor.getMissingImageDescriptor()
		Image image=null;
		if (element instanceof CoqItem){
			if (((CoqItem) element).type == CoqItem.Type.Lemma)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_LEMMA);
			if (((CoqItem) element).type == CoqItem.Type.Axiom)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_AXIOM);
			if (((CoqItem) element).type == CoqItem.Type.Definition)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_DEFINITION);
			if (((CoqItem) element).type == CoqItem.Type.Parameter)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_PARAMETER);
			if (((CoqItem) element).type == CoqItem.Type.Theorem)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_THEOREM);
			if (((CoqItem) element).type == CoqItem.Type.Variable)
				image = CoqPluginImageRegistry.
					getImage(CoqPluginImageRegistry.COQ_VARIABLE);
		}
		return image;
	}

	public String getText(Object element) {
		// TODO Auto-generated method stub
		//System.out.println(element.getClass().toString());
		if (element instanceof CoqItem)
			return ((CoqItem) element).name;
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
