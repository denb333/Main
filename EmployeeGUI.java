package formfb1;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.Date;
import com.toedter.calendar.JCalendar;

import DBManage.ConnectDB;

public class EmployeeGUI extends JFrame{
	JFrame f = new JFrame();
	JLabel lbID = new JLabel("Id");
	JTextField tfID = new JTextField(10);
	JLabel lbName = new JLabel("Name");
	JTextField tfName = new JTextField(10);
	JLabel lbBirthday = new JLabel("Birthday");
	JCalendar clBirthday = new JCalendar();
	
	
	//JTextField tfBirthday = new JTextField(10);  
	JLabel lbRate = new JLabel("Rate");
	JTextField tfRate = new JTextField(10);
	JLabel blDep = new JLabel("Department");
	String Depart[] = {"VKU","DTU","DUT"};
	JComboBox cbdepart = new JComboBox(Depart);
	JButton btSignUp = new JButton("Sign Up");
	JButton btSelect = new JButton("Select");
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	JTable tbgk;
	public EmployeeGUI() {
		f.setLocation(300,100);
		f.setLayout(new GridLayout(8,2));
		Container cont =  f.getContentPane();
		cont.add(lbID);
		cont.add(tfID);
		cont.add(lbName);
		cont.add(tfName);
		cont.add(lbBirthday);
		cont.add(clBirthday);
		 clBirthday.setBounds(500, 400, 900, 800);
		cont.add(lbRate);
		cont.add(tfRate);
		cont.add(blDep);
		cont.add(cbdepart);
		cont.add(btSignUp);
		cont.add(btSelect);
		
		btSignUp.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
			//covert string sang date trong java
				SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
				try {
					//Date birthday=formatter1.parse(tfBirthday.getText());
					Date selectedDate = (Date) clBirthday.getDate();
					java.sql.Date birthday = convertUtilToSql(selectedDate);
					ConnectDB3 conn = new ConnectDB3();
					
					int record = conn.excuteDB("Insert into Employee values('"+tfID.getText()+"','"+tfName.getText()+"','"+birthday+"','"+cbdepart.getSelectedItem().toString()+"','"+tfRate.getText()+"')");
					if(record >0) JOptionPane.showMessageDialog(null, "Success");
					else JOptionPane.showMessageDialog(null, "Fail");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
			}
			
			
		});
		btSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ConnectDB3 conn = new ConnectDB3();
//                 conn = ConnectDB3.co
                 String sql = "SELECT * FROM Employee";
                 try {
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs != null){
	                      while(rs.next()){
	                      String ID = rs.getString("Id");
	                      String Name = rs.getString("Name");
	                      Date birthday = rs.getDate("Birthday");
	                      String depart = rs.getString("Depart");
	                      float rate = rs.getFloat("Rate");
	                      System.out.println(ID+"\n"+Name+"\n"+depart+"\n"+birthday+"\n"+rate);
	                      
	                      }
	                      rs.close();
	                      }
	                      ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                 
			}
		});
		Displaygk();
		JScrollPane sp = new JScrollPane(tbgk);
		 sp.setBounds(100, 200, 900, 400);
		cont.add(sp);
		f.pack();
		f.setSize(800,800);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	 public void Displaygk() {
		   ConnectDB3 conn = new ConnectDB3();
	        rs= conn.ListAccount("select * from Employee");
	      String column[] = {"Id", "Name"," Birthday"," Department"," Rate"};
	        DefaultTableModel tm = new DefaultTableModel (column, 0);
	        try {
				while (rs.next()) {
					Object data[]= {rs.getString("Id"), rs.getString("Name"),rs.getString("Birthday"), rs.getString("Depart"),rs.getString("Rate")};    
				
					tbgk = new JTable(tm);
					tm.addRow(data);
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	}

	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
	public static void main(String[] args) {
		new EmployeeGUI();
	}
}
