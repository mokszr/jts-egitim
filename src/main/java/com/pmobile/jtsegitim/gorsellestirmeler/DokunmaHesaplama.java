package com.pmobile.jtsegitim.gorsellestirmeler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

public class DokunmaHesaplama {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Dokunma Hesaplama");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

		        Coordinate[] coordinates1 = new Coordinate[] { new Coordinate(60, 60), new Coordinate(110, 110), new Coordinate(155, 60),
		                new Coordinate(60, 60)};

		        Coordinate[] coordinates2 = new Coordinate[] { new Coordinate(155, 60), new Coordinate(250, 150), new Coordinate(310, 40),
		                new Coordinate(155, 60)};

		        Coordinate[] coordinates3 = new Coordinate[] { new Coordinate(150, 120), new Coordinate(300, 120),
		                new Coordinate(300, 180), new Coordinate(150, 180), new Coordinate(150, 120) };
		        
		        Polygon poly1 = geometryFactory.createPolygon(coordinates1);

		        Polygon poly2 = geometryFactory.createPolygon(coordinates2);
		       
		        Polygon poly3 = geometryFactory.createPolygon(coordinates3);
		        
		        System.out.println("poly1.touches(poly2)  " + (poly1.touches(poly2)));

		        System.out.println("poly1.intersects(poly2)  " + (poly1.intersects(poly2)));

		        System.out.println("poly1.overlaps(poly2)  " + (poly1.overlaps(poly2)));
		       
		        System.out.println("-----------------");
		        System.out.println("poly1.touches(poly3)  " + (poly1.touches(poly3)));

		        System.out.println("poly1.intersects(poly3)  " + (poly1.intersects(poly3)));

		        System.out.println("poly1.overlaps(poly3)  " + (poly1.overlaps(poly3)));
		       
		        System.out.println("-----------------");
		        System.out.println("poly3.touches(poly1) " + (poly3.touches(poly1)));

		        System.out.println("poly3.intersects(poly1)  " + (poly3.intersects(poly1)));

		        System.out.println("poly3.overlaps(poly1)  " + (poly3.overlaps(poly1)));
		       
		        System.out.println("-----------------");

		        System.out.println("poly3.touches(poly2)  " + (poly3.touches(poly2)));

		        System.out.println("poly3.intersects(poly2)  " + (poly3.intersects(poly2)));

		        System.out.println("poly3.overlaps(poly2)  " + (poly3.overlaps(poly2)));
		       
		       
		        System.out.println("-----------------");

		        System.out.println("poly2.touches(poly3)  " + (poly2.touches(poly3)));

		        System.out.println("poly2.intersects(poly3)  " + (poly2.intersects(poly3)));

		        System.out.println("poly2.overlaps(poly3)  " + (poly2.overlaps(poly3)));

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

						g.setColor(Color.RED);
						
						g.fillPolygon(xler, yler, coords.length);
						
						coords = poly3.getCoordinates();

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
						
						g.drawString("poly3", 160, 160);
						
						g.drawString("poly1.touches(poly2)  " + (poly1.touches(poly2)), 40, 200);
						
						g.drawString("poly1.intersects(poly2)  " + (poly1.intersects(poly2)), 40, 220);
						
						g.drawString("poly1.overlaps(poly2)  " + (poly1.overlaps(poly2)), 40, 240);
						
						g.drawString("poly1.touches(poly3)  " + (poly1.touches(poly3)), 40, 270);
						
						g.drawString("poly1.intersects(poly3)  " + (poly1.intersects(poly3)), 40, 290);
						
						g.drawString("poly1.overlaps(poly3)  " + (poly1.overlaps(poly3)), 40, 310);
						
						 
						
						g.drawString("poly3.touches(poly1) " + (poly3.touches(poly1)), 230, 200);
						
						g.drawString("poly3.intersects(poly1)  " + (poly3.intersects(poly1)), 230, 220);
						
						g.drawString("poly3.overlaps(poly1)  " + (poly3.overlaps(poly1)), 230, 240);
						
						g.drawString("poly3.touches(poly2)  " + (poly3.touches(poly2)), 230, 270);
						
						g.drawString("poly3.intersects(poly2)  " + (poly3.intersects(poly2)), 230, 290);
						
						g.drawString("poly3.overlaps(poly2)  " + (poly3.overlaps(poly2)), 230, 310);
						
						
						g.drawString("poly2.touches(poly3)  " + (poly2.touches(poly3)), 140, 340);
						
						g.drawString("poly2.intersects(poly3)  " + (poly2.intersects(poly3)), 140, 360);
						
						g.drawString("poly2.overlaps(poly3)  " + (poly2.overlaps(poly3)), 140, 380);	
					}
				});

			}
		});

	}
}
