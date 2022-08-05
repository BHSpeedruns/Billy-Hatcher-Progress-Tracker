package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import data.GameState;

public class EggGallery extends JPanel {
	
	Dashboard parent;
	GameState game;
	
	public EggGallery(Dashboard p, GameState gm) {
		parent = p;
		game = gm;
		initializeFrame();
	}

	private void initializeFrame() {
		JButton levelSelectButton = new JButton();
		levelSelectButton.setText("Level Select");
		levelSelectButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	levelSelectButtonPressed(e);
	            }
        });
		this.add(levelSelectButton);
		GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    //.addComponent(canvas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(levelSelectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
            .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                //.addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(levelSelectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
        );
		
	}
	
	protected void paintComponent(Graphics g) {
		System.out.println("hello");
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		//TODO:Content
		System.out.println("Printing Egg");
		
	}
	protected void levelSelectButtonPressed(ActionEvent e) { parent.setScreen(WindowState.LevelSelect); }
}
