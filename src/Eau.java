public class Eau extends Type {
    public Eau(){
        super();
    }
    @Override
    public Type getAvantage(){
        return new Feu();
    }

    @Override
    public Type getType(){
        return new Eau();
    }

    public String toString() {return "Eau";}

}