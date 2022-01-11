package formfb1;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.Calendar;

public class OrderOOP extends JFrame{
	JFrame f = new JFrame("Orderl");
	JLabel lbMAKH = new JLabel("MAKH");
	JTextField tfMAKH= new JTextField(10);
	JLabel lbDONDH = new JLabel("DONDH");
	JTextField tfDONDH= new JTextField(10);
	JLabel lbOrderDate = new JLabel("NGAYDH");
	
    JCalendar jcOrderDate = new JCalendar();
	JLabel lbQuantity = new JLabel("QUANTITY");
	JTextField tfQuantity = new JTextField(10);
	JLabel lbPRODUCTID = new JLabel("PRODUCTID");
	JTextField tfPRODUCTID = new JTextField(10);
	JButton btInsert = new JButton("Insert");
	JButton btSelect = new JButton("Select");
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	JTable tbgk;
	JScrollPane sp = new JScrollPane(tbgk);
	public OrderOOP() {
		f.setLocation(250,150);
		f.setLayout(new GridLayout(7,2));
		Container cont =  f.getContentPane();
		cont.add(lbDONDH);
		cont.add(tfDONDH);
		cont.add(lbOrderDate);
		cont.add(jcOrderDate);
		cont.add(lbMAKH);
		cont.add(tfMAKH);
        cont.add(lbQuantity);
		cont.add(tfQuantity);
		cont.add(lbPRODUCTID);
		cont.add(tfPRODUCTID);
		cont.add(btInsert);
		cont.add(btSelect);
		btInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
				try {
					Date selectedDate = (Date) jcOrderDate.getDate();
					java.sql.Date  birthday = convertUtilToSql(selectedDate);
					checkbirthday(tfDONDH.getText());
					ConnectDB4 conn = new ConnectDB4();
//					checkOrdetID(tfDONDH.getText());
//					checkbirthday(tfDONDH.getText());
					int record = conn.excuteDB("Insert into DONDATHANG values('"+tfDONDH.getText()+"','"+tfMAKH.getText()+"','"+birthday+"','"+tfQuantity.getText()+"','"+tfPRODUCTID.getText()+"')");
					if(record >0) JOptionPane.showMessageDialog(null, "Success");
					else JOptionPane.showMessageDialog(null, "Fail");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
//		btInsert.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//			}
//			
//		});
		
		Displaygk();
		sp.setBounds(100, 200, 900, 400);
		cont.add(sp);
		f.pack();
		f.setSize(500,500);
		f.setVisible(true);
	}
	public void Displaygk() {
		   ConnectDB4 conn = new ConnectDB4();
	        rs= conn.ListAccount("select * from DONDATHANG");
	      String column[] = {"MAKH","TENKH","DIACHI","SDT"};
	        DefaultTableModel tm = new DefaultTableModel (column, 0);
	        try {
				while (rs.next()) {
					Object data[]= {rs.getString("IDDON"), rs.getString("MAKH"),rs.getString("NGAYDATHANG"), rs.getString("QUANTITY")};    
				
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
	public void checkOrdetID(String str ) throws Exception {
		String emailulg = "^[\\w-]+@([\\w- ]+\\.)+[\\w-]+$";
		//^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$
		Boolean b = str.matches(emailulg);
		if(b==false) throw new Exception("Dia chi Email khong hop le");
	}
	public void checkbirthday (String str) throws Exception {
		Pattern patternDate = Pattern.compile("^\\d{2}[-|/]\\d{2}[-|/]\\d{4}$");
		Boolean b= patternDate.matcher(str).matches();
		if(b==false) throw new Exception("Ngay thang nam sinh khong hop le");
	}
//	public static void checkbirthday (String birthday) throws Exception {
//		Pattern patternDate = Pattern.compile("^\\d{2}[-|/]\\d{2}[-|/]\\d{4}$");
//		Boolean b= patternDate.matcher(birthday).matches();
//		if(b==false) throw new Exception("Ngay thang nam sinh khong hop le");
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OrderOOP();
	}

}
