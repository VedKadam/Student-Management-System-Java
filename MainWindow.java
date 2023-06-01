import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainWindow extends JFrame{
	Container c;
	JButton btnAdd, btnView, btnUpdate, btnDelete;

	MainWindow()
	{
		c=getContentPane();
		c.setLayout(null);
		
		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");

		Font f = new Font("Arial", Font.BOLD, 30);

		btnAdd.setFont(f);
		btnView.setFont(f);
		btnUpdate.setFont(f);
		btnDelete.setFont(f);
		
		btnAdd.setBounds(300, 50, 200, 40);
		btnView.setBounds(300, 150, 200, 40);
		btnUpdate.setBounds(300, 250, 200, 40);
		btnDelete.setBounds(300, 350, 200, 40);
		
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);

		ActionListener a1=(ae)->{
			AddWindow a=new AddWindow();
			dispose();
		};
		btnAdd.addActionListener(a1);

		ActionListener a2=(ae)->{
			ViewWindow a=new ViewWindow();
			dispose();
		};
		btnView.addActionListener(a2);
		
		ActionListener a3=(ae)->{
			UpdateWindow a=new UpdateWindow();
			dispose();
		};
		btnUpdate.addActionListener(a3);
		
		 ActionListener a4 = (ae)-> { 
			DeleteWindow a = new DeleteWindow();
			dispose();
		};			
		btnDelete.addActionListener(a4);

		setTitle(" S.M.S. ");
		setSize(800, 600);
		getContentPane().setBackground(Color.CYAN);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

}

































