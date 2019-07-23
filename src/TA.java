import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TA implements Runnable {
	
	private ObjectOutputStream	oos; //write object data to TC
	private ObjectInputStream	ois; //read object data for TC
	
	private InputStream is1;        //used to read priitive input of TB 
	private InputStream is2;        //read object input of TC
	private OutputStream os1;       //send object data to TC 
	
	
	public TA(InputStream is1, InputStream is2, OutputStream os1) {
		this.is1=is1;
		this.is2=is2;
		this.os1=os1;
	}
	

	@Override
	public void run() {
		try {
			
			//create objectoutput stream
			this.oos= new ObjectOutputStream(os1);
			//send object data to TC
			int id=1;
			int num=20;
			Message theMessage= new Message(num, id);
			
			System.out.println(getClass().getName() + " Object Message sent: " + theMessage);
			oos.writeObject(theMessage);
			
			//receive premitive data from tb
			int data=this.is1.read();
			System.out.println(getClass().getName() + " received primitive: " + data);
			
			
			//receive object data from TC
			this.ois= new ObjectInputStream(is2);
			Message n=(Message)ois.readObject();
			System.out.println(getClass().getName() + " Object Message received: " + n);
		
			
		}catch (Exception e) {
			System.out.println("ERROR: "+getClass().getName() + " " + e);
		} finally {
			try {
				oos.close();
				ois.close();
				is1.close();
				is2.close();
				os1.close();
			} catch (IOException e) {
			
			}
			
		}
		

	}

}
