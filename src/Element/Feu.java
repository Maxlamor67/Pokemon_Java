package Element;

import java.util.ArrayList;
import java.util.List;

public class Feu extends Type {
    public Feu(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> m_avantages = new ArrayList<>();
        m_avantages.add(new Air());
        m_avantages.add(new Plomb());
        return m_avantages;
    }

    @Override
    public Type getType(){
        return new Feu();
    }

    public String toString() {return "Element.Feu";}
}
