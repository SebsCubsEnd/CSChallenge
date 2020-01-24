package mainPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.InflaterInputStream; 

public class Shortest2PointsThread implements Runnable{

	int[][] adjMatrix;
	ArrayList<Integer> inputData = new ArrayList<Integer>();
	@Override
	public void run() {
		//Retrieve adjacency matrix
		
		try {
			FileInputStream fileReader = new FileInputStream("compress");
			InflaterInputStream iis = new InflaterInputStream(fileReader);
			
			int data;
			while ((data=iis.read()) != -1) {
				//refill matrix first number is numberOfNodes
				inputData.add(data);
			}
			
			iis.close();
			int counter = 1;
			adjMatrix = new int[inputData.get(0)][inputData.get(0)];
			for(int i = 0; i<inputData.get(0);i++) {
				for(int j = 0;j<inputData.get(0);j++) {
					
					adjMatrix[i][j] = inputData.get(counter);
					counter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 //Actual algorithm for the shortest path
		
		dijkstra();
		 
		
	}
	
	private void dijkstra() {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> nonVisited = new ArrayList<Integer>();
		
		
		
	}

}
