package myMath;


import java.awt.Color;

import java.util.Iterator;

import java.util.LinkedList;



import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;

import de.erichseifert.gral.plots.XYPlot;

import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;

import de.erichseifert.gral.plots.lines.LineRenderer;

import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;

import de.erichseifert.gral.plots.points.PointRenderer;

import de.erichseifert.gral.ui.InteractivePanel;

import myMath.Polynom;



public class Graph extends JFrame {

	private static final long serialVersionUID = 1L;

	private Polynom poly;

	private double starting_point;

	private double to;

	private double eps = 0.25;

	

	public Graph(Polynom _p, double x0, double x1, double _eps) {

        poly= new Polynom(_p);

        starting_point = x0;

        to = x1;

        eps = _eps;
     
        LinkedList<Double> Extreme_Points = poly.extremaPoints(x0, x1, eps);
       
        if (!Extreme_Points.isEmpty()) {

        	System.out.println("Extreme points with a deviation of "+eps+" In the range of "+ starting_point+" to "+to+":");

        	Iterator<Double> it = Extreme_Points.iterator();

        	while (it.hasNext()) {

        		double x = it.next();

        		System.out.println("x = " + x +", y ="+ poly.f(x) );

        	}

        }

        @SuppressWarnings("unchecked")
		DataTable data = new DataTable(Double.class, Double.class);

        @SuppressWarnings("unchecked")
		DataTable dataDer = new DataTable(Double.class, Double.class);

        for (double x = starting_point; x <= to; x+=eps) {

        	double y = poly.f(x);

        	if (Extreme_Points.contains(x)) 

        		dataDer.add(x, y);

        	else 

        		data.add(x, y);

        }

    	setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(500, 500);
        

        XYPlot plot = new XYPlot(data, dataDer);

        getContentPane().add(new InteractivePanel(plot));

        LineRenderer lines = new DefaultLineRenderer2D();

        plot.setLineRenderers(data, lines);

        

        Color color = new Color(0f, 0f, 1f);

        Color colorDer = new Color(1f, 0f, 0f);

        

        

        plot.getPointRenderers(data).get(0).setColor(color);

        plot.getPointRenderers(dataDer).get(0).setColor(colorDer);

        plot.getLineRenderers(data).get(0).setColor(color);
    }



    public static void main(String[] args) throws Exception {

    	Polynom p = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
    	   System.out.println("The Polynom is : "+p);
           System.out.println();
           System.out.println("The derivative is : "+p.derivative());
           System.out.println();
           
    	p.GUI(-2, 6, 0.25);

    }

}
