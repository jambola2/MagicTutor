package com.tutor;

import java.io.File;
import java.util.Map.Entry;

import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BoxLayout;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Scale;

public class QuizMenu extends ApplicationWindow {
	private User user;
	private String type;
	private Text text;
	/**
	 * Create the application window.
	 */
	public QuizMenu(User user) {
		super(null);
		this.user = user;
		this.type = user.getType();
		setBlockOnOpen(true);
		open();
		Display.getCurrent().dispose();
		createActions();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(null);
		
		Button returnButton = new Button(container, SWT.NONE);
		returnButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new MainMenu(user);
			}
		});
		returnButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		returnButton.setBounds(312, 83, 112, 76);
		returnButton.setText("Return");
		
		Label lblLogInTo = new Label(container, SWT.NONE);
		lblLogInTo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblLogInTo.setBounds(78, 10, 318, 67);
		lblLogInTo.setText("Quiz management");
		
		text = new Text(container, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		text.setBounds(47, 181, 244, 43);
		text.setVisible(false);
		text.setText("");
		
		Label marks = new Label(container, SWT.NONE);
		marks.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		marks.setBounds(34, 185, 390, 28);
		marks.setText("");
		
		Button usermngr = new Button(container, SWT.NONE);
		usermngr.setText("View Results");
		usermngr.setFont(SWTResourceManager.getFont("Cambria", 15, SWT.NORMAL));
		usermngr.setBounds(22, 83,127,76);
		usermngr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text.getText().equals("")){text.setVisible(true);}
				else{
					text.setVisible(true);
					if (!new File(Main.location + text.getText() + ".txt").exists()){
						text.setVisible(false);
						marks.setText("Non existent quiz!");
						return;
						}
					Quiz q = new Quiz(text.getText());
					for (String key : q.marklist.keySet()){
						if (type.equals("s")){
							if (q.marklist.get(user) == null){
								marks.setText("You have not parttaken in this quiz!");
							}
							else{
								marks.setText(q.marklist.get(user));
								System.out.println("Results displayed");
							}
						}
						if (type.equals("t")){
							//Teachers can access results directly from file ?
							//TODO: Add results for teachers !
						}
					}
				}
			}
		});
		Button button2 = new Button(container, SWT.NONE);
		button2.setText("Take quiz");
		button2.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.NORMAL));
		button2.setBounds(155, 83, 151, 76);
		
		if (type.equals("t")){
			button2.setText("Create new quiz");	
		}
		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text.getText().equals("")){text.setVisible(true);}
				else{
					Quiz q = new Quiz(text.getText());
					if(type.equals("t")){new QuizCreateTake(q, user, 0);}
					else{
						int a = 0;
						for(String k : q.queslist.keySet()) {
							System.out.println(k);
							new QuizCreateTake(q, user, 0 , k).getScore();
						}
					}
				}
			}
		});
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Main Menu");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
