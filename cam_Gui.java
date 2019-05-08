import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cam_Gui extends JFrame {
	
	private JPanel contentPane;
	Webcam webcam;
	JLabel imageLabel;
	public static boolean isRunning;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cam_Gui frame = new cam_Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public cam_Gui() {
		//set dimensions of JFrame content
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		imageLabel = new JLabel("");
		imageLabel.setBounds(0, 0, 640, 480);
		contentPane.add(imageLabel);
		
		isRunning=false;
		//create capture button
		JButton btnCapture = new JButton("Capture");
		//when capture button is pressed change value of isRunning
		btnCapture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isRunning) {
					isRunning=true;
					//when is running== true start a new TakeVideo thread to display and save webcam footage
					new Thread(new TakeVideo(webcam, imageLabel)).start();
				}
				else {
					isRunning=false;
				}
			}
		});
		btnCapture.setBounds(269, 481, 97, 25);
		contentPane.add(btnCapture);
		//use default web cam
		webcam = Webcam.getDefault();
		//set size of the webacam image
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.open();
	}

}

