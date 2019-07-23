import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TB implements Runnable {

	private ObjectOutputStream	oos;	// will be used to write/send object to TC
	
	private OutputStream os1;          //will be used to write/send premitive to TA
	private OutputStream os2;          //will be used to write/send object to TC
	
	public TB(OutputStream os1, OutputStream os2) {
	this.os1=os1;
	this.os2=os2;
	}
	
	@Override
	public void run() {
		try {
			//send primitive to TA
			int num=14;
			System.out.println(getClass().getName() + " sent primitive: " + num);
			this.os1.write(num); // premitive data has been sent
						
			//create object to send to TC
			this.oos= new ObjectOutputStream(os2);
			int id=2;
			Message theMessage= new Message(num, id);
			theMessage.number+=1;  //send a new number
			
			//send the object to TC
			System.out.println(getClass().getName() + " Object Message sent: " + theMessage);
			oos.writeObject(theMessage);
		
			
		}catch (Exception e) {
			System.out.println("ERROR: " + getClass().getName() + " " + e);
		} finally {
			try {
				oos.close();
				os1.close();
				os2.close();
			} catch (IOException e) {
				System.out.println("ERROR: "+getClass().getName() + " " + e);
			
			}
			
		}

	}

}
