public class Air extends Type {
    public Air(){
        super();
    }
    @Override
    public Type getAvantage(){
        return new Terre();
    }
}