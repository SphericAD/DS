package RIP;

import java.util.ArrayList;



public class InputCommand {
	 
   
	@SuppressWarnings("deprecation")
	public static  void sendProcess(String sender) {
		InputNode sendernode =Input.lookupTable.get(sender);
	ArrayList<String> receivers = sendernode.links;	
	ArrayList<Thread> ConcurrentE = new ArrayList<Thread>();
	
	for (String receiver:receivers){
	

	Input.lookupTable.get(receiver).receiveMsgFrom(sender, Input.lookupTable.get(sender).copyRoutingTable());
		//Input.lookupTable.get(receiver).receiveMsgFrom(sender,copies.get(receivers.indexOf(receiver)).getRoutingTable());
		
		

	}
	for (String receiver:receivers){
		ConcurrentE.add(new Thread(Input.lookupTable.get(receiver)));
		
	}
	
	
	 for (Thread update: ConcurrentE){
		
		 update.start();
		 
		 
	 }	
	 
	 
		Thread.currentThread().stop();
	
	}
	public  static void sendProcess(String sender,String receiver){
		
	}
}
