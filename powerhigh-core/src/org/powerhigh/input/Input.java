package org.powerhigh.input;

import java.util.HashMap;
import java.util.Map;

import org.powerhigh.graphics.Interface;

public class Input {

	private Interface win;
	private Keyboard key;
	private Mouse mouse;
	
	private boolean useDelta = true;
	
	private Map<Integer, Float> keysAxisHorizental = null;
	private Map<Integer, Float> keysAxisVertical = null;
	
	public Input(Interface win) {
		this.win = win;
		key = win.getKeyboard();
		mouse = win.getMouse();
		keysAxisHorizental = new HashMap<>();
		keysAxisVertical = new HashMap<>();
		keysAxisHorizental.put(KeyCodes.KEY_Q, -1.0f);
		keysAxisHorizental.put(KeyCodes.KEY_D, 1.0f);
		keysAxisVertical.put(KeyCodes.KEY_Z, -1.0f);
		keysAxisVertical.put(KeyCodes.KEY_S, 1.0f);
	}
	
	public float getAxis(String axis) {
		float ax = 0.0f;
		if (axis.equals("hor")) {
			for (int i : keysAxisHorizental.keySet()) {
				if (key.isKeyDown(i)) {
					ax = keysAxisHorizental.get((Integer) i);
				}
			}
		}
		if (axis.equals("ver")) {
			for (int i : keysAxisVertical.keySet()) {
				if (key.isKeyDown(i)) {
					ax = keysAxisVertical.get((Integer) i);
				}
			}
		}
		if (useDelta) {
			ax *= win.getEventThread().getDelta();
		}
		return ax;
	}

}