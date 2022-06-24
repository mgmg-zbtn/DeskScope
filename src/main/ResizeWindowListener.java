package main;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class ResizeWindowListener extends MouseAdapter {

	private Rectangle startSide = null;
	private final Frame frame;
	private JLabel[] label;
	
	public ResizeWindowListener(Frame _frame) {
		frame = _frame;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		startSide = frame.getBounds();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (label == null) label = frame.label;
		if (startSide == null) return;
		
		Component c = e.getComponent();
		if (c == label[0]) {
			startSide.x += e.getX();
			startSide.width -= e.getX();
		} else if (c == label[1]) {
			startSide.width += e.getX();
        } else if (c == label[2]) {
            startSide.y += e.getY();
            startSide.height -= e.getY();
        } else if (c == label[3]) {
            startSide.height += e.getY();
        } else if (c == label[4]) {
        	startSide.y += e.getY();
            startSide.height -= e.getY();
            startSide.x += e.getX();
            startSide.width -= e.getX();
        } else if (c == label[5]) {
            startSide.y += e.getY();
            startSide.height -= e.getY();
            startSide.width += e.getX();
        } else if (c == label[6]) {
            startSide.height += e.getY();
            startSide.x += e.getX();
            startSide.width -= e.getX();
        } else if (c == label[7]) {
            startSide.height += e.getY();
            startSide.width += e.getX();
        }
		frame.setBounds(startSide);

	}

}


