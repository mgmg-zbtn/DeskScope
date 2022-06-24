package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;


public class Frame extends JFrame implements ComponentListener, MouseWheelListener, MouseListener , MouseMotionListener{

	public JLabel[] label;
	private Panel panel;
	private ResizePanel resizePanel;
	private JPanel contentPanel;
	private JPopupMenu pop;
	private Point start;
	
	public Frame() {
		panel = new Panel();
		resizePanel = new ResizePanel();
		contentPanel = new JPanel(new BorderLayout());
		start = new Point(0, 0);
		
		getContentPane().add(panel);
		addComponentListener(this);
		addMouseWheelListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

        ResizeWindowListener rwl = new ResizeWindowListener(this);
        label = new JLabel[8];
        for(int i = 0; i < 8; i++) {
        	label[i] = new JLabel();
            label[i].addMouseListener(rwl);
            label[i].addMouseMotionListener(rwl);
        }

        Dimension d = new Dimension(4, 0);
        label[0].setPreferredSize(d);
        label[0].setMinimumSize(d);
        
        label[1].setPreferredSize(d);
        label[1].setMinimumSize(d);

        d = new Dimension(0, 4);
        label[2].setPreferredSize(d);
        label[2].setMinimumSize(d);

        label[3].setPreferredSize(d);
        label[3].setMinimumSize(d);

        d = new Dimension(4, 4);
        label[4].setPreferredSize(d);
        label[4].setMinimumSize(d);

        label[5].setPreferredSize(d);
        label[5].setMinimumSize(d);

        label[6].setPreferredSize(d);
        label[6].setMinimumSize(d);

        label[7].setPreferredSize(d);
        label[7].setMinimumSize(d);
        
        label[0].setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        label[1].setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        label[2].setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        label[3].setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        label[4].setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        label[5].setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        label[6].setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        label[7].setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        
        JPanel northPanel = new JPanel(new BorderLayout(0,0));
        northPanel.add(label[4], BorderLayout.WEST);
        northPanel.add(label[2], BorderLayout.CENTER);
        northPanel.add(label[5], BorderLayout.EAST);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(label[6], BorderLayout.WEST);
        southPanel.add(label[3], BorderLayout.CENTER);
        southPanel.add(label[7], BorderLayout.EAST);

        resizePanel.add(label[0], BorderLayout.WEST);
        resizePanel.add(label[1], BorderLayout.EAST);
        resizePanel.add(northPanel, BorderLayout.NORTH);
        resizePanel.add(southPanel, BorderLayout.SOUTH);
        resizePanel.add(contentPanel, BorderLayout.CENTER);

        northPanel.setOpaque(false);
        southPanel.setOpaque(false);

        contentPanel.setOpaque(false);
        resizePanel.setOpaque(false);
        setContentPane(resizePanel);
 		
//        this.setPopupMenu();
		setAlwaysOnTop(true);
		setMinimumSize(new Dimension(200, 200));
		setUndecorated(true);
		setVisible(true);
		pack();
	}
	
	private void setPopupMenu() {
		pop = new JPopupMenu();

		JMenuItem exitItem = new JMenuItem();
		exitItem.setText("Exit");
		exitItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		pop.add(exitItem);
	}
	
	@Override
	public Container getContentPane() {
		return contentPanel;
	}

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

	@Override
	public void mouseClicked(MouseEvent e) {
		Component c = e.getComponent();
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			pop.setVisible(false);
			break;
		case MouseEvent.BUTTON3:
			pop.show(c, e.getX(), e.getY());
			break;
		default:
			pop.setVisible(false);
			break;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		Component c = e.getComponent();
		Point pos = e.getLocationOnScreen();
		c.setLocation((int)(pos.x - start.getX()), (int)(pos.y - start.getY()));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		start = e.getPoint();
	}


}
