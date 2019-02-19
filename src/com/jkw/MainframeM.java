package com.jkw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainframeM extends JFrame{
	private JMenuBar menuBar;
	
	public MainframeM(){
		
		super("欢迎你");
		this.setSize(600, 300);
		this.setLocation(500, 250);
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
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
				dispose();
				noobRegister nrg = new noobRegister();
				nrg.setVisible(true);
			}
		});
		staffManage.addSeparator();
		
		JMenu oldGuy = new JMenu("在职员工");
		staffManage.add(oldGuy);
		
		JMenuItem attendanceM = new JMenuItem("考勤");
		oldGuy.add(attendanceM);
		attendanceM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				AttendanceM atdm = new AttendanceM();
				atdm.setVisible(true);
			}
		});
		oldGuy.addSeparator();
		JMenuItem resignM = new JMenuItem("辞退");
		oldGuy.add(resignM);
		resignM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				fuckAway fa = new fuckAway();
				fa.setVisible(true);
			}
		});
		oldGuy.addSeparator();
		JMenuItem changeM = new JMenuItem("调动");
		oldGuy.add(changeM);
		changeM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				handelChange hadc = new handelChange();
				hadc.setVisible(true);
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
		
		JMenuItem calage = new JMenuItem("年龄");
		oldGuy.add(calage);
		calage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				age a = new age();
				a.setVisible(true);
			}
		});
		
		JMenuItem search = new JMenuItem("查询");
		oldGuy.add(search);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				searchEmployee b = new searchEmployee();
				b.setVisible(true);
			}
		});
		
		JMenu salary = new JMenu("工资管理");
		menuBar.add(salary);
		
		JMenuItem salaryM = new JMenuItem("发放工资");
		salary.add(salaryM);
		salaryM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				SalaryM salM = new SalaryM();
				salM.setVisible(true);
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
	
	public MainframeM(String name_M){
		this();
		if(name_M != null){
			this.setTitle("欢迎你，"+name_M + "经理");
		}
	}
}
