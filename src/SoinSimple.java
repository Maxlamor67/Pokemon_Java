public class SoinSimple extends Pouvoir{
    private int m_soin;
    private int m_utilisation;

    public SoinSimple() {
        super();
        this.m_soin = 30;
        this.m_utilisation = 1;
    }

    public void soigner(CartePokemon pokemon) {
        if(pokemon.getVie()+m_soin > pokemon.getVieMax()) {
            pokemon.setVie(pokemon.getVieMax());
        }
        else{
            pokemon.setVie(pokemon.getVie()+m_soin);
        }
        m_utilisation = 0;
    }

    public int getSoin() {
        return m_soin;
    }

    public int getUtilisation() {
        return m_utilisation;
    }

    public void setUtilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public String toString() {return "SoinSimple";}
}
