package tools;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Map Maker
 * @author Brandon
 *
 */
//TODO Finish this later....
public class MapMaker extends JFrame implements ActionListener{
	private static final long serialVersionUID = -6140808432618712173L;
	static File file;
	int[][] map;
	public MapMaker(){
		super("Winds of Blade Map Maker");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridLayout(1,2,4,4));
		Canvas canvas = new Canvas();
		add(canvas);
		JPanel buttonPanel = new JPanel();
		JButton newBtn = new JButton("New");
		JButton loadBtn = new JButton("Load");
		
		setVisible(true);
	}
	public class Canvas extends JPanel implements MouseListener{
		private static final long serialVersionUID = -7996822086935733884L;
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			repaint();
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
	
		
	}
}
