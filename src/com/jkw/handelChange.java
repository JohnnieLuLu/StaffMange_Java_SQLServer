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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class handelChange extends JFrame{

	private JMenuBar menuBar;
	private DefaultTableModel tablemodel;
	private JButton agree = new JButton("同意");
	private JButton disagree = new JButton("拒绝");
	private String id ;

	public handelChange() {
		// TODO Auto-generated constructor stub
		super("调动管理");
		this.setSize(600, 400);
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
		
		staffManage.addSeparator();
		JMenu oldGuy = new JMenu("在职员工");
		staffManage.add(oldGuy);
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
		JMenuItem retire = new JMenuItem("退休");
		oldGuy.add(retire);
		retire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenu salary = new JMenu("工资管理");
		menuBar.add(salary);
		
		JMenuItem salaryM = new JMenuItem("修改工资");
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
		
		JLabel words_1 = new JLabel("您好," + Loginframe.headName + "经理,请开始处理" + Loginframe.deptName + "员工调动申请");
		upPanel.add(words_1);
		words_1.setBounds(10, 10, 350, 20);
		
		String titles[] = {"职工号","年","月","原部门","调入部门","期望职位"};
		this.tablemodel = new DefaultTableModel(titles,0);
		JTable jtable = new JTable(this.tablemodel);
		JScrollPane scroll = new JScrollPane(jtable);
		scroll.setBounds(0, 35, 600, 270);
		upPanel.add(scroll);
		
		/*id = (String) tablemodel.getValueAt(0, 0);
		System.out.println(id);*/
		
		upPanel.add(agree);
		agree.setBounds(300, 10, 70, 25);
		agree.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				try {
					PreparedStatement prestate = dbcon.PreparedStatement("delete from change where newdeptname = '" + Loginframe.deptName + "'");
					prestate.executeUpdate();//修改状态
					PreparedStatement prestate_ = dbcon.PreparedStatement("update person set Dept = '" + Loginframe.deptName + "' where ID = 'it02'");
					prestate_.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		upPanel.add(disagree);
		disagree.setBounds(390, 10, 70, 25);
		disagree.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				try {
					PreparedStatement prestate = dbcon.PreparedStatement("delete from change where newdeptname = '" + Loginframe.deptName + "'");
					prestate.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
					ResultSet rs = dbcon.executeQuery("select * from change where newdeptname = '" + Loginframe.deptName + "'");
					ArrayList<changeEnity> c = new ArrayList<>();
					while(rs.next()){
						changeEnity change_ = new changeEnity();
						change_.setID(rs.getString("ID"));
						change_.setYear(rs.getInt("year"));
						change_.setMonth(rs.getInt("month"));
						change_.setExdeptname(rs.getString("exdeptname"));
						change_.setNewdeptname(rs.getString("newdeptname"));
						change_.setProfessor(rs.getString("professor"));
						c.add(change_);
					}
					tablemodel.setRowCount(c.size());
					for(int i = 0;i < c.size();i++){
						tablemodel.setValueAt(c.get(i).getID(), i, 0);
						tablemodel.setValueAt(c.get(i).getYear(), i, 1);
						tablemodel.setValueAt(c.get(i).getMonth(), i, 2);
						tablemodel.setValueAt(c.get(i).getExdeptname(), i, 3);
						tablemodel.setValueAt(c.get(i).getNewdeptname(), i, 4);
						tablemodel.setValueAt(c.get(i).getProfessor(), i, 5);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
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
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
	}
	
	/*public static void main(String args[]){
	new handelChange();
}*/

}
