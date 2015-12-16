package blade;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Map {
	private Animation[][] background;
	private LinkedList<SparseMatrix<Animation>> layers;
	LinkedList<Entity> entities;
	private static Image tileset = ImageManager.get("res/tiles/tileset.png");
	private String name;
	public Map(File mapFile) throws IOException{
		entities = new LinkedList<Entity>();
		layers = new LinkedList<SparseMatrix<Animation>>();
		name = mapFile.getName();
		BufferedReader br = new BufferedReader(new FileReader(mapFile));
		br.readLine();
		int width = Integer.parseInt(br.readLine().split("=")[1].trim());
		int height = Integer.parseInt(br.readLine().split("=")[1].trim());
		background = new Animation[height][width];
		SparseMatrix<Wall> wallsTemp = new SparseMatrix<Wall>(height,width);
		String line="";
		boolean firstLayer = true;
		while((line=br.readLine())!=null){
			if(line.equals("data=")){
				String[] lineArray = br.readLine().split(","); 
				int row=0;
				if(firstLayer){
					firstLayer=false;
					while(lineArray.length!=0&&!lineArray[0].equals("")){
						for(int col = 0;col<lineArray.length;col++){
							int tileID = Integer.parseInt(lineArray[col]+"");
							background[row][col] = parseIndex(tileID);
							if(tileID==1)//Water TODO Change so it works for tile set
								wallsTemp.add(row, col, new Wall(col*Game.GRIDSIZE,row*Game.GRIDSIZE));
						}
						row++;
						lineArray = br.readLine().split(",");
					}
				}else{
					SparseMatrix<Animation> layer = new SparseMatrix<Animation>(height, width);
					while(lineArray.length!=0&&!lineArray[0].equals("")){
						for(int col = 0;col<lineArray.length;col++){
							if(!lineArray[col].equals("0"))
								layer.add(row, col, parseIndex(Integer.parseInt(lineArray[col]+"")));
						}
						row++;
						lineArray = br.readLine().split(",");
					}
					layers.add(layer);
				}
			}else if(line.equals("[Object Layer 1]")){
				String entName = br.readLine().substring(2);
				line=br.readLine();
				if(line.contains("Npc")){
					line=br.readLine().substring(9);
					Npc temp = new Npc(Integer.parseInt(line.split(",")[0])*Game.GRIDSIZE,
							Integer.parseInt(line.split(",")[1])*Game.GRIDSIZE,
							entName);
					entities.add(temp);
					entities.add(new Wall(temp.box.x, temp.box.y));
				}else if(line.contains("Inn")){
					line=br.readLine().substring(9);
					Inn temp = new Inn(Integer.parseInt(line.split(",")[0])*Game.GRIDSIZE,
							Integer.parseInt(line.split(",")[1])*Game.GRIDSIZE,
							entName);
					entities.add(temp);
					entities.add(new Wall(temp.box.x, temp.box.y));
				}else if(line.contains("Shop")){
					line=br.readLine().substring(9);
					Shop temp = new Shop(Integer.parseInt(line.split(",")[0])*Game.GRIDSIZE,
							Integer.parseInt(line.split(",")[1])*Game.GRIDSIZE,
							entName);
					entities.add(temp);
					entities.add(new Wall(temp.box.x, temp.box.y));
				}else if(line.contains("Portal")){
					
				}else{
					System.out.println("[WARNING] Unknown Object "+entName+" Skipping...");
				}
			}
		}
		br.close();
		addWalls(wallsTemp, entities);
		shiftEntities();
		
	}
	public void update(Graphics g){
		for(int i=0;i<background.length;i++){
			for(int j=0;j<background[0].length;j++){
				if(i*Game.GRIDSIZE+Camera.yShift>-Game.GRIDSIZE&&i*Game.GRIDSIZE+Camera.yShift<Game.frameHeight&&j*Game.GRIDSIZE+Camera.xShift>-Game.GRIDSIZE&&j*Game.GRIDSIZE+Camera.xShift<Game.frameWidth)
					background[i][j].update();
					background[i][j].getCurrentFrame().draw(j*Game.GRIDSIZE+Camera.xShift,i*Game.GRIDSIZE+Camera.yShift, g);
			}
		}
		for(SparseMatrix<Animation> sm:layers){
			for(Cell<Animation> cell=sm.getHead();cell!=null;cell=cell.getNext()){
				cell.getValue().update();
				cell.getValue().getCurrentFrame().draw(cell.getX()*Game.GRIDSIZE+Camera.xShift,cell.getY()*Game.GRIDSIZE+Camera.yShift, g);
			}
		}
		for(Entity e: entities)
			e.update(g);
		
	}
	private Animation parseIndex(int i){
		return new Animation(tileset.getSubimage(
				((i-1)%(tileset.getWidth()/Game.GRIDSIZE))*Game.GRIDSIZE,
				((i-1)/(tileset.getWidth()/Game.GRIDSIZE))*Game.GRIDSIZE,
				Game.GRIDSIZE, Game.GRIDSIZE));
		
	}
	private void addWalls(SparseMatrix<Wall> parentList, LinkedList<Entity> list){//TODO optimize walls more
		LinkedList<Point> flagsForRemoval = new LinkedList<Point>();
		for(Cell<Wall> current = parentList.getHead();current!=null;current=current.getNext()){
			if((parentList.get(current.getY()+1, current.getX())!=null)&&(parentList.get(current.getY()-1, current.getX())!=null)&&
			(parentList.get(current.getY(), current.getX()+1)!=null)&&(parentList.get(current.getY(), current.getX()-1)!=null)){
				flagsForRemoval.add(new Point(current.getX(),current.getY()));
			}
		}
		for(int i = 0;i<flagsForRemoval.size();i++){
			parentList.remove(flagsForRemoval.get(i).y, flagsForRemoval.get(i).x);
		}
		for(Cell<Wall> current = parentList.getHead();current!=null;current=current.getNext()){
			list.add(current.getValue());
		}
	}
	private void shiftEntities(){
		for(Entity e:entities){
			e.box.x+=Camera.xShift;
			e.box.y+=Camera.yShift;
		}
	}
	public void printSize(){
		System.out.println("[MAP]:"+name);
		System.out.println("Width x Height = "+background[0].length+"x"+background.length+"\nLayer1 - Object Count: "+(background[0].length*background.length));
		int i=1;
		for(SparseMatrix<Animation> sm:layers){
			System.out.println("Layer"+i+" - Object Count: "+sm.size());
			i++;
		}
	}
}
