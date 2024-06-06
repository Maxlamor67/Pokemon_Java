package Joueur;

import java.util.ArrayList;
import java.util.List;

public class Dresseur {
    protected String nom;
    protected List<CartePokemon> main;
    protected List<CartePokemon> pioche;
    protected List<CartePokemon> defausse;

    protected List<CartePokemon> terrain;




    public Dresseur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
        this.pioche = new ArrayList<>();
        this.defausse = new ArrayList<>();
        this.terrain = new ArrayList<>();

    }


    public List<CartePokemon> getTerrain() {
        return terrain;
    }
    public void piocher() {
        while (main.size() < 5 && !pioche.isEmpty()) {
            main.add(pioche.removeFirst());
        }
    }



    public void afficherPioche() {
        System.out.println("pioche: " + pioche.size() + " pokemons");
    }

    public void afficherDefausse() {
        System.out.println("defausse: " + defausse.size() + " pokemons");
    }


    public String getNom() {
        return nom;
    }
    public List<CartePokemon> getMain() {
        return main;
    }
    public List<CartePokemon> getPioche() {
        return pioche;
    }

    public void ajouterCarteAPioche(CartePokemon carte) {
        pioche.add(carte);
    }

    public boolean placerPokemonSurTerrain(CartePokemon carte) {
        if (terrain.size() < 3) {
            terrain.add(carte);
            main.remove(carte);
            return true;
        } else {
            System.out.println("Le terrain est plein. Vous ne pouvez pas placer plus de Pokémon.");
            return false;
        }
    }
    public void defausserPokemon(CartePokemon pokemon) {
        // Recherche l'index du Pokémon dans la liste des Pokémons sur le terrain
        int index = terrain.indexOf(pokemon);

        if (index != -1) {
            // Si le Pokémon est trouvé, on le supprime de la liste des Pokémons sur le terrain
            terrain.remove(index);

            // Puis on l'ajoute à la défausse
            defausse.add(pokemon);
        }
    }




}


