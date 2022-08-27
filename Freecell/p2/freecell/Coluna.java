package p2.freecell;

import java.awt.Graphics;
import java.awt.Point;


import p2.carta.*;

public class Coluna  extends ContentorCartasDefault{

	public Coluna(Point t, int comp, int alt) {
		super(t,comp,alt);		
	}
	
	public boolean podeReceber(Carta c) {
		// se esta vazio pode receber qualquer carta
		if( estaVazio() )
			return true;
		
		// se nao esta vazio tem de receber de cor diferente e consecutiva
		Carta ultima = getCarta( );
		return ( ultima.getCor() != c.getCor() ) &&
		       ( ultima.getFace() - 1 == c.getFace() ); 
	}
	
	public void colocar( Carta c ){
		if( estaVazio() ){
			c.setPosicao( getPosicao() );			
		}
		else {			
			Carta ultima = getCarta( );
		
			Point nova = new Point( ultima.getPosicao().x, ultima.getPosicao().y + ultima.getAltura() / 3 );
			c.setPosicao( nova );
		}		
		asCartas.add( c );		
	}


	public void setPosicao( Point p ){
		// reposicionar o topo
		topo = p;
		
		// reposicionar as cartas
		int y = topo.y; 
		int altura = estaVazio()? 0: getCarta().getAltura() / 3;
		for( int i = 0; i < asCartas.size(); i++ )
			asCartas.get( i ).setPosicao( new Point( topo.x, topo.y + i*altura ) );
	}

	
	public void desenhar( Graphics g ){
		for( int i=0; i < asCartas.size(); i++ ){
			Carta card = (Carta) asCartas.get( i );
			card.desenhar( g );
		}					
	}	
	
}
