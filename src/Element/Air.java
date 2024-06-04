package Element;

import java.util.ArrayList;
import java.util.List;

public class Air extends Type {
    public Air(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> avantages = new ArrayList<>();
        avantages.add(new Terre());
        avantages.add(new Plomb());
        return avantages;
    }

    @Override
    public Type getType(){
        return new Air();
    }

    public String toString() {return "Element.Air";}
}