public class Terre extends Type {
    public Terre(){
        super();
    }
    @Override
    public Type getAvantage(){
        return new Eau();
    }
}