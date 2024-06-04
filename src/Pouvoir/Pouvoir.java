package Pouvoir;
import Joueur.CartePokemon;

public abstract class Pouvoir {


    public Pouvoir() {};

    public void utiliserPouvoir(CartePokemon pokemon){};
    public void utiliserPouvoir(CartePokemon pokemon, CartePokemon other){};
    public abstract int nbrUtilisation();

    public abstract TypePouvoir getTypePouvoir();
    public abstract String toString();

    public abstract String getDescription();

}