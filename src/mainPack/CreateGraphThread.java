package mainPack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class CreateGraphThread implements Runnable{

	BufferedReader objReaderCreate = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public void run() {
		 System.out.println("Insert the name for the new graph(without spaces and without extension):\n");
		 //Read input from console(name)
		 String name = null;
			try {
				name = objReaderCreate.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Insert the number of nodes:\n");
			 //Read input from console(nodes)
			 String nodes = null;
				try {
					nodes = objReaderCreate.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		//create new file
		name = name + ".txt";
		File file = new File(name);
	    try {
			if(file.createNewFile()){
			    System.out.println(name + " File Created in Project root directory");
			}else System.out.println("File "+ name +" already exists in the project root directory");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Create data to write
	    int noOfNodes = Integer.parseInt(nodes);
		int noOfConnections = noOfNodes*2;
		int noOfLines = noOfNodes+noOfConnections-4;
		String dataLines[] = new String[noOfLines];
		
		Random rand = new Random();
		
		for(int i = 0;i<noOfNodes;i++) {
				//generate random strings
				int leftLimit = 97; //'a'
				int rightLimit = 122; //'z'
				int targetStringLength = 8;
				StringBuilder buffer = new StringBuilder(targetStringLength);
				for(int j = 0;j<targetStringLength;j++) {
					int randomLimitedInt = leftLimit+(int)(rand.nextFloat()*(rightLimit-leftLimit+1));
					buffer.append((char) randomLimitedInt);
				}
				dataLines[i] = buffer.toString();
			    
		}
		
		for(int i = 0; i<noOfNodes-2;i++) {
			int randomInt = rand.nextInt(26);
			dataLines[i*2+noOfNodes] = dataLines[i] + ","+dataLines[i+1]+","+Integer.toString(randomInt);
			
			int randomInt2 = rand.nextInt(26);
			dataLines[i*2+noOfNodes+1] = dataLines[i] + ","+dataLines[i+2]+","+Integer.toString(randomInt2);
		}
		
		String dataWithNewLine = dataLines[0]+"\n";
		for(int i = 0;i<dataLines.length-1;i++) {
			dataWithNewLine = dataWithNewLine + dataLines[i+1]+"\n";
		}
		
		
		FileWriter fr = null;
		BufferedWriter br = null;
			
			try {
				fr = new FileWriter(file);
				br = new BufferedWriter(fr);
				//Actual writing
				br.write(dataWithNewLine);
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					br.close();
					fr.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
		
		
		
		
		
	}
	

}
