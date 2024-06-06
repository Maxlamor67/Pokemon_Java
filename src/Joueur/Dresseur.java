package Joueur;

import java.util.ArrayList;
import java.util.List;

public class Dresseur {
    protected String m_nom;
    protected List<CartePokemon> m_main;
    protected List<CartePokemon> m_pioche;
    protected List<CartePokemon> m_defausse;

    protected List<CartePokemon> m_terrain;
    private int m_vieRestante = Integer.MAX_VALUE;



    public Dresseur(String nom) {
        this.m_nom = nom;
        this.m_main = new ArrayList<>();
        this.m_pioche = new ArrayList<>();
        this.m_defausse = new ArrayList<>();
        this.m_terrain = new ArrayList<>();

    }


    public List<CartePokemon> getTerrain() {
        return m_terrain;
    }
    public void piocher() {
        while (m_main.size() < 5 && !m_pioche.isEmpty()) {
            m_main.add(m_pioche.remove(0));
        }
    }



    public void afficherPioche() {
        System.out.println("pioche: " + m_pioche.size() + " pokemons");
    }

    public void afficherDefausse() {
        System.out.println("defausse: " + m_defausse.size() + " pokemons");
    }

    public void afficherMain() {
        System.out.println("En main:");
        for (CartePokemon carte : m_main) {
            carte.afficherCarte();
            System.out.printf("  - %-10s, %-2s, Vie: %-2d/%-3d, Attaque: %-2d\n", carte.getNom(), carte.getAffinite(), carte.getVie(), carte.getM_vieMax(), carte.getAttaque());
        }
    }
    public String getNom() {
        return m_nom;
    }
    public List<CartePokemon> getMain() {
        return m_main;
    }
    public List<CartePokemon> getPioche() {
        return m_pioche;
    }
    public void jouerCarte(CartePokemon carte) {
        if (m_main.contains(carte)) {
            // Vérifier s'il y a de la place sur le terrain
            if (m_terrain.size() < 3) {
                // Placer la carte sur le terrain
                m_terrain.add(carte);
                // Retirer la carte de la main
                m_main.remove(carte);
                System.out.println("Le pokemon " + carte.getNom() + " a été placé sur le terrain.");
            } else {
                System.out.println("Votre terrain est plein, vous ne pouvez pas jouer de nouvelle carte.");
            }
        } else {
            System.out.println("Cette carte n'est pas dans votre main.");
        }
    }
    public void ajouterCarteAPioche(CartePokemon carte) {
        m_pioche.add(carte);
    }
    public void ajouterCarte(CartePokemon carte) {
        if (m_main.size() < 5) {
            m_main.add(carte);
        } else {
            System.out.println("La main du dresseur est pleine. Impossible d'ajouter une nouvelle carte.");
        }
    }
    public boolean placerPokemonSurTerrain(CartePokemon carte) {
        if (m_terrain.size() < 3) {
            m_terrain.add(carte);
            m_main.remove(carte);
            return true;
        } else {
            System.out.println("Le terrain est plein. Vous ne pouvez pas placer plus de Pokémon.");
            return false;
        }
    }
    public void defausserPokemon(CartePokemon pokemon) {
        // Recherche l'index du Pokémon dans la liste des Pokémons sur le terrain
        int index = m_terrain.indexOf(pokemon);

        if (index != -1) {
            // Si le Pokémon est trouvé, on le supprime de la liste des Pokémons sur le terrain
            m_terrain.remove(index);

            // Puis on l'ajoute à la défausse
            m_defausse.add(pokemon);
        }
    }




}


