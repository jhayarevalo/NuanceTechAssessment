/*How it will work:
 * Will put all words from text file into an array and for each word, will divide in 2 words
 * Assume that every word in list has minimum length 2
 * For a current word, 2 sub-words will be created by changing the split in current word
 * Will then search for both sub-words in array and will return composite current word if both sub-words are found
 * 
 * Although it is easy to put all words into an array ( O(1) ), searching through the ArrayList of strings to match 
 * another string has efficiency O(n) which is inefficient for very large input
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;

public class Version1 {

	public static void main(String[] args){

		try {

			Scanner wordlist = new Scanner (new FileInputStream ("wordlist.txt"));
			ArrayList<String> list = new ArrayList<String>();

			String current, sub1, sub2;//current will be divided in 2 sub-words: sub1 and sub2
			int i = 0; //Index for ArrayList list where the current word will be achieved. Starts at index 0
			int positionLetter = 2; //This will be the "splitter" for current word which makes sub-words 1 and 2

			while (wordlist.hasNext()){//Creates list
				list.add(wordlist.next().toLowerCase()); //To simplify comparing strings, make everything lowercase --> will ignore the words' case when matching
			}

			System.out.println(list.size());
			while(i < list.size()){

				current = list.get(i);

				if (current.length() >= 4){ //Assume both sub-words are of 2 characters or more therefore current must be (2+2)
					sub1 = current.substring(0, positionLetter);
					sub2 = current.substring(positionLetter);

					if(list.contains(sub1) && list.contains(sub2)) {//Search if ArrayList list has both subwords in in
						System.out.println(sub1 + " + " + sub2 + " => " + current);
						positionLetter = 2;
						i++;
					}
					
					else if (sub2.length() > 2) //Changes the position of the "split" if match not found for present split sub-words, will only 
						positionLetter++;		//increase only if valid position in String current (until last word --> sub2, is 2 characters long)
					
					else{//No sub-words were found in list for current word --> checks for next word in list
						i++;
						positionLetter = 2; //Need to reset "splitter" for new current word
					}
				}
				
				else{//Current word is too short --> checks for next word in list
					i++;
				}
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
