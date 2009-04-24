package coq.perspectives;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;


public class CoqPerpective implements IPerspectiveFactory {

	private static final String INFO_VIEW_ID = "InfoView";
	private static final String TOPLEVEL_VIEW_ID = "CoqTopView";
	private static final String ERROR_VIEW_ID = "CoqTopErrorView";
	private static final String CONSOLE_VIEW_ID =
		IConsoleConstants.ID_CONSOLE_VIEW;
	
	private static final String BOTTOM = "bottom";
	private static final String LEFT = "left";
	private static final String RIGHT_TOP = "right_top";
	private static final String RIGHT_BOT = "right_bot";

	
	public void createInitialLayout(IPageLayout layout) {	
		
		IFolderLayout right_top = 
			layout.createFolder(RIGHT_TOP, IPageLayout.RIGHT,
					0.65f, layout.getEditorArea());
		
		IFolderLayout right_bot = 
			layout.createFolder(RIGHT_BOT, IPageLayout.BOTTOM,
					0.80f, RIGHT_TOP);
		
		IFolderLayout left = 
			layout.createFolder(LEFT, IPageLayout.LEFT,
					0.30f, layout.getEditorArea());
		
		IFolderLayout bot = 
			layout.createFolder(BOTTOM, IPageLayout.BOTTOM,
					0.76f, layout.getEditorArea());
			

		left.addView(IPageLayout.ID_RES_NAV);
		
		right_top.addView(TOPLEVEL_VIEW_ID);
		right_bot.addView(ERROR_VIEW_ID);

		bot.addView(INFO_VIEW_ID);
		bot.addView(CONSOLE_VIEW_ID);
		bot.addView(IPageLayout.ID_BOOKMARKS);
		bot.addView(IPageLayout.ID_PROBLEM_VIEW);
		bot.addView(IPageLayout.ID_PROGRESS_VIEW );
		bot.addView(IPageLayout.ID_BOOKMARKS);
		bot.addView(IPageLayout.ID_TASK_LIST);
	}
}
