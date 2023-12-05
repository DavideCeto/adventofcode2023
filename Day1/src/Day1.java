import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Day1 {

	public static void main (String... args) throws IOException {
		List<String> inputFile = readFile();
		List<Integer> valuesToSum = replaceValues(inputFile);
		int res = sum(valuesToSum);
		System.out.println(res);
	}
	
	private static List<Integer> replaceValues(List<String> input) {
		LinkedHashMap<String, String> basket = getValuesToReplace();
		Set<String> keys = basket.keySet();

		List<Integer> valuesToSum = new ArrayList<>();
		input.forEach(line ->{
			String s = line;
			for (String key : keys) {
				s = s.replaceAll(key, basket.get(key));
			}
			s = s.substring(0,1) + s.substring(s.length() -1, s.length());
			valuesToSum.add(Integer.valueOf(s));
		});

		return valuesToSum;
	}

	private static int sum(List<Integer> valuesToSum) {
		int res = 0;
		for (Integer v : valuesToSum) {
			res = res + v;
		}
		
		return res;
	}
	
	private static List<String> readFile() throws IOException{
		Path p = Paths.get("src/day1.txt");
		return Files.readAllLines(p);
	}

	private static LinkedHashMap<String, String> getValuesToReplace() {
		LinkedHashMap<String, String> h = new LinkedHashMap<>();
		h.put("one","o1e");	
		h.put("two","t2o");
		h.put("three","t3e");
		h.put("four","f4r");
		h.put("five","f5e");
		h.put("six","s6x");
		h.put("seven","s7n");
		h.put("eight","e8t");
		h.put("nine","n9e");			
		h.put("[^0-9]","");
		
		return h;
	}
}
