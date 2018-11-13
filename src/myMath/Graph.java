package myMath;

import java.awt.Color;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;

import de.erichseifert.gral.plots.XYPlot;

import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;

import de.erichseifert.gral.plots.lines.LineRenderer;

import de.erichseifert.gral.plots.points.PointRenderer;

import de.erichseifert.gral.ui.InteractivePanel;



public class Graph extends JFrame {

	public Graph(Polynom p,double start, double end) {

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(600, 400);

		DataTable data = new DataTable(Double.class, Double.class);

		for (double x = start; x <= end; x+=0.25) {

			double y = p.f(x);

			data.add(x, y);

		}

		XYPlot plot = new XYPlot(data);

		getContentPane().add(new InteractivePanel(plot));

		LineRenderer lines = new DefaultLineRenderer2D();

		plot.setLineRenderers(data, lines);

		Color color = new Color(0.0f, 0.3f, 1.0f);

		plot.getPointRenderers(data);

		plot.getLineRenderers(data);

	}

	public static void main(String[] args) throws Exception {

		Polynom p=new Polynom("x^2+3x");

		p.add(new Monom(2,1));

		Graph frame = new Graph(p,-2.0,6.0);

		frame.setVisible(true);

	}

}