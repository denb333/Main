package formfb;

import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.FlowLayout; 
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import DBManage.ConnectDB;

import java.awt.CardLayout;


public class ComboBoxs extends JFrame {
	JFrame f = new JFrame("The Frame");
	JLabel lbUser= new JLabel("Username");
	JTextField tfUser = new JTextField(10);
	JLabel lbPassword = new JLabel("Password");
	JPasswordField tfPassword = new JPasswordField(10);
	JLabel lbDepart = new JLabel("Depart");
	String[] department = {"QuangNam University","DaNang University","Ho Chi Minh University"};
	JComboBox cboDepartment = new JComboBox(department);
	// Checkbox
	JCheckBox cbCS = new JCheckBox("Computer Science");
	JCheckBox cbEco = new JCheckBox("Economic");
 	JButton btnSignUp = new JButton("Sign Up");
	JButton btnchange = new JButton("Change");
	JButton btnDelete = new JButton("Delete");
	JButton btnList = new JButton("List");
	JRadioButton rbMale=new JRadioButton("Male");    
	JRadioButton rbFemale=new JRadioButton("Female");
	ButtonGroup br= new ButtonGroup();
	JTable jtbAccount ;
	
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	public ComboBoxs() {
		//tao 1 container de add cac component vao
		 // this.getContentPane().;
		f.setLocation(100, 100);
		f.setLayout(new GridLayout(8,2));
		Container cont = f.getContentPane();
		//cont.setLayout(new LayoutManager());
		cont.add(lbUser);
		cont.add(tfUser);
		cont.add(lbPassword);
		cont.add(tfPassword);
		cont.add(lbDepart);
		cont.add(cboDepartment);
		br.add(rbMale);
		br.add(rbFemale);
		cont.add(rbMale);
		cont.add(rbFemale);
		cont.add(cbCS);
		cont.add(cbEco);
		//Hobbies : Game Music
		cont.add(btnSignUp);
		 cont.add(btnchange);
		 cont.add(btnDelete);
		 cont.add(btnList);
		 
		 ConnectDB2 cnn = new ConnectDB2();
		 rs = cnn.ListAccount("select *from Account");
		 String title[]= {"Name","Pass","Gender","Department","Faculty"};
		  DefaultTableModel tm = new DefaultTableModel(title,0);
		 try {
		  while(rs.next()) {
			  Object data[]= {rs.getString("Name"),rs.getString("Pass"),rs.getString("Gender"),rs.getString("Department"),rs.getString("Faculty")
		  };
			  
//		 String data[][]= { {"101","Amit","670000"},    
//                 {"102","Jai","780000"},    
//                 {"101","Sachin","700000"}};
//		 String column[]= {"Name","Pass","Gender","Department","Faculty"};
		 jtbAccount= new JTable(tm);
		 tm.addRow(data);
		 }
		  } catch (Exception e2) {
				// TODO: handle exception
			  e2.printStackTrace();
			}
		 JScrollPane sp = new JScrollPane(jtbAccount);
		 cont.add(sp);
		 
		 btnSignUp.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent e) {
			ConnectDB2 cnn = new ConnectDB2();
			String gender="";
			if(rbMale.isSelected()) gender=rbMale.getText();
			else gender = rbFemale.getText();
			String depart = cboDepartment.getSelectedItem().toString();
			String faculty = "";
//			if(cbCS.isSelected()) faculty +=cbCS.getText();
//			if(cbEco.isSelected()) faculty += cbEco.getText();
//			int record = cnn.excuteDB("exec sp_addUser1'"+tfUser.getText()+"','"+tfPassword.getText()+"','"+gender+"','"+depart+"','"+faculty+"'");
//			if(record >0) JOptionPane.showMessageDialog(null, "Success");
//			else JOptionPane.showMessageDialog(null, "Fail");
//			
			conn = cnn.connectSQL();
			try {
				ps=conn.prepareStatement("exec sp_addUser1 ?,?,?,?,?");
				ps.setString(1, tfUser.getText());
				ps.setString(2, tfPassword.getText());
				ps.setString(3, gender);
				ps.setString(4, depart);
				ps.setString(5, faculty);
				int record = ps.executeUpdate();
				if(record >0) JOptionPane.showMessageDialog(null, "Success");
				else JOptionPane.showMessageDialog(null, "Fail");
				DisplayAccount();
			} catch (Exception e2) {
				// TODO: handle exception
			}
				}
				
		    });
	
		 btnchange.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent e) {
			ConnectDB2 cnn = new ConnectDB2();
//			String sql = "exec sp_UPdate2 ?,?";
//			try {
//				ps = cnn.connectSQL().prepareStatement(sql);
//				ps.setString(1, tfUser.getText());
//				ps.setString(2, tfPassword.getText());
//				ps.executeUpdate();
//				JOptionPane.showMessageDialog(null, "Change Pas Sucessfully!");
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			String gender="";
			if(rbMale.isSelected()) gender=rbMale.getText();
			else gender = rbFemale.getText();
			String depart = cboDepartment.getSelectedItem().toString();
			String faculty = "";
			if(cbCS.isSelected()) faculty +=cbCS.getText();
			if(cbEco.isSelected()) faculty += cbEco.getText();
			int record = cnn.excuteDB("exec sp_Update1 '"+tfUser.getText()+"','"+tfPassword.getText()+"','"+gender+"','"+depart+"','"+faculty+"'");
			
			
			
			if(record >0) JOptionPane.showMessageDialog(null, "Change pass successfully");
			else JOptionPane.showMessageDialog(null, "Error");
				}
		    	
		    });
		 btnDelete.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent e) {
			ConnectDB2 cnn = new ConnectDB2();
//			conn=cnn.connectSQL();
//		String sql = "exec sp_delete ?";
//		 try {
//             ps = conn.prepareStatement(sql);
//             ps.setString(1, tfUser.getText());
//             int record = ps.executeUpdate();
//             if(record >0) JOptionPane.showMessageDialog(null, "success");
// 			else JOptionPane.showMessageDialog(null, "Fail");
//         } catch (SQLException ex) {
//             ex.printStackTrace();
//         }
			int record = cnn.excuteDB("exec sp_delete3 '"+tfUser.getText()+"'");
			if(record >0) JOptionPane.showMessageDialog(null, "Change pass successfully");
			else JOptionPane.showMessageDialog(null, "Error");
				}
		    	
		    });
		 btnList.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent e) {
					ConnectDB2 cnn = new ConnectDB2();
					rs = cnn.ListAccount("exec PK_seachbypass1 '"+tfPassword.getText()+"'");
					 try {
						 while(rs.next()) {
								System.out.println("Name="+rs.getString("Name")+" Pass "+rs.getString("Pass")+" Gender "+rs.getString("Gender"));
						 }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
//					conn= cnn.connectSQL();
//					String sql = "select *from Account";
//					try {
//						ps=conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				if(rs!=null) {
//					while(rs.next()) {
//						String Name = rs.getString("Name");
//						String Pass = rs.getString("Pass");
//						String Gender = rs.getString("Gender");
//						tfUser.setText(Name);
//						tfPassword.setText(Pass);
//					}
//				}
//					} catch (Exception e2) {
//						// TODO: handle exception
//					}

				}	
		    });
		f.setSize(200,200);
		f.pack();
	    f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void DisplayAccount() {
		ConnectDB2 cnn = new ConnectDB2();
		 rs = cnn.ListAccount("select *from Account");
		 String title[]= {"Name","Pass","Gender","Department","Faculty"};
		  DefaultTableModel tm = new DefaultTableModel(title, 0);
		 try {
		  while(rs.next()) {
			  Object data[]= {rs.getString("Name"),rs.getString("Pass"),rs.getString("Gender"),rs.getString("Department"),rs.getString("Faculty")
		  };
			  

		 jtbAccount= new JTable(tm);
		 tm.addRow(data);
		 }
		  } catch (Exception e2) {
				// TODO: handle exception
			}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new FacebookForm();
	    try {
	    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    	new ComboBoxs();    
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
