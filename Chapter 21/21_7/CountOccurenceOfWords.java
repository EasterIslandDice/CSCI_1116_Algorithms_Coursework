import java.util.*;

public class CountOccurenceOfWords {
	public static void main(String[] args) {
		// Set text in a string
		String text = "Good morning. Have a good class. " +
			"Have a good visit. Have fun!";

		// Create a TreeMap to hold words as key and count as value
		Map<String, Integer> map = new HashMap<>();
		
	
		String[] words = text.split("[ \n\t\r.,;:!?()]");
		for (String word: words) {
			String key = word.toLowerCase();
			
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		
		// Create an ArrayList
		ArrayList<WordOccurrence> list = new ArrayList<>();
		
		// Store the key and value from each entry in the list
		for (Map.Entry<String, Integer> entry: map.entrySet())
			list.add(new WordOccurrence(entry.getKey(), entry.getValue()));
		
		// Sort the list
		Collections.sort(list);
		
		// Display the sorted list
		System.out.println(list);
	}
}