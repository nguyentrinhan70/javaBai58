package nguyentrinhan70.example.com.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MaiUI extends JFrame {
	
	JButton btnSelect, btnInsert, btnUpdate, btnDelete;
	public MaiUI (String title){
		super(title);
		addControls();
		addEvents();
		
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SelectUI ui = new SelectUI("Màn hình Select cho Prepared Statement");
				ui.showWindow();
			}
		});
		
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InsertUI ui = new InsertUI("Màn hình Insert cho Prepastatement");
				ui.showWindow();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UpdateUI ui = new UpdateUI("Màn hình update cho Prepared Statemen");
				ui.showWindow();

			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SelectToDeleteUI ui = new SelectToDeleteUI("Màn hình Delete cho Prepatatement");
				ui.showWindow();
				
			}
		});
		
	}

	private void addControls() {
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new FlowLayout());
		btnSelect = new JButton("Prepared Statement Select");
		btnInsert = new JButton("Prepared Statement Insert");
		btnUpdate = new JButton("Prepared Statement Update");
		btnDelete = new JButton("Prepared Statement Delete");
		pnMain.add(btnSelect);
		pnMain.add(btnInsert);
		pnMain.add(btnUpdate);
		pnMain.add(btnDelete);
		con.add(pnMain);
		
	}
	public void showWindow(){
		this.setSize(300, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
}
