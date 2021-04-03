package com.pmobile.jtsegitim.gorsellestirmeler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

public class ConvexHullGorsellestirme {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Convex Hull - Zarf");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Random randomX = new Random(System.currentTimeMillis());
				
				Random randomY = new Random(System.currentTimeMillis() + 100);
				
				List<Coordinate> noktaKoordinatlari = new ArrayList<>();

				int noktaSayisi = 100;
				
				Point[] noktalar = new Point[noktaSayisi];

				for(int i = 1; i <= noktaSayisi; i++) {
					
					int xRandomlySelected = randomX.nextInt(250) + 65;
					
					int yRandomlySelected = randomY.nextInt(250) + 50;
					
					System.out.println(i + ". Rastgele secilen nokta " + xRandomlySelected + ", " + yRandomlySelected);

					Coordinate coordinate = new Coordinate(xRandomlySelected, yRandomlySelected);
					
					noktaKoordinatlari.add(coordinate);
				
					noktalar[i-1] = geometryFactory.createPoint(coordinate);
				}
				
				MultiPoint multiPoint = geometryFactory.createMultiPoint(noktalar);
				 
				Polygon convexHull = (Polygon) multiPoint.convexHull();
				
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
							
							Coordinate[] coords = convexHull.getCoordinates();
							
							int[] xler = new int[coords.length];
							int[] yler = new int[coords.length];
							
							for (int i = 0; i < coords.length; i++) {
								xler[i] = (int) coords[i].getX();
								yler[i] = (int) coords[i].getY();
							}

							g.setColor(Color.RED);
							
							g.drawPolygon(xler, yler, coords.length);
					 
							for (int i = 0; i < noktaKoordinatlari.size(); i++) {
								
								g.setColor(Color.BLACK);
								
								g.fillOval((int)noktaKoordinatlari.get(i).getX(), (int) noktaKoordinatlari.get(i).getY(), 3, 3);
							}
							
					}
				});

			}
		});

	}
}
