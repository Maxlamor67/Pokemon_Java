package Pouvoir;
import Joueur.CartePokemon;

public class FerveurGuerriere extends Pouvoir {
    private int m_utilisation;
    private TypePouvoir m_type;

    public FerveurGuerriere() {
        super();
        this.m_utilisation = 1;
        this.m_type = TypePouvoir.ALLIE;
    }

    @Override
    public void utiliserPouvoir(CartePokemon pokemon) {
        if (m_utilisation > 0) {
            pokemon.setAttaque(pokemon.getAttaque() + 10);
            m_utilisation = 0;
        }
    }

    @Override
    public int getUtilisation() {
        return m_utilisation;
    }

    @Override
    public TypePouvoir getType() {
        return m_type;
    }

    @Override
    public String toString() {
        return "FerveurGuerriere";
    }
    @Override
    public String getDescription() {
        return "Ferveur guerrière, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). " +
                "Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégâts de plus.\n";
    }
}
