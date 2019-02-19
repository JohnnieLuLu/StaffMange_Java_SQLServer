package com.jkw;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class searchEmployee extends JFrame{

	private JButton search = new JButton("查询");
	private DefaultTableModel tablemodel;
	private JTextField input = new JTextField();
	
	public searchEmployee() {
		// TODO Auto-generated constructor stub
		super("搜寻员工信息");
		this.setSize(600, 300);
		this.setLocation(500, 250);
		
		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBackground(new Color(120,200,255));
		
		JLabel words_id = new JLabel("员工ID");
		upPanel.add(words_id);
		words_id.setBounds(20, 10, 70, 20);
		
		upPanel.add(input);
		input.setBounds(70, 10, 70, 20);
		
		upPanel.add(search);
		search.setBounds(180, 10, 60, 20);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				db dbcon = new db();
				String id = input.getText();
				try {
					ResultSet rs = dbcon.executeQuery("select * from person where ID like '%"+ id + "%'");
					ArrayList<PersonEnity> v= new ArrayList<>();
					while(rs.next()){
						PersonEnity per = new PersonEnity();
						per.setID(rs.getString("ID"));
						per.setName(rs.getNString("Name"));
						per.setSex(rs.getString("Sex"));
						per.setBirthDay(rs.getDate("Birthday"));
						per.setHireDay_year(rs.getInt("Hireday_year"));
						per.setHireDay_mon(rs.getInt("Hireday_mon"));
						per.setProfessor(rs.getString("Professor"));
						per.setDeptName(rs.getString("Dept"));
						v.add(per);
					}
					rs.close();
					tablemodel.setRowCount(v.size());
					for(int j =0 ; j < v.size(); j++){
						tablemodel.setValueAt(v.get(j).getID(), j, 0);
						tablemodel.setValueAt(v.get(j).getName(), j, 1);
						tablemodel.setValueAt(v.get(j).getSex(), j, 2);
						tablemodel.setValueAt(v.get(j).getBirthDay(), j, 3);
						tablemodel.setValueAt(v.get(j).getHireDay_year(), j, 4);
						tablemodel.setValueAt(v.get(j).getHireDay_mon(), j, 5);
						tablemodel.setValueAt(v.get(j).getProfessor(), j, 6);
						tablemodel.setValueAt(v.get(j).getDeptName(), j, 7);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		String titles[] = {"职工号","姓名","性别","生日","雇佣年","雇佣月","职位","部门"};
		this.tablemodel = new DefaultTableModel(titles,0);
		JTable jtable = new JTable(this.tablemodel);
		JScrollPane scroll = new JScrollPane(jtable);
		scroll.setBounds(0, 35, 600, 270);
		upPanel.add(scroll);
		
		basicPanel.add(upPanel,BorderLayout.CENTER);
		this.setContentPane(basicPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String args[]){
		new searchEmployee();
	}
	
}
