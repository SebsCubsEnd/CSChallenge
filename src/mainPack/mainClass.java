package mainPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class mainClass {

	public static void main(String[] args) {
		// setup code
		
		
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
			
				//Always run code
		while(true) {
			System.out.print("Insert the operation to perform: \n[1]:Insert a new floor \n[2]:Find the shortest route to all rooms \n[3]:Find the shortest path between 2 points \n[4]:See the system status \n[5]:Create a new test graph \n");
			String entrada[] = null;
			try {
				entrada = objReader.readLine().split(",");
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			
			
			//Switch for every case
			switch(entrada[0].charAt(0)) {
				case '1':
					CompressionThread toAdd = new CompressionThread();
					Thread insertFloor = new Thread(toAdd);
					insertFloor.start();	
					try {
						insertFloor.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case '2':
					//Shortest route all rooms
					break;
				case '3':
					Shortest2PointsThread twoPoints = new Shortest2PointsThread();
					Thread findTwoPointsPath = new Thread(twoPoints);
					findTwoPointsPath.start();	
					try {
						findTwoPointsPath.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case '4':
					//Show system info (To check how)
					break;
				case '5':
					//Create a new test graph
					CreateGraphThread toCreate = new CreateGraphThread();
					Thread createGraph = new Thread(toCreate);
					createGraph.start();
				try {
					createGraph.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
				
			
		}

	}

}
