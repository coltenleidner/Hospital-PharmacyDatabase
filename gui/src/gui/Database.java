package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.CardLayout;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.util.Date;

public class Database {
    static String url="jdbc:oracle:thin:@131.230.133.11:1521:cs";
    static String user = "cleidner";
	static String pass = "yNQ8M2uh";
	
	private JFrame frame;
	static private JTextField textField;
	static private JTextField textField_1;
	static private JTextField textField_2;
	static private JTextField textField_3;
	static private JTextField textField_4;
	static private JTextField textField_5;
	static private JTextField textField_6;
	static private JTextField textField_7;
	static private JTextField textField_8;
	static private JTextField textField_9;
	static JLabel lblPrescriptionId;
	static JLabel lblDropOffTime;
	static JLabel lblPickUpTime;
	static JLabel lblSsnPresc;
	static JLabel lblPhysicianSsn;
	static JLabel lblPrescriptionDate;
	static JLabel lblQuantity;
	static JLabel lblTradeName;
	static JLabel lblName;
	static JLabel lblPCN;
	static JLabel lblSsn;
	static JLabel lblPersonName;
	static JLabel lblBirthdate;
	static JLabel lblAddress;
	static JLabel lblSpecialty;
	static JLabel lblYearsOfExperience;
	static JButton btnSubmit;
	static TextArea textAreaPrescription;
	static private JTextField textField_10;
	static private JTextField textField_11;
	static private JTextField textField_12;
	static private JTextField textField_13;
	static private JTextField textField_14;
	static JLabel lblNewLabel;
	static JLabel lblStartDate;
	static JLabel lblEndDate;
	static JLabel lblSupervisorName;
	static JLabel lblPharmacyCompanyName;
	static JButton btnContractSubmit;
	static TextArea textArea;
	private JLabel lblContractText;
	private TextArea textAreaPatient;
	private JTextField textField_15;
	private JTextField textField_16;
	JLabel lblNameDrug;
	JLabel lblBirthdateDrug;
	JButton btnDrugSubmit;

	
	public static String query(String input){
		String result = new String();
		try
		{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.err.println("Done with driver registractions!");
		}catch(Exception ex){System.out.println("cant find the driver");}
		
		try{
			System.out.println("Trying to connect...");
			Connection con=DriverManager.getConnection(url,user,pass);
			System.out.println("Connection successful");
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(input);
			ResultSetMetaData rsmd=rs.getMetaData();
			int columns=rsmd.getColumnCount();
			String columnnames=new String("");
			for(int i=1; i<=columns;i++){
				String name = rsmd.getColumnName(i);
				if(i!=1)
					columnnames=columnnames+"\t"+name;
					else
						columnnames=name;
			}
			result =result+columnnames;
			result = result+"\n";
			while (rs.next()){
				for(int i=1;i<=columns;i++){
					System.out.println(result);
					String s=rs.getString(i);
					if(i!=1)
						result= result+"\t"+s;
					else
							result=result+s;
					
				}
				result=result+"\n";
			}
			stmt.close();
			con.close();
			
		}catch(SQLException ex){
			System.out.println("SQLException: "+ex);
			result="Error in query.";
		}return result;
	}
	
	public static String Insert(String input){
		try
		{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.err.println("Done with driver registractions!");
		Thread.sleep(3000);
		}catch(Exception ex){System.out.println("cant find the driver");}
		
		try{
			System.out.println("Trying to connect...");
			Connection con=DriverManager.getConnection(url,user,pass);
			System.out.println("Connection successful");
			Statement stmt=con.createStatement();System.out.println(input);
			stmt.executeQuery(input);System.out.println(1);
			return "Insertion successful!";
		}catch(SQLException ex){
			System.out.println("SQLException: "+ex);
			return "Insertion failed.";
		}
	}
	
	public static String Delete(String input){
		try
		{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.err.println("Done with driver registractions!");
		}catch(Exception ex){System.out.println("cant find the driver");}
		
		try{
			System.out.println("Trying to connect...");
			Connection con=DriverManager.getConnection(url,user,pass);
			System.out.println("Connection successful");
			Statement stmt=con.createStatement();
			stmt.executeQuery(input);
			return "Deletion successful!";
		}catch(SQLException ex){
			System.out.println("SQLException: "+ex);
			return "Deletion failed.";
		}
	}
	public static String Update(String input){
		try
		{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.err.println("Done with driver registractions!");
		}catch(Exception ex){System.out.println("cant find the driver");}
		
		try{
			System.out.println("Trying to connect...");
			Connection con=DriverManager.getConnection(url,user,pass);
			System.out.println("Connection successful");
			Statement stmt=con.createStatement();
			stmt.executeQuery(input);
			return "Update successful!";
		}catch(SQLException ex){
			System.out.println("SQLException: "+ex);
			return "Update failed.";
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database window = new Database();
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
	public Database() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 998, 564);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] options = {"Not Logged In","Management","Drugs","Patient"};
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelStart = new JPanel();
		frame.getContentPane().add(panelStart, "name_337591526440559");
		panelStart.setLayout(null);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(685, 11, 156, 46);
		panelStart.add(btnLogOut);
		
		JButton btnHobbies = new JButton("My Hobbies");
		btnHobbies.setBounds(519, 11, 156, 46);
		panelStart.add(btnHobbies);
		
		
		JButton btnAcdInt = new JButton("My Academic Interests");
		btnAcdInt.setBounds(353, 11, 156, 46);
		panelStart.add(btnAcdInt);
		
		JButton btnAboutPrj = new JButton("About My Project");
		btnAboutPrj.setBounds(187, 11, 156, 46);
		panelStart.add(btnAboutPrj);
		
		JComboBox cbLogIn = new JComboBox(options);
		cbLogIn.setBounds(10, 11, 156, 46);
		panelStart.add(cbLogIn);
		
		
		JTextArea Content = new JTextArea();
		Content.setWrapStyleWord(true);
		Content.setBounds(10, 68, 962, 446);
		panelStart.add(Content);
		Content.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 32));
		Content.setLineWrap(true);
		
		JPanel panelPatient = new JPanel();
		frame.getContentPane().add(panelPatient, "name_337591533456182");
		panelPatient.setLayout(null);
	panelPatient.setVisible(false);
		
		JButton btnPatientLogOut = new JButton("Log Out");
		btnPatientLogOut.setBounds(702, 11, 156, 46);
		panelPatient.add(btnPatientLogOut);
		btnPatientLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPatient.setVisible(false);
				panelStart.setVisible(true);
			}
		});
		
		JButton btnAll = new JButton("Show all patients");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientvisibility();
				String input = "SELECT * FROM Pri_Phy_Patient";
				String answer = query(input);
				textAreaPatient.setText(answer);
//				
			}
		});
		btnAll.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAll.setBounds(10, 11, 182, 46);
		panelPatient.add(btnAll);
		
		JButton btnAllPer = new JButton("Show all patients & perscriptions");
		btnAllPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientvisibility();
				String input = "SELECT Pri_Phy_Patient.ssn, Pri_Phy_Patient.name, Prescription.pre_id FROM Pri_Phy_Patient LEFT OUTER JOIN Prescription ON Pri_Phy_Patient.ssn=Prescription.ssn";
				String answer = query(input);
				textAreaPatient.setText(answer);
//				
			}
		});
		btnAllPer.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAllPer.setBounds(10, 68, 182, 46);
		panelPatient.add(btnAllPer);
		
		JButton btnPersInfo = new JButton("Perscription Information");
		btnPersInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientvisibility();
				lblBirthdate.setVisible(true);
				textField_2.setVisible(true);
				lblName.setVisible(true);
				textField_8.setVisible(true);
				btnSubmit.setVisible(true);
				ActionListener[] reference = btnSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnSubmit.removeActionListener(reference[i]);
				}
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String birthdate= textField_2.getText();
						String name=textField_8.getText();
						String input ="SELECT Prescription.pre_id, Prescription.status, Prescription.drop_off_time, Prescription.pick_up_time FROM Pri_Phy_Patient INNER JOIN Prescription ON Pri_Phy_Patient.SSN=Prescription.SSN WHERE Pri_Phy_Patient.NAME='"+name+"' AND Pri_Phy_Patient.birth_date= '"+birthdate+"'";
						query(input);
					}
				});
			}
		});
		btnPersInfo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPersInfo.setBounds(10, 125, 182, 46);
		panelPatient.add(btnPersInfo);
		
		JButton btnAddPat = new JButton("Add Patient");
		btnAddPat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientvisibility();
				lblSsn.setVisible(true);
				textField.setVisible(true);
				lblPersonName.setVisible(true);
				textField_1.setVisible(true);
				lblBirthdate.setVisible(true);
				textField_2.setVisible(true);
				lblAddress.setVisible(true);
				textField_3.setVisible(true);
				lblPhysicianSsn.setVisible(true);
				textField_4.setVisible(true);
				btnSubmit.setVisible(true);
				ActionListener[] reference = btnSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnSubmit.removeActionListener(reference[i]);
				}
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ssn= textField.getText();
						String name=textField_1.getText();
						String date=textField_2.getText();
						String address=textField_3.getText();
						String physsn=textField_4.getText();
						String input ="INSERT INTO Pri_Phy_Patient VALUES ('"+ssn+"', '"+name+"', DATE '"+date+"', '"+address+"', '"+physsn+"')";
						String answer =Insert(input);
						textAreaPatient.setText(answer);
					}
				});
//				
			}
		});
		btnAddPat.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddPat.setBounds(10, 182, 182, 46);
		panelPatient.add(btnAddPat);
		
		JButton btnAddDoc = new JButton("Add Doctor");
		btnAddDoc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddDoc.setBounds(10, 239, 182, 46);
		panelPatient.add(btnAddDoc);
		btnAddDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//            System.out.println(Insert("INSERT INTO Doctor VALUES ('82565426839', 'Alex Steiner', 'Eye Doctor', 5)")+" checked");
				patientvisibility();
				lblSsn.setVisible(true);
				textField.setVisible(true);
				lblPersonName.setVisible(true);
				textField_1.setVisible(true);
				lblSpecialty.setVisible(true);
				textField_2.setVisible(true);
				lblYearsOfExperience.setVisible(true);
				textField_3.setVisible(true);
				btnSubmit.setVisible(true);
				ActionListener[] reference = btnSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnSubmit.removeActionListener(reference[i]);
				}
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ssn= textField.getText();
						String name=textField_1.getText();
						String specialty=textField_2.getText();
						String exp=textField_3.getText();
						String input ="INSERT INTO Doctor VALUES ('"+ssn+"', '"+name+"', '"+specialty+"', "+exp+")";
						//String answer =Insert(input);
						//textAreaPatient.setText(answer);
						System.out.println(input);
					}
				});
//				
			}
		});
		
		JButton btnAddPersc = new JButton("Add Perscription");
		btnAddPersc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientvisibility();
				lblPrescriptionId.setVisible(true);
				lblDropOffTime.setVisible(true);
				lblPickUpTime.setVisible(true);
				lblSsnPresc.setVisible(true);
				lblPhysicianSsn.setVisible(true);
				lblPrescriptionDate.setVisible(true);
				lblQuantity.setVisible(true);
				lblTradeName.setVisible(true);
				
				lblPCN.setVisible(true);
				textField.setVisible(true);
				textField_1.setVisible(true);
				textField_2.setVisible(true);
				textField_3.setVisible(true);
				textField_4.setVisible(true);
				textField_5.setVisible(true);
				textField_6.setVisible(true);
				textField_7.setVisible(true);
				
				textField_9.setVisible(true);
				btnSubmit.setVisible(true);
				ActionListener[] reference = btnSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnSubmit.removeActionListener(reference[i]);
				}
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String perid= textField.getText();
						String dropoff=textField_1.getText();
						String pickup=textField_2.getText();
						String ssn=textField_3.getText();
						String physsn=textField_4.getText();
						String predate=textField_5.getText();
						String quantity=textField_6.getText();
						String tradename=textField_7.getText();
						
						String pcn=textField_9.getText();
						String input ="INSERT INTO Prescription VALUES ("+perid+", 'pending', timestamp '"+dropoff+"', timestamp '"+pickup+"', '"+ssn+"', '"+physsn+"',DATE '"+predate+"', "+quantity+", '"+tradename+"', '"+pcn+"')";
						String answer =Insert(input);
						textAreaPatient.setText(answer);
					}
				});
//				
			}
		});
		btnAddPersc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddPersc.setBounds(10, 296, 182, 46);
		panelPatient.add(btnAddPersc);
		
		JButton btnRmvPat = new JButton("Remove Patient");
		btnRmvPat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
				patientvisibility();
				lblSsn.setVisible(true);
				textField.setVisible(true);
				btnSubmit.setVisible(true);
				ActionListener[] reference = btnSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnSubmit.removeActionListener(reference[i]);
				}
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ssn= textField.getText();
						String input ="DELETE FROM Prescription WHERE SSN='"+ssn+"'";
						String answer ="Prescription "+Delete(input)+"\n";
						input ="DELETE FROM Pri_Phy_Patient WHERE SSN='"+ssn+"'";
						answer =answer+"Patient "+Delete(input);
						textAreaPatient.setText(answer);
					}
				});
				
			}
		});
		btnRmvPat.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRmvPat.setBounds(10, 353, 182, 46);
		panelPatient.add(btnRmvPat);
		
		JButton btnRmvDoc = new JButton("Remove Doctor");
		btnRmvDoc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRmvDoc.setBounds(10, 410, 182, 46);
		panelPatient.add(btnRmvDoc);
		btnRmvDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
 //           System.out.println(Delete("DELETE FROM Doctor WHERE SSN='82565426839'"));
				patientvisibility();
				lblSsn.setVisible(true);
				textField.setVisible(true);
				btnSubmit.setVisible(true);	
				ActionListener[] reference = btnSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnSubmit.removeActionListener(reference[i]);
				}
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ssn= textField.getText();
						String input ="DELETE FROM Prescription WHERE PHY_SSN='"+ssn+"'";
						String answer ="Prescription "+Delete(input)+"\n";
						input ="DELETE FROM Pri_Phy_Patient WHERE PHY_SSN='"+ssn+"'";
						answer =answer+"Patient "+Delete(input)+"\n";
						input ="DELETE FROM Doctor WHERE SSN='"+ssn+"'";
						answer =answer+"Doctor "+Delete(input);
						textAreaPatient.setText(answer);
					}
				});
			}
		});
		
		JButton btnRmvPersc = new JButton("Remove Prescription");
		btnRmvPersc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				patientvisibility();
				lblPrescriptionId.setVisible(true);
				textField.setVisible(true);
				btnSubmit.setVisible(true);
				ActionListener[] reference = btnSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnSubmit.removeActionListener(reference[i]);
				}
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String presid= textField.getText();
						String input ="DELETE FROM Prescription WHERE pre_id="+presid;
						String answer ="Prescription "+Delete(input);
						textAreaPatient.setText(answer);
					}
				});
//				
			}
		});
		btnRmvPersc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRmvPersc.setBounds(10, 467, 182, 46);
		panelPatient.add(btnRmvPersc);
		
		textField = new JTextField();
		textField.setBounds(202, 17, 166, 35);
		panelPatient.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(202, 68, 166, 35);
		panelPatient.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(202, 114, 166, 35);
		panelPatient.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(202, 160, 166, 35);
		panelPatient.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(202, 206, 166, 35);
		panelPatient.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(202, 252, 166, 35);
		panelPatient.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(202, 296, 166, 35);
		panelPatient.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(202, 342, 166, 35);
		panelPatient.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(202, 388, 166, 35);
		panelPatient.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(202, 434, 166, 35);
		panelPatient.add(textField_9);
		
		lblPrescriptionId = new JLabel("Prescription ID");
		lblPrescriptionId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrescriptionId.setBounds(378, 17, 103, 35);
		panelPatient.add(lblPrescriptionId);
		
		lblDropOffTime = new JLabel("Drop Off Time (Ex.2018-01-31 09:26:50.12 )");
		lblDropOffTime.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDropOffTime.setBounds(378, 68, 198, 35);
		panelPatient.add(lblDropOffTime);
		
		lblPickUpTime = new JLabel("Pick Up Time (Ex.2018-01-31 09:26:50.12 )");
		lblPickUpTime.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPickUpTime.setBounds(378, 114, 198, 35);
		panelPatient.add(lblPickUpTime);
		
		lblSsnPresc = new JLabel("Patient SSN(Ex. SSS-SS-SSSS)");
		lblSsnPresc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSsnPresc.setBounds(378, 160, 198, 35);
		panelPatient.add(lblSsnPresc);
		
		lblPhysicianSsn = new JLabel("Physician SSN(Ex. SSS-SS-SSSS)");
		lblPhysicianSsn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhysicianSsn.setBounds(378, 206, 198, 35);
		panelPatient.add(lblPhysicianSsn);
		
		lblPrescriptionDate = new JLabel("Prescription Date(Ex. YYYY-MM-DD)");
		lblPrescriptionDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrescriptionDate.setBounds(378, 252, 198, 35);
		panelPatient.add(lblPrescriptionDate);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(378, 296, 64, 35);
		panelPatient.add(lblQuantity);
		
		lblTradeName = new JLabel("Trade Name");
		lblTradeName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTradeName.setBounds(378, 342, 81, 35);
		panelPatient.add(lblTradeName);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(378, 388, 51, 35);
		panelPatient.add(lblName);
		
		lblPCN = new JLabel("Pharmacy Company Name");
		lblPCN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPCN.setBounds(378, 433, 156, 35);
		panelPatient.add(lblPCN);
		
		lblSsn = new JLabel("SSN(Ex. SSS-SS-SSSS)");
		lblSsn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSsn.setBounds(378, 17, 36, 35);
		panelPatient.add(lblSsn);
		
		lblPersonName = new JLabel("Name");
		lblPersonName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersonName.setBounds(378, 68, 45, 35);
		panelPatient.add(lblPersonName);
		
		lblBirthdate = new JLabel("Birthdate(Ex. YYYY-MM-DD)");
		lblBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBirthdate.setBounds(378, 114, 156, 35);
		panelPatient.add(lblBirthdate);
		
		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(378, 160, 64, 35);
		panelPatient.add(lblAddress);
		
		lblSpecialty = new JLabel("Specialty");
		lblSpecialty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSpecialty.setBounds(378, 114, 129, 35);
		panelPatient.add(lblSpecialty);
		
		lblYearsOfExperience = new JLabel("Years Of Experience");
		lblYearsOfExperience.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYearsOfExperience.setBounds(378, 160, 129, 35);
		panelPatient.add(lblYearsOfExperience);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(673, 428, 156, 46);
		panelPatient.add(btnSubmit);
		
		textAreaPatient = new TextArea();
		textAreaPatient.setEditable(false);
		textAreaPatient.setBounds(582, 68, 390, 331);
		panelPatient.add(textAreaPatient);
		
		JPanel panelPerscription = new JPanel();
		frame.getContentPane().add(panelPerscription, "name_337591541804971");
		panelPerscription.setLayout(null);
	panelPerscription.setVisible(false);
		
		JButton btnPerscriptionsToBe = new JButton("Perscriptions to be processed");
		btnPerscriptionsToBe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = "SELECT * FROM Prescription WHERE status='pending'";
				String answer =query(input);
				textAreaPrescription.setText(answer);
			}
		});
		btnPerscriptionsToBe.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPerscriptionsToBe.setBounds(10, 11, 260, 46);
		panelPerscription.add(btnPerscriptionsToBe);
		
		JButton btnPerscriptionsCompleted = new JButton("Perscriptions Completed");
		btnPerscriptionsCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date day = new Date();
				String date = dateFormat.format(day);
				String input = "SELECT * FROM Prescriptions WHERE status='completed' AND pick_up_time>=  '"+date+" 00:00:00' AND pick_up_time<= '"+date+" 23:59:59'";
				System.out.println(input);
				String answer = query(input);
				textAreaPrescription.setText(answer);
			}
		});
		btnPerscriptionsCompleted.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPerscriptionsCompleted.setBounds(10, 68, 260, 46);
		panelPerscription.add(btnPerscriptionsCompleted);
		
		JButton btnPerscriptionsReady = new JButton("Perscriptions Ready");
		btnPerscriptionsReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_15.setVisible(true);
				textField_16.setVisible(true);
				lblNameDrug.setVisible(true);
				lblBirthdateDrug.setVisible(true);
				btnDrugSubmit.setVisible(true);
			}
		});
		btnPerscriptionsReady.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPerscriptionsReady.setBounds(10, 125, 260, 46);
		panelPerscription.add(btnPerscriptionsReady);
		
		JButton btnListOfDrugs = new JButton("List of Drugs");
		btnListOfDrugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = "SELECT * FROM Make_Drug";
				String answer =query(input);
				textAreaPrescription.setText(answer);
			}
		});
		btnListOfDrugs.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnListOfDrugs.setBounds(10, 182, 260, 46);
		panelPerscription.add(btnListOfDrugs);
		
		JButton btnDrugsSoldBy = new JButton("Drugs Sold By All Pharmacies ");
		btnDrugsSoldBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = "SELECT trade_name FROM Make_Drug GROUP BY trade_name HAVING COUNT(*) =(SELECT COUNT(*) FROM Pharmacy)";
				String answer =query(input);
				textAreaPrescription.setText(answer);
			}
		});
		btnDrugsSoldBy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDrugsSoldBy.setBounds(10, 239, 260, 46);
		panelPerscription.add(btnDrugsSoldBy);
		
		JButton btnCompaniesThatProduce = new JButton("Companies that produce the most expensive drug");
		btnCompaniesThatProduce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = "SELECT DISTINCT pharm_co_name FROM Sell WHERE price>=(SELECT AVG(price) FROM Sell)";
				String answer =query(input);
				textAreaPrescription.setText(answer);
			}
		});
		btnCompaniesThatProduce.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCompaniesThatProduce.setBounds(10, 296, 260, 46);
		panelPerscription.add(btnCompaniesThatProduce);
		
		JButton btnDrugSoldIn = new JButton("Drugs sold in other Pharmacies");
		btnDrugSoldIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = "SELECT a.PHARM_ID, a.TRADE_NAME, b.PHARM_ID AS SecondPharmacy, b.PHONE FROM (SELECT Sell.PHARM_ID, TRADE_NAME, PHONE FROM SELL INNER JOIN PHARMACY ON Sell.pharm_id=Pharmacy.pharm_id) a, (SELECT Sell.PHARM_ID, TRADE_NAME, PHONE FROM SELL INNER JOIN PHARMACY ON Sell.pharm_id=Pharmacy.pharm_id) b WHERE a.TRADE_NAME=b.TRADE_NAME AND a.PHARM_ID!=b.PHARM_ID";
				String answer =query(input);
				textAreaPrescription.setText(answer);
//
//			
//			
			}
		});
		btnDrugSoldIn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDrugSoldIn.setBounds(10, 353, 260, 46);
		panelPerscription.add(btnDrugSoldIn);
		
		JButton btnPerscriptionLogOut = new JButton("Log Out");
		btnPerscriptionLogOut.setBounds(816, 11, 156, 46);
		panelPerscription.add(btnPerscriptionLogOut);
		
		textAreaPrescription = new TextArea();
		textAreaPrescription.setEditable(false);
		textAreaPrescription.setBounds(276, 68, 696, 415);
		panelPerscription.add(textAreaPrescription);
		
		textField_15 = new JTextField();
		textField_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_15.setBounds(280, 11, 132, 35);
		panelPerscription.add(textField_15);
		textField_15.setColumns(10);
		
		lblNameDrug = new JLabel("Name");
		lblNameDrug.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNameDrug.setBounds(422, 11, 37, 35);
		panelPerscription.add(lblNameDrug);
		
		textField_16 = new JTextField();
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_16.setColumns(10);
		textField_16.setBounds(469, 11, 132, 35);
		panelPerscription.add(textField_16);
		
		lblBirthdateDrug = new JLabel("Birthdate(Ex. YYYY-MM-DD)");
		lblBirthdateDrug.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBirthdateDrug.setBounds(611, 11, 165, 35);
		panelPerscription.add(lblBirthdateDrug);
		
		btnDrugSubmit = new JButton("Submit");
		btnDrugSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = lblNameDrug.getText();
				String date = lblBirthdateDrug.getText();
				String answer = query("SELECT pre_id.Prescription, status.Prescription, drop_off_time.Prescription, pick_up_time.Prescription FROM Pri_Phy_Patient, Prescription WHERE Pri_Phy_Patient.SSN = Prescription.SSN AND Pri_Phy_Patient.NAME = '"+name+"' AND BIRTH_DATE = '"+date+"'");
			}
		});
		btnDrugSubmit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDrugSubmit.setBounds(10, 410, 260, 46);
		panelPerscription.add(btnDrugSubmit);
		btnPerscriptionLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPerscription.setVisible(false);
				panelStart.setVisible(true);
			}
		});
		
		JPanel panelMang = new JPanel();
		frame.getContentPane().add(panelMang, "name_337591547654422");
		panelMang.setLayout(null);
	panelMang.setVisible(false);
		JButton btnListAllContracts = new JButton("List All Contracts");
		btnListAllContracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managementvisibility();
				String input = "SELECT * FROM Contract";
				String answer = query(input);
				textArea.setText(answer);
			}
		});
		btnListAllContracts.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListAllContracts.setBounds(10, 11, 155, 46);
		panelMang.add(btnListAllContracts);
		
		JButton btnUpdateContract = new JButton("Update Contract");
		btnUpdateContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managementvisibility();
				textField_10.setVisible(true);
				textField_11.setVisible(true);
				textField_12.setVisible(true);
				textField_13.setVisible(true);
				textField_14.setVisible(true);
				textArea.setEditable(true);
				lblNewLabel.setVisible(true);
				lblStartDate.setVisible(true);
				lblEndDate.setVisible(true);
				lblSupervisorName.setVisible(true);
				lblPharmacyCompanyName.setVisible(true);
				btnContractSubmit.setVisible(true);
				ActionListener[] reference = btnContractSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnContractSubmit.removeActionListener(reference[i]);
				}
				btnContractSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pid= textField_10.getText();
						String pharmconame=textField_14.getText();
						String sdate = textField_11.getText();
						String fdate = textField_12.getText();
						String Supervisor = textField_13.getText();
						String text = textArea.getText();
						String answer = new String();
						if(!(sdate.equals(""))){
							answer=Update("UPDATE Contract SET start_date=DATE '"+sdate+"' WHERE pharm_id='"+pid+"' AND pharm_co_name='"+pharmconame+"'");
							answer="Start date "+answer+"\n";
						}
						if(!(fdate.equals(""))){
							answer=answer+"Finish date "+Update("UPDATE Contract SET end_date=DATE '"+fdate+"' WHERE pharm_id='"+pid+"' AND pharm_co_name='"+pharmconame+"'");
						}
						if(!(Supervisor.equals(""))){
							answer=answer+"\n Supervisor "+Update("UPDATE Contract SET supervisor= '"+Supervisor+"' WHERE pharm_id='"+pid+"' AND pharm_co_name='"+pharmconame+"'");
						}
						if(!(text.equals(""))){
							answer=answer+"\n Contract text "+Update("UPDATE Contract SET text='"+text+"' WHERE pharm_id='"+pid+"' AND pharm_co_name='"+pharmconame+"'");
						}
						textArea.setText(answer);
					}
				});

			}
		});
		btnUpdateContract.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateContract.setBounds(175, 11, 155, 46);
		panelMang.add(btnUpdateContract);
		
		JButton btnAddContract = new JButton("Add Contract");
		btnAddContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managementvisibility();
				textArea.setEditable(true);
				textField_10.setVisible(true);
				textField_11.setVisible(true);
				textField_12.setVisible(true);
				textField_13.setVisible(true);
				textField_14.setVisible(true);
				lblNewLabel.setVisible(true);
				lblStartDate.setVisible(true);
				lblEndDate.setVisible(true);
				lblSupervisorName.setVisible(true);
				lblPharmacyCompanyName.setVisible(true);
				btnContractSubmit.setVisible(true);
				ActionListener[] reference = btnContractSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnContractSubmit.removeActionListener(reference[i]);
				}
				btnContractSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pid= textField_10.getText();
						String start=textField_11.getText();
						String end=textField_12.getText();
						String text=textArea.getText();
						String supervisor=textField_13.getText();
						String pharmconame=textField_14.getText();
						String input ="INSERT INTO Contract VALUES ('"+pid+"',DATE '"+start+"',DATE '"+end+"', '"+text+"', '"+supervisor+"', '"+pharmconame+"')";
						Insert(input);
					}
				});
			}
		});
		btnAddContract.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddContract.setBounds(345, 11, 155, 46);
		panelMang.add(btnAddContract);
		
		JButton btnRemoveContract = new JButton("Remove Contract");
		btnRemoveContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managementvisibility();
				lblNewLabel.setVisible(true);
				lblPharmacyCompanyName.setVisible(true);
				textField_10.setVisible(true);
				textField_14.setVisible(true);
				
				btnContractSubmit.setVisible(true);
				ActionListener[] reference = btnContractSubmit.getActionListeners();
				for(int i =0;i<reference.length;i++){
				btnContractSubmit.removeActionListener(reference[i]);
				}
				btnContractSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pid= textField_10.getText();
						String pharmconame=textField_14.getText();
						String input ="DELETE FROM Contract WHERE pharm_id='"+pid+"' AND pharm_co_name='"+pharmconame+"'";
						Delete(input);
					}
				});
			}
		});
		btnRemoveContract.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemoveContract.setBounds(510, 11, 155, 46);
		panelMang.add(btnRemoveContract);
		
		JButton LogOutManagement = new JButton("Log Out");
		LogOutManagement.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LogOutManagement.setBounds(686, 11, 155, 46);
		panelMang.add(LogOutManagement);
		
		textField_10 = new JTextField();
		textField_10.setBounds(10, 68, 155, 31);
		panelMang.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(10, 110, 155, 31);
		panelMang.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(10, 152, 155, 31);
		panelMang.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(10, 194, 155, 31);
		panelMang.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(10, 236, 155, 31);
		panelMang.add(textField_14);
		
		lblNewLabel = new JLabel("Pharmacy ID");
		lblNewLabel.setBounds(175, 68, 75, 31);
		panelMang.add(lblNewLabel);
		
		lblStartDate = new JLabel("Start Date(Ex. YYYY-MM-DD)");
		lblStartDate.setBounds(175, 110, 155, 31);
		panelMang.add(lblStartDate);
		
		lblEndDate = new JLabel("End Date(Ex. YYYY-MM-DD)");
		lblEndDate.setBounds(175, 152, 155, 31);
		panelMang.add(lblEndDate);
		
		lblSupervisorName = new JLabel("Supervisor Name");
		lblSupervisorName.setBounds(175, 194, 113, 31);
		panelMang.add(lblSupervisorName);
		
		lblPharmacyCompanyName = new JLabel("Pharmaceutical Company Name");
		lblPharmacyCompanyName.setBounds(175, 236, 204, 31);
		panelMang.add(lblPharmacyCompanyName);
		
		btnContractSubmit = new JButton("Submit");
		btnContractSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnContractSubmit.setBounds(10, 278, 155, 46);
		panelMang.add(btnContractSubmit);
		
		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(385, 90, 587, 425);
		panelMang.add(textArea);
		
		lblContractText = new JLabel("Contract Text");
		lblContractText.setBounds(545, 68, 93, 31);
		panelMang.add(lblContractText);
		LogOutManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMang.setVisible(false);
				panelStart.setVisible(true);
			}
		});
		
		btnAboutPrj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Content.setText("TBA");
			}
		});
		btnAcdInt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Content.setText("My current interest in academics is to focus on Computer Security as well as become more adept with the Python programming language.");
			}
		});
		btnHobbies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Content.setText("I have three main hobbies, hanging out with friends, watching tv, and playing video games. My favorite tv show is The Flash and my favorite video game is Devil May Cry");
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbLogIn.setSelectedIndex(0);
			}
		});
		panelStart.setVisible(true);
		
		cbLogIn.addActionListener(new ActionListener() {    //Action listener to change between pages
			public void actionPerformed(ActionEvent e) {
				String check =(String) cbLogIn.getSelectedItem();
				if (check.equals("Patient")){
					patientvisibility();
					panelStart.setVisible(false);
					panelPatient.setVisible(true);
				}else if(check.equals("Drugs")){
					panelStart.setVisible(false);
					panelPerscription.setVisible(true);
				}else if(check.equals("Management")){
					panelStart.setVisible(false);
					panelMang.setVisible(true);
				}
					
			}
		});
		
	}
	
	public static void managementvisibility(){
		textField_10.setVisible(false);
		textField_11.setVisible(false);
		textField_12.setVisible(false);
		textField_13.setVisible(false);
		textField_14.setVisible(false);
		lblNewLabel.setVisible(false);
		lblStartDate.setVisible(false);
		lblEndDate.setVisible(false);
		lblSupervisorName.setVisible(false);
		lblPharmacyCompanyName.setVisible(false);
		btnContractSubmit.setVisible(false);
		textArea.setEditable(false);
		textField_10.setText("");
		textField_11.setText("");
		textField_12.setText("");
		textField_13.setText("");
		textField_14.setText("");
		textArea.setText("");
	}
	
	public static void patientvisibility(){
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		textField_9.setText("");
		textField.setVisible(false);
		textField_1.setVisible(false);
		textField_2.setVisible(false);
		textField_3.setVisible(false);
		textField_4.setVisible(false);
		textField_5.setVisible(false);
		textField_6.setVisible(false);
		textField_7.setVisible(false);
		textField_8.setVisible(false);
		textField_9.setVisible(false);
		lblPrescriptionId.setVisible(false);
		lblDropOffTime.setVisible(false);
		lblPickUpTime.setVisible(false);
		lblSsnPresc.setVisible(false);
		lblPhysicianSsn.setVisible(false);
		lblPrescriptionDate.setVisible(false);
		lblQuantity.setVisible(false);
		lblTradeName.setVisible(false);
		lblName.setVisible(false);
		lblPCN.setVisible(false);
		lblSsn.setVisible(false);
		lblPersonName.setVisible(false);
		lblBirthdate.setVisible(false);
		lblAddress.setVisible(false);
		lblSpecialty.setVisible(false);
		lblYearsOfExperience.setVisible(false);
		btnSubmit.setVisible(false);
	}
}
