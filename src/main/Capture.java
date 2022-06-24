
package main;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;


public class Capture {

	private Robot robot;
	private Point pos;
	private PointerInfo pf;

	public Capture() {

		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImage(Dimension _dim) {
		BufferedImage img = null;
		if (img == null) 
		pf = MouseInfo.getPointerInfo();
		pos = pf.getLocation();

		img = robot.createScreenCapture(new Rectangle(pos.x - (_dim.width/2), pos.y - (_dim.height/2), _dim.width, _dim.height));
//		img = robot.createScreenCapture(new Rectangle(pos.x - (_dim.width/2), pos.y - (_dim.height/2), pos.x + (_dim.width/2), pos.y + (_dim.height/2)));
		return img;
	}


}
