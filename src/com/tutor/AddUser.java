package com.tutor;

import java.util.Map;

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

public class AddUser extends ApplicationWindow {
	private Text username;
	private Text password;
	private String type;
	private User user;

	/**
	 * Create the application window.
	 */
	public AddUser(User user, String type) {
		super(null);
		this.type = type;
		this.user = user;
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
		
		username = new Text(container, SWT.BORDER);
		username.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		username.setBounds(176, 55, 116, 36);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblNewLabel.setBounds(23, 58, 147, 60);
		lblNewLabel.setText("New Username:");
		
		password = new Text(container, SWT.BORDER);
		password.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		password.setBounds(176, 111, 116, 36);
		
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setText("New Password:");
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPassword.setBounds(23, 114, 147, 60);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				User u = new User(username.getText(), password.getText(), type);
				u.add(u.getUserName(), u.getPasswordHash(), type);
				new MainMenu(user);
				}
			}
		);
		
		Button returnButton = new Button(container, SWT.NONE);
		returnButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new MainMenu(user);
			}
		});
		returnButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		returnButton.setBounds(312, 71, 112, 76);
		returnButton.setText("Cancel");
		
		btnNewButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		btnNewButton.setBounds(165, 169, 82, 40);
		btnNewButton.setText("Create");
		
		Label lblLogInTo = new Label(container, SWT.NONE);
		lblLogInTo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblLogInTo.setBounds(124, 10, 264, 31);
		lblLogInTo.setText("Add new student");
		if (type.equals("t")){lblLogInTo.setText("Add new teacher");}
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
		newShell.setText("Change password");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
