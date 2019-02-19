package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainframeE extends JFrame {
	private JMenuBar menuBar;	
	
	public MainframeE(){
		super("欢迎你");
		this.setSize(600, 345);
		this.setLocation(500, 250);
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu search = new JMenu("查询");
		menuBar.add(search);
		
		JMenuItem salary = new JMenuItem("工资");
		search.add(salary);
		salary.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				SalaryE salary_e = new SalaryE();
				salary_e.setVisible(true);
			}
		});
		search.addSeparator();
		JMenuItem attendance = new JMenuItem("考勤");
		attendance.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				AttendanceE attendance_e = new AttendanceE();
				attendance_e.setVisible(true);
			}
		});
		search.add(attendance);
		
		JMenu staff = new JMenu("人事");
		menuBar.add(staff);
		
		JMenuItem change =  new JMenuItem("调动");
		staff.add(change);
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Change change = new Change();
				change.setVisible(true);
			}
		});
		staff.addSeparator();
		JMenuItem resign = new JMenuItem("辞职");
		resign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ResignE rsg = new ResignE();
				setVisible(false);
				rsg.setVisible(true);
			}
		});
		staff.add(resign);
		
		JMenu update = new JMenu("个人信息更改");
		menuBar.add(update);
		
		JMenuItem pwd = new JMenuItem("个人密码修改");
		pwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				updatePassword upp = new updatePassword();
				upp.setVisible(true);
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
		
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp_image = new JPanel(){
			private static final long seriaVersionUID=1L;
			protected void paintComponent(Graphics arg0){
				super.paintComponent(arg0);
				Image img = null;
				try {
					img = ImageIO.read(new File("src/image/bgc.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				arg0.drawImage(img, 180, 0, 250,250,null);
			}
		};
		basicPanel.add(jp_image,BorderLayout.CENTER);
		
	}
	
	public MainframeE(String name_E){
		this();
		if(name_E != null){
			this.setTitle("欢迎你，"+name_E);
		}
	}
	
	
}
