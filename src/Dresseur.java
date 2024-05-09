import java.util.ArrayList;
import java.util.List;

public class Dresseur {
    protected String nom;
    protected List<CartePokemon> main;
    protected List<CartePokemon> pioche;
    protected List<CartePokemon> defausse;

    protected List<CartePokemon> terrain;
    private int vieRestante = Integer.MAX_VALUE;



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
            main.add(pioche.remove(0));
        }
    }



    public void afficherPioche() {
        System.out.println("pioche: " + pioche.size() + " pokemons");
    }

    public void afficherDefausse() {
        System.out.println("defausse: " + defausse.size() + " pokemons");
    }

    public void afficherMain() {
        System.out.println("En main:");
        for (CartePokemon carte : main) {
            carte.afficherCarte();
            System.out.printf("  - %-10s, %-2s, Vie: %-2d/%-3d, Attaque: %-2d\n", carte.getNom(), carte.getAffinite(), carte.getVie(), carte.getVieMax(), carte.getAttaque());
        }
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
    public void jouerCarte(CartePokemon carte) {
        if (main.contains(carte)) {
            // Vérifier s'il y a de la place sur le terrain
            if (terrain.size() < 3) {
                // Placer la carte sur le terrain
                terrain.add(carte);
                // Retirer la carte de la main
                main.remove(carte);
                System.out.println("Le pokemon " + carte.getNom() + " a été placé sur le terrain.");
            } else {
                System.out.println("Votre terrain est plein, vous ne pouvez pas jouer de nouvelle carte.");
            }
        } else {
            System.out.println("Cette carte n'est pas dans votre main.");
        }
    }
    public void ajouterCarteAPioche(CartePokemon carte) {
        pioche.add(carte);
    }
    public void ajouterCarte(CartePokemon carte) {
        if (main.size() < 5) {
            main.add(carte);
        } else {
            System.out.println("La main du dresseur est pleine. Impossible d'ajouter une nouvelle carte.");
        }
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
    public boolean peutAttaquer() {
        return terrain.size() >= 3 ;
    }

    public String afficherCartesTerrainAvecIndex() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < terrain.size(); i++) {
            CartePokemon pokemon = terrain.get(i);
            sb.append(i + 1).append(". ").append(pokemon.getNom()).append(", ").append(pokemon.getAffinite()).append(", Vie: ").append(pokemon.getVie()).append("/").append(pokemon.getVieMax()).append(", Attaque: ").append(pokemon.getAttaque()).append("\n");
        }
        return sb.toString();
    }

    public int getVieRestante() {
        return vieRestante;
    }

    public void setVieRestante(int vieRestante) {
        this.vieRestante = vieRestante;
    }




}


