import java.awt.*;    
import java.awt.event.*;        
import javax.swing.*;   
import java.sql.*;
import java.util.*;

class UpdateWindow extends JFrame{
	Container c;
	JLabel labrno, labname, labsubmarks1, labsubmarks2, labsubmarks3;
	JTextField txtrno, txtname, txtsubmarks1, txtsubmarks2, txtsubmarks3;
	JButton btnSave, btnBack;

	UpdateWindow(){
		c = getContentPane();
		c.setLayout(null);
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");
				
		labrno = new JLabel("Enter Rno ");
		txtrno = new JTextField(10);
		labname = new JLabel("Enter Name");
		txtname= new JTextField(10);
		labsubmarks1 = new JLabel("Enter Marks of Subject 1");
		txtsubmarks1= new JTextField(10);
		labsubmarks2 = new JLabel("Enter Marks of Subject 2");
		txtsubmarks2= new JTextField(10);
		labsubmarks3 = new JLabel("Enter Marks of Subject 3");
		txtsubmarks3= new JTextField(10);
		
		Font f = new Font("Arial", Font.BOLD, 15);		

		labrno.setFont(f);
		txtrno.setFont(f);
		labname.setFont(f);
		txtname.setFont(f);
		labsubmarks1.setFont(f);
		txtsubmarks1.setFont(f);
		labsubmarks2.setFont(f);
		txtsubmarks2.setFont(f);
		labsubmarks3.setFont(f);
		txtsubmarks3.setFont(f);
 
		
		labrno.setBounds(100, 10, 200, 40);
        	txtrno.setBounds(200, 50, 200, 40); 
        	labname.setBounds(100, 100, 200, 40); 
        	txtname.setBounds(200, 140, 200, 40);
        	labsubmarks1.setBounds(100, 190, 200, 40);
        	txtsubmarks1.setBounds(200, 230, 200, 40);
        	labsubmarks2.setBounds(100, 280, 200, 40);
        	txtsubmarks2.setBounds(200, 320, 200, 40);
        	labsubmarks3.setBounds(100, 370, 200, 40);
        	txtsubmarks3.setBounds(200, 410, 200, 40);
        	btnSave.setBounds(100, 460, 100, 40);
        	btnBack.setBounds(220, 460, 100, 40); 


		c.add(labrno);
		c.add(txtrno);
		c.add(labname);
		c.add(txtname);
		c.add(labsubmarks1);
		c.add(txtsubmarks1);
		c.add(labsubmarks2);
		c.add(txtsubmarks2);
		c.add(labsubmarks3);
		c.add(txtsubmarks3);
		c.add(btnSave);
		c.add(btnBack);
		
		ActionListener a1 = (ae)-> { 
			MainWindow a = new MainWindow();
			dispose();
		};
		
		btnBack.addActionListener(a1);

		ActionListener a2 = (ae)->{
		
			try{
				String input = txtname.getText().trim();
    				if(!input.matches("[a-zA-Z]+") || input.length() < 2) {
        				JOptionPane.showMessageDialog(c, "Name should not be EMPTY & Name should contain only alphabets and minimum length should be 2.");
        				txtname.setText("");
        				txtname.requestFocus();
					return;
    				} 
					
			        String input1 = txtsubmarks1.getText().trim();
    				String input2 = txtsubmarks2.getText().trim();
    				String input3 = txtsubmarks3.getText().trim();
    				
				if(!input1.isEmpty() && !input2.isEmpty() && !input3.isEmpty()) { 
    					if (Integer.parseInt(input1) < 0 || Integer.parseInt(input1) > 100 || Integer.parseInt(input2) < 0 || Integer.parseInt(input2) > 100 || Integer.parseInt(input3) < 0 || Integer.parseInt(input3) > 100) {
        					JOptionPane.showMessageDialog(c, "Marks should be in the range of 0-100.");
       							txtsubmarks1.setText("");
        						txtsubmarks1.requestFocus();
        						txtsubmarks2.setText("");
        						txtsubmarks2.requestFocus();
        						txtsubmarks3.setText("");
        						txtsubmarks3.requestFocus();
    					} 
				else if (!input1.matches("[0-9]+") || !input2.matches("[0-9]+") || !input3.matches("[0-9]+")) {
        				JOptionPane.showMessageDialog(c, "Marks should be positive integers only.");
					txtsubmarks1.setText("");
					txtsubmarks1.requestFocus();
					txtsubmarks2.setText("");
					txtsubmarks2.requestFocus();
					txtsubmarks3.setText("");
					txtsubmarks3.requestFocus();
    				} 
        			
				
				}
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/sms_26oct22";
				String username = "root";
				String password = "abc123";
				Connection con = DriverManager.getConnection(url, username, password);
            			String sql = "update student set name = ?, submarks1 = ?, submarks2 = ?, submarks3 = ? where rno = ?";
            			PreparedStatement pst = con.prepareStatement(sql);
				int rno = Integer.parseInt(txtrno.getText());
				String name = txtname.getText();
				int submarks1 = Integer.parseInt(txtsubmarks1.getText());
				int submarks2 = Integer.parseInt(txtsubmarks2.getText());
				int submarks3 = Integer.parseInt(txtsubmarks3.getText());

            			pst.setString(1, txtname.getText());
            			pst.setString(2, txtsubmarks1.getText());
            			pst.setString(3, txtsubmarks2.getText());
            			pst.setString(4, txtsubmarks3.getText());
            			pst.setString(5, txtrno.getText());
			
            			int row = pst.executeUpdate();
            			if (row > 0) {
                			JOptionPane.showMessageDialog(c, "Record Updated");
            			}
				else {
                			JOptionPane.showMessageDialog(c, "Record Not Found");
            			}
				txtrno.setText("");
				txtname.setText("");
				txtsubmarks1.setText("");
				txtsubmarks2.setText("");
				txtsubmarks3.setText("");
				txtrno.requestFocus();
				con.close();
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(c, "record already present");
			}
			catch(NumberFormatException e){
					JOptionPane.showMessageDialog(c, "Integers Only ");
					
					txtsubmarks1.setText("");
					txtsubmarks2.setText("");
					txtsubmarks3.setText("");
					txtsubmarks1.requestFocus();
			} 
		
    		};
        	btnSave.addActionListener(a2);
		
		setTitle("Update Student Information ");
		setSize(800, 600);
		getContentPane().setBackground(Color.CYAN);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
