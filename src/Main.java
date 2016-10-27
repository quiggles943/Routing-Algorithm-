
public class Main {
	
	static LoadCities cityLoader;
	static String fileLocation;
	
	public static void main(String[] args) {
		fileLocation = "D:/a280.tsp";
		cityLoader = new LoadCities(fileLocation);

	}

}
