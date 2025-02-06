package com.client;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UserPage  extends JFrame{
	
	private JButton  login , logout ;
    Color loginColor = Color.black ;
    Color logoutColor = Color.black ;
    
	
	
	public UserPage() {
		 setFrame();
		 initializeStyledButton();
		 addComponents() ;
	}
	
	
	
    public void setFrame() {
    	setSize(1366,768) ;
    	setVisible(true) ;
    	
    }
    
    public void addComponents() {
//    	JPanel contentPanel = new JPanel() ;
    	JLabel lable = new JLabel("User-Portal",SwingConstants.CENTER) ;
    	lable.setFont(new Font("Arial",Font.BOLD,20));
    	lable.setForeground(Color.black) ;
    	add(lable) ;
    	
    	JPanel panel = new JPanel() ;
    	setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
    	panel.add(login) ;
    	panel.add(logout) ;
    	add(panel);
    	
    }
    
    public JButton createButton(String text , Color backColor) {
    	JButton button =  new JButton(text) ;
    	button.setFont(new Font("Arial",Font.BOLD ,18));
    	button.setBackground(backColor);
    	button.setForeground(Color.white);
    	
    	return button ;
    }
    
    public void initializeStyledButton() {
    	this.login = createButton("Log-In",loginColor) ;
    	this.logout = createButton("Log-Out",logoutColor);
    }
    
	public static void main(String[] args) {
		new UserPage() ;
	}

}
