package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Doctor extends User {
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;

	public static String name = "test";
	private static String username = "";
	public ArrayList<String> schedule = new ArrayList<String>(); 
	
	public Doctor(String fname,String lName, String username,String email,String password) {
		firstName = fname;
		lastName = lName;
		this.userName = username;
		this.email = email;
		this.password = password;
	}
	
	public Doctor() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String Authenticate(String username,String password) {
		String response =super.Authenticate("src/doctorRecords/" + username + ".txt", password);
		Doctor.name = super.name;
		Doctor.username = super.username; 
		return response;
	}
	
	public List<String> getSchedule() {
		File file = new File("src/doctorRecords/" + username + ".txt");
		try {
			Scanner scan = new Scanner(file);
			scan.nextLine();scan.nextLine();scan.nextLine();scan.nextLine();
			while(scan.hasNextLine()) {
				schedule.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
	}
	
	
	public void updateSchedule() {
		
		try {
			String allfile = "";
			File file = new File("src/doctorRecords/" + username + ".txt");
			Scanner scan = new Scanner(file);
			allfile = scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine();
			scan.close();
			Writer wr = new FileWriter("src/doctorRecords/"+username + ".txt");
			wr.write(allfile);
			for(int i = 0;i < schedule.size();i++) {
				wr.write("\n" + schedule.get(i));
			}
			wr.flush();wr.close();
			System.out.println("WROTE TO FILE");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		wr.write(firstName + " " + lastName + "\n" + username + "\n" + password + "\n" + email);
		
	}
	

	public void create() {
		try {
			Writer wr = new FileWriter("src/doctorRecords/"+userName + ".txt");
			wr.write(firstName + " " + lastName + "\n" + userName + "\n" + password + "\n" + email);
			wr.flush();wr.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
