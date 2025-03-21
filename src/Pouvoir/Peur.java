package Pouvoir;
import Joueur.CartePokemon;

public class Peur extends Pouvoir {
    private int m_utilisation;
    private TypePouvoir m_type;

    public Peur() {
        super();
        this.m_utilisation = 1;
        this.m_type = TypePouvoir.ENNEMI;
    }

    @Override
    public void utiliserPouvoir(CartePokemon pokemon) {
        if (m_utilisation > 0) {
            pokemon.setAttaque(pokemon.getAttaque() - 10);
            m_utilisation = 0;
        }
    }

    @Override
    public int nbrUtilisation() {
        return m_utilisation;
    }

    @Override
    public TypePouvoir getTypePouvoir() {
        return m_type;
    }

    @Override
    public String toString() {
        return "Peur";
    }
    @Override
    public String getDescription() {
        return "Peur, à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. \n" +
                "Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégats de moins.\n";
    }
}