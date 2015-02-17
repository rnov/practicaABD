package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	public ImagePanel(String path){
		try{
			this.image= ImageIO.read(new File(path));
		}catch(IOException ex){
			
		}
	}
	protected void paintComponet(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.image, 0,0,null);
	}
}
