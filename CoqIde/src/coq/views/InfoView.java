package coq.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

public class InfoView extends ViewPart {

	Table table;
	StyledText text;
	
	
	public InfoView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		table = new Table(parent,SWT.SINGLE);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 450;
		gd.heightHint = 200;
		table.setLayoutData(gd);
		
		TableColumn colOffset = new TableColumn(table, SWT.CENTER);
		colOffset.setText("Offset");
		colOffset.setWidth(50);
		colOffset.setAlignment(SWT.CENTER);
 
		TableColumn colEnvState = new TableColumn(table, SWT.CENTER);
		colEnvState.setText("Env. State");
		colEnvState.setWidth(80);
		colEnvState.setAlignment(SWT.CENTER);
		
		TableColumn colProofState = new TableColumn(table, SWT.CENTER);
		colProofState.setText("Proof. State");
		colProofState.setWidth(80);
		colEnvState.setAlignment(SWT.CENTER);
		
		TableColumn colProofs = new TableColumn(table, SWT.RIGHT);
		colProofs.setText("Opened Proofs");
		colProofs.setWidth(100);
		colEnvState.setAlignment(SWT.RIGHT);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	public void clear(){
		table.removeAll();
	}
	
	public void addContent(int offset, int env, int proof, String [] proofs){
		String str_offset = String.valueOf(offset);
		String str_env = String.valueOf(env);
		String str_proof = String.valueOf(proof);
		String str_proofs = "";
		for (int j = 0;j<proofs.length;j++)
			str_proofs+=" "+proofs[j];
		(new TableItem(table,0)).setText(new String[]{
				str_offset,str_env,str_proof,str_proofs});
	}

}
