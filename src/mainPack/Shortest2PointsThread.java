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
			adjMatrix = new int[inputData.get(0)][inputData.get(0)];
			for(int i = 0; i<inputData.get(0);i++) {
				for(int j = 0;j<inputData.get(0);j++) {
					adjMatrix[i][j] = inputData.get(matrixFillCounter);
					matrixFillCounter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		

        
        int graph[][] = adjMatrix;
        ShortestPath shortestPathThread = new ShortestPath(); 
        shortestPathThread.dijkstra(graph, start); 
		
		
//		dijkstra(adjMatrix,start,stop);
//		
//		System.out.println(minDistFinal[1][stop]);
		
	}
	
	
	
	/*
	private void dijkstra(int[][] graph, int start, int stop) {
		
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> nonVisited = new ArrayList<Integer>();
		int[][] minDist = new int[2][graph.length];
		
		
		//Filling the unvisited array and distances
		for (int i = 0;i<graph.length;i++) {
			nonVisited.add(i);
			if(i==start) {
				minDist[1][i] = 0; 
			}else {
				minDist[1][i] = Integer.MAX_VALUE;
			}
		}
		
		while(!nonVisited.isEmpty()) {
			//Check the smallest distance in minDist array
			int next = indexOfSmallestNonVisited(minDist[1],nonVisited);
			
			nonVisited.remove(nonVisited.indexOf(next));
			//Examine unvisited neighbors
			for(int j = 0;j<nonVisited.size();j++) {
				//distance from first node
				int fromFirst = minDist[next][nonVisited.get(j)];
				int lastNode = minDist[0][nonVisited.get(j)];
				
				while(minDist[0][lastNode]!=0) {
					fromFirst = fromFirst+graph[next][lastNode];
					lastNode = minDist[0][lastNode];
				}
				
				if(minDist[1][nonVisited.get(j)]>graph[next][nonVisited.get(j)]) {
					if(graph[next][nonVisited.get(j)]!=0) {
						minDist[1][nonVisited.get(j)] = fromFirst; 
						minDist[0][nonVisited.get(j)] = next;
					}
				}
			}
			
			visited.add(next);
			
		}
		
		minDistFinal = minDist;
		
	}
	
	private static int indexOfSmallestNonVisited(int[] array, ArrayList<Integer> nonVisited) {
	    if (array.length == 0)
	        return -1;

	    
	    int index = nonVisited.get(0);
	    int min = array[index];

	    for (int i = 1; i < nonVisited.size();i++){
	        if (array[nonVisited.get(i)] <= min){
	        min = array[nonVisited.get(i)];
	        index = nonVisited.get(i);
	        }
	    }
	    return index;
	}

*/
}
