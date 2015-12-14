package essential;

import input.InputFlag;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import render.RendableHolder;

@SuppressWarnings("serial")
public class GameScreen extends JComponent {
	
	public static int FRAMERATE = 60;
	
	public static int WIDTH;
	public static int HEIGHT;
	
	public GameScreen() {
		initializeInputListener();
	}
	
	private void initializeInputListener() {
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					InputFlag.set(InputFlag.MOUSE_LEFT, false);
				} else if(SwingUtilities.isRightMouseButton(e)) {
					InputFlag.set(InputFlag.MOUSE_RIGHT, false);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					InputFlag.set(InputFlag.MOUSE_LEFT, true);
				} else if(SwingUtilities.isRightMouseButton(e)) {
					InputFlag.set(InputFlag.MOUSE_RIGHT, true);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					InputFlag.set(InputFlag.MOUSE_LEFT_CLICK, true);
				} else if(SwingUtilities.isRightMouseButton(e)) {
					InputFlag.set(InputFlag.MOUSE_RIGHT_CLICK, true);
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				InputFlag.setMousePosition(e.getX(), e.getY());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				InputFlag.set(InputFlag.KEYBOARD, e.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				InputFlag.set(InputFlag.KEYBOARD, e.getKeyCode(), true);
			}
		});
	}
	
	public void update() {
		GameState.getInstance().update();
	}
	
	public void updateScreenSize() {
		WIDTH = this.getWidth();
		HEIGHT = this.getHeight();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		RendableHolder.getInstance().draw(g2d);
	}
}
