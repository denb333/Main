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
import java.util.Calendar;

public class SelectOrder extends JFrame{
	JFrame f = new JFrame("Orderl");
	
	JLabel lbOrderDateStart = new JLabel("OrderDate Start");
	JCalendar jcOrderDateStart = new JCalendar();
	JLabel lbOrderDateEnd = new JLabel("OrderDate End");
	JCalendar jcOrderDateEnd = new JCalendar();
	
	JButton btSelect = new JButton("Select");
	
	JFrame f1 = new JFrame();
	
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	JTable tbmonth = new JTable();
	JScrollPane sp = new JScrollPane(tbmonth);
	public SelectOrder() {
		f.setLocation(250,150);
		f.setLayout(new GridLayout(4,2));
		Container cont =  f.getContentPane();
		
		cont.add(lbOrderDateStart);
		cont.add(jcOrderDateStart);
		cont.add(lbOrderDateEnd);
		cont.add(jcOrderDateEnd);
		cont.add(btSelect);
		f.pack();
		f.setSize(500,500);
		f.setVisible(true);
		
	
		btSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jframe2();
			}
		});
		
		
	}
	
	public void jframe2() {	
		
		f1.setLocation(250,150);
		f1.setLayout(new GridLayout(4,2));
		Container cont1 =  f1.getContentPane();
		
		cont1.add(sp);
		f1.pack();
		f1.setSize(400,400);
		f1.setVisible(true);
		DisplayOrder();
	}
	public void DisplayOrder() {
		   ConnectDB4 conn = new ConnectDB4();
	        rs= conn.ListAccount("select KHACHHANG.MAKH, KHACHHANG.TENKH,PRODUCT.PRODUCTID, PRODUCT.ProductName from KHACHHANG, DONDATHANG,PRODUCT\r\n"
	        		+ "where KHACHHANG.MAKH = DONDATHANG.MAKH and PRODUCT.PRODUCTID = DONDATHANG.PRODUCTID and MONTH(NGAYDATHANG) = 3 ");
	      String column[] = {"MAKH", "TENKH"," PRODUCTID"," ProductName"};
	        DefaultTableModel tm = new DefaultTableModel (column, 0);
	        try {
				while (rs.next()) {
					Object data[]= {rs.getString("MAKH"), rs.getString("TENKH"),rs.getString("PRODUCTID"),rs.getString("ProductName")};    
	
					tm.addRow(data);
					
				}
				tbmonth.setModel(tm);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SelectOrder();
		
	}

}
