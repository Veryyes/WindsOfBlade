package tools;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EnemyMaker extends JFrame implements ActionListener {
	private static final long serialVersionUID = 2834580233388067077L;
	private static JTextField name, exp, money, type, hp, str, intel, dex, will, agil;
	private static JRadioButton dmgType1, dmgType2, dmgType3, ai1, ai2, ai3, ai4, ai5;
	private static int dmgType, ai;
	public static void main(String[] args) throws IOException {
		new EnemyMaker().setVisible(true);
	}
	public EnemyMaker(){
		super("Winds of Blade - Enemy Maker Tool");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBag = new GridBagLayout();
		setLayout(gridBag);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx=1;
		gbc.ipadx=4;
		gbc.ipady=4;
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel namelbl = new JLabel("Name:");
		gridBag.setConstraints(namelbl, gbc);
		add(namelbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		name = new JTextField();
		gridBag.setConstraints(name, gbc);
		add(name);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel explbl = new JLabel("Experience Gained:");
		gridBag.setConstraints(explbl, gbc);
		add(explbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		exp = new JTextField();
		gridBag.setConstraints(exp, gbc);
		add(exp);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel moneylbl = new JLabel("Money Dropped:");
		gridBag.setConstraints(moneylbl, gbc);
		add(moneylbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		money = new JTextField();
		gridBag.setConstraints(money, gbc);
		add(money);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel typelbl = new JLabel("Type:");
		gridBag.setConstraints(typelbl, gbc);
		add(typelbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		type = new JTextField();
		gridBag.setConstraints(type, gbc);
		add(type);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel dmgTypelbl = new JLabel("Damage Type:");
		gridBag.setConstraints(dmgTypelbl, gbc);
		add(dmgTypelbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		JPanel dmgPanel = new JPanel();
		dmgPanel.setLayout(new FlowLayout());
		dmgType1 = new JRadioButton("Physical", true);
		dmgType1.addActionListener(this);
		dmgPanel.add(dmgType1);
		dmgType2 = new JRadioButton("Magic", false);
		dmgType2.addActionListener(this);
		dmgPanel.add(dmgType2);
		dmgType3 = new JRadioButton("Mix", false);
		dmgType3.addActionListener(this);
		dmgPanel.add(dmgType3);
		gridBag.setConstraints(dmgPanel, gbc);
		add(dmgPanel);
	
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel ailbl = new JLabel("Targeting AI:");
		gridBag.setConstraints(ailbl, gbc);
		add(ailbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		JPanel aiPanel = new JPanel();
		aiPanel.setLayout(new GridLayout(0,3,4,4));
		ai1 = new JRadioButton("Random",true);
		ai1.addActionListener(this); 
		aiPanel.add(ai1); 
		ai2 = new JRadioButton("Lowest HP(by value)",false);
		ai2.addActionListener(this);
		aiPanel.add(ai2);
		ai3 = new JRadioButton("Lowest HP(by %)",false);
		ai3.addActionListener(this);
		aiPanel.add(ai3);
		ai4 = new JRadioButton("Highest HP(by value)",false);
		ai4.addActionListener(this);
		aiPanel.add(ai4);
		ai5 = new JRadioButton("Highest HP(by %)",false);
		ai5.addActionListener(this);
		aiPanel.add(ai5);
		gridBag.setConstraints(aiPanel, gbc);
		add(aiPanel);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel hplbl = new JLabel("HP:");
		gridBag.setConstraints(hplbl, gbc);
		add(hplbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		hp = new JTextField();
		gridBag.setConstraints(hp, gbc);
		add(hp);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel strlbl = new JLabel("Strength:");
		gridBag.setConstraints(strlbl, gbc);
		add(strlbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		str = new JTextField();
		gridBag.setConstraints(str, gbc);
		add(str);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel intellbl = new JLabel("Intelligence:");
		gridBag.setConstraints(intellbl, gbc);
		add(intellbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		intel = new JTextField();
		gridBag.setConstraints(intel, gbc);
		add(intel);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel dexlbl = new JLabel("Dexterity:");
		gridBag.setConstraints(dexlbl, gbc);
		add(dexlbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		dex = new JTextField();
		gridBag.setConstraints(dex, gbc);
		add(dex);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel willlbl = new JLabel("Will:");
		gridBag.setConstraints(willlbl, gbc);
		add(willlbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		will = new JTextField();
		gridBag.setConstraints(will, gbc);
		add(will);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel agillbl = new JLabel("Agility:");
		gridBag.setConstraints(agillbl, gbc);
		add(agillbl);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		agil = new JTextField();
		gridBag.setConstraints(agil, gbc);
		add(agil);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JButton enter = new JButton("Create Enemy!");
		enter.addActionListener(this);
		add(enter);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		JButton clear = new JButton("Clear");
		clear.addActionListener(this);
		add(clear);
		
		pack();
	}
	public void actionPerformed(ActionEvent e) {
		String actionName = e.getActionCommand();
		switch(actionName){
		case "Physical":
			dmgType1.setSelected(true);
			dmgType2.setSelected(false);
			dmgType3.setSelected(false);
			break;
		case "Magic":
			dmgType1.setSelected(false);
			dmgType2.setSelected(true);
			dmgType3.setSelected(false);
			break;
		case "Mix":
			dmgType1.setSelected(false);
			dmgType2.setSelected(false);
			dmgType3.setSelected(true);
			break;
		case "Random":
			ai1.setSelected(true);
			ai2.setSelected(false);
			ai3.setSelected(false);
			ai4.setSelected(false);
			ai5.setSelected(false);
			break;
		case "Lowest HP(by value)":
			ai1.setSelected(false);
			ai2.setSelected(true);
			ai3.setSelected(false);
			ai4.setSelected(false);
			ai5.setSelected(false);
			break;
		case "Lowest HP(by %)":
			ai1.setSelected(false);
			ai2.setSelected(false);
			ai3.setSelected(true);
			ai4.setSelected(false);
			ai5.setSelected(false);
			break;
		case "Highest HP(by value)":
			ai1.setSelected(false);
			ai2.setSelected(false);
			ai3.setSelected(false);
			ai4.setSelected(true);
			ai5.setSelected(false);
			break;
		case "Highest HP(by %)":
			ai1.setSelected(false);
			ai2.setSelected(false);
			ai3.setSelected(false);
			ai4.setSelected(false);
			ai5.setSelected(true);
			break;
		case "Create Enemy!":
			String checks = checkInputs();
			if(checks.equals("good")){
				try{
					FileWriter fw = new FileWriter(new File("data/enemy/"+name.getText()+".txt"));
					int level = 1;
					//TODO calculate level based on stats;
					fw.write("level="+level);
					fw.write("\nexp="+exp.getText());
					fw.write("\nmoney="+money.getText());
					fw.write("\ntype="+type.getText());
					updateRadioButtons();
					fw.write("\ndmgType="+dmgType);
					fw.write("\nai="+ai);
					fw.write("\nhp="+hp.getText());
					fw.write("\nstr="+str.getText());
					fw.write("\nint="+intel.getText());
					fw.write("\ndex="+dex.getText());
					fw.write("\nwill="+will.getText());
					fw.write("\nagil="+agil.getText());
					fw.close();
					JOptionPane.showMessageDialog(null, name.getText()+" has been created!");
				}catch(IOException error){
					error.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, checks);
				break;
			}
		case "Clear":
			dmgType1.setSelected(true);
			dmgType2.setSelected(false);
			dmgType3.setSelected(false);
			ai1.setSelected(true);
			ai2.setSelected(false);
			ai3.setSelected(false);
			ai4.setSelected(false);
			ai5.setSelected(false);
			name.setText("");
			exp.setText("");
			money.setText("");
			type.setText("");
			hp.setText("");
			str.setText("");
			intel.setText("");
			will.setText("");
			dex.setText("");
			agil.setText("");
			break;
		}
	}
	private String checkInputs(){
		if(name.getText().length()==0)
			return "Enemy must have a name!";
		if(name.getText().length()>=20)
			return "Enemy name is too long!";
		try{
			int expValue = Integer.parseInt(exp.getText());
			if(expValue<0)
				return "Experience Gained cannot be negative!";
		}catch(NumberFormatException e){
			return "Experience Gained must be a number!";
		}
		try{
			int moneyValue = Integer.parseInt(money.getText());
			if(moneyValue<0)
				return "Money Dropped cannot be negative!";
		}catch(NumberFormatException e){
			return "Money Dropped must be a number!";
		}
		try{
			wob.Type.parseType(type.getText());
		}catch(NumberFormatException e){
			return "Please input a valid type\nValid types are:\ndark\ndivine\nearth\nelectric\nfire\nice\nmetal\nnormal\nvoid\nwater\nwind\nwood";
		}
		try{
			int value = Integer.parseInt(hp.getText());
			if(value<=0)
				return "HP must be positive!";
		}catch(NumberFormatException e){
			return "HP must be a number!";
		}
		try{
			int value = Integer.parseInt(str.getText());
			if(value<=0)
				return "Strength must be positive!";
		}catch(NumberFormatException e){
			return "Strength must be a number!";
		}
		try{
			int value = Integer.parseInt(intel.getText());
			if(value<=0)
				return "Intelligence must be positive!";
		}catch(NumberFormatException e){
			return "Intelligence must be a number!";
		}
		try{
			int value = Integer.parseInt(dex.getText());
			if(value<=0)
				return "Dexterity must be positive!";
		}catch(NumberFormatException e){
			return "Dexterity must be a number!";
		}
		try{
			int value = Integer.parseInt(will.getText());
			if(value<=0)
				return "Will must be positive!";
		}catch(NumberFormatException e){
			return "Will must be a number!";
		}
		try{
			int value = Integer.parseInt(agil.getText());
			if(value<=0)
				return "Agility must be positive!";
		}catch(NumberFormatException e){
			return "Agility must be a number!";
		}
		return "good";
	}
	private static void updateRadioButtons(){
		if(dmgType1.isSelected())
			dmgType=1;
		else if(dmgType2.isSelected())
			dmgType=2;
		else if(dmgType3.isSelected())
			dmgType=3;
		if(ai1.isSelected())
			ai=1;
		else if(ai2.isSelected())
			ai=2;
		else if(ai3.isSelected())
			ai=3;
		else if(ai4.isSelected())
			ai=4;
		else if(ai5.isSelected())
			ai=5;
	}
}
