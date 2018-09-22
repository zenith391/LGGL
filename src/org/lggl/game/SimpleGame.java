package org.lggl.game;

import java.awt.Component;
import java.awt.Toolkit;

import org.lggl.Camera;
import org.lggl.audio.Audio;
import org.lggl.graphics.ErrorBox;
import org.lggl.graphics.Window;
import org.lggl.input.Input;
import org.lggl.utils.LGGLException;
import org.lggl.utils.debug.DebugLogger;

public abstract class SimpleGame {

	protected Window window = null;
	protected Audio audio = null;
	protected Camera camera = null;
	protected Input input = null;
	
	public static boolean enableLaunchDebug = true;

	public abstract void update(Window win, double delta);

	public abstract void init(Window win);
	
	public void exit(Window win) {}

	private void dbg() {
		String name = System.getProperty("os.name");
		String ver = System.getProperty("os.version");
		DebugLogger.logInfo("Operating System is " + name + "/" + ver);
	}
	
	/**
	 * Core init of game, if overriding this method, add <code>super.coreInit()</code> unless you know what you're doing !
	 * <p><b>Note:</b> This method is called before init, it is like a pre-init</p>
	 */
	protected void coreInit() {
		window = new Window();
		try {
			audio = new Audio();
			if (enableLaunchDebug)
				DebugLogger.logInfo("Audio ready !");
		} catch (LGGLException e) {
			System.err.println("Error when creating Audio system! Aborting.");
			e.printStackTrace();
			System.exit(1);
		}
		input = new Input(window);
		camera = new Camera(0, 0);
	}
	
	public Audio getAudio() {
		return audio;
	}

	protected boolean launched, a1;
	
	public void start() {
		try {
			
			if (enableLaunchDebug) {
				DebugLogger.logInfo("Preparing game..");
				dbg();
			}
			
			coreInit();
			init(window);
			
			window.getEventThread().addUpdateListener(new Runnable() {
				public void run() {
					try {
						update(window, window.getEventThread().getDelta());
					} catch (Throwable t) {
						Component parent = window.getJFrame().getContentPane();
						if (!window.isVisible()) {
							parent = null;
						}
						Toolkit.getDefaultToolkit().beep();
						t.printStackTrace();
						ErrorBox.create()
							.throwable(t)
								.show(parent);
						System.exit(1);
					}
				}
			});
			window.show();
			
			while (true) {
				if (window.isClosed() && a1 == false) {
					exit(window);
					a1 = true;
				}
				Thread.sleep(1000 / 60);
				if (!launched) {
					if (enableLaunchDebug) {
						DebugLogger.logInfo("Game launched");
					}
					launched = true;
				}
			}
		} catch (Throwable t) {
			Component parent = window.getJFrame().getContentPane();
			if (!window.isVisible()) {
				parent = null;
			}
			Toolkit.getDefaultToolkit().beep();
			t.printStackTrace();
			ErrorBox.create()
				.throwable(t)
					.show(parent);
			System.exit(1);
		}
	}
}