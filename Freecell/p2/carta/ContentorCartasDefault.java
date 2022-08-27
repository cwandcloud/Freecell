package p2.carta;

import java.awt.Point;
import java.util.ArrayList;

public abstract class ContentorCartasDefault implements ContentorCartas{
	
	protected ArrayList<Carta> asCartas = new ArrayList<Carta>();
	protected Point topo;
	protected int comprimento;
	protected int altura;
	protected boolean selecionado = false;
	
	public ContentorCartasDefault(Point t, int comp, int alt) {
		topo = t;
		comprimento = comp;
		altura = alt;		
	}

	public boolean receber( Carta c ){
		if( !podeReceber( c ) )
			return false;
		colocar( c );
		return true;
	}	

	public Carta retirar( ){		
		return asCartas.size()>0?(Carta) asCartas.remove( asCartas.size()-1 ):null;
	}

	public Carta getCarta( ){
		return asCartas.size()>0?(Carta) asCartas.get( asCartas.size()-1 ):null;
	}	

	public Point getPosicao() {
		return topo;
	}	

	public boolean estaVazio() {
		return asCartas.isEmpty();
	}

	public int getComprimento() {
		return comprimento;
	}
	
	public int getAltura(){
		return altura;
	}
	
	public void limpar() {
		asCartas.clear();
	}
	
	public boolean estaDentro( Point pt ) {
		// ver se clicou em alguma das cartas do componente
		for( int i = 0; i < asCartas.size(); i++ )
			if( ((Carta)asCartas.get( i )).estaDentro( pt ) )
				return true;
		
		// senão ver se clicou na área do componente
		return topo.x <= pt.x && topo.y <= pt.y && topo.x + comprimento >= pt.x && topo.y + altura >= pt.y;
	}

	public void setSeleccionado( boolean sel ) {
		selecionado = sel; 
		if( !estaVazio() )
			getCarta().setSelecionada( sel );
	}

}