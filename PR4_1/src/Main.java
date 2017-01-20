import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	private JFrame frame;
	private JButton button;

	public Main() {
		frame = new JFrame("Yahtzee");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		button = new JButton("roll dices");
		frame.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				RandN[] rn = new RandN[5];

				for (int i = 0; i < 5; ++i) {
					JLabel l = new JLabel();
					rn[i] = new RandN(l);
					frame.add(l);
				}
				Thread[] t = new Thread[5];
				for (int i = 0; i < 5; ++i) {
					t[i] = new Thread(rn[i]);
					t[i].start();
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						;
					}
				}

				JLabel i = new JLabel();

				try {
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					URL po = getClass().getResource("/resource/yathzee.jpg");
					Image img = toolkit.getImage(po);
					img = img.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
					ImageIcon icon = new ImageIcon(img);
					i.setIcon(icon);
				} catch (Exception ex) {
					i.setText("dang it");
				}
				frame.add(i);

			}
		});

		frame.setVisible(true);

	}

	public static void main(String[] args) {
		Main m = new Main();
	}
}

class RandN implements Runnable {
	private JLabel _label;

	public RandN(JLabel label) {
		_label = label;
	}

	public void run() {
		for (int i = 10; i >= 0; i--) {
			Random rand = new Random();
			int n = rand.nextInt(6) + 1;
			_label.setText(String.format("%d", n));
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				;
			}
		}
	}
}