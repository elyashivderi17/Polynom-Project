package myMath;
import java.awt.Color;

public class Point {
	
	private Color rootColor;
	private double x0;
	private double y0;
	
	public Point() {
		this.x0 = 0;
		this.y0 = 0;
	}
	
	public Point(Point p) {
		this.x0 = p.x0;
		this.y0 = p.y0;
	}
	
	public Point(double x, double y) {
		this.x0 = x;
		this.y0 = y;
	}
	
	public void setPointColor(Point p) {
		p.rootColor = Color.BLUE;
	}
}
