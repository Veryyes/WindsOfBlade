package tools;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ItemMaker extends JFrame implements ActionListener {
	private static final long serialVersionUID = -5887928167884660493L;
	private static JRadioButton use, equip, etc;
	private static JTextField name,descript,hp,mp,sp,slot,str,intel,dex,will,agil,def,mdef;
	private static JCheckBox teamOnly;
	private static GridBagConstraints gbc = new GridBagConstraints();
	private static GridBagLayout gridBag = new GridBagLayout();
	public static void main(String[] args){
		new ItemMaker().setVisible(true);
	}
	public ItemMaker(){
		super("Winds of Blade - Item Maker Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(gridBag);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx=1;
		gbc.ipadx=4;
		gbc.ipady=4;
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		JPanel typePanel = new JPanel();
		typePanel.setLayout(new FlowLayout());
		use = new JRadioButton("Use",true);
		use.addActionListener(this);
		typePanel.add(use);
		equip = new JRadioButton("Equip",false);
		equip.addActionListener(this);
		typePanel.add(equip);
		etc = new JRadioButton("Etc",false);
		etc.addActionListener(this);
		typePanel.add(etc);
		gridBag.setConstraints(typePanel,gbc);
		add(typePanel);
		
		addLabel("Name:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		name = new JTextField();
		gridBag.setConstraints(name, gbc);
		add(name);
		
		addLabel("Description:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		descript = new JTextField();
		gridBag.setConstraints(descript, gbc);
		add(descript);
		
		addLabel("HP:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		hp = new JTextField();
		gridBag.setConstraints(hp, gbc);
		add(hp);
		
		addLabel("MP:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		mp = new JTextField();
		gridBag.setConstraints(mp, gbc);
		add(mp);
		
		addLabel("SP:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		sp = new JTextField();
		gridBag.setConstraints(sp, gbc);
		add(sp);
		
		addLabel("Friendly Unit target only?");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		teamOnly = new JCheckBox("Yes",false);
		gridBag.setConstraints(teamOnly,gbc);
		add(teamOnly);
		
		addLabel("Slot:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		slot = new JTextField();
		gridBag.setConstraints(slot, gbc);
		add(slot);
		
		addLabel("STR:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		str = new JTextField();
		gridBag.setConstraints(str, gbc);
		add(str);
		
		addLabel("INT:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		intel = new JTextField();
		gridBag.setConstraints(intel, gbc);
		add(intel);
		
		addLabel("DEX:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		dex = new JTextField();
		gridBag.setConstraints(dex, gbc);
		add(dex);
		
		addLabel("WILL:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		will = new JTextField();
		gridBag.setConstraints(will, gbc);
		add(will);
		
		addLabel("AGIL:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		agil = new JTextField();
		gridBag.setConstraints(agil, gbc);
		add(agil);
		
		addLabel("DEF:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		def = new JTextField();
		gridBag.setConstraints(def, gbc);
		add(def);
		
		addLabel("M-DEF:");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		mdef = new JTextField();
		gridBag.setConstraints(mdef, gbc);
		add(mdef);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JButton enter = new JButton("Create Item!");
		enter.addActionListener(this);
		add(enter);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		JButton clear = new JButton("Clear");
		clear.addActionListener(this);
		add(clear);
		
		slot.setEnabled(false);
		str.setEnabled(false);
		intel.setEnabled(false);
		dex.setEnabled(false);
		will.setEnabled(false);
		agil.setEnabled(false);
		def.setEnabled(false);
		mdef.setEnabled(false);
		
		pack();
	}
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
		case "Use":
			use.setSelected(true);
			equip.setSelected(false);
			etc.setSelected(false);
			hp.setEnabled(true);
			mp.setEnabled(true);
			sp.setEnabled(true);
			teamOnly.setEnabled(true);
			slot.setEnabled(false);
			str.setEnabled(false);
			intel.setEnabled(false);
			dex.setEnabled(false);
			will.setEnabled(false);
			agil.setEnabled(false);
			def.setEnabled(false);
			mdef.setEnabled(false);
			break;
		case "Equip":
			use.setSelected(false);
			equip.setSelected(true);
			etc.setSelected(false);
			hp.setEnabled(false);
			mp.setEnabled(false);
			sp.setEnabled(false);
			teamOnly.setEnabled(false);
			slot.setEnabled(true);
			str.setEnabled(true);
			intel.setEnabled(true);
			dex.setEnabled(true);
			will.setEnabled(true);
			agil.setEnabled(true);
			def.setEnabled(true);
			mdef.setEnabled(true);
			break;
		case "Etc":
			use.setSelected(false);
			equip.setSelected(false);
			etc.setSelected(true);
			hp.setEnabled(false);
			mp.setEnabled(false);
			sp.setEnabled(false);
			teamOnly.setEnabled(false);
			slot.setEnabled(false);
			str.setEnabled(false);
			intel.setEnabled(false);
			dex.setEnabled(false);
			will.setEnabled(false);
			agil.setEnabled(false);
			def.setEnabled(false);
			mdef.setEnabled(false);
			break;
		case "Create Item!":
			String checks = checkInputs();
			if(checks.equals("good")){
				try{
					FileWriter fw = new FileWriter(new File("data/items.txt"),true);
					if(use.isSelected())
						fw.write("\nname="+name.getText()+"\ndescription="+descript.getText()+"\nhp="+hp.getText()+"\nmp="+mp.getText()+"\nsp="+sp.getText()+"\nteamOnly="+teamOnly.isSelected()+";\n");
					else if(equip.isSelected())
						fw.write("\nname="+name.getText()+"\ndescription="+descript.getText()+"\nslot="+slot.getText()+"\nstr="+str.getText()+"\nint="+intel.getText()+"\ndex="+dex.getText()+"\nwill="+will.getText()+"\nagil="+agil.getText()+"\ndef="+def.getText()+"\nmagicdef="+mdef.getText()+";\n");
					else
						fw.write("\nname="+name.getText()+"\ndescription="+descript.getText()+";\n");
					fw.close();
					JOptionPane.showMessageDialog(null, name.getText()+" has been created!");
				}catch(IOException error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, "Cannot find file data/items.txt");
				}
			}else{
				JOptionPane.showMessageDialog(null, checks);
				break;
			}
		case "Clear":
			name.setText("");
			descript.setText("");
			hp.setText("");
			mp.setText("");
			sp.setText("");
			teamOnly.setSelected(false);
			slot.setText("");
			str.setText("");
			intel.setText("");
			dex.setText("");
			will.setText("");
			agil.setText("");
			def.setText("");
			mdef.setText("");
			break;
		}
	}
	public String checkInputs(){
		if(name.getText().length()==0)
			return "Item must have a name!";
		if(name.getText().length()>20)
			return "Item name is too long!";
		if(use.isSelected()){
			try{
				Integer.parseInt(hp.getText());
			}catch(NumberFormatException e){
				return "HP must be a number!";
			}
			try{
				Integer.parseInt(mp.getText());
			}catch(NumberFormatException e){
				return "MP must be a number!";
			}
			try{
				Integer.parseInt(sp.getText());
			}catch(NumberFormatException e){
				return "SP must be a number!";
			}
		}else if(equip.isSelected()){
			if(!isValidSlot(slot.getText()))
				return slot.getText()+" is not a valid input"
						+ "\nValid inputs are:\nhelmet,h,H\nchest,c,C,\npants,p,P\nshoes,s,S\ngloves,g,G\nright,r,R\nleft,l,L";
			try{
				Integer.parseInt(str.getText());
			}catch(NumberFormatException e){
				return "STR must be a number!";
			}
			try{
				Integer.parseInt(intel.getText());
			}catch(NumberFormatException e){
				return "INT must be a number!";
			}
			try{
				Integer.parseInt(dex.getText());
			}catch(NumberFormatException e){
				return "DEX must be a number!";
			}
			try{
				Integer.parseInt(will.getText());
			}catch(NumberFormatException e){
				return "WILL must be a number!";
			}
			try{
				Integer.parseInt(agil.getText());
			}catch(NumberFormatException e){
				return "AGIL must be a number!";
			}
			try{
				Integer.parseInt(def.getText());
			}catch(NumberFormatException e){
				return "DEF must be a number!";
			}
			try{
				Integer.parseInt(str.getText());
			}catch(NumberFormatException e){
				return "M-DEF must be a number!";
			}
		}
		return "good";
	}
	private void addLabel(String s){
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		JLabel label = new JLabel(s);
		gridBag.setConstraints(label,gbc);
		this.add(label);
	}
	private boolean isValidSlot(String s){
		char c = s.toLowerCase().toCharArray()[0];
		return (c=='h'||c=='c'||c=='p'||c=='s'||c=='g'||c=='r'||c=='l'||c=='p');
	}
}
