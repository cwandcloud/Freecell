package p2.freecell;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import p2.carta.*;


public class FreeCell extends JFrame {

	private ZonaJogo mesaJogo = new ZonaJogo();

	private static final int NUM_COLUNAS = 8;
	private static final int NUM_CELULAS = 4;
	private static final int NUM_CASAS = 4;
	private static final int IDX_COLUNA = 0;	
	private static final int IDX_CELULA = NUM_COLUNAS;
	private static final int IDX_CASA =  NUM_COLUNAS+NUM_CELULAS;

	private int click = 1;

	private Baralho baralho = new Baralho( 73, 97, "cartaswin.gif", 1 );
	
	private ContentorCartasDefault[] contentor=new ContentorCartasDefault[NUM_COLUNAS+NUM_CELULAS+NUM_CASAS];
	
	private int origemIdx;
	
	public FreeCell( ){
		setTitle( "Freecell" );
		setSize( 680, 600 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		mesaJogo.setBackground( new Color( 0, 127, 0 ) );
		getContentPane().add( mesaJogo );		
		
		for( int i = IDX_COLUNA; i< IDX_COLUNA+NUM_COLUNAS; i++ ){ 
			contentor[ i ] = new Coluna( new Point( 8 + (baralho.getComprimentoCarta()+8)*i, 150 ),
										baralho.getComprimentoCarta(), baralho.getAlturaCarta());
		}

		for( int i = IDX_CELULA; i< IDX_CELULA+NUM_CELULAS; i++ ){
			contentor[ i ] = new Celula( new Point( 2 + (baralho.getComprimentoCarta()+3)*(i-IDX_CELULA), 0 ) ,
					                     baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );
		}
		
		for( int i = IDX_CASA; i< IDX_CASA+NUM_CASAS; i++ ) {
			contentor[ i ] = new Casa( new Point( 360 + (baralho.getComprimentoCarta()+3)*(i-IDX_CASA), 0 ) ,
					                     baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );			
		}

		colocarComponentes();
		distribuirCartas();
		
		mesaJogo.addMouseListener( new MouseAdapter() {
			public void mousePressed( MouseEvent e ){
				if( e.getButton() != MouseEvent.BUTTON1 )
					return;
				if( click == 1 )
					escolherOrigem( e.getPoint() );
				else{
					escolherDestino( e.getPoint() );
					testarFim();
				}
			}			
		});
		
		mesaJogo.addComponentListener( new ComponentAdapter() {			
			public void componentResized(ComponentEvent e) {
				colocarComponentes();
				repaint();
			}
		});
	}
	
	private void colocarComponentes() {
		int minComp = (contentor[IDX_COLUNA].getComprimento()) * NUM_COLUNAS;
		Rectangle mesa = mesaJogo.getBounds();
		
		int comp = mesa.width < minComp? minComp: mesa.width;
		int cx = (comp - contentor[IDX_COLUNA].getComprimento() * NUM_COLUNAS) / (NUM_COLUNAS + 1); 
		int distCol = cx + contentor[IDX_COLUNA].getComprimento();
				
		for( int i = IDX_COLUNA; i< IDX_COLUNA+NUM_COLUNAS; i++ ){ 
			contentor[ i ].setPosicao( new Point( cx + distCol*i, contentor[IDX_COLUNA].getAltura() + 10 ));
		}

		for( int i = IDX_CELULA; i< IDX_CELULA+NUM_CELULAS; i++ ){
			contentor[ i ].setPosicao( new Point( contentor[i].getComprimento()*(i-IDX_CELULA), 0 ) );
		}
		
		int px = comp - contentor[IDX_CASA].getComprimento()*NUM_CASAS;
		for( int i = IDX_CASA; i< IDX_CASA+NUM_CASAS; i++ ) {
			contentor[ i ].setPosicao( new Point( px+contentor[ i ].getComprimento()*(i-IDX_CASA),0) );			
		}

	}
	
	private void distribuirCartas() {
		baralho.baralhar();
		for( int i=0; i < 52; i++ ) {
			Carta c = baralho.dar( i );
			c.virar();
			contentor[IDX_COLUNA+ i % NUM_COLUNAS ].colocar( c );
		}
	}

		
	private void escolherOrigem( Point pt ) {
		for(int c=0;c<NUM_COLUNAS+NUM_CELULAS;c++) {
			if(contentor[c].estaDentro(pt)) {
				if(contentor[c].estaVazio())
					return;
				origemIdx=c;
				contentor[c].setSeleccionado(true);
				repaint();
				click=2;
				return;
			}
		}
	}

	private void escolherDestino( Point pt ) {
		for(int c=0;c<contentor.length;c++) {
			if(contentor[c].estaDentro(pt)) {
				contentor[origemIdx].setSeleccionado(false);;
				if(contentor[c].podeReceber(contentor[origemIdx].getCarta())) {
					contentor[c].receber(contentor[origemIdx].retirar());
				}
				click=1;
				repaint();
				return;
			}
		}

	}
	
	
	private void testarFim() {
		if( ganhou() )
			JOptionPane.showMessageDialog( this, "Parabens! Ganhou!",
                                           "Freecell", JOptionPane.INFORMATION_MESSAGE);
		else if( perdeu() )
			JOptionPane.showMessageDialog( this, "Ja nao tem mais jogadas validas!!! Perdeu!",
	                                       "Freecell", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean ganhou(){	
		for( int i = IDX_CASA; i< IDX_CASA+NUM_CASAS; i++ ) {
			if(contentor[i].estaVazio())return false;
			if(contentor[i].getCarta().getFace()!=Carta.REI)return false;
		}			
		return true;
	}
	

	private boolean perdeu() {
		if(ganhou())return false;
		for( int i = IDX_COLUNA; i< IDX_COLUNA+NUM_COLUNAS; i++ ){
			if(i<IDX_CASA && contentor[i].estaVazio())return false;
			Carta c=contentor[i].getCarta();
			if(verSeAlguemPodeReceber(c))return false;
		}
		return true;
	}

	private boolean verSeAlguemPodeReceber(Carta c) {
		for(int i=0;i<contentor.length;i++)
			if(contentor[i].podeReceber(c))return true;
		return false;
	}

	class ZonaJogo extends JPanel {		
		public void paint( Graphics g ){
			super.paint( g );
			
			for( ContentorCartas c : contentor )
			      c.desenhar( g );
		}		
	}

	public static void main(String[] args) {
		FreeCell jogo = new FreeCell( );
		jogo.setVisible( true );
	}	
}
