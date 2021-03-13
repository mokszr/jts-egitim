package com.pmobile.jtsegitim.gorsellestirmeler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

public class KesiyorHesaplama {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Kesiyor Hesaplama");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

		        Coordinate[] coordinates1 = new Coordinate[] { new Coordinate(60, 60), new Coordinate(110, 110), new Coordinate(155, 60),
		                new Coordinate(60, 60)};

		        Coordinate[] coordinates2 = new Coordinate[] { new Coordinate(150, 120), new Coordinate(300, 120),
		                new Coordinate(300, 180), new Coordinate(150, 180), new Coordinate(150, 120) };
		        
		        Coordinate[] coordinates3 = new Coordinate[] { new Coordinate(40, 75), new Coordinate(350, 120) };
		        
		        Coordinate[] coordinates4 = new Coordinate[] { new Coordinate(210, 160), new Coordinate(350, 250) };

		        Polygon poly1 = geometryFactory.createPolygon(coordinates1);

		        Polygon poly2 = geometryFactory.createPolygon(coordinates2);
		        
		        LineString line1 = geometryFactory.createLineString(coordinates3);
		        
		        LineString line2 = geometryFactory.createLineString(coordinates4);


		        System.out.println("poly1.crosses(line1)  " + (poly1.crosses(line1)));
		       
		        System.out.println("-----------------");

		        System.out.println("line1.crosses(poly2)  " + (line1.crosses(poly2)));
	       
		        System.out.println("-----------------");

		        System.out.println("line1.crosses(poly1)  " + (line1.crosses(poly1)));
		        
		        System.out.println("-----------------");
		        
		        System.out.println("poly2.crosses(line2)  " + poly2.crosses(line2));
		        
		        System.out.println("-----------------");
		        
		        System.out.println("line2.crosses(poly2)  " + line2.crosses(poly2));

				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = poly1.getCoordinates();
						
						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(Color.BLUE);
						
						g.fillPolygon(xler, yler, coords.length);
						
						coords = poly2.getCoordinates();

						xler = new int[coords.length];
						yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(new Color(Color.YELLOW.getRed(), Color.YELLOW.getGreen(),Color.YELLOW.getBlue(), 150));
						
						g.fillPolygon(xler, yler, coords.length);
						
						
						g.setColor(Color.WHITE);
						
						g.drawString("poly1", 100, 80);
						
						g.drawString("poly2", 220, 80);
						
						g.setColor(Color.BLACK);
						
						g.drawString("poly2", 160, 160);
						
						coords = line1.getCoordinates();
						
						g.drawLine((int) coords[0].getX(),(int) coords[0].getY(),(int) coords[1].getX(),(int) coords[1].getY());
						
						coords = line2.getCoordinates();
						
						g.drawLine((int) coords[0].getX(),(int) coords[0].getY(),(int) coords[1].getX(),(int) coords[1].getY());
						
						g.drawString("line1", 310, 100);
						
						g.drawString("line2", 310, 220);
						
						
						g.drawString("poly1.crosses(line1)  " + (poly1.crosses(line1)), 50, 210);
						
						g.drawString("line1.crosses(poly2)  " + (line1.crosses(poly2)), 50, 240);
						
						g.drawString("line1.crosses(poly1)  " + (line1.crosses(poly1)), 50, 270);
						
						g.drawString("poly2.crosses(line2)  " + poly2.crosses(line2), 50, 300);
						
						g.drawString("line2.crosses(poly2)  " + line2.crosses(poly2), 50, 330);
					}
				});

			}
		});

	}
}
