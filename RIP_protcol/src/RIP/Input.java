package RIP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JFileChooser;




public class Input {
static Hashtable<String,InputNode>  lookupTable = new Hashtable<String,InputNode>();
	
	public static void main(String[] args){
		JFileChooser openfile = new JFileChooser();
		int reval =openfile.showOpenDialog(null);
			if (reval== JFileChooser.APPROVE_OPTION){
				 File input =openfile.getSelectedFile();
				 	if (input.isDirectory()){
				 		System.out.println("the specified file is a directory :the file must be a text file!");
				 		}
				 	try {
						Scanner reader = new Scanner(input);
						 while (reader.hasNextLine()){
							 		String line= reader.nextLine();
							 		if (!line.isEmpty()){
							 		      String[] commands=line.split(" ");
							 		      			if (commands[0].equals((String)"node")){
							 		      				int[] addresses = new int[commands.length-2];
							 		      				for (int i=2;i<=commands.length-2;i++){
							 		      						addresses[i-2]= Integer.parseInt(  commands[i]);	
							 		      					}
							 		      				lookupTable.put(commands[1], new InputNode(commands[1],addresses));
							 		    	   
							 		       			}else if (commands[0].equals((String)"link")){
							 		       			   ((InputNode)lookupTable.get(commands[1])).addEntries((InputNode)lookupTable.get(commands[2]));
							 		       			((InputNode)lookupTable.get(commands[2])).addEntries((InputNode)lookupTable.get(commands[1]));
							 		       			}else{
							 		       			 ArrayList<String> links = lookupTable.get(commands[1]).links;
							 		       			 		for (String link:links){
							 		       			 			lookupTable.get(link).receiveTable(lookupTable.get(commands[1]).getRoutingTable(), commands[1]);
							 		       			 		}
									 		    	   
							 		       			}
							 		    }
							 		
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("file doesnt exist ");
					}
				 	
			}
			else {
				System.exit(0);
			}
	}
}
