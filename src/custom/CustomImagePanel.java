package custom;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class CustomImagePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5317433044968159258L;

	private Image image;

	public CustomImagePanel(String imagePath) {
		this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
	}

	public void setImage(String imagePath) {
		this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}
}
