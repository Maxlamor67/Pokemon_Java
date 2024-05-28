public class Resistance extends Pouvoir {
    private int m_resistance;
    private int m_utilisation;
    private TypePouvoir m_type;
    public Resistance() {
        super();
        this.m_resistance = 10;
        this.m_utilisation = 1;
        this.m_type = TypePouvoir.ALLIE;
    }

    public void utiliserPouvoir(CartePokemon pokemon) {
        pokemon.setResistance(this.m_resistance);
        m_utilisation = 0;

    }

    public int getResistance() {
        return m_resistance;
    }

    public int getUtilisation() {
        return m_utilisation;
    }

    public void setUtilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public TypePouvoir getType() {return m_type;}

    public String toString() {return "Resistance";}
}
