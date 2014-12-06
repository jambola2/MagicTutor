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

public class PasswordChange extends ApplicationWindow {
	private Text newpassword;
	private Text password;
	private User user;
	private String type;

	/**
	 * Create the application window.
	 */
	public PasswordChange(User user, String type) {
		super(null);
		this.user = user;
		this.type = type;
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
		
		newpassword = new Text(container, SWT.BORDER);
		newpassword.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		newpassword.setBounds(190, 55, 116, 36);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblNewLabel.setBounds(37, 58, 147, 60);
		lblNewLabel.setText("New Password:");
		
		password = new Text(container, SWT.BORDER);
		password.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		password.setBounds(190, 111, 116, 36);
		
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setText("Old Password:");
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPassword.setBounds(37, 114, 147, 60);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Password change attempted");
				if (user.hash(password.getText()).replaceAll("//s","").equals(user.getPasswordHash())){
					Main.passwords.put(user.getUserName(), (user.hash(newpassword.getText()) + "#" + type));
					for (Map.Entry<String, String> e1 : Main.passwords.entrySet()){
						System.out.println(e1.getKey() + e1.getValue());
					}
					user.add(user.getUserName(), newpassword.getText(), type);
					System.out.println("Password changed");
					new MainMenu(user);
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		btnNewButton.setBounds(140, 169, 82, 40);
		btnNewButton.setText("Change");
		
		Label lblLogInTo = new Label(container, SWT.NONE);
		lblLogInTo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblLogInTo.setBounds(88, 10, 264, 31);
		lblLogInTo.setText("Change password");

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
