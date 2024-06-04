package Pouvoir;
import Joueur.CartePokemon;

public class Resistance extends Pouvoir {
    private int m_resistance;
    private int m_utilisation;
    private TypePouvoir m_type;
    public Resistance() {
        super();
        this.m_resistance = 10;
        this.m_utilisation = 1;
        this.m_type = TypePouvoir.TOUTCAMP;
    }

    public void utiliserPouvoir(CartePokemon pokemon) {
        pokemon.setResistance(this.m_resistance);
        if(m_utilisation != 0){
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

    public String toString() {return "Resistance";}
    @Override
    public String getDescription() {
        return "Résistance, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). \n" +
                "Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, à chaque attaque reçue celui-ci subit subit 10 dégâts de moins.\n"
        ;
    }
}
