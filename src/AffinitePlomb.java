public class AffinitePlomb extends Pouvoir {
    private int m_utilisation;
    private TypePouvoir m_type;

    public AffinitePlomb() {
        super();
        this.m_utilisation = 1;
        this.m_type = TypePouvoir.ENNEMI;
    }

    public void utiliserPouvoir(CartePokemon pokemon) {
        if (m_utilisation > 0) {
            pokemon.setAffinite(new Plomb());
            m_utilisation = 0;
        }    }

    public void utiliserPouvoir(CartePokemon pokemon, CartePokemon other) {
        m_utilisation = 0;
    }

    public int getUtilisation() {
        return m_utilisation;
    }

    public void setUtilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public TypePouvoir getType() {
        return m_type;
    }

    public String toString() {
        return "AffinitePlomb";
    }
}
