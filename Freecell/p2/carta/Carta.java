package p2.carta;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Representa uma carta de jogar, com face e naipe. Tam ainda uma posi��o
 * no �cran, pode estar ou n�o selecionada, etc
 */
public class Carta {
	
	// constantes para os naipes
	// isto devia ser uma enumera��o
	public static final int OUROS   = 0; 
	public static final int COPAS   = 1; 
	public static final int PAUS    = 2;  
	public static final int ESPADAS = 3; 
	public static final int NENHUM  = 4; 

	// constantes para os valores
	// tamb�m devia ser uma enumera��o
	public static final int AS     = 0;
	public static final int DUQUE  = 1;
	public static final int TERNO  = 2;
	public static final int QUADRA = 3;
	public static final int QUINA  = 4;
	public static final int SENA   = 5;
	public static final int SETE   = 6;
	public static final int OITO   = 7;
	public static final int NOVE   = 8;
	public static final int DEZ    = 9;
	public static final int VALETE = 10;
	public static final int DAMA   = 11;
	public static final int REI    = 12;
	
	public static final int JOKER = 13;
	
	// constantes para as cores dos naipes
	public static final int VERMELHA = 0;
	public static final int PRETA    = 1;
	
	// os nomes das vari�veis indicam o que elas representam
	private int face;
	private int naipe;
	private boolean faceUp = false;
	private boolean select = false;
	private Point topo = new Point( 0,0 );
	private int comprimento = 71;
	private int altura = 96;
	
	private BufferedImage vistaCima;          // imagem da carta vista de cima
	private BufferedImage vistaCostas;        // imagem da carta virada de costas
	private BufferedImage vistaSeleccionada;  // imagem da carta seleccionada (s� de frente)
	
	/** criar uma dada carta com uma dada face e um dado naipe
	 * 
	 * @param fc face da carta (de AS a REI )
	 * @param np naipe da carta (de OUROS a ESPADAS )
	 */ 
    public Carta( int fc, int np ) {
    	face = fc;
    	naipe = np;
    }
    

    /**
     *  virar a carta. Se estiver de face para cima fica virada para baixo e vice-versa
     */
    public void virar( ) {
    	faceUp = !faceUp;
    }
    

    /**
     * Define se a carta est� virada para cima ou para baixo 
     * @param vir true = virada para cima e false = virada para baixo
     */
    public void setFaceUp( boolean vir ){
    	faceUp = vir;
    }
    
    
    /**
     * indica se est� virada para cima
     * @return true = virada para cima, false = virada para baixo
     */
    public boolean getFaceUp( ){
    	return faceUp;
    }
    

    /**
     * seleciona ou desseleciona a carta
     * @param s true = selecionar, false = desselecionar
     */
    public void  setSelecionada( boolean s ) {  
    	select = s;
    }         
    
    
    /**
     * indica se a carta est� selecionada 
     * @return true se carta est� selecionada
     */
    public boolean getSeleccionada( ) {
    	return select;
    }

    
    /**
     * devolve o valor da carta
     * @return o valor da carta de AS a REI   
     */    
    public int getFace( ) {
    	return face;
    }           
    
    /**
     * devolve o naipe da carta
     * @return o naipe da carta de OUROS a ESPADAS
     */
    public int getNaipe( ) {
    	return naipe;
    }
    
    /**
     * devolve a cor da carta
     * @return VERMELHA ou PRETA
     */
    public int getCor( ) {
    	if( naipe == COPAS || naipe == OUROS )
    		return VERMELHA;
    	else 
    		return PRETA;
    }
    
    
    /**
     * define uma nova posi��o para a carta
     * @param p a nova posi��o (em pixeis) da carta
     */
    public void setPosicao( Point p ){
    	topo = p;
    }
    
    
    /**
     * devolve a posi��o da carta em pixeis
     * @return a posi��o da carta em pixeis
     */
    public Point getPosicao( ){
    	return topo;
    }
    
    
    /**
     * define um novo tamanho para a carta. Este tamanho serve apenas para efeitos de 
     * verificar se uma dada coordenada est� dentro ou fora da carta 
     * @param comp novo comprimento (em pix�is) da carta
     * @param alt nova altura (em pix�is) da carta
     */
    public void setTamanho( int comp, int alt ){
    	comprimento = comp;
    	altura = alt;
    }
    
    
    /**
     * retorna o comprimento da carta 
     * @return o comprimento (em pix�is) da carta
     */
    public int getComprimento( ){
    	return comprimento;
    }
    
    
    /**
     * retorna a altura da carta 
     * @return a altura (em pix�is) da carta
     */
    public int getAltura( ) {
    	return altura;
    }
    
    
    /**
     * define qual a imagem da carta quando est� virada para cima
     * @param img a imagem da carta virada para cima
     */
    public void setVistaCima( BufferedImage img ){
    	vistaCima = img;
    }
    
    /**
     * devolve a imagem da carta quando est� virada para cima
     * @return a imagem da carta virada para cima
     */
    public BufferedImage getVistaCima(  ){
    	return vistaCima;
    }
    
    
    /**
     * define qual a imagem da carta quando est� virada para baixo
     * @param img a imagem da carta virada para baixo
     */
    public void setVistaCostas( BufferedImage img ){
    	vistaCostas = img;
    }

    
	/**
	 * devolve qual a imagem da carta quando est� virada para baixo
	 * @return a imagem da carta virada para baixo
	 */
    public BufferedImage getVistaCostas(  ){
    	return vistaCostas;
    }
    
    
    /**
     * define qual a imagem da carta quando est� selecionada
     * @param img a imagem da carta quando est� selecionada
     */
    public void setVistaSelecionada( BufferedImage img ){
    	vistaSeleccionada = img;
    }

    
    /**
     * devolve qual a imagem da carta quando est� selecionada
     * @param a imagem da carta quando est� selecionada
     */
    public BufferedImage getVistaSelecionada(  ){
    	return vistaSeleccionada;
    }
    
    
	/**
	 * indica se uma dada coordenada est� dentro do espa�o da carta 
	 * @param pt a coordenada a verificar
	 * @return true se a coordenada est� dentro, false caso contr�rio 
	 */
    public boolean estaDentro( Point pt ) {
    	 // para estar dentro a coordenada dever� estar entre o x e x+comp e y e y+comp da carta
    	 return ( ( pt.x >= topo.x && pt.x <= topo.x + comprimento ) && 
    	          ( pt.y >= topo.y && pt.y <= topo.y + altura      )  );
    }
    
    
	/**
	 * desenha a carta no �cran
	 * @param g sistema gr�fico onde se vai desenhar
	 */
    public void  desenhar( Graphics g ){
    	if( faceUp ) {
    		if( select )
    			g.drawImage( vistaSeleccionada, topo.x, topo.y, null );
    		else    	
    			g.drawImage( vistaCima, topo.x, topo.y, null );
    	}    		
    	else
    		g.drawImage( vistaCostas, topo.x, topo.y, null );
    }
    

}