import java.io.*;
import java.nio.file.Files;


public class SplitVideo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File fileIn = new File("new_vid.mp4");
		
		System.out.println(fileIn.length());
		byte[] fileContent = Files.readAllBytes(fileIn.toPath());
		
		File fileOut = new File("vid_segment.mp4");
		FileOutputStream fos = new FileOutputStream(fileOut);
		fos.write(fileContent,fileContent.length/4,(3*fileContent.length/4));
		fos.close();
	}
}
