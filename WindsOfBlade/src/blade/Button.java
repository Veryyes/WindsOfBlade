package blade;

import java.awt.Graphics;

public class Button extends Window{
	//private byte textAlign;
	public Button(int x, int y, int width, int height, String text){
		super(x,y,width,height,text);
	}
	public Button(int x, int y,  String text){
		super(x,y,(text.length()+1)*TypeWriter.getCharWidth(),(int) ((1f/4f)*TypeWriter.getCharHeight()),  text);
	}
	public Button(Window parent, String text){
		super(parent,text);
	}
	public Button(Window parent,  String text, Animation animation){
		super(parent,text,animation);
	}
	@Override
	public void update(Graphics g){
		if(visible){
			drawBackground(g, backColor);
			if(animation==null)
				drawText(g);
			else if(text.length()>0){
				animation.getCurrentFrame().draw(x+BORDER_SIZE+8, y+BORDER_SIZE+8, g);
				TypeWriter.drawString(text, x+BORDER_SIZE+8, y+BORDER_SIZE+animation.getCurrentFrame().getHeight(), width-2*BORDER_SIZE-32, height-2*BORDER_SIZE-animation.getCurrentFrame().getHeight(), g);
			}else
				animation.getCurrentFrame().draw(x+BORDER_SIZE+8, y+BORDER_SIZE+8, g);			
			if(contains(Game.frame.getMousePosition())){
				drawFrame(g);
				if(Game.mousePressed)
					run();
			}
		}
		updateSubWindows(g);
	}

	public void run(){}
}
