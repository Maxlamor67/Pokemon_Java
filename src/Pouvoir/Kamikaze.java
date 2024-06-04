package Pouvoir;
import Joueur.CartePokemon;

public class Kamikaze extends Pouvoir {
    private int m_utilisation;
    private TypePouvoir m_type;

    public Kamikaze(){
        super();
        m_utilisation = 1;
        this.m_type = TypePouvoir.ENNEMI;
    }

    public void utiliserPouvoir(CartePokemon pokemon, CartePokemon other) {
        pokemon.setVie(0);

        other.setVie(0);
        if(m_utilisation != 0){
            m_utilisation--;
        }
    }

    public int nbrUtilisation() {
        return m_utilisation;
    }

    public TypePouvoir getTypePouvoir() {return m_type;}


    public String toString() {return "Kamikaze";}
    @Override
    public String getDescription() {
        return "Kamikaze, à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. Les deux Pokémons sont alors éliminés.\n";
    }

}
