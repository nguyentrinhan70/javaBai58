package nguyentrinhan70.example.com.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Driver;

public class SelectToDeleteUI extends JFrame {
	DefaultTableModel dtmTaiSan;
	JTable tblTaiSan;
	Connection conn = null;

	JMenuItem mnuDelete;
	JPopupMenu popMenu;
	public SelectToDeleteUI(String title){
		super(title);
		addControls();
		addEvents();
		ketNoi();
		hienThiDanhSachTaiSan();

	}

	private void hienThiDanhSachTaiSan() {
		// TODO Auto-generated method stub
		try{
			String sql = "Select * from taisan";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
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
		tblTaiSan.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.isPopupTrigger()){
					int row = tblTaiSan.rowAtPoint(e.getPoint());
					int column = tblTaiSan.columnAtPoint(e.getPoint());
					if(!tblTaiSan.isRowSelected(row))
						tblTaiSan.changeSelection(row, column, false, false);
					popMenu.show(e.getComponent(), e.getX(), e.getY());
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		mnuDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyXoa();
			}
		});
	}

	protected void xuLyXoa() {
		// TODO Auto-generated method stub
		try{
			String ma = tblTaiSan.getValueAt(tblTaiSan.getSelectedRow(), 0) +"";
			String sql = "delete from taisan where ma = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, ma);
			int x = preparedStatement.executeUpdate();
			if(x>0){
				JOptionPane.showConfirmDialog(null, "Xóa thành công");
				hienThiDanhSachTaiSan();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
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

		mnuDelete = new JMenuItem("Xóa");
		popMenu = new JPopupMenu();
		popMenu.add(mnuDelete);
	}
	public void showWindow(){
		this.setSize(600, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
