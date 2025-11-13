/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent", "listen")); // true
		System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie", "Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));

		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

		// Performs a stress test of randomAnagram
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass)
				break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// char str1Arr[] = str1.toCharArray();
		String clStr1 = preProcess(str1);
		String clStr2 = preProcess(str2);
		if (clStr1.length() != clStr2.length()) {
			return false;
		}
		// char str2Arr[] = str2.toCharArray();
		for (int i = 0; i < clStr1.length(); i++) {
			char current = clStr1.charAt(i);
			int index = clStr2.indexOf(current);
			if (index == -1) {
				return false;
			} else {
				clStr2 = clStr2.substring(0, index) + clStr2.substring(index + 1);
			}

		}
		return true;
	}

	// Returns a preprocessed version of the given string: all the letter characters
	// are converted
	// to lower-case, and all the other characters are deleted, except for spaces,
	// which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String lowerStr = str.toLowerCase();
		String letters = "abcdefghijklmnopqrstuvwxyz";
		String cleanStr = "";
		for (int i = 0; i < lowerStr.length(); i++) {
			char current = lowerStr.charAt(i);
			if (letters.indexOf(current) != -1) {
				cleanStr += current;
			}
		}
		return cleanStr;
	}

	// Returns a random anagram of the given string. The random anagram consists of
	// the same
	// characters as the given string, re-arranged in a random order.
	public static String randomAnagram(String str) {
		String clStr = str;
		String newStr = "";
		int strLength = clStr.length();
		for (int i = 0; i < strLength; i++) {
			int rnd = (int) (Math.random() * clStr.length());
			newStr += clStr.charAt(rnd);
			clStr = clStr.substring(0, rnd) + clStr.substring(rnd + 1);
			
		}

		return newStr;
	}
}
