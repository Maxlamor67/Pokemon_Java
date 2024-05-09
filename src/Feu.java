public class Feu extends Type {
    public Feu(){
        super();
    }
    @Override
    public Type getAvantage(){
        return new Air();
    }
    @Override
    public Type getType(){
        return new Feu();
    }
}