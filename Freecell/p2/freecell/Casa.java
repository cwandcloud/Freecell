package p2.freecell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import p2.carta.Carta;
import p2.carta.ContentorCartasDefault;

public class Casa  extends ContentorCartasDefault{

	public Casa(Point t, int comp, int alt) {
		super(t,comp,alt);		
	}
	
	public boolean podeReceber(Carta c) {
		if( estaVazio() ) 
			return c.getFace() == Carta.AS;
		else {				
			Carta ultima = getCarta();
			return ultima.getNaipe() == c.getNaipe() && ultima.getFace() + 1 == c.getFace();  
		}
	}
	
	public void colocar( Carta c ){
		c.setPosicao( new Point( getPosicao().x+1, getPosicao().y+1 ) );
		asCartas.add( c );
	}

	public void setPosicao( Point p ){
		// reposicionar o topo
		topo = p;
		
		// reposicionar as cartas
		for( int i = 0; i < asCartas.size(); i++ )
			asCartas.get( i ).setPosicao( new Point( topo.x+1, topo.y + 1 ) );
	}

	public void desenhar( Graphics g ){
		int x1 = getPosicao().x;
		int y1 = getPosicao().y;
		int x2 = getPosicao().x + getComprimento();
		int y2 = getPosicao().y + getAltura();
		
		g.setColor( Color.black );
		g.drawLine( x1, y1, x2, y1 );
		g.drawLine( x1, y1, x1, y2 );
		g.setColor( Color.green );	
		g.drawLine( x2, y1+1, x2, y2 );
		g.drawLine( x1+1, y2, x2, y2 );			
		
		for( int i=0; i < asCartas.size(); i++ ){
			Carta card = (Carta) asCartas.get( i );
			card.desenhar( g );
		}					
	}
	
}
