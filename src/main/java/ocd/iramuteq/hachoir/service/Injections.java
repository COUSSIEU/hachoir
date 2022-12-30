package ocd.iramuteq.hachoir.service;

public class Injections {

    private static final String TEXTE = "**** *var1_mod";

	private static final String PARAGRAPHE = "-*paragraphe_";

//	private static Logger LOG = LoggerFactory.getLogger(Injections.class);

	private int 		compteur = 0;
	private String 		prefixe = TEXTE ;

	public Injections() {
	}

	@Override
	public String toString() {
		final String injection = prefixe+compteur ++;
		prefixe = PARAGRAPHE ;
		return injection;	
		

	}
	
	

}
