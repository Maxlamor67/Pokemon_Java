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
    public int getUtilisation() {
        return m_utilisation;
    }

    @Override
    public TypePouvoir getType() {
        return m_type;
    }

    @Override
    public String toString() {
        return "Peur";
    }
}