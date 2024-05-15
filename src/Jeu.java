import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Jeu {
    private final Scanner scanner;
    private Dresseur joueur1;
    private Dresseur joueur2;
    private Dresseur joueurActuel;
    private int numeroTour;
    private List<String> nomsPokemon;
    JoueurOrdinateur joueurAdverse = new JoueurOrdinateur("Ordinateur");



    public Jeu(Dresseur joueur1, Dresseur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurActuel = joueur1;
        this.numeroTour = 1;
        this.nomsPokemon = new ArrayList<>(Arrays.asList("Herbizarre", "Florizarre", "Salamèche", "Dracaufeu", "Bulbizarre", "Pikachu",
                "Arceus", "Keunotor", "Entei", "Sharpedo", "Lippoutou", "Fulguris",
                "Carchacrok", "Elektor", "Philaly", "Raiku", "Zeraora", "Poussacha",
                "Simiabraz", "Roigada", "Tengalice", "Metamorph", "Malamandre", "Altaria",
                "Galifeu", "Mustéflott", "Cacnea", "Laggron", "Flambusard", "Suicune",
                "Psykokwak", "Tiplouf", "Démolosse", "Pingoléon", "Aéroptéryx", "Manaphy",
                "Scorplane", "Cizayox", "Yveltal", "Mewtwo","Giratina"));

//        this.nomsPokemon = new ArrayList<>(Arrays.asList("He", "Flo", "Sal", "Dra", "Bul", "Pi",
//                "Arc", "Keu", "Entei", "Shar", "Lip", "Ful",
//                "Car", "Elek", "Phi", "Raiku", "Zer", "Pou",
//                "Si", "Roi", "Tengo", "Meta", "Mala", "Alta",
//                "Gal", "Must", "Cac", "Lag", "Flame", "Sui",
//                "Psy", "Ti", "Démo", "Ping", "Ryx", "Mana",
//                "Sco", "Ciz", "Yve", "Mew","Gir"));
        Collections.shuffle(nomsPokemon);
        this.scanner = new Scanner(System.in);
    }

    public void jouerTour() {
        Dresseur joueurActuel = (numeroTour % 2 == 1) ? joueur1 : joueur2;

        // Vérifier si c'est le premier tour et si le joueur actuel est le joueur 2
        if (numeroTour == 1 && joueurActuel == joueur2) {
            // Demander au joueur 2 de choisir 3 cartes à placer sur le terrain
            System.out.println("Joueur 2, veuillez choisir 3 cartes à placer sur le terrain :");
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < 3; i++) {
                System.out.println("Carte " + (i+1) + " :");
                String choixJoueur2 = scanner.nextLine();
                CartePokemon carteChoisie = trouverCarteDansMainJoueur2(choixJoueur2);
                if (carteChoisie != null) {
                    if (joueur2.placerPokemonSurTerrain(carteChoisie)) {
                        System.out.println("Le pokemon " + carteChoisie.getNom() + " a été placé sur le terrain.");
                    } else {
                        System.out.println("Le terrain est plein. Vous ne pouvez pas placer plus de Pokémon.");
                    }
                } else {
                    System.out.println("Le pokemon choisi n'est pas dans votre main. Veuillez choisir un pokemon valide.");
                    i--; // Décrémenter i pour que le joueur puisse choisir à nouveau une carte
                }
            }
        } else {
            // Code pour les tours suivants
            while (joueur1.getTerrain().size() < 3 && joueur1.getPioche().size() > 0) {
                CartePokemon carte = joueur1.getPioche().get(0);
                joueur1.getTerrain().add(carte);
                joueur1.getPioche().remove(0);
            }
            joueur2.piocher();

            // Vérifier si le joueur 2 a déjà 3 Pokémons sur le terrain
            if (joueur2.getTerrain().size() == 3) {
                // Vérifier si on est au quatrième tour
                if (numeroTour == 4) {
                    // Afficher toutes les cartes
                    System.out.println("Voici les cartes sur le terrain :");
                    for (CartePokemon carte : joueur1.getTerrain()) {
                        carte.afficherCarte();
                    }
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    String cartesTerrainJoueur2 = afficherCartesTerrainJoueur2();
                    System.out.println("Cartes sur le terrain : " + cartesTerrainJoueur2);
                }

                // Boucle pour que tous les Pokémons du joueur actuel attaquent
                boolean tousLesPokemonsOntAttaque = false;
                int compteurPokemonsAttaque = 0;//ajout d'un compteur
                while (!tousLesPokemonsOntAttaque) {
                    for (CartePokemon carteAttaque : joueurActuel.getTerrain()) {
                        if (!carteAttaque.getADejaAttaque()) {
                            // Afficher les cartes du terrain
                            System.out.println("Voici les cartes sur le terrain :");
                            for (CartePokemon carte : joueur1.getTerrain()) {
                                carte.afficherCarte();
                            }
                            System.out.println("----------------------------------------------------------------------------------------------------");
                            String cartesTerrainJoueur2 = afficherCartesTerrainJoueur2();
                            System.out.println("Cartes sur le terrain : " + cartesTerrainJoueur2);

                            // Demander au joueur 2 de choisir un Pokémon à attaquer
                            System.out.println("Joueur 2, veuillez choisir le nom du Pokémon à attaquer avec "+joueurActuel.getTerrain().get(compteurPokemonsAttaque).getNom() + ":");
                            String choixCible = scanner.nextLine();
                            CartePokemon carteCible = trouverCarteDansTerrainJoueurAdverse(choixCible, joueurActuel);

                            if (carteCible != null) {
                                // Faire attaquer le Pokémon choisi par le joueur 2
                                carteAttaque.attaquer(carteCible);
                                System.out.println("Le pokemon " + carteCible.getNom() + " a été attaqué et il lui reste " + carteCible.getVie() + " points de vie.");
                                compteurPokemonsAttaque+=1;
                                if (carteCible.getVie() == 0) {
                                    System.out.println("Le pokemon " + carteCible.getNom() + " a été mis K.O. !");
                                    joueur1.defausserPokemon(carteCible);
                                    joueur1.getTerrain().remove(carteCible);
                                }
                            } else {
                                System.out.println("Le pokemon cible n'a pas été trouvé.");

                            }
                            // Vérifier si tous les Pokémons ont attaqué et passer au tour suivant si c'est le cas
                            if (compteurPokemonsAttaque == joueurActuel.getTerrain().size()) {
                                tousLesPokemonsOntAttaque = true;

                            }
                        }
                    }
                }

                // Tour de l'ordinateur
                joueurAdverse.jouerTour(this);

                if (numeroTour >= 3) {


                    // Passer au tour suivant
                    numeroTour++;
                }



//                // Réinitialiser l'attribut aDejaAttaque de toutes les cartes Pokémon sur le terrain
//                for (CartePokemon carte : joueur1.getTerrain()) {
//                    carte.resetAttaque();
//                }
//                for (CartePokemon carte : joueur2.getTerrain()) {
//                    carte.resetAttaque();
//                }

                // Réafficher la pioche, la défausse, les cartes du joueur dans sa main et tout ceux sur le plateau
                System.out.println("********************************************************************************");
                System.out.println("Tour "+ numeroTour + ":");
                System.out.println();
                System.out.println("                                    " + (joueurActuel == joueur2 ? joueur2.getNom() : joueur1.getNom()));
                System.out.println();
                System.out.println("********************************************************************************");
                System.out.println();
                System.out.println("                                    " + (joueurActuel == joueur1 ? joueur1.getNom() : joueur2.getNom()));
                System.out.println();

                joueur1.afficherPioche();
                joueur1.afficherDefausse();

                for (CartePokemon carte : joueur1.getTerrain()) {
                    carte.afficherCarte();
                }
                System.out.println("----------------------------------------------------------------------------------------------------");

                // Afficher les cartes sur le terrain du joueur 2
                String cartesTerrainJoueur2 = afficherCartesTerrainJoueur2();
                System.out.println("Cartes sur le terrain : " + cartesTerrainJoueur2);

                joueur2.afficherPioche();
                joueur2.afficherDefausse();

                // Afficher la main du joueur 2
                String mainJoueur2 = afficherMainJoueur2();
                System.out.println(mainJoueur2);

                System.out.println();
                System.out.println();
                System.out.println("                                    " + joueur2.getNom());
            } else {
                // Affichage du tour et des informations des joueurs
                System.out.println("********************************************************************************");
                System.out.println("Tour " + numeroTour + ":");
                System.out.println();
                System.out.println("                                    " + (joueurActuel == joueur2 ? joueur2.getNom() : joueur1.getNom()));
                System.out.println();
                System.out.println("********************************************************************************");
                System.out.println();
                System.out.println("                                    " + (joueurActuel == joueur1 ? joueur1.getNom() : joueur2.getNom()));
                System.out.println();

                joueur1.afficherPioche();
                joueur1.afficherDefausse();

                for (CartePokemon carte : joueur1.getTerrain()) {
                    carte.afficherCarte();
                }
                System.out.println("----------------------------------------------------------------------------------------------------");

                // Afficher les cartes sur le terrain du joueur 2
                String cartesTerrainJoueur2 = afficherCartesTerrainJoueur2();
                System.out.println("Cartes sur le terrain : " + cartesTerrainJoueur2);

                joueur2.afficherPioche();
                joueur2.afficherDefausse();

                // Afficher la main du joueur 2
                String mainJoueur2 = afficherMainJoueur2();
                System.out.println(mainJoueur2);

                System.out.println();
                System.out.println();
                System.out.println("                                    " + joueur2.getNom());

                Scanner scanner = new Scanner(System.in);
                System.out.println("Quel pokemon souhaitez-vous placer sur le terrain? (" + afficherCarteMainJoueur2() + "): ");
                String choixJoueur2 = scanner.nextLine();

                CartePokemon carteChoisie = trouverCarteDansMainJoueur2(choixJoueur2);
                if (carteChoisie != null) {
                    if (joueur2.placerPokemonSurTerrain(carteChoisie)) {
                        System.out.println("Le pokemon " + carteChoisie.getNom() + " a été placé sur le terrain.");
                    } else {
                        System.out.println("Le terrain est plein. Vous ne pouvez pas placer plus de Pokémon.");
                    }
                } else {
                    System.out.println("Le pokemon choisi n'est pas dans votre main. Veuillez choisir un pokemon valide.");
                }
            }
        }
        if (numeroTour == 1) {


            // Passer au tour suivant
            numeroTour++;
        }
    }







    public CartePokemon trouverCarteDansTerrainJoueurAdverse(String nomCarte, Dresseur joueurActuel) {
        Dresseur joueurAdverse = getJoueurAdverse(joueurActuel);
        for (CartePokemon carte : joueurAdverse.getTerrain()) {
            if (carte.getNom().equalsIgnoreCase(nomCarte)) {
                return carte;
            }
        }
        return null;
    }



    private String afficherCartesTerrainJoueur2() {
        StringBuilder cartesTerrain = new StringBuilder();

        for (int i = 0; i < joueur2.getTerrain().size(); i++) {
            CartePokemon carte = joueur2.getTerrain().get(i);

            cartesTerrain.append("\n");
            cartesTerrain.append("  *--------------------*");
            if (i < joueur2.getTerrain().size() - 1) {
                cartesTerrain.append("\t");
            }

            cartesTerrain.append("\n");
            cartesTerrain.append(String.format("  |     %-10s     |", carte.getNom()));
            if (i < joueur2.getTerrain().size() - 1) {
                cartesTerrain.append("\t");
            }

            cartesTerrain.append("\n");
            cartesTerrain.append(String.format("  | Affinite : %-6s  |", carte.getAffinite().getClass().getSimpleName()));
            if (i < joueur2.getTerrain().size() - 1) {
                cartesTerrain.append("\t");
            }

            cartesTerrain.append("\n");
            cartesTerrain.append(String.format("  | Vie: %-2d/%-3d       |", carte.getVie(), carte.getVieMax()));
            if (i < joueur2.getTerrain().size() - 1) {
                cartesTerrain.append("\t");
            }

            cartesTerrain.append("\n");
            cartesTerrain.append(String.format("  | Attaque: %-2d        |", carte.getAttaque()));
            if (i < joueur2.getTerrain().size() - 1) {
                cartesTerrain.append("\t");
            }

            cartesTerrain.append("\n");
            cartesTerrain.append("  *--------------------*");
            if (i < joueur2.getTerrain().size() - 1) {
                cartesTerrain.append("\t");
            }

            if ((i + 1) % 3 == 0 && i != 0) {
                cartesTerrain.append("\n");
            }
        }

        return cartesTerrain.toString();
    }
    private CartePokemon trouverCarteDansTerrainJoueur2(String nomCarte) {
        for (CartePokemon carte : joueur2.getTerrain()) {
            if (carte.getNom().equalsIgnoreCase(nomCarte)) {
                return carte;
            }
        }
        return null;
    }


    public void prochainTour() {
        if (joueurActuel == joueur1) {
            joueurActuel = joueur2;
        } else {
            joueurActuel = joueur1;
        }
    }

    private String afficherMainJoueur2() {
        StringBuilder mainJoueur2 = new StringBuilder();

        mainJoueur2.append("En main:\n");
        for (CartePokemon carte : joueur2.getMain()) {
            mainJoueur2.append("- ").append(carte.getNom()).append(", ").append(carte.getAffinite().getClass().getSimpleName()).append(", Vie: ").append(carte.getVie()).append("/").append(carte.getVieMax()).append(", Attaque: ").append(carte.getAttaque()).append("\n");
        }

        return mainJoueur2.toString();
    }

    private String afficherCarteMainJoueur2() {
        StringBuilder mainJoueur2 = new StringBuilder();

        for (CartePokemon carte : joueur2.getMain()) {
            mainJoueur2.append(carte.getNom()).append("/");
        }

        // Supprimer le dernier "/"
        if (mainJoueur2.length() > 0) {
            mainJoueur2.setLength(mainJoueur2.length() - 1);
        }

        return mainJoueur2.toString();
    }

    private CartePokemon trouverCarteDansMainJoueur2(String nomCarte) {
        for (CartePokemon carte : joueur2.getMain()) {
            if (carte.getNom().equalsIgnoreCase(nomCarte)) {
                return carte;
            }
        }
        return null;
    }
    public CartePokemon genererPokemon() {
        if (nomsPokemon.isEmpty()) {
            throw new RuntimeException("Aucun nom de Pokémon disponible.");
        }

        List<Type> affinites = Arrays.asList(new Air(), new Feu(), new Eau(), new Terre());

        Random rand = new Random();

        String nom = nomsPokemon.get(0);
        nomsPokemon.remove(0); // Retirer le nom utilisé pour éviter les doublons

        int vieMax = rand.nextInt(11) * 10 + 100; // Nombre aléatoire entre 100 et 200, multiple de 10
        int vie = vieMax;
        int attaque = rand.nextInt(4) * 10 + 10; // Nombre aléatoire entre 10 et 40, multiple de 10

        Type affinite = affinites.get(rand.nextInt(affinites.size()));

        return new CartePokemon(nom, affinite, vie, vieMax, attaque);
    }
    public Dresseur getJoueurAdverse(Dresseur joueurActuel) {
        if (joueurActuel == joueur1) {
            return joueur2;
        } else {
            return joueur1;
        }
    }




}



