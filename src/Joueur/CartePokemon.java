package Joueur;

import Element.Type;
import Pouvoir.Pouvoir;

import java.util.List;

public class CartePokemon {
    private String m_nom;
    private Type m_affinite;
    private Pouvoir m_pouvoir;
    private int m_vie;
    private int m_vieMax;
    private int m_attaque;
    private int m_resistance;

    private boolean aDejaAttaque;

    public CartePokemon(String nom, Type affinite, Pouvoir pouvoir, int vie, int m_vieMax, int attaque) {
        this.m_nom = nom;
        this.m_affinite = affinite;
        this.m_pouvoir = pouvoir;
        this.m_vie = vie;
        this.m_vieMax = m_vieMax;
        this.m_attaque = attaque;
        this.m_resistance = 0;
        this.aDejaAttaque = false;
    }

    public String getNom() {
        return m_nom;
    }

    public int getM_vieMax() {
        return m_vieMax;
    }

    public int getAttaque() {
        return m_attaque;
    }

    public int getVie() {
        return m_vie;
    }

    public void setVie(int vie) {
        this.m_vie = Math.max(0, vie); // Assure que la vie ne peut pas être négative
    }

    public Type getAffinite() {
        return m_affinite;
    }

    public Pouvoir getPouvoir() {
        return m_pouvoir;
    }

    public void setResistance(int resistance) {
        this.m_resistance = resistance;
    }

    public boolean getADejaAttaque() {
        return this.aDejaAttaque;
    }


    public void attaquer(CartePokemon pokemonCible) {
        List<Type> affiniteAvantages = this.m_affinite.getAvantages();
        List<Type> cibleAvantages = pokemonCible.getAffinite().getAvantages();

        if (affiniteAvantages.contains(pokemonCible.getAffinite())) {
            pokemonCible.setVie(pokemonCible.getVie() - this.m_attaque + pokemonCible.m_resistance - 10);
            System.out.println(this.m_nom + " a infligé " + (this.m_attaque - pokemonCible.m_resistance + 10) + " dégâts à " + pokemonCible.m_nom);
        } else if (cibleAvantages.contains(this.getAffinite())) {
            pokemonCible.setVie(pokemonCible.getVie() - this.m_attaque + pokemonCible.m_resistance + 10);
            System.out.println(this.m_nom + " a infligé " + (this.m_attaque - pokemonCible.m_resistance - 10) + " dégâts à " + pokemonCible.m_nom);
        } else {
            pokemonCible.setVie(pokemonCible.getVie() - this.m_attaque + pokemonCible.m_resistance);
            System.out.println(this.m_nom + " a infligé " + (this.m_attaque - pokemonCible.m_resistance) + " dégâts à " + pokemonCible.m_nom);
        }
    }

    public void setADejaAttaque(boolean aDejaAttaque) {
        this.aDejaAttaque = aDejaAttaque;
    }
    public void setAffinite(Type affinite) {
        this.m_affinite = affinite;
    }
    public void afficherCarte() {
        System.out.println("  *--------------------*");
        System.out.printf("  | Attaque: %-2d        |\n", m_attaque);
        System.out.printf("  | Vie: %-2d/%-3d       |\n", m_vie, m_vieMax);
        System.out.printf("  | Affinite : %-6s  |\n", m_affinite.getClass().getSimpleName());
        if (m_pouvoir == null) {
            System.out.printf("  | Pouvoir.Pouvoir : None     |\n");
        } else {
            System.out.printf("  | Pouvoir.Pouvoir : %-6s  |\n", m_pouvoir);
        }
        System.out.printf("  |     %-10s     |\n", m_nom);
        System.out.println("  *--------------------*");
    }

    public void setAttaque(int attaque) {
        this.m_attaque = attaque;
    }


}
