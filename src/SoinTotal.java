public class SoinTotal extends Pouvoir {

    private int m_utilisation;
    private TypePouvoir m_type;

    public SoinTotal() {
        super();
        this.m_utilisation = 1;
        this.m_type = TypePouvoir.ALLIE;
    }

    public void utiliserPouvoir(CartePokemon pokemon) {
        pokemon.setVie(pokemon.getVieMax());
        if(m_utilisation != 0){
            m_utilisation--;
        }
    }

    public int getUtilisation() {
        return m_utilisation;
    }

    public void setUtilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public TypePouvoir getType() {return m_type;}


    public String toString() {return "SoinTotal";}

}