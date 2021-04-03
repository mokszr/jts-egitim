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
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import com.pmobile.jtsegitim.ui.DrawingCommand;
import com.pmobile.jtsegitim.ui.JTSGorsellestirmePanel;

public class VoronoiDiagramGorsellestirme {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSGorsellestirmePanel panel = new JTSGorsellestirmePanel();

				JFrame frame = new JFrame("JTS Görselleştirme - Voronoi Diagram");
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
					
					int xRandomlySelected = randomX.nextInt(250) + 65;
					
					int yRandomlySelected = randomY.nextInt(250) + 50;
					
					System.out.println(i + ". Rastgele secilen nokta " + xRandomlySelected + ", " + yRandomlySelected);

					coords.add(new Coordinate(xRandomlySelected, yRandomlySelected));
				
				}
				
				VoronoiDiagramBuilder diagramBuilder = new VoronoiDiagramBuilder();
				
				diagramBuilder.setSites(coords);
				
				Geometry triangles = diagramBuilder.getDiagram(geometryFactory);
				
				List<Polygon> uretilenPoligonlar = new ArrayList<>();
				
				if(triangles instanceof GeometryCollection) {
					
					GeometryCollection geometryCollection = (GeometryCollection) triangles;
					
					System.out.println("Üretilen poligon sayısı: " + geometryCollection.getNumGeometries());
					
					for(int i = 0; i < geometryCollection.getNumGeometries(); i++) {
						
						Polygon poligon = (Polygon) geometryCollection.getGeometryN(i);
						
						uretilenPoligonlar.add(poligon);
					}
					
				}
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						for (Polygon poligon : uretilenPoligonlar) {
							
							Coordinate[] coords = poligon.getCoordinates();
							
							int[] xler = new int[coords.length];
							int[] yler = new int[coords.length];
							
							for (int i = 0; i < coords.length; i++) {
								xler[i] = (int) coords[i].getX();
								yler[i] = (int) coords[i].getY();
							}

							g.setColor(Color.RED);
							
							g.drawPolygon(xler, yler, coords.length);
						}
						
						for (int i = 0; i < coords.size(); i++) {
							
							g.setColor(Color.BLACK);
							
							g.fillOval((int)coords.get(i).getX(), (int) coords.get(i).getY(), 3, 3);
						}

					}
				});

			}
		});

	}
}
