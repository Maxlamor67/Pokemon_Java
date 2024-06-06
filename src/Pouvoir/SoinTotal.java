package Pouvoir;
import Joueur.CartePokemon;

public class SoinTotal extends Pouvoir {

    private int m_utilisation;
    private TypePouvoir m_type;

    public SoinTotal() {
        super();
        this.m_utilisation = 1;
        this.m_type = TypePouvoir.TOUTCAMP;
    }

    public void utiliserPouvoir(CartePokemon pokemon) {
        if(m_utilisation > 0){
            pokemon.setVie(pokemon.getM_vieMax());
            m_utilisation--;
        }
    }

    public int nbrUtilisation() {
        return m_utilisation;
    }

    public void setUtilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public TypePouvoir getTypePouvoir() {return m_type;}


    public String toString() {return "SoinTotal";}
    @Override
    public String getDescription() {
        return "Soin total, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). \n" +
                "Celui-ci regagne toute sa vie.\n";
    }

}