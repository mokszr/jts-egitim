package com.pmobile.jtsegitim.gorsellestirmeler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

public class EsitlikHesaplama {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Eşitlik Hesaplama");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates1 = new Coordinate[] { new Coordinate(60, 60), new Coordinate(150, 60), new Coordinate(350, 60)};
			       
		        Coordinate[] coordinates2 = new Coordinate[] { new Coordinate(350, 60), new Coordinate(60, 60)};
		       
		        LineString line1 = geometryFactory.createLineString(coordinates1);
		       
		        LineString line2 = geometryFactory.createLineString(coordinates2);
		       
		        System.out.println("(line1.equals((Object)line2)) " + (line1.equals((Object)line2)));
		       
		        System.out.println("(line1.equals(line2)) " + (line1.equals(line2)));
		       
		        System.out.println("(line1.equals((Geometry)line2)) " + (line1.equals((Geometry)line2)));
		       
		        System.out.println("(line1.equalsExact(line2)) " + (line1.equalsExact(line2)));

				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = line1.getCoordinates();

						g.setColor(Color.RED);
 						
						g.drawString("(" + (int)coords[0].getX() + ", " + (int)coords[0].getY() + ")"  , 50, 45);
						
						g.drawString("(" + (int)coords[2].getX() + ", " + (int)coords[2].getY() + ")"  , 340, 45);

						g.fillRect((int) coords[0].getX(),(int) coords[0].getY() - 3, (int)  (coords[2].getX() - coords[0].getX()), 6);

						g.setColor(new Color(Color.BLUE.getRed(), Color.BLUE.getGreen(),Color.YELLOW.getBlue(), 100));
						
						coords = line2.getCoordinates();
 
						g.fillRect((int) coords[0].getX(),(int) coords[0].getY() - 10, (int)  (coords[1].getX() - coords[0].getX()), 20);
						
						g.setColor(Color.BLACK);
						
						g.drawString("line1.equals((Object)line2) " + (line1.equals((Object)line2)), 70, 90);
						
						g.drawString("line1.equals(line2) " + (line1.equals(line2)), 70, 130);
						
						g.drawString("line1.equals((Geometry)line2) " + (line1.equals((Geometry)line2)), 70, 180);
						
						g.drawString("line1.equalsExact(line2) " + line1.equalsExact(line2), 70, 230);


					}
				});

			}
		});

	}
}
