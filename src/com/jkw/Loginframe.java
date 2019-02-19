package com.jkw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class Loginframe extends JFrame{
	private JTextField userName;
	private JPasswordField passWord;
	private JButton login = new JButton("Login");
	PersonEnity person = new PersonEnity();
	public static String headName;
	public static String id;
	public static String confirmPro;
	public static String deptName;
	
	public Loginframe(){
		super("”√ªßµ«¬º");
		this.setSize(600, 300);
		this.setLocation(500, 250);
		//JFrame mainFrame = new JFrame();
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBackground(new Color(120,200,255));
		
		JLabel userID = new JLabel("’ ∫≈");
		upPanel.add(userID);
		userID.setBounds(200, 80, 50, 20);
		
		userName = new JTextField(10);
		upPanel.add(userName);
		userName.setBounds(250, 80, 120, 20);
		
		JLabel userPwd = new JLabel("√‹¬Î");
		upPanel.add(userPwd);
		userPwd.setBounds(200, 110, 50, 20);
		
		passWord = new JPasswordField(10);
		upPanel.add(passWord);
		passWord.setBounds(250, 110, 120, 20);

		basicPanel.add(login, BorderLayout.SOUTH);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String name = userName.getText();
				String pass = passWord.getText();
				try {
					db dbConn = new db();
					ResultSet rs = dbConn.executeQuery("select * from person where ID = '" + name+"'and Password = '" + pass+"'");
					if(rs.next()){
						//PersonEnity person = new PersonEnity();
						person.setName(rs.getString(2));
						person.setProfessor(rs.getString(7));
						person.setDeptName(rs.getString(8));
						person.setID(rs.getString(1));
						headName = person.getName().trim();
						confirmPro = person.getProfessor().trim();//trim()÷¡πÿ÷ÿ“™£°
						id = person.getID().trim();
						deptName = person.getDeptName().trim();
						if(confirmPro.equals("Manager")){
							setVisible(false);
							MainframeM MFM = new MainframeM(headName);
							MFM.setVisible(true);
						}
						else{
							setVisible(false);
							MainframeE MFE = new MainframeE(headName);
							MFE.setVisible(true);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "√‹¬ÎªÚ’ ∫≈¥ÌŒÛ", "¥ÌŒÛ!", JOptionPane.ERROR_MESSAGE);//µØ≥ˆ¥∞∏Ò
					}
					rs.close();
					dbConn.closeConn();
				} catch (SQLException sqle) {
					// TODO Auto-generated catch block
					System.out.println(sqle.toString());
				}
			}
		});
		
		//add(basicPanel);
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args){
		new Loginframe();
	}
	
}

