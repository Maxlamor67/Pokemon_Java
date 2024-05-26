public class Terre extends Type {
    public Terre(){
        super();
    }
    @Override
    public Type getAvantage(){
        return new Eau();
    }

    @Override
    public Type getType(){
        return new Terre();
    }

    public String toString() {return "Terre";}

}