package Pouvoir;
import Joueur.*;
import Element.*;

public class AffiniteEther extends Pouvoir {
    private int m_utilisation;
    private TypePouvoir m_type;

    public AffiniteEther() {
        super();
        this.m_utilisation = 1;
        this.m_type = TypePouvoir.TOUTCAMP;
    }

    public void utiliserPouvoir(CartePokemon pokemon) {
        if (m_utilisation > 0) {
            pokemon.setAffinite(new Ether());
            m_utilisation--;
        }
    }

    public void utiliserPouvoir(CartePokemon pokemon, CartePokemon other) {
        m_utilisation = 0;
    }

    public int nbrUtilisation() {
        return m_utilisation;
    }

    public void setUtilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public TypePouvoir getTypePouvoir() {
        return m_type;
    }

    public String toString() {
        return "AffiniteEther";
    }

    @Override
    public String getDescription() {
        return "Affinité Ether, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). \n" +
                "Le Pokémon change son affinité pour une affinité avec l'Ether. L'Ether est un nouvel élément ayant un avantage sur tous les autres.\n";
    }
}
