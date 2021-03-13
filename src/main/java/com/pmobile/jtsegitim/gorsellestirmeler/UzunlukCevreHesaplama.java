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

public class UzunlukCevreHesaplama {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Uzunluk Çevre Hesaplama");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(150, 60), new Coordinate(200, 150), 
						new Coordinate(80, 80), new Coordinate(40, 160), new Coordinate(50, 60)};

				LineString lineString = geometryFactory.createLineString(coordinates);
				
				double uzunluk = lineString.getLength();
				
				
				Coordinate[] polygonCoordinates = new Coordinate[] { new Coordinate(60, 250), new Coordinate(160, 270), 
						new Coordinate(170, 330), new Coordinate(100, 350), new Coordinate(50, 280), new Coordinate(60, 250)};
				
				Polygon polygon = geometryFactory.createPolygon(polygonCoordinates);
				
				double poligonCevre = polygon.getLength();
				

				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = lineString.getCoordinates();
						
						g.setColor(Color.BLUE);
						
						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];
						
						for (int i = 0; i < coords.length -1; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
							
							g.drawLine((int) coords[i].getX(),(int) coords[i].getY(),(int) coords[i+ 1].getX(),(int) coords[i +1].getY());
						}

						g.setColor(Color.black);
						
						
						g.drawString("(" + xler[0] + ", " + yler[0] + ")"  , 140, 50);
						
						g.drawString("(" + xler[1] + ", " + yler[1] + ")"  , 190, 165);
						
						g.drawString("(" + xler[2] + ", " + yler[2] + ")"  , 80, 70);
						
						g.drawString("(" + xler[3] + ", " + yler[3] + ")"  , 40, 170);
						
						g.drawString("(" + (int) coords[4].getX() + ", " + (int) coords[4].getY() + ")"  , 50, 50);

						
						g.drawString("Hesaplanan Uzunluk\n " + uzunluk, 70, 190);
						
						coords = polygon.getCoordinates();
						
						g.setColor(Color.BLUE);
						
						xler = new int[coords.length];
						yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
							
						}

						g.fillPolygon(xler, yler, coords.length);
						
						g.setColor(Color.black);
						
						g.drawString("Hesaplanan Çevre\n " + poligonCevre, 70, 380);
						
						g.drawString("(" + xler[0] + ", " + yler[0] + ")"  , 50, 240);
						
						g.drawString("(" + xler[1] + ", " + yler[1] + ")"  , 160, 260);
						
						g.drawString("(" + xler[2] + ", " + yler[2] + ")"  , 175, 330);
						
						g.drawString("(" + xler[3] + ", " + yler[3] + ")"  , 100, 365);
						
						g.drawString("(" + xler[4] + ", " + yler[4] + ")"  , 10, 270);
						
					}
				});

			}
		});

	}
	
}
