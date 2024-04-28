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
    // Nombre de tours à jouer
    int nbTours = 10;

// Boucle pour simuler les tours du jeu
    for (int i = 0; i < nbTours; i++) {
      // Joueur 1 (ordinateur) joue son tour
      Dresseur adversaire = jeu.getJoueurAdverse(joueur1);

      // Joueur 2 (humain) joue son tour
    }

    // Jouer 10 tours
    for (int i = 0; i < 10; i++) {
      jeu.jouerTour();
    }


    // Afficher les Pokémon sur le terrain pour chaque joueur


    System.out.println("Pokémon sur le terrain du joueur 1 :");
    for (CartePokemon carte : joueur1.getTerrain()) {
      carte.afficherCarte();
    }

    System.out.println("Pokémon sur le terrain du joueur 2 :");
    for (CartePokemon carte : joueur2.getTerrain()) {
      carte.afficherCarte();
    }


    // Afficher les Pokémon dans la main du joueur 2
    System.out.println("Pokémon dans la main du joueur 2 :");
    for (CartePokemon carte : joueur2.getMain()) {
      carte.afficherCarte();
    }
  }


}
