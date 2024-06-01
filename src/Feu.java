import java.util.ArrayList;
import java.util.List;

public class Feu extends Type {
    public Feu(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> avantages = new ArrayList<>();
        avantages.add(new Air());
        avantages.add(new Plomb());
        return avantages;
    }

    @Override
    public Type getType(){
        return new Feu();
    }

    public String toString() {return "Feu";}
}
