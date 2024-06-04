package Element;

import java.util.ArrayList;
import java.util.List;

public class Eau extends Type {
    public Eau(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> avantages = new ArrayList<>();
        avantages.add(new Feu());
        avantages.add(new Plomb());
        return avantages;
    }

    @Override
    public Type getType(){
        return new Eau();
    }

    public String toString() {return "Element.Eau";}
}