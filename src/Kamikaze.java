public class Kamikaze extends Pouvoir{
    private int m_utilisation;

    public Kamikaze(){
        super();
        m_utilisation = 1;
    }

    public void exploser(CartePokemon pokemon, CartePokemon other) {
        pokemon.setVie(0);
        other.setVie(0);
        m_utilisation = 0;
    }

    public int getUtilisation() {
        return m_utilisation;
    }

    public void set_utilisation(int utilisation) {
        m_utilisation = utilisation;
    }

    public String toString() {return "SoinSimple";}

}
