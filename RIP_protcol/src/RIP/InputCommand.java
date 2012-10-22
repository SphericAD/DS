package RIP;

import java.util.ArrayList;



public class InputCommand {

	public static void sendProcess(String sender) {
	ArrayList<String> receivers = Input.lookupTable.get(sender).links;	
	ArrayList<Thread> ConcurrentE = new ArrayList<Thread>();
	ArrayList<InputNode> copies= new ArrayList<InputNode>();
	for (int  i=0 ; i<receivers.size();i++){
		copies.add(i, Input.lookupTable.get(sender).clone());
	}
	for (String receiver:receivers){
	//Input.lookupTable.get(receiver).receiveMsgFrom(sender, Input.lookupTable.get(sender).copyRoutingTable());
		Input.lookupTable.get(receiver).receiveMsgFrom(sender,copies.get(receivers.indexOf(receiver)).getRoutingTable());
		
		
	System.out.println("send "+sender +" " + receiver);
	}
	for (String receiver:receivers){
		ConcurrentE.add(new Thread(Input.lookupTable.get(receiver)));
	}
	 for (Thread update: ConcurrentE){
		 update.start();
		 
	 }
	 
	 try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public  static void sendProcess(String sender,String receiver){
		
	}
}
