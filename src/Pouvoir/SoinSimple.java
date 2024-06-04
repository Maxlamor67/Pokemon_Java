package Pouvoir;
import Joueur.CartePokemon;

public class SoinSimple extends Pouvoir {
    private int m_soin;
    private int m_utilisation;
    private TypePouvoir m_type;

    public SoinSimple() {
        super();
        this.m_soin = 30;
        this.m_utilisation = 1000;
        this.m_type = TypePouvoir.TOUTCAMP;
    }

    public void utiliserPouvoir(CartePokemon pokemon) {
        if(m_utilisation > 0){
            if(pokemon.getVie()+m_soin > pokemon.getVieMax()) {
                pokemon.setVie(pokemon.getVieMax());
            }
            else{
                pokemon.setVie(pokemon.getVie()+m_soin);
            }
            m_utilisation--;
        }
    }


    public int getUtilisation() {
        return m_utilisation;
    }

    public void setUtilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public TypePouvoir getType() {return m_type;}


    public String toString() {return "SoinSimple";}
    @Override
    public String getDescription() {
        return "Soin simple, utilisable à chaque tour : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même).\n" +
                " Celui-ci regagne 30 points de vie (mais ne peut pas dépasser son nombre de points de vie initial).\n";
    }
}
