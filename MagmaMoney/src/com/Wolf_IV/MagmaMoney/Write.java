package com.Wolf_IV.MagmaMoney;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class Write {
	public static void write(UUID uuid,int point, int stats) throws IOException {
        PrintWriter writer = new PrintWriter(uuid+".json", "UTF-8");
        writer.println(point+","+stats);
        
        writer.close();
       
       

    }
}
