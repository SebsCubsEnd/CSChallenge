package mainPack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.DeflaterOutputStream;

public class CompressionThread implements Runnable{

	BufferedReader objReaderComp = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public void run() {	
		
		String[] fullData;
		int numberOfNodes = 0;
		ArrayList<String> nodes = new ArrayList<String>(); // Create an ArrayList object
		ArrayList<String[]> connections = new ArrayList<String[]>();
		
		
		
		 System.out.println("Insert the route for the text file containing the graph:\n");
		 //Read input from console
		 String route = null;
			try {
				route = objReaderComp.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		//Read text file with provided route
		try {
			String dataLine;
			BufferedReader fileReader = new BufferedReader(new FileReader(route));
			
			try {
				while ((dataLine = fileReader.readLine()) != null) {
					//Generate an adjacency matrix
					fullData = dataLine.split(",");
					
					//Retrieve nodes
					if(fullData.length == 1) {
						numberOfNodes++;
						nodes.add(fullData[0]);
					}else {
						//Retrieve connections:
						connections.add(fullData);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(fileReader != null)
						fileReader.close();
				}catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Generate matrix
		int[][] adjMatrix = new int[numberOfNodes][numberOfNodes];
		
		//find the index of each node and place distance between nodes
		for(int i = 0;i<connections.size();i++) {
			int index1 = nodes.indexOf(connections.get(i)[0]);
			int index2 = nodes.indexOf(connections.get(i)[1]);
			int distance = Integer.parseInt(connections.get(i)[2]);
			//Given it's an undirected graph, matrix should be symmetric
			adjMatrix[index1][index2] = distance;  
			adjMatrix[index2][index1] = distance;  
		}
		
		//perform compression
		try {
			FileOutputStream fos = new FileOutputStream("compress");
			DeflaterOutputStream dos = new DeflaterOutputStream(fos);
			
			dos.write(numberOfNodes);
			for(int i = 0;i<numberOfNodes;i++) {
				for(int j = 0;j<numberOfNodes;j++) {
					
					dos.write(adjMatrix[i][j]);	
					
				}
			}
			
			
			dos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
