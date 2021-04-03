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
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

public class DelaunayUcgenleme {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Delaunay Üçgenleme");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Random randomX = new Random(System.currentTimeMillis());
				
				Random randomY = new Random(System.currentTimeMillis() + 100);
				
				List<Coordinate> coords = new ArrayList<>();

				int noktaSayisi = 100;

				for(int i = 1; i <= noktaSayisi; i++) {
					
					int xRandomlySelected = randomX.nextInt(405) + 25;
					
					int yRandomlySelected = randomY.nextInt(405) + 20;
					
					System.out.println(i + ". Rastgele secilen nokta " + xRandomlySelected + ", " + yRandomlySelected);

					coords.add(new Coordinate(xRandomlySelected, yRandomlySelected));
				
				}
				
				DelaunayTriangulationBuilder ucgenBuilder = new DelaunayTriangulationBuilder();
				
				ucgenBuilder.setSites(coords);
				
				Geometry triangles = ucgenBuilder.getTriangles(geometryFactory);
				
				List<Polygon> uretilenUcgenler = new ArrayList<>();
				
				if(triangles instanceof GeometryCollection) {
					
					GeometryCollection geometryCollection = (GeometryCollection) triangles;
					
					System.out.println("Üretilen üçgen sayısı: " + geometryCollection.getNumGeometries());
					
					for(int i = 0; i < geometryCollection.getNumGeometries(); i++) {
						
						Polygon ucgen = (Polygon) geometryCollection.getGeometryN(i);
						
						uretilenUcgenler.add(ucgen);
					}
					
				}
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						for (Polygon ucgen : uretilenUcgenler) {
							
							Coordinate[] coords = ucgen.getCoordinates();
							
							int[] xler = new int[coords.length];
							int[] yler = new int[coords.length];
							
							for (int i = 0; i < coords.length; i++) {
								xler[i] = (int) coords[i].getX();
								yler[i] = (int) coords[i].getY();
							}

							g.setColor(Color.RED);
							
							g.drawPolygon(xler, yler, coords.length);
						}

					}
				});

			}
		});

	}
}
