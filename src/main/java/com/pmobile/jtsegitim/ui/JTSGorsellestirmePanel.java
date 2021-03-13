package com.pmobile.jtsegitim.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JTSGorsellestirmePanel extends JPanel {

	private List<DrawingCommand> drawPathCommand = new ArrayList<>();
	
	public JTSGorsellestirmePanel(){
		setSize(450, 450);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
 
		g.fillRect(10, 10, 4, 4);
	 
		g.drawLine(10, 10, 10, 600);
		
		g.drawLine(10, 10, 600, 10);
		
		for(int i = 10; i <= 600; i += 50) {
			
			g.drawString(Integer.toString(i), i, 10);
			
			g.drawString(Integer.toString(i), 10, i);
		}
		
		
		for (DrawingCommand drawingCommand : drawPathCommand) {
			
			drawingCommand.doDrawing(g);
		}
		
	}
	

	
	public void addDrawCommand(DrawingCommand c) {
		this.drawPathCommand.add(c);
		this.repaint();
	}
}
