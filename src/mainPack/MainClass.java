package mainPack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class MainClass {

	public static void main(String[] args) {
		// setup code
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		String entrada[] = null;
		int start = 0, stop = 0;
		Properties prop = new Properties();
		InputStream toRetrieveProperties = null;
		resetProperties();
		
		//Always run code
		while(true) {
			System.out.print("Insert the operation to perform: \n[1]:Insert a new floor \n[2]:Find the shortest route to all rooms \n[3]:Find the shortest path between 2 points \n[4]:See the system status \n[5]:Create a new test graph \n");
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
					BestRouteThread bestToAll = new BestRouteThread();
					Thread bestRouteT = new Thread(bestToAll);
					bestRouteT.start();	
					break;
				case '3':
					System.out.println("Insert the index of starting and stop node separated by a space: \n");
				try {
					String enteredNodes[] = objReader.readLine().split(" ");
					start = Integer.parseInt(enteredNodes[0]);
					stop = Integer.parseInt(enteredNodes[1]);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					Shortest2PointsThread twoPoints = new Shortest2PointsThread(start,stop);
					Thread findTwoPointsPath = new Thread(twoPoints);
					findTwoPointsPath.start();	
					try {
						findTwoPointsPath.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case '4':
					//Establish a properties file
				try {
					toRetrieveProperties = new FileInputStream("sysStat.properties");
					prop.load(toRetrieveProperties);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(prop.getProperty("sysStat"));
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
	
	public static void resetProperties() {
		
		String output = "No processes terminated \n";
	
    	try {
			FileOutputStream setProperty = new FileOutputStream("sysStat.properties");
			FileInputStream getProperty = new FileInputStream("sysStat.properties");
			Properties sysProp = new Properties();
			sysProp.load(getProperty);
			getProperty.close();
			
			sysProp.setProperty("sysStat", output);
			sysProp.store(setProperty, null);
			setProperty.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		
	}
	}

}
