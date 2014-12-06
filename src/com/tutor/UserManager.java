package com.tutor;

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

public class UserManager extends ApplicationWindow {
	private User user;
	private String type;
	/**
	 * Create the application window.
	 */
	public UserManager(User user) {
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
				System.out.println("User logged out");
				new MainMenu(user);
			}
		});
		returnButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		returnButton.setBounds(312, 83, 112, 76);
		returnButton.setText("Return");
		
		Label lblLogInTo = new Label(container, SWT.NONE);
		lblLogInTo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblLogInTo.setBounds(78, 10, 318, 67);
		lblLogInTo.setText("Welcome to magic tutor \n " + user.getUserName());
		
		Button user = new Button(container, SWT.NONE);
		user.setText("Add student");
		user.setFont(SWTResourceManager.getFont("Cambria", 15, SWT.NORMAL));
		user.setBounds(22, 83,127,76);
		user.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		Button button2 = new Button(container, SWT.NONE);
		button2.setText("Add teacher");
		button2.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.NORMAL));
		button2.setBounds(155, 83, 151, 76);
		if (type.equals("t")){
			button2.setText("Modify group");	
		}
		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
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
