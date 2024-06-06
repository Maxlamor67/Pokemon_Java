package Element;

import java.util.ArrayList;
import java.util.List;

public class Terre extends Type {
    public Terre(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> m_avantages = new ArrayList<>();
        m_avantages.add(new Eau());
        m_avantages.add(new Plomb());
        return m_avantages;
    }

    @Override
    public Type getType(){
        return new Terre();
    }

    public String toString() {return "Element.Terre";}
}