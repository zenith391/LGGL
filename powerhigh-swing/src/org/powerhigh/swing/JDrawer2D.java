package org.powerhigh.swing;

import java.awt.Graphics2D;

import org.powerhigh.graphics.Drawer;
import org.powerhigh.utils.Color;

public class JDrawer2D extends Drawer {

	private Graphics2D g2d;
	
	public JDrawer2D(Graphics2D g2d) {
		this.g2d = g2d;
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		g2d.fillRect(x, y, width, height);
	}

	@Override
	public void setColor(Color color) {
		g2d.setColor(SwingUtils.phColorToAwtColor(color));
	}

	@Override
	public Color getColor() {
		return SwingUtils.awtColorTophColor(g2d.getColor());
	}
	
}