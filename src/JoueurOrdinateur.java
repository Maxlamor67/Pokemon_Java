public class JoueurOrdinateur extends Dresseur {
    private int compteurTour;

    public JoueurOrdinateur(String nom) {
        super(nom);
        compteurTour = 0;
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

        // Vérifie si le compteur de tour est supérieur ou égal à 3
        if (compteurTour == 3) {
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
        // Trouve un Pokémon à attaquer en fonction des règles de l'ordinateur
        // Parcours les Pokémons adverses
        for (CartePokemon pokemonAdverse : adversaire.getTerrain()) {
            if (pokemonAdverse != null) {
                // Vérifie si l'affinité du Pokémon adverse avantage l'ordinateur
                if (pokemonAdverse.getAffinite().equals(this.getTerrain().get(0).getAffinite().getAvantage())){
                    return pokemonAdverse;
                }
                // Autres règles d'affinité à ajouter ici
            }
        }

        // Si aucun Pokémon n'a d'affinité avantageuse, attaque le Pokémon ayant le moins de points de vie
        CartePokemon pokemonMoinsDeVie = null;
        int vieMin = Integer.MAX_VALUE;

        for (CartePokemon pokemonAdverse : adversaire.getTerrain()) {
            if (pokemonAdverse != null && pokemonAdverse.getVie() < vieMin) {
                vieMin = pokemonAdverse.getVie();
                pokemonMoinsDeVie = pokemonAdverse;
            }
        }


        return pokemonMoinsDeVie;
    }
}
