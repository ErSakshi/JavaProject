package com.notepad;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NotePad {
	JFrame frame ;
	JTextArea textArea ;
	JMenuBar menuBar ;
	JMenu fileMenu , languageMenu , formatMenu ,comandPromptMenu  ; 
	
	//File Menu Items
	JMenuItem itemNew ,itemNewWindow , itemOpen , itemSave ,itemSaveAs ,itemExit ; 
	
	//Format Menu Items
	JMenuItem itemWordWrap , itemFont ,itemFontSize ; 
	
	// Command Prompt item 
	JMenuItem itemCmd ;
	
	String openPath = null ;
	String openFileName = null ;
	boolean wrap = false ;
	
	//Font styles 
	Font arial , newRoman , consolas ;
	//by default fontstyle
	String fontstyle = "Arial" ;
	
	public NotePad() {
		
		createFrame() ;
		createTextArea() ;
		createScrollBars() ;
		createMenuBar() ;
		createFileMenuItems() ;
		createFormatMenuItems() ;
		createCommandPromptItem() ;
		createLanguageItems();
	}

	
	public void createFrame() {
		
		 frame = new JFrame("Notepad") ;
		 frame.setSize(1200,700);
		 
	
		
		 Image icon = Toolkit.getDefaultToolkit().getImage("E:\\JavaInternship\\Image\\images.jpg") ;
		 frame.setIconImage(icon) ;
		 frame.setVisible(true);
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void createTextArea() {
		textArea = new JTextArea() ;
		textArea.setFont(new Font("Arial",Font.PLAIN,25) ) ;
		frame.add(textArea) ;
	}
	
	public void createScrollBars() {
		JScrollPane scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) ;
	    scroll.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
	    frame.add(scroll) ;
	}
	
	public void createMenuBar() {
		menuBar = new JMenuBar() ;
		frame.setJMenuBar(menuBar) ;
		
		fileMenu = new JMenu("File") ;
		menuBar.add(fileMenu) ;
		
		languageMenu = new JMenu("Language");
		menuBar.add(languageMenu) ;
		
		formatMenu = new JMenu("Format");
		menuBar.add(formatMenu) ;
		
		comandPromptMenu = new JMenu("Command Prompt") ;
		menuBar.add(comandPromptMenu) ;
	}
	
	public void createFileMenuItems() {
		
		itemNew = new JMenuItem("New") ;
		itemNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				textArea.setText("");
				frame.setTitle("Untitled Notepad") ;
				
			   openPath = null ;
			   openFileName = null ;
			}
			
		});
		
		fileMenu.add(itemNew);
	    
		itemNewWindow = new JMenuItem("NewWindow") ;
		
		itemNewWindow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NotePad n1 = new NotePad () ;
				n1.frame.setTitle("Untitled");
			}});
		fileMenu.add(itemNewWindow);
		
		
		itemOpen = new JMenuItem("Open") ;
		
		itemOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileDialog fd = new FileDialog(frame,"Open",FileDialog.LOAD) ;
				fd.setVisible(true);
				
				String path = fd.getDirectory();
				String filename = fd.getFile();
				System.out.println(path+filename);
				
				if(filename!=null) {
					frame.setTitle(filename) ;
				}
				
				BufferedReader br = null ;
				try {
					br = new BufferedReader(new FileReader(path+filename) {}) ;
					String sentence = br.readLine() ;
					textArea.setText("");
					 while(sentence!=null) {
						 textArea.append(sentence+"\n") ;
						 sentence = br.readLine() ;
					 }
				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				} catch (IOException e1) {
					System.out.println("Data could not be read");
				}
				catch(NullPointerException n) {}
				finally {
					try {
						br.close() ;
					} catch (IOException e1) {
						System.out.println("File could not be close ");
					}	catch(NullPointerException n) {}
				}
			}
			
		});
		
		fileMenu.add(itemOpen);
		
		itemSave = new JMenuItem("Save") ;
		
		itemSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			 if(openFileName !=null && openPath!=null) {
				 writeDataToFile(openFileName,openPath) ;
			 }else {
				 FileDialog fd = new FileDialog(frame,"Save As",FileDialog.SAVE) ;
				 fd.setVisible(true) ;
				 
				 String path = fd.getDirectory() ;
				 String filename = fd.getFile() ;
				 
				 if(path!=null && filename!=null) {
					 writeDataToFile(filename,path) ;
					 
					 openFileName = filename ;
					 openPath = path ;
					 
					 frame.setTitle(openPath) ;
				 }
			 }
				
			}});
		fileMenu.add(itemSave); 
	
		itemSaveAs = new JMenuItem("SaveAs") ;
		
		itemSaveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			   FileDialog fd = new FileDialog(frame,"SaveAs",FileDialog.SAVE) ;
			   fd.setVisible(true);
			   
			 String path = fd.getDirectory() ;
			 String filename = fd.getFile() ;
			  
		
			 if( path!=null && filename!=null) {
			
				 writeDataToFile(filename,path) ;
				 openFileName = filename ;
				 openPath = path ;
				 
				 frame.setTitle(openFileName) ;
			 }
			  
			   
			}});
		fileMenu.add(itemSaveAs);
		
		itemExit = new JMenuItem("Exit") ;
		
		itemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
			
		});
		fileMenu.add(itemExit);
	} 
	
	public void createLanguageItems() {
		JMenuItem itemJava = new JMenuItem("Java") ;
		
		itemJava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    setLanguage("Java") ;
			}});;
		
		 languageMenu.add(itemJava) ;
		 
		 JMenuItem itemHtml = new JMenuItem("HTML") ;
		 itemHtml.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				    setLanguage("html") ;
				}});
			
		 languageMenu.add(itemHtml) ;
		 
		 JMenuItem itemC = new JMenuItem("C") ;
		 itemC.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				    setLanguage("C") ;
				}});
			
		 languageMenu.add(itemC) ;
		 
		 JMenuItem itemCpp = new JMenuItem("C++") ;
		 itemCpp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				    setLanguage("Cpp") ;
				}});
			
		 languageMenu.add(itemCpp) ;
	}
	
	public void setLanguage(String lang) {
		BufferedReader br = null ;
		try {
			br = new BufferedReader(new FileReader("E:\\JavaInternship\\"+lang+"Format.txt") ) ;
			String sentence = br.readLine() ;
			textArea.setText("");
			 while(sentence!=null) {
				 textArea.append(sentence+"\n") ;
				 sentence = br.readLine() ;
			 }
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
		} catch (IOException e1) {
			System.out.println("Data could not be read");
		}
		catch(NullPointerException n) {}
		finally {
			try {
				br.close() ;
			} catch (IOException e1) {
				System.out.println("File could not be close ");
			}	catch(NullPointerException n) {}
		}
	}
	
	public void createFormatMenuItems() {
		
		itemWordWrap = new JMenuItem("Word Wrap Off") ;
		itemWordWrap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			 if(wrap==false) {
				 textArea.setLineWrap(true) ;
				 textArea.setWrapStyleWord(true);
				 wrap = true ;
				 itemWordWrap.setText("Word Wrap On") ;
			 }
			 else {
				 textArea.setLineWrap(false) ;
				 textArea.setWrapStyleWord(false);
				 wrap = false ;
				 itemWordWrap.setText("Word Wrap Off") ;
				 
			 }
				
			
			}
			
		}) ;
		
		formatMenu.add(itemWordWrap) ;
		
		itemFont = new JMenu("Font") ;
		JMenuItem itemArial = new JMenuItem("Arial") ;
		
		  itemArial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontStyle("Arial");
			}
			  
		  }) ;
		JMenuItem itemTimesNewRoman = new JMenuItem("Times New Roman") ;
		
		 itemTimesNewRoman.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setFontStyle("Times New Roman");
				}
				  
			  }) ;
		 
		JMenuItem itemConsolas = new JMenuItem("Consolas") ;
		
		 itemConsolas.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setFontStyle("Consolas");
				}
				  
			  }) ;
		formatMenu.add(itemFont) ;
		itemFont.add(itemArial);
		itemFont.add(itemTimesNewRoman) ;
		itemFont.add(itemConsolas) ;
		
		itemFontSize = new JMenu("FontSize") ;
		JMenuItem size10 = new JMenuItem("10");
		
		size10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			 setFontSize(10) ;	
			}
			
		});
		JMenuItem size14 = new JMenuItem("14");
		size14.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			 setFontSize(14) ;	
			}
			
		});
		
		JMenuItem size18 = new JMenuItem("18");
		size18.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			 setFontSize(18) ;	
			}
			
		});
		JMenuItem size22 = new JMenuItem("22");
		size22.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			 setFontSize(22) ;	
			}
			
		});
		JMenuItem size26 = new JMenuItem("26");
		size26.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			 setFontSize(26) ;	
			}
			
		});
		JMenuItem size30 = new JMenuItem("30");
		size30.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			 setFontSize(30) ;	
			}
			
		});
		JMenuItem size34 = new JMenuItem("34");
		size34.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			 setFontSize(34) ;	
			}
			
		});
		
		formatMenu.add(itemFontSize) ;
		itemFontSize.add(size10) ;
		
		
		itemFontSize.add(size14) ;
		itemFontSize.add(size18) ;
		itemFontSize.add(size22) ;
		itemFontSize.add(size26) ;
		itemFontSize.add(size30) ;
		itemFontSize.add(size34) ;
	}
	
	public void createCommandPromptItem() {
		itemCmd = new JMenuItem("Open cmd") ;
		
		itemCmd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					if(openPath!=null) {
						Runtime.getRuntime().exec(new String[] {"cmd","/c","start"},null,new File(openPath)) ;   // "/k" is used to keep command prompt open
					}else {
						Runtime.getRuntime().exec(new String[] {"cmd","/c","start"},null,null) ;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		comandPromptMenu.add(itemCmd);
	}
	
	public void writeDataToFile(String filename,String path) {
		
		BufferedWriter bw = null ;
		 try {
				bw = new BufferedWriter(new FileWriter(path+filename)) ;
				String text = textArea.getText();
				bw.write(text) ;
				
			} catch (IOException e1) {
			 System.out.println("Data cannot be written");
				
			}
			 finally {
				 try {
					bw.close() ;
				} catch (IOException e1) {
					System.out.println("File cannot be close ");
				}
				 catch(NullPointerException n) {
					 System.out.println("File not found to close");
				 }
			 }
	}
	
	
	public void setFontSize (int size) {
		
		 arial= new Font ("Arial",Font.PLAIN,size);
		 newRoman = new Font ("Times New Roman",Font.PLAIN,size);
		 consolas = new Font ("Consolas",Font.PLAIN,size);
		
		setFontStyle(fontstyle) ;
	}
	
	public void setFontStyle(String font) {
		fontstyle =font ;
		switch(font) 
		{
		case "Arial":
		{
			textArea.setFont(arial);
			break;
		}
        case "Times New Roman":
        {
			textArea.setFont(newRoman);
			break;
		}
        case "Consolas":
        {
			textArea.setFont(consolas);
			break;
		}
        default : break ;
		}
	}
	
}
