import javax.swing.JFrame;

public class AirForce {
	public static void main(String[] args) {
		JFrame container = new JFrame("Air Space");

		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setContentPane(new SpacePanel());

		//		Hides the border in our game
		container.setUndecorated(true);

		container.setResizable(false);
		container.pack();
		container.setLocationRelativeTo(null);
		container.setVisible(true);
	}
}