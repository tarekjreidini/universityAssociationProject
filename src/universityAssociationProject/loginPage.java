package universityAssociationProject;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.ListSelectionModel;



public class loginPage {

	
	//global variables to be used per session (to keep track of who is logged in, default is 0)
	int currentStudent=0;
	int currentTeacher=0;
	int currentAdmin=0;
	
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage window = new loginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, "name_1447783491025678000");
		loginPanel.setLayout(null);
		
		
		
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 171, 134, 28);
		loginPanel.add(passwordField);
		
		final JPanel studentPanel = new JPanel();
		frame.getContentPane().add(studentPanel, "name_1447783494532332000");
		studentPanel.setLayout(null);
		
		Button button = new Button("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			populateTable();
			
				
				
		
				
				
				
			}
		});
		button.setBounds(157, 239, 117, 29);
		studentPanel.add(button);
		
		JButton btnTest = new JButton("test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
					PreparedStatement stmt = conn.prepareStatement("select * from class");
					
					 ResultSet rs = stmt.executeQuery();
					 System.out.println("entering loop");
					 
					 for (int i = 0; i<=table.getSelectedRow();i++)
					 {
						 rs.next();
					 }
					 int classID=rs.getInt(1);
					 
					 PreparedStatement stmt2 = conn.prepareStatement("insert into registered_in (studentID, classID) values (?,?)");
					 stmt2.setInt(1, currentStudent);
					 stmt2.setInt(2, classID);
					 stmt2.execute();
					 
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				   
			}
		});
		btnTest.setBounds(157, 6, 117, 29);
		studentPanel.add(btnTest);
		
		table = new JTable();
		table.setBounds(91, 55, 285, 155);
		studentPanel.add(table);
		
		final JPanel teacherPanel = new JPanel();
		teacherPanel.setLayout(null);
		frame.getContentPane().add(teacherPanel, "name_1447794247375231000");
		
		final JPanel administratorPanel = new JPanel();
		administratorPanel.setLayout(null);
		frame.getContentPane().add(administratorPanel, "name_1447794265299764000");
		
		JLabel lblAdministratorPage = new JLabel("Administrator Page");
		lblAdministratorPage.setBounds(107, 124, 115, 16);
		administratorPanel.add(lblAdministratorPage);
		
		
		
		
		
		
		usernameField = new JTextField();
		usernameField.setBounds(156, 125, 134, 28);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(66, 131, 82, 16);
		loginPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(62, 177, 82, 16);
		loginPanel.add(lblPassword);
		
		JButton signInButton = new JButton("Sign In");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				String user = usernameField.getText();
				String pass = passwordField.getText();
				
				
				Connection conn = null;

				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
					stmt = conn.prepareStatement("select * from student where email = ? and password = ?");
					stmt.setString(1, user); //username
					stmt.setString(2, pass); //eventname
					
					ResultSet rs1 = stmt.executeQuery();
					
					if (rs1.next())
					{
						currentStudent=rs1.getInt(1);
						loginPanel.setVisible(false);
						studentPanel.setVisible(true);
						
						
					}
					else 
					{
						stmt2 = conn.prepareStatement("select * from teacher where email = ? and password = ?");
						stmt2.setString(1, user); //username
						stmt2.setString(2, pass); //eventname
						ResultSet rs2 = stmt2.executeQuery();
						if (rs2.next())
						{
							currentTeacher=rs2.getInt(1);
							loginPanel.setVisible(false);
							teacherPanel.setVisible(true);
							
							
						}

							
						else 
						{

							stmt3 = conn.prepareStatement("select * from administrator where email = ? and password = ?");
							stmt3.setString(1, user); //username
							stmt3.setString(2, pass); //eventname
							ResultSet rs3 = stmt3.executeQuery();
							if (rs3.next())
							{
								currentAdmin=rs3.getInt(1);
								loginPanel.setVisible(false);
							administratorPanel.setVisible(true);
							}
								
							else
							{
								System.out.println("not found");
							}
						
						}
						
					}
			
					
			
					

				
					
					
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		signInButton.setBounds(166, 211, 117, 29);
		loginPanel.add(signInButton);
		
		
		
		
	}
	
	
	public static void populateTable (){
		System.out.println("started");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
			stmt = conn.prepareStatement("select * from class");
			
			 rs = stmt.executeQuery();
			 ResultSetMetaData metaData = rs.getMetaData();
				System.out.println("connected");
				
			 Vector<String> columnNames = new Vector<String>(); //vector to store names of columns
			 
			 int columnCount = metaData.getColumnCount(); //get number of columns
			 
			 for (int i=1; i<= columnCount; i++)
			 {	System.out.println("loop columnams.add");
				
				 columnNames.add(metaData.getColumnName(i));  //adding column names in the vector
			 }
			 
			 
			 
			 Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			    while (rs.next()) {
			    	System.out.println("while rs.next");
			    	
			        Vector<Object> vector = new Vector<Object>();
			        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			            vector.add(rs.getObject(columnIndex));
			        }
			        data.add(vector);
			    }
				System.out.println("done loop");
				
			    DefaultTableModel temp = new DefaultTableModel(data, columnNames);
			   
			  //  JTable tablez = new JTable(temp);
			    //System.out.println(tablez);
			 
			    table.setModel(temp);
			    table.setModel(temp);
			    
			    //table = tablez;
			    //System.out.println();
			    
			  
			    
			    
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
	}
}
