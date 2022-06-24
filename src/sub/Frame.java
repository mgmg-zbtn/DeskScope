
package sub;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class Frame extends JFrame implements WindowListener , ComponentListener , MouseWheelListener {

	private Panel panel;
	
	public Frame() {
		
		panel = new Panel();
		getContentPane().add(panel);
		
		addWindowListener(this);
		addComponentListener(this);
		addMouseWheelListener(this);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		
		pack();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new Frame();
	}


	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}


	@Override
	public void componentResized(ComponentEvent e) {
		panel.set(e.getComponent().getSize());
	}
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int n = e.getWheelRotation();
		if (n == 1) {
			panel.add();
		} else {
			panel.sub();
		}
		panel.update();
	}

}
