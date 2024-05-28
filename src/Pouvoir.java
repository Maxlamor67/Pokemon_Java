public abstract class Pouvoir {


    public Pouvoir() {};

    public void utiliserPouvoir(CartePokemon pokemon){};
    public abstract int getUtilisation();
    public abstract TypePouvoir getType();
    public abstract String toString();
}