package com.pmobile.jtsegitim.gorsellestirmeler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.densify.Densifier;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;
/**
 * 
 * @see https://muratoksuzer.com/
 *
 */
public class GeometriYogunlastirma {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Geometri Yoğunlaştırma");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(50, 20), new Coordinate(70, 70),
						new Coordinate(90, 50), 
						new Coordinate(110, 80),
						new Coordinate(120, 60), 
						new Coordinate(150, 60), new Coordinate(175, 120),
						new Coordinate(200, 150), new Coordinate(220, 100), new Coordinate(230, 130),
						new Coordinate(260, 80), new Coordinate(310, 110) };

				LineString lineString = geometryFactory.createLineString(coordinates);
				
				Densifier densifier = new Densifier(lineString);

				densifier.setDistanceTolerance(15);
				
				LineString denserLineString = (LineString) densifier.getResultGeometry();
			
				System.out.println("original line coordinate size : " + coordinates.length);
				
				System.out.println("densified line coordinate size " + denserLineString.getCoordinates().length);

				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {

						Coordinate[] coords = lineString.getCoordinates();

						g.setColor(Color.BLUE);

						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];

						for (int i = 0; i < coords.length - 1; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();

							xler[i + 1] = (int) coords[i + 1].getX();
							yler[i + 1] = (int) coords[i + 1].getY();

							g.drawLine(xler[i], yler[i], xler[i + 1], yler[i + 1]);
							
							// Just to draw line thicker, draw the same line one pixel below 
							g.drawLine(xler[i], yler[i]+1, xler[i + 1], yler[i + 1]+1);
							
							g.fillOval(xler[i], yler[i], 8, 8);

						}

						g.setColor(Color.RED);

						coords = denserLineString.getCoordinates();

						xler = new int[coords.length];
						yler = new int[coords.length];

						for (int i = 0; i < coords.length - 1; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();

							xler[i + 1] = (int) coords[i + 1].getX();
							yler[i + 1] = (int) coords[i + 1].getY();

							
							// To understand well the difference, not draw top of the original
							// Just draw simplified version 100 pixel below
							g.drawLine(xler[i], yler[i]+ 100, xler[i + 1], yler[i + 1] + 100);
							g.drawLine(xler[i], yler[i]+ 101, xler[i + 1], yler[i + 1] + 101);
							
							g.fillOval(xler[i], yler[i] + 100, 8, 8);

						}

						g.setColor(Color.black);

						g.drawString("original line coordinate size " + lineString.getNumPoints(), 60, 300);

						g.drawString("densified line coordinate size " + denserLineString.getNumPoints(), 60, 330);
					}
				});

			}
		});

	}
}
