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

public class MainMenu extends ApplicationWindow {
	String username;
	String type;
	/**
	 * Create the application window.
	 */
	public MainMenu(String username, String type) {
		super(null);
		this.username = username;
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
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("User logged out");
				new Login();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		btnNewButton.setBounds(300, 83, 112, 76);
		btnNewButton.setText("Log Out!");
		
		Label lblLogInTo = new Label(container, SWT.NONE);
		lblLogInTo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblLogInTo.setBounds(78, 10, 318, 67);
		lblLogInTo.setText("Welcome to magic tutor \n " + username);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		button.setText("Change password");
		button.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		button.setBounds(130, 176, 183, 48);

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
		newShell.setText("Log In!");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
