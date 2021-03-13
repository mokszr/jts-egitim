package com.pmobile.jtsegitim.gorsellestirmeler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

public class KesisimHesaplama {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Kesişim Hesaplama");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(50, 50), new Coordinate(150, 150) };

				LineString cizgi1 = geometryFactory.createLineString(coordinates);

				LineString cizgi2 = geometryFactory.createLineString(new Coordinate[] { new Coordinate(80, 40), new Coordinate(30, 250) });

				boolean cizgi1Cizgi2KesisirMi = cizgi1.intersects(cizgi2);
				
				Point intersectionPoint = (Point) cizgi1.intersection(cizgi2);
				
				LineString cizgi3 = geometryFactory.createLineString(new Coordinate[] { new Coordinate(210, 75), new Coordinate(350, 170) });

				
				boolean cizgi3Cizgi1AyrikMi = cizgi3.disjoint(cizgi1);
				
				boolean cizgi3Cizgi1KesşsşrMi = cizgi3.intersects(cizgi1);
								
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {

						g.drawLine((int) cizgi1.getCoordinateN(0).getX(),(int) cizgi1.getCoordinateN(0).getY(),
								(int) cizgi1.getCoordinateN(1).getX(), (int) cizgi1.getCoordinateN(1).getX());
 
						g.drawLine((int) cizgi2.getCoordinateN(0).getX(),(int) cizgi2.getCoordinateN(0).getY(),
								(int) cizgi2.getCoordinateN(1).getX(), (int) cizgi2.getCoordinateN(1).getY());
						
						g.drawLine((int) cizgi3.getCoordinateN(0).getX(),(int) cizgi3.getCoordinateN(0).getY(),
								(int) cizgi3.getCoordinateN(1).getX(), (int) cizgi3.getCoordinateN(1).getY());
						
						g.setColor(Color.RED);
						
						g.fillRect((int) intersectionPoint.getX(),(int) intersectionPoint.getY(), 10, 10);
					
						g.setColor(Color.black);
						
						g.drawString("Çizgi 1 ve Çizgi 2 kesişir mi? " + cizgi1Cizgi2KesisirMi, 60, 250);
						
						g.drawString("Çizgi 3 ve Çizgi 1 kesişir mi? " + cizgi3Cizgi1KesşsşrMi, 70, 280);
						
						g.drawString("Çizgi 3 ve Çizgi 1 ayrık mı? " + cizgi3Cizgi1AyrikMi, 70, 300);


						
					}
				});

			}
		});

	}
}
