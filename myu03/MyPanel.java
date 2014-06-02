package myu03;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyPanel extends JPanel implements MouseListener {
	
	//attributes
	
	//int x_max = 600;	\\nicht benötigt
	//int y_max = 400;
	
	//Verschiebung des geforderten Kreises
	int x_transl =0;
	int y_transl =0;
	
	//Faktor für die Dichte der Ringe
	int n = 10  ;
	
	//Variablen für die Farbmischung
	int red = 1;
	int blue = 1;
	int green = 1;
	
	
	
	public void paintComponent(Graphics g){
		g.setColor(Color.green); // setzt die Hintergrundfarbe
		//bekommt die Größe des Fensters
		Dimension d = getSize();
		
		//doppelte for-schleife um die Bildpunkte anzusprechen
		//schleife für die Breite
		for (int i = 0; i < d.width; i++) {
			//Schleife für die Höhe
			for (int j = 0 ; j < d.height ; j++){
				//Blau wird variiert 
				blue = (int)Math.sqrt(Math.pow(n*(i-x_transl),2)+ Math.pow(n*(j-y_transl),2))%254;
				g.setColor(new Color(red ,green ,blue ));
				g.drawRect((i)%d.width,(j)%d.height , 1 , 1);
			}
		}
	}
	
	public MyPanel() {
		addMouseListener(this);
	    }
	
	//MouseListener Methods
	@Override
	
	/* 
	 * Wenn Maustaste gedrückt wird, wird der
	 * Mittelpunkt des Kreises unter den Mauszeiger gelegt.
	*/
	public void mouseClicked(MouseEvent e) {
		x_transl=e.getX();
		y_transl=e.getY();
		repaint();
	}

	@Override
	// n bewegt sich zwischen 1 und 111
	// rotiert durch 
	public void mousePressed(MouseEvent e) {
		n=(2*n)%111;
		if (n==0) n=1;
		repaint();
	}
	// bei kleinen r entstehen Muster aufgrund der Pixelgröße dokumentation 
	// geschieht extra

	@Override
	public void mouseReleased(MouseEvent e) {
//		blue = red%254;
//		red = 50;
//		repaint();
//		
	}

	@Override
	/*
	 * spielerei:
	 * wenn Kommentare entfernt wird der Grünwert abhängig vom 
	 * Eintrittspunkt der Maus ins Fenster geändert
	*/
	public void mouseEntered(MouseEvent e) {
		//green=e.getX()%254;
		//repaint();
		
	}

	@Override
	/*
	 * spielerei:
	 * wenn kommentare entfernt wird der Rotwert abhängig vom 
	 * Eintrittspunkt der Maus ins Fenster geändert
	*/
	public void mouseExited(MouseEvent e) {
		//red = e.getY()%254;
		//repaint();
	}		
}

