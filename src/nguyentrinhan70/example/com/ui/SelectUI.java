package nguyentrinhan70.example.com.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Driver;

public class SelectUI extends JFrame {
	DefaultTableModel dtmTaiSan;
	JTable tblTaiSan;
	Connection conn = null;
	
	public SelectUI(String title){
		super(title);
		addControls();
		addEvents();
		ketNoi();
		hienThiDanhSachTaiSan();
		
	}

	private void hienThiDanhSachTaiSan() {
		// TODO Auto-generated method stub
		try{
			String sql = "Select * from taisan where giatri>?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, 50);
			dtmTaiSan.setRowCount(0);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				Object [] arr = {resultSet.getString(1),
						resultSet.getString(2),
						resultSet.getDate(3),
						resultSet.getInt(4),
						resultSet.getInt(5)};
				dtmTaiSan.addRow(arr);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void ketNoi() {
		// TODO Auto-generated method stub
		try{
			String strConn = "jdbc:mysql://localhost/dbquanlytaisan?"
					+ "useUnicode=true&characterEncoding=utf-8";
			Properties pro = new Properties();
			pro.put("user", "root");
			pro.put("password", "");
			Driver driver = new Driver();
			conn = driver.connect(strConn, pro);


		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		dtmTaiSan = new DefaultTableModel();
		dtmTaiSan.addColumn("Mã tài sản");
		dtmTaiSan.addColumn("Tên tài sản");
		dtmTaiSan.addColumn("Ngày nhập");
		dtmTaiSan.addColumn("Khấu hao");
		dtmTaiSan.addColumn("Giá trị");
		tblTaiSan = new JTable(dtmTaiSan);
		JScrollPane sc = new JScrollPane(tblTaiSan, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		con.add(sc, BorderLayout.CENTER);
		
	}
	public void showWindow(){
		this.setSize(600, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}
