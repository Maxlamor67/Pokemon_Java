public class Resistance extends Pouvoir {
    private int m_resistance;
    private int m_utilisation;

    public Resistance() {
        super();
        this.m_resistance = 10;
        this.m_utilisation = 1;
    }

    public void resister(CartePokemon pokemon) {
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

    public String toString() {return "Resistance";}
}
