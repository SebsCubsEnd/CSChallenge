package mainPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.InflaterInputStream; 




public class Shortest2PointsThread implements Runnable{
	
	int[][] adjMatrix;
	ArrayList<Integer> inputData = new ArrayList<Integer>();
	int start;
	int stop;
	int[][] minDistFinal;
	
	
	
	public Shortest2PointsThread(int start, int stop) {
		this.start = start;
		this.stop = stop;
		
	}
	
	
	
	@Override
	public void run() {
		
		adjMatrix = retrieveCompressed();
       
        int graph[][] = adjMatrix;
        ShortestPath shortestPathThread = new ShortestPath(); 
        shortestPathThread.dijkstra(graph, start); 
		
		
	}
	
	
	
	
	private int[][] retrieveCompressed() {
		int[][] adjMatrixTemp = null;
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
			int matrixFillCounter = 1;
			adjMatrixTemp = new int[inputData.get(0)][inputData.get(0)];
			for(int i = 0; i<inputData.get(0);i++) {
				for(int j = 0;j<inputData.get(0);j++) {
					adjMatrixTemp[i][j] = inputData.get(matrixFillCounter);
					matrixFillCounter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return adjMatrixTemp;
	}
	
	
	
	
}
