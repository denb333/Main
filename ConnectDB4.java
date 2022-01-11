package formfb1;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB4 {
	Statement stmt;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
    public void connect() {
    	try {
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		String connectionurl = "jdbc:sqlserver://ADMIN\\SQLEXPRESS:1433;databaseName=VTLORDER;user=sa;password=123456";
    		conn = DriverManager.getConnection(connectionurl);
    		System.out.println(" Connected.....");
    		

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
    public Connection connectSQL(String string) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getConstructor().newInstance();
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://ADMIN\\SQLEXPRESS:1433;databaseName=VTLORDER;user=sa;password=123456";
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected...");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
    }
    public int excuteDB(String sql) {
		int record=0;
		try {
			connect();
			stmt = conn.createStatement();
			record = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch ( Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return record;
	
}
    public ResultSet ListAccount(String sql) {
    	try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
//				conn.close();
//				stmt.close();
			} catch ( Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
    	return rs;
    }
    public int excuteDBPrepare(String sql) {
		int record=0;
		try {
			connect();
		ps = conn.prepareStatement(sql);
		record =ps.executeUpdate();
//			record = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch ( Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return record;
    }
	public PreparedStatement prepareStatement(String sql) {
		// TODO Auto-generated method stub
		return null;
	}}

