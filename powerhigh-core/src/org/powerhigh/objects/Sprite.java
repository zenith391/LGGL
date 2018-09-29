package org.powerhigh.objects;

import org.powerhigh.graphics.Animation;
import org.powerhigh.graphics.Drawer;
import org.powerhigh.graphics.Texture;
import org.powerhigh.utils.Color;
import org.powerhigh.graphics.Interface;

public class Sprite extends GameObject {
	
	private Texture texture;
	private Animation animation;
	
	public void setTexture(Texture texture) {
		this.texture = texture;
		if (texture != null) {
			setSize(texture.getWidth(), texture.getHeight());
		}
	}
	
	/**
	 * Note: Animation bypass Texture property
	 * @param anim
	 */
	public void setAnimation(Animation anim) {
		this.animation = anim;
	}
	
	public Animation getAnimation() {
		return animation;
	}

	public Texture getTexture() {
		return texture;
	}

	/** Will render a blue quad until you set an texture with setTexture(...) */
	public Sprite() {
		this( (Texture) null);
	}
	/** Will associate the following Texture object with this Sprite. */
	public Sprite(Texture texture) {
		setTexture(texture);
	}
	/** Will associate the following Animation object with this Sprite. */
	public Sprite(Animation anim) {
		setAnimation(anim);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		animation.dispose();
		animation = null;
	}
	

	@Override
	public void paint(Drawer g, Interface source) {
		if (texture != null) {
			g.drawTexture(x, y, width, height, texture);
		}
		else if (animation != null) {
			if (animation.getCurrentSprite() != null)
				g.drawTexture(x, y, width, height, animation.getCurrentSprite());
		}
		else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
	

}
