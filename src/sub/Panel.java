
package sub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Panel extends JPanel implements Runnable {

	private Capture cap;
	private Dimension dim;
	private double mag;
	private double index;

	private BufferedImage image;
	private Image dbImage;
	private Graphics2D dbg;
	private AffineTransform at;
	
	private Thread thread;
	
	public Panel() {
		
		cap = new Capture();
		dim  = new Dimension(500, 500);
		mag = 1;
		index = 1;
		at = new AffineTransform();

		thread = new Thread(this);
		thread.start();
		
		setPreferredSize(dim);
	}
	
	public void set(Dimension _dim) {dim = _dim;}
	public void add() {if (64 <= mag) return; index = ((index * 10) + 2) / 10;}
	public void sub() {if (mag <= 1) return; index = ((index * 10) - 2) / 10;}

	@Override
	public void run() {
		while (true) {
			update();
			render();
			buffer();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void update() {
		setPreferredSize(dim);
		image = cap.getImage(new Dimension((int)(dim.width/mag), (int)(dim.height/mag)));

		mag = Math.pow(2, index);
		mag = Math.floor(mag);
		at.setToIdentity();
		at.scale(mag, mag);
	}
	
	private void render() {
        if (dbImage == null) {
            dbImage = createImage(dim.width, dim.height);
            if (dbImage == null) {
                return;
            } else {
            	dbg = (Graphics2D)dbImage.getGraphics();
            }
        }
        dbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dbg.setTransform(at);
        
        dbg.drawImage(image, 0, 0, this);

        // center line
        dbg.setTransform(new AffineTransform());
        dbg.setColor(Color.BLUE);
        dbg.drawLine(dim.width/2, 0, dim.width/2, dim.height);
        dbg.drawLine(0, dim.height/2, dim.width, dim.height/2);
	}
	
	private void buffer() {
        try{
            Graphics g = getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();
            if (g != null) g.dispose();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // for redraw
        dbImage = null;
	}
	
}
