import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import com.xuggle.xuggler.IPixelFormat;

/**
Class for creating a thread to receive video from default webcam and store to a file in
source directory. Works alongside cam_gui.class to write videos to a file in a thread
so multiple instances of video can be taken and put into the Footage object.

Created by: Tommy Margolis, Daylon Blakely, and Thomas Hwang

 */

class TakeVideo extends Thread{
	private Webcam webcam;
	private JLabel imageLabel;
	
	public TakeVideo(Webcam w, JLabel jl) {
		webcam = w;
		imageLabel = jl;		
	}
	
	public void run() {
		//Create a new file in source
		File fileOut = new File("new_vid.mp4");
		IMediaWriter writer = ToolFactory.makeWriter(fileOut.getName());
		
		Dimension size = WebcamResolution.VGA.getSize();
		writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, size.width, size.height);
		
		long start = System.currentTimeMillis();
		
		while(cam_Gui.isRunning) {
			BufferedImage image = ConverterFactory.convertToType(webcam.getImage(), BufferedImage.TYPE_3BYTE_BGR);
			IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);
			imageLabel.setIcon(new ImageIcon(image));
			

			IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
			frame.setQuality(100);

			writer.encodeVideo(0, frame);			
		}
		writer.close();
	}
}