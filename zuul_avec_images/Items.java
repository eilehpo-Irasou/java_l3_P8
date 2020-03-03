/*
Classe pour les items du jeu.
On peut décrire l'objet
connaitre son poid, et son prix.*/

public class Items
{
	private String Description_objet;
	private double son_prix;
	private double son_poids;

	// contructeur de la classe Items
	/* on utilise le mot clé "final"
	pour dire qu'un objet ne changera pas
	de caractéristiques pendant tout 
	le long du jeu.*/
	public Items(final String DSO, final double SPX, final double SPS)
	{
		this.Description_objet = DSO;
		this.son_prix = SPX;
		this.son_poids = SPS;
	}

	// les méthodes get

	// faire la description d'un objet.

	public String Descripion_item()
	{
		return this.Description_objet;
	}

	// connaitre poids d'un objet

	public double Descripion_poids()
	{
		return this.son_poids;
	}

	// connaitre prix d'un objet
	public double Descripion_prix()
	{
		return this.son_prix;
	}

// les methodes set
	public void _DSO(final String DSO)
	{
		this.Description_objet=DSO;
	}

	public void _SPX(final double SPX)
	{
		this.son_prix=SPX;
	}

	public void _SPS(final double SPS)
	{
		this.son_poids=SPS;
	}


	public String toString()
	{
	return this.Description_objet + "L'item  pèse " 
	+this.son_prix+"argent et son poids"+this.son_poids+"\n";	
	}



}