package tools;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class MoveMaker extends JFrame implements ActionListener{
		private static final long serialVersionUID = -4534343322441442772L;
		private static JTextField nameInput,baseInput,accuracyInput,descriptionInput,typeInput,effectInput,hpInput,mpInput,spInput;
		private static JRadioButton contactInput1, contactInput2;
		public static void main(String[] args) {
			new MoveMaker().setVisible(true);
		}
		public MoveMaker(){
			super("Winds of Blade - Move Maker Tool");
			setSize(400,300);
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new GridLayout(0,2,4,4));
			
			JLabel namelbl = new JLabel("Move name:");
			add(namelbl);
			nameInput = new JTextField();
			add(nameInput);
			
			JLabel baselbl = new JLabel("Power(0-100):");
			add(baselbl);
			baseInput = new JTextField();
			add(baseInput);
			
			JLabel accuracylbl = new JLabel("Accuracy(1-100):");
			add(accuracylbl);
			accuracyInput = new JTextField();
			add(accuracyInput);
			
			JLabel descriptionlbl = new JLabel("Description:");
			add(descriptionlbl);
			descriptionInput = new JTextField();
			add(descriptionInput);
			
			JLabel typelbl = new JLabel("Type:");
			add(typelbl);
			typeInput = new JTextField();
			add(typeInput);
			
			JLabel contactlbl = new JLabel("Contact Type:");
			add(contactlbl);
			JPanel contactPanel = new JPanel();
			contactPanel.setLayout(new FlowLayout());
			contactInput1 = new JRadioButton("Physical",true);
			contactInput1.addActionListener(this);
			contactPanel.add(contactInput1);
			contactInput2 = new JRadioButton("Magical",false);
			contactInput2.addActionListener(this);
			contactPanel.add(contactInput2);
			add(contactPanel);
			
			JLabel effectlbl = new JLabel("Effect:");
			add(effectlbl);
			effectInput = new JTextField();
			add(effectInput);
			
			JLabel hplbl = new JLabel("HP Consumed:");
			add(hplbl);
			hpInput = new JTextField();
			add(hpInput);
			
			JLabel mplbl = new JLabel("MP Consumed:");
			add(mplbl);
			mpInput = new JTextField();
			add(mpInput);

			JLabel splbl = new JLabel("SP Consumed:");
			add(splbl);
			spInput = new JTextField();
			add(spInput);
			
			JButton enter  = new JButton("Create Move!");
			enter.addActionListener(this);
			add(enter);
			
			JButton clear = new JButton("Clear");
			clear.addActionListener(this);
			add(clear);
			
			pack();
		}
		
		public void actionPerformed(ActionEvent e) {
			String actionName = e.getActionCommand();
			switch(actionName){
			case "Create Move!":
				String checks = checkInputs();
				if(checks.equals("good")){
					try {
						FileWriter fw = new FileWriter(new File("data/moves.txt"), true);
						fw.write("\nname="+nameInput.getText()+"\nbase="+baseInput.getText()+"\naccuracy="+accuracyInput.getText()+"\ndescription="+descriptionInput.getText()+"\ntype="+typeInput.getText()+"\nphysical="+contactInput1.isSelected()+"\neffect="+effectInput.getText()+"\nhp="+hpInput.getText()+"\nmp="+mpInput.getText()+"\nsp="+spInput.getText()+";\n");
						fw.close();
						JOptionPane.showMessageDialog(null, "Move: "+nameInput.getText()+" Created!");
					} catch (IOException error){
						error.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, checks);
					break;
				}
			case "Clear":
				nameInput.setText("");
				baseInput.setText("");
				accuracyInput.setText("");
				descriptionInput.setText("");
				typeInput.setText("");
				contactInput1.setSelected(true);
				contactInput2.setSelected(false);
				effectInput.setText("");
				hpInput.setText("");
				mpInput.setText("");
				spInput.setText("");
				break;
			}
		}
		public String checkInputs(){
			//Checks Invalid/Blank Inputs
			if(nameInput.getText().length()==0)
				return "Moves must have a name!";
			if(nameInput.getText().length()>=20)
				return "Move name is too long!";
			if(baseInput.getText().length()==0)
				baseInput.setText("0");
			try{
				byte base = Byte.parseByte(baseInput.getText());
				if(base<0||base>100)
					return "Power must be a number between 0-100 inclusivly!";
			}catch(NumberFormatException e){
				return "Power must be a number and be between 0-100 inclusivly!";
			}
			if(accuracyInput.getText().length()==0)
				accuracyInput.setText("0");
			try{
				byte acc = Byte.parseByte(accuracyInput.getText());
				if(acc<1||acc>100)
					return "Accuracy must be a number between 1-100 inclusivly!";
			}catch(NumberFormatException e){
				return "Accuracy must be a number and be between 1-100inclusivly!";
			}
			if(typeInput.getText().length()==0)
				return "Move must have a type!";
			try{
				wob.Type.parseType(typeInput.getText());
			}catch(NumberFormatException e){
				return "Please input a valid type\nValid types are:\ndark\ndivine\nearth\nelectric\nfire\nice\nmetal\nnormal\nvoid\nwater\nwind\nwood";
			}
			try{
				Integer.parseInt(hpInput.getText());
			}catch(NumberFormatException e){
				return "HP consumed must be a number!";
			}
			try{
				Integer.parseInt(mpInput.getText());
			}catch(NumberFormatException e){
				return "MP consumed must be a number!";
			}
			try{
				Integer.parseInt(spInput.getText());
			}catch(NumberFormatException e){
				return "SP consumed must be a number!";
			}
			return "good";
		}
	}