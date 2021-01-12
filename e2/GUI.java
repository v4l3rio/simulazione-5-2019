package e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private List<JButton> list = new ArrayList<>();
	private Map<Pair<Integer, Integer>, JButton> cords = new HashMap<Pair<Integer, Integer>, JButton>();

	public GUI(int size) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(100 * size, 100 * size);
		this.setVisible(true);

		JPanel panel = new JPanel(new GridLayout(size, size));
		this.getContentPane().add(BorderLayout.CENTER, panel);
		ActionListener al = (e) -> {
			final JButton bt = (JButton) e.getSource();
			Set<Pair<Integer,Integer>> allKey = cords.keySet();
			Iterator<Pair<Integer, Integer>> iter = allKey.iterator();
			while(iter.hasNext()) {
				Pair<Integer, Integer> pairToVerify = iter.next();
				if(cords.get(pairToVerify).equals(bt)) {
					removeX(pairToVerify);
				}
			}
			verifyMoreX(size);
		};
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				final JButton jb = new JButton("");
				jb.addActionListener(al);
				cords.put(new Pair<Integer, Integer>(i, j), jb);
				panel.add(jb);
			}
		}
		putTenRandomX();
		this.setVisible(true);
	}
	
	private void putTenRandomX() {
		
		Random rnd = new Random();
		Pair<Integer, Integer> pairToX;
		for(int i=0;i<10;i++) {
			pairToX=new Pair<Integer, Integer>(rnd.nextInt(7), rnd.nextInt(7));
			if(cords.get(pairToX).getText().equals("*")) {
				i--;
			}
			else {
				cords.get(pairToX).setText("*");
			}
			
		}
	}
	
	private void verifyMoreX(int size) {
		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {
				if(cords.get(new Pair<Integer, Integer>(i, j)).getText().equals("*")) {
					return;
				}
			}
		}
		
		System.exit(1);
	}
	
	
	private void removeX(Pair<Integer, Integer> pair) {
		if(!cords.containsKey(pair)) {
			return;
		}
		if(!cords.get(pair).getText().equals("*")) {
			return;
		}
		
		
		cords.get(pair).setText("");
		
		
		for(int i = 0;i<8;i++) {
			removeX(new Pair<Integer, Integer>(pair.getX()-1, pair.getY()));
			removeX(new Pair<Integer, Integer>(pair.getX()-1, pair.getY()-1));
			removeX(new Pair<Integer, Integer>(pair.getX(), pair.getY()-1));
			removeX(new Pair<Integer, Integer>(pair.getX()+1, pair.getY()-1));
			removeX(new Pair<Integer, Integer>(pair.getX()+1, pair.getY()));
			removeX(new Pair<Integer, Integer>(pair.getX()+1, pair.getY()+1));
			removeX(new Pair<Integer, Integer>(pair.getX(), pair.getY()+1));
			removeX(new Pair<Integer, Integer>(pair.getX()-1, pair.getY()+1));
		}
		
	}

}
