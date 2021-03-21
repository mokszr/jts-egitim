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

public class BirlesimAlanHesaplama {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Birleşim Alan Hesaplama");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(150, 60), 
						new Coordinate(325, 180),new Coordinate(90, 150), new Coordinate(150, 60)};

				Coordinate[] coordinates2 = new Coordinate[] { new Coordinate(210, 120), new Coordinate(350, 120),
						new Coordinate(400, 210),new Coordinate(250, 300), new Coordinate(180, 150), new Coordinate(210, 120)};
				
				Polygon maviPolygon = geometryFactory.createPolygon(coordinates);
				
				Polygon sariPolygon = geometryFactory.createPolygon(coordinates2);
				
				Polygon birlesimPoligonu = (Polygon) sariPolygon.union(maviPolygon);
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = birlesimPoligonu.getCoordinates();
						
						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(new Color(Color.RED.getRed(), Color.RED.getGreen(),Color.RED.getBlue(), 160));
						
						g.fillPolygon(xler, yler, coords.length);
						
						g.setColor(Color.BLACK);
						
						g.drawString("İki poligonun birleşiminden oluşan poligon.", 60, 330);
						
					}
				});

			}
		});

	}
}
