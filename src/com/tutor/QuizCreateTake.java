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

public class QuizCreateTake extends ApplicationWindow {
	private Text answer;
	private User user;
	private String type;
	private Quiz quiz;
	private int score;
	private String key;

	/**
	 * Create the application window.
	 */
	
	public int getScore() {
		return score;
	}
	
	public QuizCreateTake(Quiz q, User user, int score, String key){
		this(q, user, score);
		this.key = key;
	}

	public QuizCreateTake(Quiz q, User user, int score) {
		super(null);
		this.quiz = q;
		this.user = user;
		this.type = user.getType();
		this.score = score;
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
		Text question = new Text(container, SWT.BORDER);
		if(type.equals("s")){
			question.setEditable(false);
			System.out.println(key);
			if (key == null){
				System.out.println("Quiz not found");
				return null;
			}
			question.setText(key);
		}
		question.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		question.setBounds(190, 55, 116, 36);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblNewLabel.setBounds(37, 58, 147, 60);
		lblNewLabel.setText("Question:");
		
		answer = new Text(container, SWT.BORDER);
		answer.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		answer.setBounds(190, 111, 116, 36);
		
		Label lblanswer = new Label(container, SWT.NONE);
		lblanswer.setText("Answer:");
		lblanswer.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblanswer.setBounds(37, 114, 147, 60);
		
		Button returnButton = new Button(container, SWT.NONE);
		returnButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (type.equals("s")){
					quiz.marklist.put(user.getUserName(), String.valueOf(score));
				}
				new MainMenu(user);
			}
		});
		returnButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		returnButton.setBounds(312, 71, 112, 76);
		returnButton.setText("Done");
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (type.equals("t")){
					quiz.addQuestion(question.getText(), answer.getText());
					new QuizCreateTake (quiz, user, 0);
				}
				if (type.equals("s")){
					if (answer.getText().equals(quiz.queslist.get(key))){
						score += 1;
						return;
					}
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Cambria", 16, SWT.BOLD));
		btnNewButton.setBounds(140, 169, 82, 40);
		btnNewButton.setText("Next");
		
		Label lblLogInTo = new Label(container, SWT.NONE);
		lblLogInTo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblLogInTo.setBounds(170, 10, 264, 31);
		lblLogInTo.setText("Take quiz");
		if (type.equals("t")){ lblLogInTo.setText("Create quiz");}
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
		newShell.setText("Take quiz");
		if (type.equals("t")){ newShell.setText("Create quiz");}
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
