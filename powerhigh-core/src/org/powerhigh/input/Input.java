package org.powerhigh.input;

import java.util.HashMap;
import java.util.Map;

import org.powerhigh.graphics.Interface;

public class Input {

	private Interface win;
	private static AbstractKeyboard key;
	private static Mouse mouse;
	
	public static void setKeyboardImpl(AbstractKeyboard keyboard) {
		Input.key = keyboard;
	}
	
	public static void setMouseImpl(Mouse mouse) {
		Input.mouse = mouse;
	}
	
	private int lastUserX, lastUserY;
	
	/**
	 * The last X position the user input device was located at.
	 * @return last X position
	 */
	public int getLastUserX() {
		return lastUserX;
	}

	/**
	 * The last Y position the user input device was located at.
	 * @return last Y position
	 */
	public int getLastUserY() {
		return lastUserY;
	}

	private boolean useDelta = true;
	
	private Map<Integer, Float> keysAxisHorizontal = null;
	private Map<Integer, Float> keysAxisVertical = null;
	
	public Input(Interface win) {
		this.win = win;
		keysAxisHorizontal = new HashMap<>();
		keysAxisVertical = new HashMap<>();
		keysAxisHorizontal.put(KeyCodes.KEY_Q, -1.0f);
		keysAxisHorizontal.put(KeyCodes.KEY_A, -1.0f);
		keysAxisHorizontal.put(KeyCodes.KEY_D, 1.0f);
		keysAxisVertical.put(KeyCodes.KEY_Z, -1.0f);
		keysAxisVertical.put(KeyCodes.KEY_W, -1.0f);
		keysAxisVertical.put(KeyCodes.KEY_S, 1.0f);
	}
	
	public AbstractKeyboard getKeyboard() {
		return key;
	}
	
	/**
	 * Set whether the axis value should vary with delta.
	 * Avoids slowering down when FPS lower and fastening when FPS increases.
	 * @param vary
	 */
	public void setVaryWithDelta(boolean vary) {
		useDelta = vary;
	}
	
	/**
	 * Get the mouse if available, otherwise returns <code>null</code>.
	 * @return
	 */
	public Mouse getMouse() {
		return mouse;
	}
	
	/**
	 * Remove all trigger keys for all axis.
	 */
	public void removeAllAxisTriggers() {
		keysAxisHorizontal.clear();
		keysAxisVertical.clear();
	}
	
	/**
	 * Register trigger key for an axis.
	 * @param axis
	 * @param keyCode
	 * @param value
	 */
	public void registerAxisTrigger(String axis, int keyCode, float value) {
		if (axis.equals("Horizontal")) {
			keysAxisHorizontal.put(keyCode, value);
		}
		if (axis.equals("Vertical")) {
			keysAxisVertical.put(keyCode, value);
		}
	}
	
	public float getAxis(String axis) {
		float ax = 0.0f;
		if (axis.equals("Horizontal")) {
			for (int i : keysAxisHorizontal.keySet()) {
				if (key.isKeyDown(i)) {
					ax = keysAxisHorizontal.get(i);
				}
			}
		}
		if (axis.equals("Vertical")) {
			for (int i : keysAxisVertical.keySet()) {
				if (key.isKeyDown(i)) {
					ax = keysAxisVertical.get(i);
				}
			}
		}
		if (useDelta) {
			ax *= win.getEventThread().getDelta();
		}
		return ax;
	}

}
