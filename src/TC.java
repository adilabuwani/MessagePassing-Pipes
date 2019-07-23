import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TC implements Runnable {
	
	private ObjectOutputStream oos; //write object data to TA
	private ObjectInputStream ois1; //read object input from TA
	private ObjectInputStream ois2;  //read object input from TB
	
	private OutputStream os1; // write object data to TA
	private InputStream is1;  //read object input from TA
	private InputStream is2; //read object input from TB
	
	public TC(InputStream is1, InputStream is2, OutputStream os1) {
		this.is1=is1;
		this.is2=is2;
		this.os1=os1;
	}
	
	@Override
	public void run() {
          try {
        	  
        	  //write object to TA
        	this.oos= new ObjectOutputStream(os1);
        	int id=3;
        	int num=25;
        	Message theMessage= new Message(num, id);
        	System.out.println(getClass().getName() + " Object Message sent: " + theMessage);
        	oos.writeObject(theMessage);
        	
        	//read object input from TA
        	ois1= new ObjectInputStream(is1);
        	Message n=(Message)ois1.readObject();
        	System.out.println(getClass().getName() + " Object Message received: " + n);
        	
        	//read object input from TB
        	ois2= new ObjectInputStream(is2);
        	Message p= (Message)ois2.readObject();
        	System.out.println(getClass().getName() + " Object Message received: " + p);
        
		
			
		}catch (Exception e) {
			System.out.println("ERROR: " + getClass().getName() + " " + e);
		} finally {
			try {
				oos.close();
				ois1.close();
				ois2.close();
				is1.close();
				is2.close();
				os1.close();
			} catch (IOException e) {
				System.out.println("ERROR: "+getClass().getName() + " " + e);
			}
			
		}
		
	}

}
