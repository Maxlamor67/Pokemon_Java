import java.util.ArrayList;
import java.util.List;

public class Ether extends Type {
    public Ether(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> avantages = new ArrayList<>();
        avantages.add(new Terre());
        avantages.add(new Eau());
        avantages.add(new Feu());
        avantages.add(new Air());
        avantages.add(new Plomb());
        avantages.add(new Ether());
        return avantages;
    }

    @Override
    public Type getType(){
        return new Ether();
    }

    public String toString() {return "Ether";}
}
