package com.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignInPage extends JFrame {
	
	JTextField usernameTextField , mobileTextField ;
    private JCheckBox termsCheckBox ;
    private JButton signInButton ;

	public SignInPage() {
		setFrame() ;
		initializeStyledTextField() ;
		addComponents() ;
		setUpListener() ;
		
		
	}
	
	public  void setFrame() {
	 
	   setSize(1366,768) ;
		setVisible(true); 
		setContentPane(createBackgroundImage() ) ;
		
		
		
	}
	
	private JPanel createBackgroundImage() { 
		return new JPanel() 
		{
		   @Override
		   protected void paintComponent(Graphics g) {
			   
			   super.paintComponent(g);
			   ImageIcon icon = new ImageIcon(getClass().getResource("/com/icon/linkedIn.jpeg")) ;
			   Image image = icon.getImage();
			   
			   double panelHeight = getHeight() ;
			   double panelWidth = getWidth() ;
			   
			   double imageHeight = image.getHeight(this) ;
			   double imageWidth = image.getWidth(this) ;
			   
			   double scaled = Math.max(panelWidth/imageWidth, panelHeight/imageHeight) ;
				  
				  int scaledHeight = (int)(scaled*imageHeight) ;
				  int scaledWidth = (int)(scaled*imageWidth) ;
				  
				  
				  int x =((int)(panelWidth - scaledWidth) / 2);
				  int y = ((int)(panelHeight - scaledHeight) / 2);
				  
				  g.drawImage(image, x, y, scaledWidth, scaledHeight, this) ;
				  
				  g.setColor(new Color(0,0,0,100)) ;
				  g.fillRect(0, 0, getWidth(), getHeight()) ;
			   
		   }
		} ;
	}
	

	private void addComponents() {
		
		JPanel formPanel = new JPanel() ;
	   formPanel.setLayout(new BoxLayout(formPanel,BoxLayout.Y_AXIS)) ;
	   formPanel.setBackground(new Color(30,30,30)); 
	  
	  
		 
		JLabel title = new JLabel("User-Sign-in",SwingConstants.CENTER) ;
		 title.setFont(new Font("Arial",Font.BOLD,20)) ;
	     title.setForeground(Color.blue);
		
		formPanel.add(title) ;
		formPanel.add(Box.createVerticalStrut(20)) ;
		addForm( "User-Name" ,usernameTextField , formPanel) ;
		addForm("Mobile-Number",mobileTextField,formPanel) ;
	    formPanel.add(termsCheckBox) ;
	    formPanel.add(signInButton) ;
		
		
		add(formPanel) ;
		
	}
	
	private JTextField createStyledTextField(int width) {
		JTextField textField = new JTextField(width) ;
		textField.setFont(new Font("Arial",Font.PLAIN,15));
		textField.setBackground(new Color(255,255,255));
		textField.setForeground(new Color(33,33,33));
		return textField ;
	}
	
	private void initializeStyledTextField() {
		usernameTextField = createStyledTextField(20) ;
		mobileTextField = createStyledTextField(20) ;
		termsCheckBox = createStyledCheckBox("Accept terms and condition") ;
		signInButton = createStyledButton("SignIn",Color.BLACK) ;
	}
	
	
	 private JCheckBox createStyledCheckBox(String text) {
		 JCheckBox checkBox = new JCheckBox(text) ;
		 checkBox.setFont(new Font("Arial",Font.BOLD,18));
		 checkBox.setForeground(Color.white);
		 checkBox.setOpaque(false);
		
		 return checkBox ;
	 }
	 
	
	 private JButton createStyledButton(String text ,Color backColor) {
		 JButton button = new JButton(text) ;
		 button.setFont(new Font("Arial",Font.BOLD,18));
		 button.setForeground(Color.white) ;
		 button.setBackground(backColor);
		 button.setOpaque(false);
		 button.setFocusPainted(false);
		 
		 return button ;
	 }
	 
	 private void handleSignIn() {
		 
		 if(!(termsCheckBox.isSelected())) 
		 {
			 showErrorMessage("Agree to Terms & Conditions First") ;
			 return ;
		 }
//		 JOptionPane.showMessageDialog(this,"Logged in Success","Success",JOptionPane.INFORMATION_MESSAGE);
		 
		 String name = usernameTextField.getText().trim() ;
		 String no = mobileTextField.getText().trim() ;
		 long phoneNo = Long.parseLong(no) ; 
		 
		 String query = "select * from signin_table where username='"+name+"' "+"and mobileNumber='"+phoneNo+"'" ;
		 
		 ConnectionJDBC connection = new ConnectionJDBC() ;
		 
		 try {
			ResultSet rs = connection.s.executeQuery(query) ;
			if(rs.next()) {
				
				JOptionPane.showMessageDialog(this, "Welcome Back "+name+" ","Success",JOptionPane.INFORMATION_MESSAGE);			}
				else {
		            // User not found, handle invalid credentials
		            showErrorMessage("Invalid username or mobile number.");
		        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 
	 private void setUpListener() {
		 
		 signInButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleSignIn() ;
			}});
	 }
	 
	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message , "Error" ,JOptionPane.ERROR_MESSAGE);
		
	}

	private void addForm(String labelText ,JComponent component , JPanel formPanel) {
		JPanel rowPanel = new JPanel() ;
		rowPanel.setLayout(new BoxLayout(rowPanel , BoxLayout.X_AXIS));
		rowPanel.setBackground(new Color(0,0,0,0));
		
		JLabel label = new JLabel(labelText) ;
		label.setFont(new Font("Arial",Font.BOLD,18)) ;
		label.setForeground(Color.blue);
		
		rowPanel.add(label) ;
		
		rowPanel.add(Box.createHorizontalStrut(20)) ;
		rowPanel.add(component) ;
		
		formPanel.add(rowPanel) ;
//		formPanel.add(Box.createHorizontalStrut(20)) ;
//		rowPanel.add(formPanel) ;
//		
		formPanel.add(Box.createVerticalStrut(20)) ;
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		new SignInPage() ;
	}

}
