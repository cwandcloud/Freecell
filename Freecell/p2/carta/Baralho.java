package p2.carta;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * Representa um baralho de 52 cartas o qual pode ser embaralhado.
 * 
 */
public class Baralho {
	
	private static final int NUMCARTAS = 52;
	private BufferedImage imgCartas;
	private int comprimentoCarta;
	private int alturaCarta;
	private Carta asCartas[]; // array de cartas
	private int next = 0;     // carta a dar
	
	/**
	 * cria um baralho em que as cartas t m um determinado comprimento e largura e a imagem   dada num ficheiro 
	 * @param comp comprimento (em pix is) de cada carta
	 * @param alt altura (em pix is) de cada carta
	 * @param fichCartas ficheiro gif, ou jpeg, onde est o definidos os desenhos das cartas
	 * @param costas  ndice da imagem a utilizar para as costas da carta
	 */
	public Baralho( int comp, int alt, String fichCartas, int costas ){		
		comprimentoCarta = comp;
		alturaCarta = alt;
		imgCartas = lerImagem( fichCartas );
		asCartas = new Carta[ NUMCARTAS + 1 ]; // 52 cartas mais o joker
		int i = 0;
		
		// definir a imagem das costas
		BufferedImage costasImg = imgCartas.getSubimage( costas * comprimentoCarta, 9*alturaCarta, comprimentoCarta, alturaCarta );
		
		// criar as 52 cartas
		for( int naipe = Carta.OUROS; naipe <= Carta.ESPADAS; naipe++ ){
			for( int face = Carta.AS; face <= Carta.REI; face++ ){
				asCartas[ i ] = new Carta( face, naipe );
				asCartas[ i ].setTamanho(comp, alt);
				asCartas[ i ].setVistaCima( imgCartas.getSubimage(face*comprimentoCarta, naipe*2*alturaCarta, comprimentoCarta, alturaCarta) );
				asCartas[ i ].setVistaSelecionada( imgCartas.getSubimage(face*comprimentoCarta, (naipe*2+1)*alturaCarta, comprimentoCarta, alturaCarta) );
				asCartas[ i ].setVistaCostas( costasImg );  
				i++;
			}
		}
		
		// definir o joker
		asCartas[ NUMCARTAS ] = new Carta( Carta.JOKER, Carta.NENHUM );
		asCartas[ NUMCARTAS ].setVistaCima( imgCartas.getSubimage( 0, 8*alturaCarta, comprimentoCarta, alturaCarta) );
		asCartas[ NUMCARTAS ].setVistaCostas( costasImg );
	}

	
	/**
	 * retorna a pr xima carta. Se n o tiver mais retorna null
	 * @return a pr xima carta.
	 */
	public Carta dar( ){		
		if( next > NUMCARTAS )
			return null;
		next++;
		return asCartas[ next - 1 ];
	}
	
		
	/**
	 * retorna a carta com o indice i.
	 * @param i indice da carta a retirar
	 * @return a carta com o indice i
	 */
	public Carta dar( int i ){
		return asCartas[ i ];
	}
	
	/**
	 * indica se tem mais cartas para dar
	 * @return true = h  mais cartas, false = n o h  mais cartas
	 */
	public boolean temMais(){
		return next < NUMCARTAS;			
	}
	
	
	/**
	 * baralha as cartas presentes no baralho
	 */
	public void baralhar( ){
		// se baralhou voltar tudo ao principio
		next = 0; 
		
		Random rand = new Random();
		// baralhar 200 cartas   sorte
		for( int i=0; i< 200; i++ ) {
			int o = rand.nextInt( NUMCARTAS );
			int d = rand.nextInt( NUMCARTAS );
			Carta troca = asCartas[ o ];
			asCartas[ o ] = asCartas[ d ];
			asCartas[ d ] = troca;
		}
	}
	
	/**
	 * devolve o comprimento das cartas representadas por este baralho
	 * @return o comprimento das cartas representadas por este baralho
	 */
	public int getComprimentoCarta(){
		return comprimentoCarta;		
	}

	
	/**
	 * devolve a altura das cartas representadas por este baralho
	 * @return a altura das cartas representadas por este baralho
	 */
	public int getAlturaCarta(){
		return alturaCarta;		
	}
	
	
    // fun  o para auxiliar na leitura de um ficheiro de imagem
    private BufferedImage lerImagem( String nome ){
        try {
            File f = new File(nome);
            return javax.imageio.ImageIO.read(f);
        } catch (java.io.IOException e) {System.out.println("Erro a ler o ficheiro de imagem: " + nome); }
        return null;
    }
}