package com.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SignUpPage  extends JFrame{
	
	private JTextField username , mobileNumber ;
	private JCheckBox termBox ;
	private JButton signUpButton ;
	private JComboBox<String> designation ;

	public SignUpPage() {
		setFrame() ;
		intializeComponents() ;
		addComponents() ;
		setUpListener() ;
	}
	
	private void  setFrame() {
		setSize(1366,768) ;
		setVisible(true) ;
		setContentPane(createBackgroundImage()) ;
		
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
		
		JPanel mainPanel = new JPanel() ;
		   mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS)) ;
		   mainPanel.setBackground(new Color(0,0,0,0)); 
		  
		JLabel title = new JLabel("User Create Account",SwingConstants.CENTER) ;
		title.setFont(new Font("Arial",Font.BOLD,28));
		title.setForeground(new Color(25,194,246));
		
		mainPanel.add(title) ;
		mainPanel.add(Box.createVerticalStrut(300)) ;
		addFormRow("Username",username, mainPanel) ;
		mainPanel.add(Box.createVerticalStrut(30)) ;
		addFormRow("Mobile-Number",mobileNumber,mainPanel) ;
		mainPanel.add(Box.createVerticalStrut(30)) ;
		mainPanel.add(designation) ;
		mainPanel.add(Box.createVerticalStrut(20)) ;
		mainPanel.add(termBox) ;
		mainPanel.add(Box.createVerticalStrut(20)) ;
		mainPanel.add(signUpButton) ;
		
		
		add(mainPanel) ;
	}
	
	private JTextField  createStyledTextField(int width) {
		
		JTextField textField = new JTextField (width) ;
		textField.setFont(new Font("Arial",Font.PLAIN,19));
		textField.setBackground(new Color(30,30,30));
		textField.setForeground(Color.white);
		return textField;
		
	}
	
	private void intializeComponents() {
		username = createStyledTextField(10) ;
		mobileNumber = createStyledTextField(10) ;
		termBox = createStyledCheckBox("Agree terms & condition") ;
		signUpButton = createStyledButton("Create-Account") ;
		
		String[] desig = {"Select Designation","Associate Software Developer","Hiring Manager","System Engineer","Intern","Software Architect","Software Trainer"} ;
		designation=new JComboBox<String>(desig) ;
		createStyledComboBox(designation) ;
	}
	
	private JCheckBox createStyledCheckBox(String text) {
		JCheckBox checkBox = new JCheckBox(text) ;
		 checkBox.setFont(new Font("Arial",Font.BOLD,18));
      	 checkBox.setBackground(new Color(30,30,30));
		// checkBox.setBackground( Color.white);
		 checkBox.setForeground(new Color(47,129,229));
	     
		return checkBox;
		
	}
	
	private JButton createStyledButton(String text) {
		JButton button =  new JButton(text) ;
	
		 button.setFont(new Font("Arial",Font.BOLD,18));
		 button.setForeground(Color.white) ;
		 button.setBackground(new Color(33,171,230));
		 button.setFocusPainted(false);
		 
		 button.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 button.setBackground(new Color(33,171,230).brighter());
			 }
			 
			 @Override
			 public void mouseExited(MouseEvent e) {
				 button.setBackground(new Color(33,171,230));
			 }
		 });
		 
		 SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) ;
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (InstantiationException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
				
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					
					e.printStackTrace();
				}
			}
			 
		 });
		return button ;
				
	}
	
	private void handleSignUp() {
		 if(!(termBox.isSelected())) 
		 {
			 showErrorMessage("please agree to terms and conditions to proceed") ;
//			 JOptionPane.showMessageDialog(this, "please agree to terms and conditions to proceed","Error",JOptionPane.ERROR_MESSAGE);
			 return ;
			 
			 }
		 
		  String userName = username.getText().trim();
		  String mobileNumberField = mobileNumber.getText() ;
		 String selectedDesignation = (String)designation.getSelectedItem();
		 
		 long phoneNo ;
		 
		 if(mobileNumberField.isEmpty()) {
			 phoneNo=0 ;
		 }else {
			 phoneNo = Long.parseLong(mobileNumberField) ;
		 }
		
		   
		 if( userName.isEmpty() || phoneNo==0 || selectedDesignation.equals("Select Designation")) {
			 showErrorMessage("Please fill the details firstly") ;
		 }
    
		 insertUserToDatabase(userName, phoneNo, selectedDesignation);
		 
	}
	
	private void insertUserToDatabase(String userName, long phoneNo, String selectedDesignation) {
        String query = "INSERT INTO user_table (username, mobileNumber, designation) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linkedin_db", "root", "root");
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, userName);
            stmt.setLong(2, phoneNo);
            stmt.setString(3, selectedDesignation);
            stmt.executeUpdate();
            System.out.println("Data inserted successfully.");
            showSuccessMessage("Account Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorMessage("Failed to insert data into the database.");
        }
    }
		
	private void showErrorMessage(String string) {
	
		 JOptionPane.showMessageDialog(this, string,"Error",JOptionPane.ERROR_MESSAGE);
	}
	
	private void showSuccessMessage(String string) {
		JOptionPane.showMessageDialog(this, string,"Success",JOptionPane.INFORMATION_MESSAGE);
	}
	
	 
	 private void setUpListener() {
		 
		 signUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleSignUp() ;
			}

			});
	 }
	 
	 
	 
	private void createStyledComboBox(JComboBox<String> des) {
		des.setFont(new Font("Arial",Font.BOLD,19)) ;
		des.setBackground(new Color(33,171,230));
		des.setForeground(Color.WHITE);
	}
	
	
	private void addFormRow(String labelText , JComponent theComponent , JPanel formPanel) {
		JPanel rowPanel = new JPanel() ;
		rowPanel.setLayout(new BoxLayout(rowPanel , BoxLayout.X_AXIS));
		rowPanel.setBackground(new Color(0,0,0,0));
		
		JLabel label = new JLabel(labelText) ;
		label.setFont(new Font("Arial",Font.BOLD,18)) ;
		label.setForeground(Color.WHITE);
		
		rowPanel.add(label) ;
		
		rowPanel.add(Box.createHorizontalStrut(30)) ;
		rowPanel.add(theComponent) ;
		
		formPanel.add(rowPanel) ;	
//		formPanel.add(Box.createVerticalStrut(20)) ;
		
		add(formPanel) ;
		
		
		
		
	}
	
	

	public static void main(String[] args) {
		new SignUpPage() ;

	}

}
