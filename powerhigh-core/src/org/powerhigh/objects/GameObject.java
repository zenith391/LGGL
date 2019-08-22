package org.powerhigh.objects;

import org.powerhigh.utils.Area;
import org.powerhigh.utils.Color;

import org.powerhigh.Material;
import org.powerhigh.graphics.Drawer;
import org.powerhigh.graphics.Interface;

public abstract class GameObject {

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	protected int x;
	protected int y;
	protected int rotation;
	protected int width = 15;
	protected int height = 15;
	private String name = Integer.toHexString(hashCode());
	private boolean visible = true;
	protected Area hitbox = new Area(x, y, width, height);
	protected Color color = Color.BLACK;

	protected Material material = new Material(); // default

	public Material getMaterial() {
		return material;
	}

	/**
	 * Disposing a GameObject will mostly set it's variables to null so they can be cleaned (this GameObject) via Garbage Collector<br/>
	 * <b>Note:</b> For performance reasons, this don't call System.gc(), It must be manually did
	 */
	public void dispose() {
		x = y = width = height = rotation = 0;
		color = null;
		material = null;
		hitbox = null;
		name = null;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public abstract void paint(Drawer drawer, Interface source);

	public void onEvent(String type, Object... args) {

	}

	public GameObject() {
	}

	public int getX() {
		return x;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation % 360;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[x=" + x + ", y=" + y + ", width=" + width
				+ ", height=" + height + "]";
	}

	public void centerTo(Interface window) {
		x = window.getViewport().getWidth() / 2 - (width / 2);
		y = window.getViewport().getHeight() / 2 - (height / 2);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setX(int nx) {
		x = nx;
	}

	public Area getHitbox() {
		hitbox.setBounds(x, y, width, height);
		return hitbox;
	}

	public void setY(int ny) {
		y = ny;
	}

	public boolean isColliding(GameObject g) {
		return (g.x >= x && g.x < x + width) && (g.y >= y && g.y < y + height);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setSize(int w, int h) {
		setWidth(w);
		setHeight(h);
	}

	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setPosition(Area dim) {
		setPosition(Math.max(dim.getX(), dim.getWidth()), Math.max(dim.getY(), dim.getHeight()));
	}

	public void setSize(Area dim) {
		setSize(dim.getWidth(), dim.getHeight());
	}

	public Area getSize() {
		return new Area(width, height);
	}
}
