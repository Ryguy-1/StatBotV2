import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.login.LoginException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.dv8tion.jda.api.entities.Activity;

//hidden class in public version
public class Runner implements ActionListener {

	// GUI elements
	public static JFrame frame;
	public static JPanel panel;
	public static JButton b1;
	public static JButton updateFromJSON;

	private final String discordAPIKey = "Nzc5MTg1MTM3OTcxNDk0OTMy.X7c2tQ.UXkX1wWybGeoEK5JOMX3Pqgu7y4";

	public static void main(String[] args) throws LoginException {
		GeneralInputManager discordJDA = new GeneralInputManager();
		Runner r = new Runner();
		r.setup();
	}

	public String getDiscordAPIKey() {
		return this.discordAPIKey;
	}

	// creates GUI
	private void setup() {
		frame = new JFrame();
		panel = new JPanel();
		b1 = new JButton("Quit Recon");
		updateFromJSON = new JButton("Update Values from JSON");
		b1.addActionListener(this);
		updateFromJSON.addActionListener(this);
		frame.add(panel);
		panel.add(b1);
		panel.add(updateFromJSON);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// exit program
		if (e.getSource() == b1) {
			System.exit(0);
		}else if(e.getSource() == updateFromJSON) {
			//Updates the User ArrayList from the JSON
			GeneralInputManager.readWrite.readAndSet();
		}

	}

}
