package RIP;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class InputNode {

	private String name;
	private int[] addresses;
	/*the routing table of the corresponding node */
	private Hashtable<Integer,LinkCost> routingTable = new Hashtable<Integer,LinkCost>();
	/* list of the nodes that are linked to the current node*/
	ArrayList<String> links= new ArrayList<String>();
	
	public InputNode(String name ,int[] addresses){
		this.name=name;
		this.addresses=addresses;
	}
	
	public void addEntries(InputNode node){
		int[] destinations = node.getAddresses();
		links.add(node.getName());
			for (int i=0;i<destinations.length;i++){
				routingTable.put(destinations[i], new LinkCost(node.getName(),1));
			}
		
		
	}
	public Hashtable<Integer, LinkCost> getRoutingTable(){
		return routingTable;
	}
	public void receiveTable(Hashtable<Integer ,LinkCost> table, String name){
		String info="";
		Enumeration<Integer> e= table.keys();
		while (e.hasMoreElements()){
			Integer address= (Integer) e.nextElement();
			info = info.concat(" ( "+address+ "|" + table.get(address).link +"|"+ table.get(address).Cost + ") ");
		}
		System.out.println(" recieve "+ name + " "+ this.getName() + info);
		
		
	}
	
	public String getName(){
		return name;
		
	}
	public int[] getAddresses(){
		return addresses;
	}
	
	public static class LinkCost{
		String link;
		int Cost;
		public LinkCost(String link,int cost){
		   this.link=link;
		   this.Cost=cost;
		}
	}

}

