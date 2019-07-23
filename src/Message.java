import java.io.Serializable;


//the Message.java class is similar and referenced to the one posted on lecture Notes
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4599323255709849276L;
	public int number;
	public int	id;

	public Message(int number, int id) {
		this.number = number;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", magic number=" + number + "]";
	}

}