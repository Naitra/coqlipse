package coq.views.outline;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class ContentProvider implements ITreeContentProvider{

	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		if (parentElement instanceof OutlineItem)
			return ((OutlineItem) parentElement).getChildren();
			//System.out.println("provider getChildren");
		return null;
	}

	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		//System.out.println("provider getParent");
		if (element instanceof OutlineItem)
			return ((OutlineItem) element).getParent();
		return null;
	}

	public boolean hasChildren(Object element) {
		//System.out.println("provider hasChildren");
		// TODO Auto-generated method stub
		if (element instanceof OutlineItem)
			return ((OutlineItem) element).hasChildren();
		
		return false;
	}

	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		//System.out.println("provider getElements");
		if (inputElement instanceof OutlineItem)
			return ((OutlineItem) inputElement).getChildren();
		return new Object[]{inputElement};
	}

	public void dispose() {
		//System.out.println("provider dispose");
		// TODO Auto-generated method stub
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//System.out.println("provider inputChanged");
		// TODO Auto-generated method stub
		
	}


	
}
