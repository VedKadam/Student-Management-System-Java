import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


class ViewWindow extends JFrame{
	Container c;
	TextArea taData;
	JButton btnBack;

	ViewWindow()
	{
		c=getContentPane();
		c.setLayout(null);
		
		Font f = new Font("Arial", Font.BOLD, 15);

		taData = new TextArea(10, 20);
		btnBack = new JButton("Back");
		
		taData.setFont(f);
		btnBack.setFont(f);
	
		taData.setBounds(160, 100, 500, 200);
		btnBack.setBounds(300, 350, 200, 40);
		
		c.add(taData);
		c.add(btnBack);

		 ActionListener a1 = (ae)-> { 
			MainWindow a = new MainWindow();
			dispose();
		};
		btnBack.addActionListener(a1);
		
		try{
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/sms_26oct22";
				String username = "root";
				String password = "abc123";
				Connection con = DriverManager.getConnection(url, username, password);
				String sql = "select * from student";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
				while(rs.next())
				{
					String data = "rno " + rs.getInt(1) + " name " + rs.getString(2) + " submarks1 " + rs.getString(3) + " submarks2 " + rs.getString(4) + " submarks3 " + rs.getString(5) ;
					sb.append(data + "\n");
				}
				taData.setText(sb.toString());
				con.close();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(c, "issue " + e);
		}		
		
		setTitle("View Employee");
		setSize(800, 600);
		getContentPane().setBackground(Color.CYAN);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


}