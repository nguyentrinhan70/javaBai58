package nguyentrinhan70.example.com.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;

import com.mysql.jdbc.Driver;

public class InsertUI extends JFrame {
	JTextField txtMa, txtTen, txtNgayNhap, txtKhauHao, txtGiaTi;
	JButton btnLuu;
	Connection conn= null;
	public InsertUI (String title){
		super(title);
		addControls();
		addEvents();
		ketNoi();
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

		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyLuu();
			}
		});
	}

	protected void xuLyLuu() {
		// TODO Auto-generated method stub
		try{
			String sql ="Insert into taisan values(?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, txtMa.getText());
			preparedStatement.setString(2, txtTen.getText());
			preparedStatement.setString(3, txtNgayNhap.getText());
			preparedStatement.setInt(4, Integer.parseInt(txtKhauHao.getText()));
			preparedStatement.setInt(5, Integer.parseInt(txtGiaTi.getText()));
			int x = preparedStatement.executeUpdate();
			if(x>0){
				JOptionPane.showConfirmDialog(null, "Thêm tài sản mới thành công");
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMa = new JLabel("Mã:");
		txtMa = new JTextField(20);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		con.add(pnMa);

		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTen = new JLabel("Tên:");
		txtTen = new JTextField(20);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		con.add(pnTen);

		JPanel pnNgayNhap = new JPanel();
		pnNgayNhap.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgayNhap = new JLabel("Ngày nhập:");
		txtNgayNhap = new JTextField(20);
		pnNgayNhap.add(lblNgayNhap);
		pnNgayNhap.add(txtNgayNhap);
		con.add(pnNgayNhap);


		JPanel pnKhauHao = new JPanel();
		pnKhauHao.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblKhauHao = new JLabel("Khấu hao:");
		txtKhauHao = new JTextField(20);
		pnKhauHao.add(lblKhauHao);
		pnKhauHao.add(txtKhauHao);
		con.add(pnKhauHao);
		JPanel pnGiaTri = new JPanel();
		pnGiaTri.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblGiaTri = new JLabel("Giá trị:");
		txtGiaTi = new JTextField(20);
		pnGiaTri.add(lblGiaTri);
		pnGiaTri.add(txtGiaTi);
		con.add(pnGiaTri);

		lblMa.setPreferredSize(lblNgayNhap.getPreferredSize());
		lblTen.setPreferredSize(lblNgayNhap.getPreferredSize());
		lblKhauHao.setPreferredSize(lblNgayNhap.getPreferredSize());
		lblGiaTri.setPreferredSize(lblNgayNhap.getPreferredSize());
		JPanel pnButton = new JPanel();
		btnLuu = new JButton("Lưu");
		pnButton.add(btnLuu);
		con.add(btnLuu);

	}
	public void showWindow(){
		this.setSize(400, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
