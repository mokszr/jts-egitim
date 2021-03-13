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

public class KapsiyorHesaplama {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Kapsıyor Hesaplama");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(250, 110), new Coordinate(300, 200),  new Coordinate(190, 200),new Coordinate(250, 110)};

				Polygon polygon = geometryFactory.createPolygon(coordinates);
				
				Coordinate[] dortgenCoordinates = new Coordinate[] { new Coordinate(110, 80), new Coordinate(370, 90),  new Coordinate(370, 250),new Coordinate(100, 250) , new Coordinate(110, 80)};

				Polygon dortgen = geometryFactory.createPolygon(dortgenCoordinates);
				
				boolean dortgenPoligonuKapsiyorMu = dortgen.covers(polygon);
				
				boolean poligonDortgenTarafindanKapsaniyorMu = polygon.coveredBy(dortgen);

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
						
						g.setColor(Color.BLACK);
						
						g.drawString("(" + xler[0] + ", " + yler[0] + ")"  , 240, 100);
						
						g.drawString("(" + xler[1] + ", " + yler[1] + ")"  , 290, 215);
						
						g.drawString("(" + xler[2] + ", " + yler[2] + ")"  , 180, 215);

						g.setColor(Color.BLUE);
						
						g.fillPolygon(xler, yler, coords.length);
						
						
						coords = dortgen.getCoordinates();
						
						xler = new int[coords.length];
						yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(new Color(0.f, 0.5f, 0.f, 0.5f));
						
						g.fillPolygon(xler, yler, coords.length);

						g.setColor(Color.BLACK);

						g.drawString("(" + xler[0] + ", " + yler[0] + ")"  , 100, 65);
						
						g.drawString("(" + xler[1] + ", " + yler[1] + ")"  , 370, 75);
						
						g.drawString("(" + xler[2] + ", " + yler[2] + ")"  , 370, 265);
						
						g.drawString("(" + xler[3] + ", " + yler[3] + ")"  , 100, 265);
						
						g.drawString("Dörtgen üçgeni kapsıyor mu? "  + dortgenPoligonuKapsiyorMu, 40, 285);
					
						g.drawString("Üçgen, dörtgen tarafından kapsanıyor mu? "  + poligonDortgenTarafindanKapsaniyorMu, 40, 310);


					}
				});

			}
		});

	}
}
