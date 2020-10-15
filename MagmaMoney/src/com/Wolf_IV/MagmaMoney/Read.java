package com.Wolf_IV.MagmaMoney;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class Read {
	
	
	
	public static int point = 0;
	public static int stats = 0;
	
	@SuppressWarnings("resource")
	public static void read(UUID uuid) throws IOException{
		//Le read n'est pas lu
		 FileReader fileReader = new FileReader(uuid+".json");

		      
		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        
		        String pointS = null;
		        String statsS = null;
		        //point=false stats=true
		        boolean PS =false;
		        String line = bufferedReader.readLine();
		        for(int i=0;i<line.length();i++) {
		        	if(line.substring(i, i+1).equalsIgnoreCase(",")) {
		        		PS = true;
		        	}else {
		        		if(PS == false) {
		        			if(pointS == null) {
		        				pointS = line.substring(i, i+1);
		        			}else {
		        		pointS = pointS+line.substring(i, i+1);
		        		
		        			}
		        		}else {
		        			
		        			if(statsS == null) {
		        				statsS = line.substring(i, i+1);
		        			}else {
		        			statsS = statsS+line.substring(i, i+1);
		        			
		        			}
		        		}
		        	}
		        }
		       
		        point = Integer.parseInt(pointS);
		        stats = Integer.parseInt(statsS);
		        
		       
		        
	}

}
