public abstract class Pouvoir {


    public Pouvoir() {};

    public void utiliserPouvoir(CartePokemon pokemon){};
    public void utiliserPouvoir(CartePokemon pokemon, CartePokemon other){};
    public abstract int getUtilisation();
    public abstract TypePouvoir getType();
    public abstract String toString();
}