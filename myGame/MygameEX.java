package myGame;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class MygameEX extends JFrame {
	static boolean start;

	public MygameEX(boolean start) throws IOException {
		
		initUI(start);
	}
	
	private void initUI(boolean start) throws IOException {
		
		add(new Board());
		
	
		setSize(1000, 700);
		setResizable(false);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable(){
		
		@Override
		public void run() {
			
			
			
			MygameEX ex;
			try {
				ex = new MygameEX(start);
				ex.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		});

	}

}
