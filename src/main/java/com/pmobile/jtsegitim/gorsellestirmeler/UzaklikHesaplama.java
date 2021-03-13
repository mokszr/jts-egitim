package com.pmobile.jtsegitim.gorsellestirmeler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.operation.distance.DistanceOp;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

public class UzaklikHesaplama {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Uzaklık Hesaplama");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(250, 60), new Coordinate(300, 150),  new Coordinate(190, 150),new Coordinate(250, 60)};

				Polygon polygon = geometryFactory.createPolygon(coordinates);
				
				Point point = geometryFactory.createPoint(new Coordinate(100, 100));
				
				double distance = polygon.distance(point);
				
				DistanceOp op = new DistanceOp(polygon, point);
				
				Coordinate[] nearestPoints = op.nearestPoints();
				
				boolean mesafedeMi103 = point.isWithinDistance(polygon, 103);
				
				boolean mesafedeMi102 = point.isWithinDistance(polygon, 102);

				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = polygon.getCoordinates();
						
						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(Color.BLUE);
						
						g.fillPolygon(xler, yler, coords.length);

						g.setColor(Color.black);
						
						g.drawString("(" + xler[0] + ", " + yler[0] + ")"  , 240, 50);
						
						g.drawString("(" + xler[1] + ", " + yler[1] + ")"  , 290, 165);
						
						g.drawString("(" + xler[2] + ", " + yler[2] + ")"  , 180, 165);
						
						g.drawString("(" + (int)point.getX() + ", " + (int)point.getY() + ")"  , 90, 90);
						
						g.fillRect((int)point.getX(), (int)point.getY(), 4, 4);
						
						g.drawString("Hesaplanan Uzaklık\n " + distance, 70, 200);
						
						g.drawString("103 birim mesafe içerisinde mi?\n " + mesafedeMi103, 70, 220);
						
						g.drawString("102 birim mesafe içerisinde mi?\n " + mesafedeMi102, 70, 240);

						
						g.drawLine((int) nearestPoints[0].getX(),(int) nearestPoints[0].getY(),(int) nearestPoints[1].getX(),(int) nearestPoints[1].getY());

					}
				});

			}
		});

	}
}
