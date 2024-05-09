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
        if (compteurTour >= 3) {
            // Joue chacun des Pokémons de son terrain dans l'ordre de son choix
            for (CartePokemon pokemon : terrain) {
                if (pokemon != null ) {
                    // Trouve un Pokémon à attaquer en fonction des règles de l'ordinateur
                    CartePokemon pokemonACibler = trouverPokemonACibler(jeu.getJoueurAdverse(this));

                    if (pokemonACibler != null) {
                        // Attaque le Pokémon ciblé
                        pokemon.attaquer(pokemonACibler);

                        // Vérifie si le Pokémon ciblé n'a plus de points de vie et le place dans la défausse de l'adversaire
                        if (pokemonACibler.getVie() <= 0) {
                            jeu.getJoueurAdverse(this).defausserPokemon(pokemonACibler);
                        }
                    }
                }
            }
        }

        // Incrémente le compteur de tour
        compteurTour++;
    }

    private CartePokemon trouverPokemonACibler(Dresseur adversaire) {
        // Trouve un Pokémon à attaquer en fonction des règles de l'ordinateur
        // Parcours les Pokémons adverses
        for (CartePokemon pokemonAdverse : adversaire.getTerrain()) {
            if (pokemonAdverse != null) {
                // Vérifie si l'affinité du Pokémon adverse avantage l'ordinateur
<<<<<<< HEAD
                if (pokemonAdverse.getAffinite().equals(this.getTerrain().get(0).getAffinite().getAvantage())){
=======
                if (pokemonAdverse.getAffinite().equalsIgnoreCase("eau") && this.getTerrain().get(0).getAffinite().equalsIgnoreCase("feu")) {
>>>>>>> e89514b0096bab0428b64fe29eedc0ec936b3eb5
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

<<<<<<< HEAD

=======
>>>>>>> e89514b0096bab0428b64fe29eedc0ec936b3eb5
        return pokemonMoinsDeVie;
    }
}
