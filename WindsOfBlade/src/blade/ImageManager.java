package blade;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class ImageManager{
	private static Image[] database;
	/**
	 * Reads and loads all the images in the game's "res" folder
	 * and places them into database which is sorted
	 * @see FileIO#loadFiles(String, LinkedList)
	 * @throws IOException
	 */
	public static void loadImages() throws IOException{
		LinkedList<File> picFiles = new LinkedList<File>();
		FileIO.loadFiles("res", picFiles);
		database = new Image[picFiles.size()];
		int i=0;
		for(File f:picFiles){
			database[i]=new Image(f);
			i++;
		}
		Arrays.sort(database);
	//	for(Image m:database){
		//	System.out.println(m+" "+m.getFile());
		//}
	}
	/**
	 * Retrieves a specified BufferedImage
	 * @param filePath the filepath of the image file
	 * @return An Image at the path given
	 */
	public static Image get(String filePath){
		Image testImg = new Image(null, new File(filePath));
		int index = Arrays.binarySearch(database, testImg);
		if(index<0){
			System.out.println("[WARNING] Missing Image: "+filePath);
			return null;
		}
		return database[index];
	}
	/**
	 * Retrieves a list of BufferedImages with consecutive indexes starting at 1 to listLength appended right before the file extension
	 * <p>
	 * for example: getList("apple.png",4) returns {apple1.png, apple2.png, apple3.png, apple4.png}
	 * @param filePath the file path of the image file without its animation index
	 * @param listLength the index of image to retrieve
	 * @return An array of BufferedImage objects representing the list of images
	 */
	public static Image[] getList(String filePath, int listLength){
		Image[] imgs = new Image[listLength];
		for(int i=0;i<imgs.length;i++)
			imgs[i]=get(filePath.split("\\.")[0]+(i+1)+filePath.split("\\.")[1]);
		return imgs;
	}
	/**
	 * Retrieves a list of specified BufferedImage
	 * @param filePaths a list of image filepath
	 * @return an array of BufferedImage objects representing the list of image
	 */
	public static Image[] getList(String ...filePaths){
		Image[] imgs = new Image[filePaths.length];
		for(int i=0;i<imgs.length;i++)
			imgs[i]=get(filePaths[i]);
		return imgs;
	}
	public static void printAllImages(){
		for(Image m:database)
			System.out.println(m);
	}
}
