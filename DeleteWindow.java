import java.awt.*;    
import java.awt.event.*;        
import javax.swing.*;   
import java.sql.*;
import java.util.*;

class DeleteWindow extends JFrame{
	Container c;
	JLabel labrno;
	JTextField txtrno;
	JButton btnSave, btnBack;

	DeleteWindow(){
		c = getContentPane();
		c.setLayout(null);
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");
	
		labrno = new JLabel("Enter Rno ");
		txtrno = new JTextField(20);
		
		Font f = new Font("Arial", Font.BOLD, 30);		

		labrno.setFont(f);
		txtrno.setFont(f);
		
		labrno.setBounds(350, 30, 200, 40);
        	txtrno.setBounds(200, 90, 400, 40); 
        	btnSave.setBounds(260, 160, 100, 40);
        	btnBack.setBounds(420, 160, 100, 40); 

		c.add(labrno);
		c.add(txtrno);
		c.add(btnSave);
		c.add(btnBack);

	    ActionListener a1 = (ae)-> { 
			MainWindow a = new MainWindow();
			dispose();
		};
		
		btnBack.addActionListener(a1);
				
	ActionListener a2 = (ae)->{
			try{
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/sms_26oct22";
				String username = "root";
				String password = "abc123";
				Connection con = DriverManager.getConnection(url, username, password);
				String sql = "DELETE FROM student WHERE rno = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				int rno = Integer.parseInt(txtrno.getText());
				pst.setInt(1, rno);
				int count = pst.executeUpdate();
				if (count == 0) {
                    			JOptionPane.showMessageDialog(c, "Record not found.");
                		} else if(rno<1){
					JOptionPane.showMessageDialog(c, "rno should be positive integers only");
				}
				 else {
                    			JOptionPane.showMessageDialog(c, "Record deleted.");
                		}
				
				txtrno.setText("");
				txtrno.requestFocus();
				con.close();
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(c, "issue " + e);
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(c,"INTEGERS ONLY");
			}
		};
		btnSave.addActionListener(a2);

		setTitle("Delete Student Information ");
		setSize(800, 600);
		getContentPane().setBackground(Color.CYAN);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}