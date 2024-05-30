public class Pokemon {
  public static void main(String[] args) {
    JoueurOrdinateur joueur1 = new JoueurOrdinateur("Joueur 1");
    Dresseur joueur2 = new Dresseur("Joueur 2");

    Jeu jeu = new Jeu(joueur1, joueur2);

    // Générer les Pokémon pour les joueurs
    for (int i = 0; i < 21; i++) {
      CartePokemon cartePokemon = jeu.genererPokemon();
      joueur1.ajouterCarteAPioche(cartePokemon);
    }

    for (int i = 0; i < 20; i++) {
      CartePokemon cartePokemon = jeu.genererPokemon();
      joueur2.ajouterCarteAPioche(cartePokemon);
    }

    // Piocher 5 cartes pour chaque joueur
    joueur1.piocher();
    joueur2.piocher();

    // Joueur 1 (ordinateur) joue son tour
    joueur1.jouerTour(jeu);

    // Joueur 2 (humain) joue son tour
    jeu.jouerTour();
  }

}
