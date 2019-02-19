package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class fuckAway extends JFrame {

	private JMenuBar menuBar;
	private JComboBox cmb_ID = new JComboBox();
	private JButton fuckOff = new JButton("走开!");
	
	public fuckAway() {
		// TODO Auto-generated constructor stub
		super("工资管理");
		this.setSize(600, 300);
		this.setLocation(500, 250);
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBackground(new Color(120,200,255));
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu staff = new JMenu("人事");
		menuBar.add(staff);
		
		JMenu staffManage = new JMenu("人事管理");
		staff.add(staffManage);
		
		JMenuItem noob = new JMenuItem("新员工注册");
		staffManage.add(noob);
		noob.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		staffManage.addSeparator();
		JMenu oldGuy = new JMenu("在职员工");
		staffManage.add(oldGuy);
		oldGuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		oldGuy.addSeparator();
		JMenuItem changeM = new JMenuItem("调动");
		oldGuy.add(changeM);
		changeM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		oldGuy.addSeparator();
		JMenuItem retire = new JMenuItem("退休");
		oldGuy.add(retire);
		retire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLabel words = new JLabel("您好，" + Loginframe.headName + "经理,请开始炒鱿鱼吧！" );
		upPanel.add(words);
		words.setBounds(15, 10, 320, 20);
		
		JLabel words_1 = new JLabel("被清除员工ID:");
		upPanel.add(words_1);
		words_1.setBounds(30, 40, 90, 20);
		
		upPanel.add(cmb_ID);
		cmb_ID.setBounds(120, 40, 60, 20);
		
		upPanel.add(fuckOff);
		fuckOff.setBounds(250, 90, 70, 30);
		fuckOff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				String str = cmb_ID.getSelectedItem().toString();
				int isDelete = JOptionPane.showConfirmDialog(null, "确认炒了这个人吗？","您确定吗？",JOptionPane.YES_NO_OPTION);
				if(isDelete == JOptionPane.YES_OPTION){
					try {
						PreparedStatement prestate = dbcon.PreparedStatement("delete from person where ID = '" + str + "'");
						prestate.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
				}
			}
				
			}
		});
		
		JButton exit_ = new JButton("退出");
		basicPanel.add(exit_,BorderLayout.SOUTH);
		exit_.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				try {
					ResultSet rs = dbcon.executeQuery("select ID from person where Dept = '"+ Loginframe.deptName + "'");
					while(rs.next()){
						cmb_ID.addItem(rs.getString(1).trim());
					}
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String arg[]){
		new fuckAway();
	}
}
