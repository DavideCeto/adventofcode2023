import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Day2 {

	public static void main (String... args) throws IOException {
		try {
			List<String> inputFile = readFile();
			List<Object> result = getValidIDsAndPowerValues(inputFile);
			
			Set<Long> validIDs = (Set<Long>) result.get(0); //part one
			List<Long> validPowerValues = (List<Long>) result.get(1); //part two
			
			System.out.println(validIDs.stream().reduce(0L,Long::sum));
			System.out.println(validPowerValues.stream().reduce(0L,Long::sum));

		} catch (Exception ex){
			System.out.println(ex.toString());
		}
	}
	
	private static List<Object> getValidIDsAndPowerValues(List<String> input){
		Set<Long> validIDs = new HashSet<>();
		List<Long> validPowerValues = new ArrayList<>();
		LinkedHashMap<String, Integer> mapColorMaxValues = new LinkedHashMap<>();
		
		LinkedHashMap<String, Integer> values = getValidCubesValues();
		
		for (String line : input) {
			String[] games = line.trim().split(":");
			
			Iterator<String> g = Arrays.asList(games).iterator();
			while (g.hasNext()) {

				initializeMap(mapColorMaxValues);

				Long gID = Long.valueOf(g.next().replaceAll("[^0-9]",""));

				List<String> colors = new ArrayList<>();
				
				String[] setOfCubes = g.next().split(";");
				
				for (String cube : setOfCubes) {
					String[] setOfColors = cube.split(",");
					for (String color : setOfColors) {
						colors.add(color);
					}
				}

				values.forEach((k, v) -> {
					colors.stream().filter(color -> color.replaceAll("[^a-z]","").equals(k)).forEach(color ->{
						int number = Integer.valueOf(color.replaceAll("[^0-9]","")); 
						
						validIDs.add(number <= v ? gID : -gID); //part 1
						
						if (number > mapColorMaxValues.get(k)) mapColorMaxValues.put(k, number); //part 2
					});
				});

				//multiplication and add to list
				validPowerValues.add(mapColorMaxValues.values().stream().mapToLong(Integer::intValue).reduce(1, (a, b) -> a * b)); //part 2
			}
		}
		List<Object> result = new ArrayList<>();
		result.add(validIDs);
		result.add(validPowerValues);

		return result;
	}

	private static List<String> readFile() throws IOException{
		Path p = Paths.get("src/day2.txt");
		return Files.readAllLines(p);
	}
	
	private static LinkedHashMap<String, Integer> getValidCubesValues() {
		//only 12 red cubes, 13 green cubes, and 14 blue cubes
		LinkedHashMap<String, Integer> h = new LinkedHashMap<>();
		h.put("red",12);	
		h.put("green",13);
		h.put("blue",14);

		return h;
	}
	
	private static void initializeMap(LinkedHashMap m) {
		m.clear();
		m.put("red", 0);
		m.put("green", 0);
		m.put("blue", 0);
	}
}
