
package start;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class Start implements ActionListener
{
	private static Start INSTANCE = new Start();
	private static JFrame mainWindow;
	private static JButton login;
	private static JTextField usernameField;
	private static JPasswordField passwordField;
	private static JTextArea newsFeed;
	private static JScrollPane newsScroller;
	
	public static void main(String[] args)
	{
		//Instantiates some buttons.
		mainWindow = new JFrame("Realms of Cælum");
		login = new JButton("Log in");
		usernameField = new JTextField("Username");
		passwordField = new JPasswordField("Password");
		newsFeed = new JTextArea(20, 30);
		newsScroller = new JScrollPane(newsFeed);
		
		//Setters.
		mainWindow.setLayout(null);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().add(login);
		mainWindow.getContentPane().add(usernameField);
		mainWindow.getContentPane().add(passwordField);
		mainWindow.getContentPane().add(newsScroller);
		newsFeed.setEditable(false);
		mainWindow.setVisible(true);
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//Sets the positions of everything.
		login.setBounds(new Rectangle(mainWindow.getWidth() - 160, mainWindow.getHeight() - 124, 120, 40));
		usernameField.setBounds(new Rectangle(mainWindow.getWidth() - 290, mainWindow.getHeight() - 128, 125, 24));
		passwordField.setBounds(new Rectangle(mainWindow.getWidth() - 290, mainWindow.getHeight() - 100, 125, 24));
		newsScroller.setBounds(new Rectangle(mainWindow.getWidth() - 20, mainWindow.getHeight() - 20, 300, 400));
		
		//Adds action listeners.
		login.addActionListener(INSTANCE);
		passwordField.addActionListener(INSTANCE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		System.out.println(event.getActionCommand());
		
		if (event.getActionCommand() == "Log in" || event.getSource().equals(passwordField))
		{
			if (passwordField.getPassword().length > 0 && usernameField.getText().length() > 0)
			{
				//TODO Add login system.
				
			}
			
		}
		
	}
	
}