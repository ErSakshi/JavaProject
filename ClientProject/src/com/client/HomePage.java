package com.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;



public class HomePage extends JFrame{
	 
	private JButton signIn , signUp , adminSignIn ;
	Color signInColor = Color.black ;
	Color signUpColor = Color.black ;
	Color adminSignInColor = Color.black ;
	
	public HomePage() {
		setFrame() ;
		initializeButtonComponents() ;
		addComponents() ;
		setUpListener() ;
		
	}
	
	public void setFrame() {
		setSize(1366,768) ;
		setVisible(true) ;
		setContentPane(createBackgroundImage()) ;
	}
	
	 public JPanel createBackgroundImage() {
		 
		 return new JPanel()
				 {
			  @Override
			  protected void paintComponent(Graphics g) {
				  ImageIcon icon = new ImageIcon(getClass().getResource("/com/icon/linkedIn.jpeg")) ;
				  Image image = icon.getImage() ;
				  
				  // get current height and width of the frame 
				  double panelHeight = getHeight () ;
				  double panelWidth = getWidth ()  ;
				  
				  // get the image's height and width
				  double imageWidth = image.getWidth(this) ;
				  double imageHeight = image.getHeight(this) ;
				  
				  // get the maximum scale value
				  double scaled = Math.max(panelWidth/imageWidth, panelHeight/imageHeight) ;
				  
				  int scaledHeight = (int)(scaled*imageHeight) ;
				  int scaledWidth = (int)(scaled*imageWidth) ;
				  
				  
				  int x =((int)(panelWidth - scaledWidth) / 2);
				  int y = ((int)(panelHeight - scaledHeight) / 2);
				  
				  g.drawImage(image, x, y, scaledWidth, scaledHeight, this) ;
				  
				  g.setColor(new Color(0,0,0,150)) ;
				  g.fillRect(0, 0, getWidth(), getHeight()) ;
				  
			  }
				 } ;
	 }
	
	public void addComponents() {
		JPanel contentPanel = new JPanel() ;
		 setLayout (new BorderLayout()) ;
		
		contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);
		 JLabel title = new JLabel ("LinkedIn - Job Web Portal ",SwingConstants.CENTER) ;
		 title.setFont(new Font("Arial",Font.BOLD,22)) ;
		 title.setForeground(Color.white);
		 title.setAlignmentX(CENTER_ALIGNMENT);
		 
		
		 
		 JPanel buttonPanel = new JPanel() {
			 @Override
			 protected void paintComponent(Graphics g) {
 			 g.setColor(new Color(0,0,0,100));
				// g.setColor(new Color(255,255,255,100));
				 g.fillRect(0, 0, 10000, 100) ;
				 
			 }
		 } ;
		 buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,30)) ;
		 buttonPanel.setOpaque(false);
		 buttonPanel.add(signIn) ;
		 buttonPanel.add(signUp) ;
		 buttonPanel.add(adminSignIn) ;
		 //contentPanel.add(Box.createVerticalStrut(400)) ;
		 
		 contentPanel.add(Box.createVerticalGlue()) ;
		 contentPanel.add(title) ;
		 contentPanel.add(Box.createVerticalStrut(60)) ;
		 contentPanel.add(buttonPanel) ;
		
		 add(contentPanel ,BorderLayout.CENTER) ;
	}
	
	public JButton createStyledButtons(String text , Color backColor)
	{
		JButton button = new JButton(text) ;
		button.setFont(new Font("Arial",Font.BOLD,15));
		button.setBackground(backColor);
		button.setForeground(Color.white);
		button.setFocusPainted(false);
		
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e ) {
				button.setBackground(Color.blue);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(backColor);
			}
			
		});
		return button;
		
	}
	
	public void initializeButtonComponents() {
		this.signIn = createStyledButtons("Sign-In",signInColor) ;
		this.signUp = createStyledButtons("Sign-Up",signUpColor) ;
		this.adminSignIn = createStyledButtons("AdminSign-In",adminSignInColor) ;
	}
	
	public void handleSignIn()
	{
		int option = JOptionPane.showConfirmDialog(this,"Existing User","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) ;
		  
		if(option==JOptionPane.YES_OPTION) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) ;
			}
			catch(Exception e) {
			e.printStackTrace();
				
			}
			JOptionPane.showMessageDialog(null,"Successfully Loged-in") ;
		}
		
	}
	
	 
	public void handleSignUp()
	{
		int option = JOptionPane.showConfirmDialog(this,"New User","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) ;
		  
		if(option==JOptionPane.YES_OPTION) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) ;
			}
			catch(Exception e) {
			e.printStackTrace();
				
			}
			JOptionPane.showMessageDialog(null,"Create Your Account") ;
		}
		
		
	
}
	public void handleAdminSignIn()
	{
		int option = JOptionPane.showConfirmDialog(this,"Admin","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) ;
		  
		if(option==JOptionPane.YES_OPTION) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) ;
			}
			catch(Exception e) {
			e.printStackTrace();
				
			}
			JOptionPane.showMessageDialog(null,"Successfully Loged-In") ;
		}
		
	}
	
	
	public void setUpListener() {
		signIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			  handleSignIn() ;
			 
			  
			}});
		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			  handleSignUp() ;
			  
			}});
		adminSignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			  handleAdminSignIn() ;
			  
			}});
		
		
		
	}

	public static void main(String[] args) {

        new HomePage() ;

	}

}



//setFrame
//Add Components
//create Button
//