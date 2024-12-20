import java.awt.*;
import java.awt.image.*;


public class Player {
	private boolean left, right, up, down, firing, recovering;
	private BufferedImage imageUp, image, imageUpTrans;

	private int requiredPower[] = {1, 2, 3, 4, 5, 6, 7, 8};
	private int x, y, r, dx, dy, speed, lives, score, powerLevel, power;
	private long firingTimer, firingDelay, recoveryTimer;
	private SoundEffect shootSound;

	public Player () {
		x = SpacePanel.width / 2;
		y = SpacePanel.height - 100;
		r = 25;

		dx = 0;
		dy = 0;
		speed = 5;
		lives = 3;


		firing = false;
		firingTimer = System.nanoTime();
		firingDelay = 200;

		recovering = false;
		recoveryTimer = System.nanoTime();

		score = 0;

		shootSound = new SoundEffect("audio/gun-shot.wav");

		if (imageUp == null) imageUp = new Generals().loadImg("/img/hero/hero-up.png");

		if (imageUpTrans == null) imageUpTrans = new Generals().loadImg("/img/hero/hero-up-transp.png");
		image = imageUp;
	}

	//		Render methods
	public void update () {
		if (left) dx = -speed;
		if (right) dx = speed;
		if (up) dy = -speed;  
		if (down) dy = speed;

		x += dx;
		y += dy;

		//		setting the frame limit
		if (x < r) x = r;
		if (y < r) y = r;
		if (x > SpacePanel.width - r) x = SpacePanel.width - r;
		if (y > SpacePanel.height - r) y = SpacePanel.height - r;

		dx = 0;
		dy = 0;

		if (firing) {
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if (elapsed > firingDelay) {
				firingTimer = System.nanoTime();
				if (powerLevel < 2) {
					SpacePanel.bullets.add(new Bullet(270, (x + 10), y));
					shootSound.play();}
				else
				if (powerLevel < 3) {
					SpacePanel.bullets.add(new Bullet(270, (x + 10) + 5, y));
					SpacePanel.bullets.add(new Bullet(270, (x + 10) - 5, y));
					shootSound.play();
				} else
				if (powerLevel < 4) {
					SpacePanel.bullets.add(new Bullet(265, (x + 10) - 5, y));
					SpacePanel.bullets.add(new Bullet(270, (x + 10), y));
					SpacePanel.bullets.add(new Bullet(277, (x + 10) + 5, y));
					shootSound.play();
				} else {
					SpacePanel.bullets.add(new Bullet(264, (x + 10) - 3, y));
					SpacePanel.bullets.add(new Bullet(267, (x + 10) - 3, y));
					SpacePanel.bullets.add(new Bullet(270, (x + 10), y));
					SpacePanel.bullets.add(new Bullet(273, (x + 10) + 3, y));
					SpacePanel.bullets.add(new Bullet(276, (x + 10) + 3, y));
					shootSound.play();
				}
			}
		}

		//		Time, i am invencible (2 s)
		if (recovering) {
			long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
			if (elapsed > 2000) {
				recovering = false;
				recoveryTimer = 0;
			}
		}
	}
	public void draw (Graphics2D g) {
		if (recovering) 
			image = imageUpTrans;
		else 
			image = imageUp;

		g.drawImage(image, x - r, y - r, null);
	}

	//		Setters
	public void setLife (int life) {lives = life;}
	public void setLeft (boolean direction) {left = direction;}
	public void setRigth (boolean direction) {right = direction;}
	public void setUp (boolean direction) {up = direction;}
	public void setDown (boolean direction) {down = direction;}
	public void setFiring (boolean fire) {firing = fire;}
	public void setScore (int sc) {score += sc;}
	//		Getters
	public boolean isDead () {return lives <= 0;}
	public boolean isRecovering () {return recovering;}
	public int getLives () {return lives;}
	public int getPower () {return power;}
	public int getPowerLevel () {return powerLevel;}
	public int getR () {return r;}
	public int getRequiredPower () {return requiredPower[powerLevel];}
	public int getScore () {return score;}
	public int getX () {return x;}
	public int getY () {return y;}

	//		General methods
	public void loseLife () {
		lives--;
		recovering = true;
		recoveryTimer = System.nanoTime();
	}
	public void increasePower (int pwr) {
		if (power < 6) {
			power += pwr;
			if (power >= requiredPower[powerLevel]) {
				power -= requiredPower[powerLevel];
				powerLevel++;
			}
		}
	}
}