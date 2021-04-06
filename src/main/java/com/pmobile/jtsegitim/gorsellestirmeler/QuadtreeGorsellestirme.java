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
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.index.quadtree.Quadtree;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

/**
 * @see https://muratoksuzer.com
 *
 */

public class QuadtreeGorsellestirme {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - QuadTree ");
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

				Quadtree quadTree = new Quadtree();
				
				for(int i = 1; i <= noktaSayisi; i++) {
					
					int xRandomlySelected = randomX.nextInt(250) + 65;
					
					int yRandomlySelected = randomY.nextInt(250) + 50;
					
					Coordinate coordinate = new Coordinate(xRandomlySelected, yRandomlySelected);
					
					noktaKoordinatlari.add(coordinate);
				
					noktalar[i-1] = geometryFactory.createPoint(coordinate);
					
					quadTree.insert(noktalar[i-1].getEnvelopeInternal(), noktalar[i-1]);
					
				}
				
				Coordinate[] coordinates = new Coordinate[] { new Coordinate(150, 60), new Coordinate(250, 60),  new Coordinate(250, 250),new Coordinate(150, 250), new Coordinate(150, 60)};
				
				Polygon cercevePoligon = geometryFactory.createPolygon(coordinates);
				
				List<Object> cerceveOlasiKapsananNoktalar = quadTree.query(cercevePoligon.getEnvelopeInternal());
				
				List<Point> falsePositives = new ArrayList<>();
				
				for (Object c : cerceveOlasiKapsananNoktalar) {
					Point p = (Point) c;
					
					System.out.println(p + " intersects polygon: " + cercevePoligon.intersects(p) + " envlope " + p.getEnvelopeInternal());
					
					if(!cercevePoligon.intersects(p)) {
						falsePositives.add(p);
					}
				}
				
				cerceveOlasiKapsananNoktalar.removeAll(falsePositives);
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
							
							Coordinate[] coords = cercevePoligon.getCoordinates();
							
							int[] xler = new int[coords.length];
							int[] yler = new int[coords.length];
							
							for (int i = 0; i < coords.length; i++) {
								xler[i] = (int) coords[i].getX();
								yler[i] = (int) coords[i].getY();
							}

							g.setColor(Color.RED);
							
							g.drawPolygon(xler, yler, coords.length);
					 
							for (int i = 0; i < noktaKoordinatlari.size(); i++) {
								
								g.setColor(Color.BLUE);
								
								Point point = noktalar[i];
								
								if(cerceveOlasiKapsananNoktalar.contains(point)) {
						
									g.setColor(Color.RED);
								}
								
								g.fillOval((int)noktaKoordinatlari.get(i).getX(), (int) noktaKoordinatlari.get(i).getY(), 5, 5);
							}
					}
				});

			}
		});

	}
}
