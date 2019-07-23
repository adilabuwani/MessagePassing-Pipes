import java.io.*;

/*
Since Read is a block operation, it will always wait until the message has been received. Therefore, we shall always
perform WRITE OPERATION first, and then followed by read operation to prevent from DEADLOCKS.
*/

public class ConnectionManager {

	public static void main(String[] args){
		try {
			System.out.println("Pipe setup");
			// Output pipes for TA
			PipedOutputStream ta_tc_pos = new PipedOutputStream();

			// Output pipes for TB
			PipedOutputStream tb_ta_pos = new PipedOutputStream();
			PipedOutputStream tb_tc_pos= new PipedOutputStream();

			// Output pipes for TC
			PipedOutputStream tc_ta_pos = new PipedOutputStream();

			// Input pipes for TA
			PipedInputStream ta_tb_pis = new PipedInputStream(tb_ta_pos);
			PipedInputStream ta_tc_pis = new PipedInputStream(tc_ta_pos);
			
			// TB input pipes-none

			// Input pipes for TC
			PipedInputStream tc_ta_pis = new PipedInputStream(ta_tc_pos);
			PipedInputStream tc_tb_pis = new PipedInputStream(tb_tc_pos);
	
			////////////////////////////////////////////////////

			// initialize constrctors
			TA ta = new TA(ta_tb_pis, ta_tc_pis,ta_tc_pos);
			TB tb = new TB(tb_ta_pos,tb_tc_pos);
			TC tc = new TC(tc_ta_pis, tc_tb_pis,tc_ta_pos);
			
			//create threads
			Thread ta_Thread= new Thread(ta);
			Thread tb_Thread= new Thread(tb);
			Thread tc_Thread= new Thread(tc);
			
			//start threads
			ta_Thread.start();
			tb_Thread.start();
			tc_Thread.start();

		} catch (Exception exc) {
			 System.out.println(exc);
		} 
		
	}
}
