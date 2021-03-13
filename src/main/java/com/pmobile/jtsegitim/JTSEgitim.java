package com.pmobile.jtsegitim;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.IntersectionMatrix;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

public class JTSEgitim {

	public static void main(String[] args) {
		GeometryFactory geometryFactory = new GeometryFactory();
		
		Coordinate coordinate = new Coordinate(3, 5, 10);
		
		Point point = geometryFactory.createPoint(coordinate);
		
		Coordinate[] coordinates1 = new Coordinate[] { new Coordinate(39, 33), new Coordinate(46, 23)};
		 
		LineString lineString1 = geometryFactory.createLineString(coordinates1);
		
		Coordinate[] coordinates2 = new Coordinate[] { new Coordinate(26, 38), new Coordinate(30, 37), new Coordinate(40.5, 39.785)};
		 
		LineString lineString2 = geometryFactory.createLineString(coordinates2);
		
		LineString[] lineStrings = new LineString[] {lineString1, lineString2};
		
		MultiLineString multiLineString = geometryFactory.createMultiLineString(lineStrings);
		
		Coordinate[] polygonCoordinates = new Coordinate[] { new Coordinate(39, 33), new Coordinate(46, 23), new Coordinate(40, 27), new Coordinate(39, 33)};
		
		Coordinate[] holeCoordinates = new Coordinate[] { new Coordinate(40, 34), new Coordinate(45, 24), new Coordinate(41, 26), new Coordinate(40, 34)};
		
		LinearRing hole = geometryFactory.createLinearRing(holeCoordinates);
		
		LinearRing[] holes = new LinearRing[] { hole };
		
		LinearRing shell = geometryFactory.createLinearRing(polygonCoordinates);
		
		Polygon polygonWithHole = geometryFactory.createPolygon(shell, holes);
		
		Polygon polygon = geometryFactory.createPolygon(polygonCoordinates);
		
		double area = polygonWithHole.getArea();
		
		
		Polygon[] polygons = new Polygon[] {polygon, polygonWithHole};
		
		MultiPolygon multiPolygon = geometryFactory.createMultiPolygon(polygons);
		
		Geometry[] geometries = new Geometry[] {point, multiLineString, polygon, multiPolygon};
		
		GeometryCollection geometryCollection = geometryFactory.createGeometryCollection(geometries);
		
		
	//	Point point = geometryFactory.createPoint(coordinate);
		Point point2 = geometryFactory.createPoint(coordinate);
		Geometry buffer = point.buffer(110);
		
		System.out.println(buffer.getClass());
	
		Point centroid = point.getCentroid();
		System.out.println(centroid);
		Geometry convexHull = point.convexHull();
		System.out.println(convexHull);
		point.setSRID(56);
		System.out.println("srid " + point.getSRID());
		System.out.println("srid " + point2.getSRID());
		
		IntersectionMatrix relate = point.relate(point2);
		
		System.out.println(relate);
		
		Point reverse = point.reverse();
		System.out.println("reverse " + reverse);
	}
	
}
