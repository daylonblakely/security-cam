import java.util.*;
import java.io.File;

/**
Footage object used for handling the saving of video data. Stores an array of files in a linked list.
 Methods include functionality for insertion of entire file arrays, insertion of individual file elements
 to specified file arrays, retrieval of segments and stories (file arrays) requested.

Created by: Tommy Margolis, Daylon Blakely, and Thomas Hwang

 * */
public class Footage {
	//Local split video container of files
	private LinkedList<File[]> splitVids = new LinkedList<File[]>();
	
	//Constructor initialized with file array
	public Footage(File []day) {
		splitVids.add(day);
	}
	
	//Constructor with no initialization
	public Footage() {
		File[] day = new File[23];
		splitVids.add(day);
	}
	
	//Insert an entire file array(story) given via parameter
	void insert_story(File [] story) {
		splitVids.add(story);
	}
	
	//Insert a single segment of video into a file array at the next available position
	void insert_segment(File segment, int index) {
		File[] selectedStory = splitVids.get(index);
		for (int i = 0; i < selectedStory.length; i++) {
			if(selectedStory[i]==null) 
				selectedStory[i] = segment;
			else if(selectedStory[i]!=null && selectedStory.length == i-1)
				System.err.print("Selected story is full");
		}
		splitVids.remove(index);
		splitVids.add(selectedStory);
	}
	
	//Return the segment of video in the story at parameters 
	File get_segment(int story, int index) {
		return splitVids.get(story)[index];
	}
	//Return the story (file array) at parameter requested 
	File get_segment(int story) {
		return splitVids.get(story);
	}
}
