package formfb1;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//import java.util.Date;

import javax.swing.ButtonGroup;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import formfb.ComboBoxs;
import formfb.ConnectDB2;

public class EmployeeAWT  extends JFrame{
	JFrame f = new JFrame("Thong tin chi tiet");
	JLabel lbID = new JLabel("ID");
	JTextField jtID = new JTextField(10);
	JLabel lbName = new JLabel("Name");
	JTextField jtName = new JTextField(10);
	JLabel lbBirthday = new JLabel("Birthday");
	JCalendar clBirthday = new JCalendar();
	JLabel lbDepart = new JLabel("Depart");
	String[] department = {"VKU","DTU","DUT"};
	JComboBox cboDepartment = new JComboBox(department);
	JLabel lbgender = new JLabel("Gender");
	JRadioButton rbMale=new JRadioButton("Male");    
	JRadioButton rbFemale=new JRadioButton("Female");
	ButtonGroup br= new ButtonGroup();
	 JLabel lbRate = new JLabel("Rate");
		JTextField jtRate = new JTextField(10);
		   JButton btnsave =  new JButton("Add");
		JButton btnchange = new JButton("Change");
		PreparedStatement ps;
		Connection conn;
	 public  EmployeeAWT() {
		 f.setLocation(100, 100);
			f.setLayout(new GridLayout(5,2));
			Container cont = f.getContentPane();
			cont.add(lbID);
			cont.add(jtID);
			cont.add(lbName);
			cont.add(jtName);
			cont.add(lbBirthday);
			cont.add(clBirthday);
			cont.add(lbDepart);
			cont.add(cboDepartment);
			cont.add(lbgender);
			br.add(rbMale);
			br.add(rbFemale);
			cont.add(rbMale);
			cont.add(rbFemale);
			cont.add(lbRate);
			cont.add(jtRate);
			cont.add(btnsave);
			btnsave.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
//					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					try {
						String gender="";
						if(rbMale.isSelected()) gender=rbMale.getText();
						else gender = rbFemale.getText();
						ConnectDB3 cnn = new ConnectDB3();
						Date selectedDate = (Date) clBirthday.getDate();
						java.sql.Date birthday = convertUtilToSql(selectedDate);
						int record = cnn.excuteDB(" Insert into Employee1 values ('"+jtID.getText()+"','"+jtName.getText()+"','"+birthday+"','"+cboDepartment.getSelectedItem().toString()+"','"+gender+"','"+jtRate.getText()+"')");
						if(record >0) JOptionPane.showMessageDialog(null, "Success");
						else JOptionPane.showMessageDialog(null, "Error");
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
			});
			cont.add(btnchange);
			f.setSize(300,300);
			f.pack();
		    f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	 }
	 private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
	        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
	        return sDate;
	    }
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
			//new FacebookForm();
		    try {
		    
		    	new EmployeeAWT();    
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}
