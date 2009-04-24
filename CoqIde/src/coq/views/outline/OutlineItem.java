package coq.views.outline;

import java.util.LinkedList;

public class OutlineItem {
	public enum Type{
		Dummy,
		Axiom,
		Definition,
		Lemma,
		Theorem,
		Section,
		Variable,
		Parameter
	}
	
	
	public String name;
	public Type type;
	int start, end;
	
	public OutlineItem parent;
	public LinkedList<OutlineItem> children;
	
	public OutlineItem(Type type, String name, int start, int end){
		this.type = type;
		this.name = name;
		this.start = start;
		this.end = end;
		this.parent=null;
		this.children = new LinkedList<OutlineItem>();
	}
	
	public void setParent(OutlineItem parent){
		this.parent=parent;
	}
	
	public void addChil(OutlineItem child){
		children.add(child);
	}
	
	public Object getParent(){
		return parent;
	}
	
	public Object[] getChildren(){
		return children.toArray();
	}
	
	public boolean hasChildren(){
		return (children.size()>0);
	}
}
