package blade;

import java.util.LinkedList;

@SuppressWarnings("serial")
/**
 * Just a LinkedList<String> with an extra field for an id
 * @author Brandon
 *
 */
public class Conversation extends LinkedList<String>{
	int id;
	public Conversation(int id) {
		this.id=id;
	}

}
