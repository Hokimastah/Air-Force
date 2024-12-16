import java.awt.*;
import java.awt.image.*;

public class Explosion {
	private double x, y;
	private int r, maxRadius;
	private BufferedImage e;

	public Explosion (double x, double y, int r, int max) {
		if (e == null) e = new Generals().loadImg("/img/badass/explosion/explosionBoom.png");

		this.x = x;
		this.y = y;
		this.r = r;
		this.maxRadius = max;
	}

	public boolean update () {
		r += 2;
		if (r >= maxRadius) return true;
		return false;
	}
	public void draw (Graphics2D g) {
		g.drawImage(e, (int) (x - r), (int) (y - r), 75, 75, null);
	}
}