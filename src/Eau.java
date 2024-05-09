public class Eau extends Type {
    public Eau(){
        super();
    }
    @Override
    public Type getAvantage(){
        return new Feu();
    }
}