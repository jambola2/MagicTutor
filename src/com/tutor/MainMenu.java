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

public class MainMenu extends ApplicationWindow {
	private User user;
	private String type;
	/**
	 * Create the application window.
	 */
	public MainMenu(User user) {
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
		
		Button logOutButton = new Button(container, SWT.NONE);
		logOutButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("User logged out");
				new Login();
			}
		});
		logOutButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		logOutButton.setBounds(312, 83, 112, 76);
		logOutButton.setText("Log Out!");
		
		Label lblLogInTo = new Label(container, SWT.NONE);
		lblLogInTo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblLogInTo.setBounds(78, 10, 318, 67);
		lblLogInTo.setText("Welcome to magic tutor \n " + user.getUserName());
		
		Button changePasswordButton = new Button(container, SWT.NONE);
		changePasswordButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new PasswordChange(user);
			}
		});
		changePasswordButton.setText("Change password");
		changePasswordButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		changePasswordButton.setBounds(138, 176, 183, 48);
		
		Button usermngr = new Button(container, SWT.NONE);
		usermngr.setText("Manage users");
		usermngr.setFont(SWTResourceManager.getFont("Cambria", 15, SWT.NORMAL));
		usermngr.setVisible(false);
		if (type.equals("a") || type.equals("t")){
			usermngr.setVisible(true);
			usermngr.setBounds(0, 83, 291, 76);
			if (type.equals("t")){
				usermngr.setBounds(22, 83,127,76);
			}
		}
		usermngr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new UserManager(user);
			}
		});
		Button quizzes = new Button(container, SWT.NONE);
		quizzes.setText("Manage quizzes");
		quizzes.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.NORMAL));
		quizzes.setVisible(false);
		if (type.equals("t")||type.equals("s")){
			quizzes.setVisible(true);
			quizzes.setBounds(0, 83, 291, 76);
			if (type.equals("t")){
				quizzes.setBounds(155, 83, 151, 76);
			}
		}
		quizzes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new QuizMenu(user);
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
