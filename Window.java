import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class Window{
	static int number_of_field = 4;
	static JTextField[] link = new JTextField[number_of_field];
	static JTextField[] link_out = new JTextField[2];
	static JButton[] jb = new JButton[number_of_field];
	static JButton[] ans = new JButton[2];
	static TypoListener t;
	static float mass, height, lean, active_level;
	static String gender= "";
	
	public static  void main(String[] args) throws IOException {
		Window win = new Window();
	}
	
	public Window() throws IOException {
	t = new TypoListener();
	JFrame window = new JFrame("My Calorie Calculator");
	window.setLayout(new BorderLayout());
	JPanel mainframe = createInputStuff();
	JPanel answer = createOutput();	

	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setBounds(0,0,300,300);
	window.getContentPane().add(mainframe, BorderLayout.NORTH);
	window.getContentPane().add(answer, BorderLayout.SOUTH);
	window.pack();
	window.setVisible(true);
	}
	
	public JPanel createInputStuff() throws IOException{
		JPanel mainframe = new JPanel();
		mainframe.setLayout(new FlowLayout());
		
		jb[0] = new JButton();
		jb[0].setText("Mass (kg)");
		jb[1] = new JButton();
		jb[1].setText("Height (m)");
		jb[2] = new JButton();
		jb[2].setText("Body Fat Percentage (%)");
		jb[3] = new JButton();
		jb[3].setText("Gender: Male/Female");
		
		for(int i = 0; i < number_of_field; i++) {
			link[i] = new JTextField(20);
			jb[i].addActionListener(t);
			mainframe.add(jb[i]);
			mainframe.add(link[i]);
		}
		
		return mainframe;
	}
	
	public JPanel createOutput() throws IOException{
		JPanel output = new JPanel();
		output.setLayout(new FlowLayout());
		
		ans[0] = new JButton("BMI");
		ans[1] = new JButton("Calarie Intake");
		
		for(int i = 0; i < 2; i++) {
			link_out[i] = new JTextField(30);
			ans[i].addActionListener(t);
			output.add(ans[i]);
			output.add(link_out[i]);
		}
		
		return output;
	}
	
	public class TypoListener implements ActionListener{
		
		public void actionPerformed(ActionEvent ae){
			// TODO Auto-generated method stub
			if(ae.getSource() == jb[0]) {
				String name = link[0].getText();
				System.out.println("Mass: " + link[0].getText() + " kg");
				mass = Float.parseFloat(link[0].getText());
			}else if(ae.getSource() == jb[1]) {
				String name2 = link[1].getText();
				System.out.println("Height is: " + name2 + " meters");
				height = Float.parseFloat(link[1].getText());
			}else if(ae.getSource() == jb[2]) {
				String name3 = link[2].getText();
				System.out.println("Fat Percentage is: " + name3 + " %");
				lean = Float.parseFloat(link[2].getText());
			}else if(ae.getSource() == jb[3]){
				gender = createGenderOptions();
			}else if (ae.getSource() == ans[0]) {
				float bmi = Calculator.bmi_calculator(mass, height);
				link_out[0].setText(Float.toString(bmi));
			}else {
				active_level = createPopupOptions();
				float calorie = Calculator.calorie_intake(Calculator.basal_metabolic_rate(mass, lean, gender), active_level);
				link_out[1].setText(Float.toString(calorie));
			}
		}
		
	}
	
	public static float createPopupOptions() {
		 String[] options = new String[] {"Very Light", "Light", "Moderate", "Heavy", "Very Heavy"};
		 int response = JOptionPane.showOptionDialog(null, "Choose", "Active Level", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		 if (response == 0) active_level = (float) 1.3;
		 else if (response == 1) active_level = (float) 1.55;
		 else if (response == 2) active_level = (float) 1.65;
		 else if (response == 3) active_level = (float) 1.80;
		 else active_level = (float) 2.0;
		 
		 return active_level;
	}

	public static String createGenderOptions() {
		 String[] options = new String[] {"Male", "Female"};
		 int response = JOptionPane.showOptionDialog(null, "Choose", "Gender", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		 if (response == 0) gender = "Male";
		 else gender = "Female";
		 
		 return gender;
	}

}
