package Element;

import java.util.ArrayList;
import java.util.List;

public class Eau extends Type {
    public Eau(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> m_avantages = new ArrayList<>();
        m_avantages.add(new Feu());
        m_avantages.add(new Plomb());
        return m_avantages;
    }

    @Override
    public Type getType(){
        return new Eau();
    }

    public String toString() {return "Element.Eau";}
}