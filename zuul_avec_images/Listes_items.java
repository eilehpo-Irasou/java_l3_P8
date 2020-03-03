import java.util.*;

public class Listes_items
{
	private HashMap<String, Items> Inventaire;


	public Listes_items()
	{
		this.Inventaire = new HashMap<>();
	}

	public String get_ItemsString()
    {
        String R_String="";
        Set<String> Keys = Inventaire.keySet();
        for (String Items : Keys ) 
        {
        	R_String += "items "+Items+"\n";
        	
        }
        return R_String;

    }

    public void Prendre_item(final String Stringitem, final Items bItems )
    {
    	this.Inventaire.put(Stringitem,bItems);
    }

     public void Retirer_item(final String Stringitem )
    {
    	this.Inventaire.remove(Stringitem);
    }

/*public void	addItem(final String pName,final Items pItem){
	Inventaire.put(pName,pItem);}*/


    public Items getItems(final String Itms)
    {
    	return this.Inventaire.get(Itms);

    }


























}