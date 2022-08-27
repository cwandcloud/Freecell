package p2.carta;

import java.awt.Graphics;
import java.awt.Point;

public interface ContentorCartas {	
	
	public boolean podeReceber(Carta c);
	public void colocar( Carta c );
	public void setPosicao( Point p );
	public void desenhar( Graphics g );

}