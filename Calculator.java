import java.util.*;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JTextField;


public class Calculator {

	public static float mass, height, lean;
	public static String gender = "none";
	/*public static void main(String[] args) {
		
		/*Scanner sc = new Scanner(System.in);
		System.out.print("Enter your mass in kilogram: ");
		mass = sc.nextFloat();
		System.out.print("Enter your height in meter: ");
		height = sc.nextFloat();
		System.out.print("Enter your lean factor: ");
		lean = sc.nextFloat();
		while(!gender.equals("Male") && !gender.equals("Female")) {
		System.out.print("Enter your gender (Male or Female only): ");
		gender = sc.next();
		
	}
		
		float bmi = bmi_calculator(mass, height);
		float calo = calorie_intake(basal_metabolic_rate(mass, lean, gender));
		
		//On Screen
		System.out.println("Your BMI is: " + bmi);
		System.out.println("Your calorie intake is: " + calo);
		
		sc.close();
		
	}*/
	
	public static float calorie_intake(float bmr, double al) {
		Scanner read = new Scanner(System.in);
		/*while(al != 1.3 && al != 1.55 && al != 1.65 && al != 1.80 && al != 2.00) {
		System.out.println("Choose your activity level rate: \n1.3(Very light)\n1.55(Light)\n1.65(Moderate)\n1.80(Heavy)\n2.00(Very Heavy))");
		System.out.print("My activity level is: ");
		al =  read.nextDouble();
		}
		read.close();*/
		
		return bmr * (float)al;
	}
	
	public static float bmi_calculator(float mass, float height){
		
		float bmi = mass / (height * height);
		
		return bmi;
	}	
	
	public static float basal_metabolic_rate(float mass, float lean, String gender){
		float key = 0;
		float lean_factor = 0;
		
		if (gender.equals("Male")) {
			key = (float) 0.9;
			
			if (lean > 28) lean_factor = (float) 0.85;
			else if (lean >= 21) lean_factor = (float) 0.9;
			else if (lean >= 15) lean_factor = (float) 0.95;
			else lean_factor = (float) 1.0;
			
		}
		else {
			key = (float) 1.0;
			
			if (lean > 38) lean_factor = (float) 0.85;
			else if (lean >= 29) lean_factor = (float) 0.9;
			else if (lean >= 19) lean_factor = (float) 0.95;
			else lean_factor = (float) 1.0;
		}
		
		float ans;
		
		ans = key * mass * 24 * lean_factor;
		
		return ans;
	}
}
