/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	for(int i=0 ; i<=3 ; i++)
    	{
    		turtle.forward(sideLength);
    		turtle.turn(90);
    	}
    	
       
    

		
	}

	/**
     * Determine inside angles of a regular polygon.    
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	double allAngle = (sides - 2)*180;
    	double angle;
    	angle = allAngle / sides ;
    	return angle;
    	
        
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	return (int) (360 / (180.00 - angle) + 0.5);
        
        
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double degree =calculateRegularPolygonAngle(sides);
        for(int i = 0 ; i<sides ; i++){
        	turtle.forward(sideLength);
        	turtle.turn(180-degree);
        }
       
        	
 
        
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double degree = Math.toDegrees(Math.atan2(targetY - currentY, targetX - currentX));
        degree = 90 - degree -currentBearing;
        if(degree < 0){
        	degree += 360 ;	
        }
        return degree;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	List<Double> result = new ArrayList<>();
    	double t = 0;
    	for(int i = 1 ; i<xCoords.size() ; i++){
    		t = calculateBearingToPoint(t, xCoords.get(i-1), yCoords.get(i-1), xCoords.get(i), yCoords.get(i));
    		result.add(t);
    	}
    	return result;
    }
        /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
    	if(points.size()<=3)//С����������㼯��ÿ���㶼��͹���ϣ�ֱ�ӷ���
    		return points;
    	Point sp = null; //��ʼ��
    	Set<Point> rp = new HashSet<Point>(); //��Ҫ���ص�͹��
    	//�����㼯�ҵ���ʼ��
    	for(Point t : points){
    		if(sp == null){  //��ʼ����ʼ��
    			sp = t ;
    			continue;
    			
    		}
    		/*
    		 * ��͹���ĵ�һ����
    		 */
    		if(t.x() == sp.x()){
    			if(t.y() < sp.y()){
    				sp = t;
    			}
    		}
    		else if(t.x() < sp.x()){
    			sp = t;
    		}
    	}
    	rp.add(sp);//��ʼ�����͹��
    	
    	//Ѱ����һ����
    	while(true){
    		Point np = null;  //�ȴ�����͹�����ĵ�
    		for(Point t : points){
    			if(rp.contains(t) == false){
    				np = t;
    				break;
    			}
    		}
    		if(np == null){//������־�������������㼯
    			break;
    		}
    		
    	
   
    		
    		for(Point t : points){
    			double c,d1,d2;
    			d1 = Math.hypot((sp.x() - t.x()), (sp.y() - t.y()));//����sp��t�����
    			d2 = Math.hypot((sp.x() - np.x()), (sp.y() - np.y()));//����sp��np�����
    			c = (sp.x() - t.x()) * (t.y() - np.y()) - (sp.y() - t.y()) * (t.x() - np.x());
    			if(c>=0 &&(d1>d2) ){ //�˴��ж��ĸ���Ϊ͹���ϵĵ㣬��Ҫ������������ �������t�㣬sp�㣬np������˳ʱ������
    				np = t;
    			}
    		}
    		if(rp.contains(np)){
    			break;
    		}
    		rp.add(np);
    		sp = np;
    		
    	}
    	return rp;
    	
    	
        
    }
    
    public static void drawStar(Turtle turtle, int sideLength) {
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(288);
        turtle.forward(sideLength);
        turtle.turn(144);
        turtle.forward(sideLength);
        turtle.turn(288);
        turtle.forward(sideLength);
        turtle.turn(144);
        turtle.forward(sideLength);
        turtle.turn(288);
        turtle.forward(sideLength);
        turtle.turn(144);
        turtle.forward(sideLength);
        turtle.turn(288);
        turtle.forward(sideLength);
        turtle.turn(144);
        turtle.forward(sideLength);
        turtle.turn(288);
        turtle.forward(sideLength);
        
        
       
    }
    
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	PenColor[] color = new PenColor[4];
    	color[0] = PenColor.PINK;
    	color[1] = PenColor.BLUE;
    	color[2] = PenColor.RED;
    	color[3] = PenColor.YELLOW;
		for (int i = 0; i < 150; i++) {
			drawStar(turtle,  ( i + 10));
			turtle.color(color[i % 4]);
		}
	}
        
    

    /**
     * Main method.
     *  
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        turtle.draw();

        drawPersonalArt(turtle);
        
         
        
        

       
        
    }

}
