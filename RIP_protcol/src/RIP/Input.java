package RIP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JFileChooser;




public class Input {
static Hashtable<String,InputNode>  lookupTable = new Hashtable<String,InputNode>();
static JFileChooser openfile;
	public static void main(String[] args){
		 openfile= new JFileChooser();
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
							 		      					for (int i=2;i<commands.length;i++){
							 		      						addresses[i-2]= Integer.parseInt(  commands[i]);	
							 		      					}
							 		      				lookupTable.put(commands[1], new InputNode(commands[1],addresses));
							 		    	   
							 		       			}else if (commands[0].equals("link")){
							 		       					if( !lookupTable.containsKey(commands[1]) || !lookupTable.containsKey(commands[2]) ){
							 		       						System.out.println("Define these nodes first before specifying the link");
							 		       					}else{
							 		       						((InputNode)lookupTable.get(commands[1])).addEntries((InputNode)lookupTable.get(commands[2]));
							 		       						((InputNode)lookupTable.get(commands[2])).addEntries((InputNode)lookupTable.get(commands[1]));
							 		       					}
							 		       			}else{
							 		       				
							 		       				 for (Enumeration<String> e= lookupTable.keys(); e.hasMoreElements();){
							 		       					 String process = e.nextElement();
							 		       					 System.out.println(" The process is"+process);
							 		       					 ArrayList<String> links = lookupTable.get(process).links;
							 		       					 String link =" ";
							 		       					 for (String l:links){
							 		       						 link= link + l+ " ";
							 		       					 }
							 		       					 System.out.println("The links are "+link);
							 		       					 
							 		       				 }
							 		       					if (commands.length==2){
							 		       						
							 		       						InputCommand.sendProcess(commands[1]);
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
