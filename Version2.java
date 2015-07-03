/*How it will work:
 * Will put all words from text file into a tree of strings and for each word, will divide in 2 words
 * Assume that every word in list has minimum length 2
 * For a current word, 2 sub-words will be created by changing the split in current word
 * Will then search for both sub-words in the tree and will return composite current word if both sub-words are found
 * 
 * This method works almost the same way as Version1 but duplicate words do not get added because of the tree data structure
 * This search method is more efficient than searching through an array list because it has O(log(n))
 * To improve this method for larger input, we can use an AVL tree structure so that the tree is balanced and search will be faster.
 */

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;


public class Version2 {

	public static void main(String[] args){

		try {
			Scanner wordlist = new Scanner(new FileInputStream("wordlist.txt"));
			TreeSet<String> searchTree = new TreeSet<String>();

			String current, sub1, sub2;
			int positionLetter = 2;

			while(wordlist.hasNext()){ //While loops adds all words from wordlist file into searchTree
				String temp = wordlist.next().toLowerCase(); 
				searchTree.add(temp);
			}

			Iterator<String> ite = searchTree.iterator(); //Iterator will be used to traverse tree
			current = ite.next();//This is the beginning of the tree. This string will be used as Version1 
								//to be split in 2 and find if it is composite if both sub-words are present in the tree

			while(ite.hasNext()){

				if (current.length() >= 4){ //Assume both sub-words are of 2 characters or more therefore current must be (2+2)
					sub1 = current.substring(0,positionLetter);
					sub2 = current.substring(positionLetter);


					if(searchTree.contains(sub1) && searchTree.contains(sub2)){
						System.out.println(sub1 + " + " + sub2 + " => " + current);
						positionLetter = 2;
						current = ite.next();
					}

					else if (sub2.length() > 2) //Changes the position of the "split" if match not found for present split sub-words
						positionLetter++;		//increase only if valid position in String current (until last word --> sub2, is 2 characters long)

					else{//No sub-words were found in searchTree for current word --> checks for next word in the tree
						if (ite.hasNext())//Prevents NullPointerException when reached last string in tree when setting current 
							current = ite.next();
						positionLetter = 2; //Need to reset "splitter" for new current word
					}

				}

				else{//Current word is too short --> checks for next word in tree
					current = ite.next();
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

