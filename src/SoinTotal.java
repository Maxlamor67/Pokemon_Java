public class SoinTotal extends Pouvoir {

    private int m_utilisation;

    public SoinTotal() {
        super();
        this.m_utilisation = 1;
    }

    public void soignerTotalement(CartePokemon pokemon) {
        pokemon.setVie(pokemon.getVieMax());
        m_utilisation = 0;
    }

    public int getUtilisation() {
        return m_utilisation;
    }

    public void setUtilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public String toString() {return "SoinSimple";}

}