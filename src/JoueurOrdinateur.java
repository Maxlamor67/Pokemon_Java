import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JoueurOrdinateur extends Dresseur {
    private int compteurTour;
    private int compteurAttaque;

    public JoueurOrdinateur(String nom) {
        super(nom);
        compteurTour = 0;
        compteurAttaque = 0;
    }

    public void jouerTour(Jeu jeu) {
        // Pioche des Pokémons jusqu'à en avoir 5 en main ou que la pioche soit vide
        piocher();

        // Place un Pokémon de sa main face visible sur chaque emplacement vide du terrain
        for (int i = 0; i < terrain.size(); i++) {
            if (terrain.get(i) == null && !main.isEmpty()) {
                terrain.set(i, main.get(0));
                main.remove(0);
            }
        }

        // Vérifie si le compteur de tour est supérieur ou égal à 3 et
        if (compteurTour >= 3 ) {
            // Trouve le Pokémon de l'ordinateur avec le plus d'attaque et la meilleure affinité
            CartePokemon pokemonAUtiliser = trouverPokemonAUtiliser();

            // Trouve un Pokémon adverse à cibler
            CartePokemon pokemonACibler = trouverPokemonACibler(jeu.getJoueurAdverse(this));

            if (pokemonACibler != null && pokemonAUtiliser != null) {
                // Utilise le Pokémon de l'ordinateur avec le plus d'attaque et la meilleure affinité pour attaquer le Pokémon adverse
                pokemonAUtiliser.attaquer(pokemonACibler);

                // Vérifie si le Pokémon adverse n'a plus de points de vie et le place dans la défausse de l'adversaire
                if (pokemonACibler.getVie() <= 0) {
                    jeu.getJoueurAdverse(this).defausserPokemon(pokemonACibler);
                }

                // Incrémente le compteur d'attaque
                compteurAttaque++;
            }
        }

        // Incrémente le compteur de tour
        compteurTour++;
    }

    private CartePokemon trouverPokemonAUtiliser() {
        // Trouve le Pokémon de l'ordinateur avec le plus d'attaque et la meilleure affinité
        CartePokemon pokemonAUtiliser = null;
        int attaqueMax = Integer.MIN_VALUE;

        for (CartePokemon pokemon : terrain) {
            if (pokemon != null && pokemon.getAttaque() > attaqueMax && pokemon.getAffinite().getAvantage().equals(pokemon.getAffinite().getType())) {
                pokemonAUtiliser = pokemon;
                attaqueMax = pokemon.getAttaque();
            }
        }

        return pokemonAUtiliser;
    }

    private CartePokemon trouverPokemonACibler(Dresseur adversaire) {
        List<CartePokemon> pokemonsAvecMoinsDeVie = new ArrayList<>();

        // Parcours les Pokémons adverses
        for (CartePokemon pokemonAdverse : adversaire.getTerrain()) {
            if (pokemonAdverse != null) {
                boolean avantageTrouve = false;
                // Vérifie si l'affinité du Pokémon adverse avantage l'ordinateur
                for (CartePokemon pokemonOrdinateur : this.terrain) {
                    if (pokemonAdverse.getAffinite().equals(pokemonOrdinateur.getAffinite().getAvantage())) {
                        return pokemonAdverse;
                    }
                }
                // Si aucun Pokémon n'a d'affinité avantageuse, ajoute le Pokémon ayant le moins de points de vie à une liste
                if (!avantageTrouve && pokemonAdverse.getVie() <= adversaire.getVieRestante()) {
                    if (pokemonAdverse.getVie() < adversaire.getVieRestante()) {
                        pokemonsAvecMoinsDeVie.clear();
                        adversaire.setVieRestante(pokemonAdverse.getVie());
                    }
                    pokemonsAvecMoinsDeVie.add(pokemonAdverse);
                }
            }
        }
        // Si plusieurs Pokémon adverses ont le moins de points de vie, choisit l'un d'eux au hasard
        if (!pokemonsAvecMoinsDeVie.isEmpty()) {
            int index = new Random().nextInt(pokemonsAvecMoinsDeVie.size());
            return pokemonsAvecMoinsDeVie.get(index);
        }
        return null;
    }

}
