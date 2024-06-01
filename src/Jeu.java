import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Jeu {
    private final Scanner scanner;
    private Dresseur joueur1;
    private Dresseur joueur2;
    private Dresseur joueurActuel;
    private int numeroTour;
    private List<String> nomsPokemon;
    private List<Pouvoir> pouvoirs;
    JoueurOrdinateur joueurAdverse = new JoueurOrdinateur("Ordinateur");
    private Clip clip;

    public Jeu(Dresseur joueur1, Dresseur joueur2) {
        this.joueur1 = joueur1;
        joueurAdverse = (JoueurOrdinateur) joueur1;
        this.joueur2 = joueur2;
        this.joueurActuel = joueur2;
        this.numeroTour = 1;
//        this.nomsPokemon = new ArrayList<>(Arrays.asList("Herbizarre", "Florizarre", "Celebi", "Dracaufeu", "Bulbizarre", "Pikachu",
//                "Arceus", "Keunotor", "Entei", "Sharpedo", "Lippoutou", "Fulguris",
//                "Carchacrok", "Elektor", "Philaly", "Raiku", "Zeraora", "Poussacha",
//                "Simiabraz", "Roigada", "Tengalice", "Metamorph", "Malamandre", "Altaria",
//                "Galifeu", "Magmar", "Cacnea", "Laggron", "Flambusard", "Suicune",
//                "Psykokwak", "Tiplouf", "Taupiqueur", "Jungko", "Roucool", "Manaphy",
//                "Scorplane", "Cizayox", "Yveltal", "Mewtwo","Giratina"));
        this.nomsPokemon = new ArrayList<>(Arrays.asList("He", "Flo", "Sal", "Dra", "Bul", "Pi",
                "Arc", "Keu", "Entei", "Shar", "Lip", "Ful",
                "Car", "Elek", "Phi", "Raiku", "Zer", "Pou",
                "Si", "Roi", "Tengo", "Meta", "Mala", "Alta",
                "Gal", "Must", "Cac", "Lag", "Flame", "Sui",
                "Psy", "Ti", "Taup", "Ping", "Ryx", "Mana",
                "Sco", "Ciz", "Yve", "Mew", "Gir"));
        pouvoirs = new ArrayList<>(Arrays.asList(new SoinTotal(),new SoinTotal(), new Kamikaze(), new Resistance(),new SoinTotal(),new SoinTotal(), new Resistance(),new SoinSimple(),new SoinSimple(),new SoinSimple(), new Resistance()));
        Collections.shuffle(nomsPokemon);
        this.scanner = new Scanner(System.in);
        playBackgroundMusic("src/son/Pokemon Heart Gold & Soul Silver Musique - Combat ： Champion Arène de Kanto.wav");
        setupKeyListener();
    }

    public void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("Musique est en route. Appuyez sur flèche du haut pour arrêter la musique.");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            System.out.println("Musique de fond éteinte.");
        }
    }

    private void setupKeyListener() {
        JFrame frame = new JFrame();
        frame.setSize(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    stopBackgroundMusic();

                }
            }
        });
    }

    public void jouerTour() {
        boolean estPerdant = false;
        while (!estPerdant) {
            placerPokemonsSurTerrainPourJoueur1();
            afficherInformationsJoueurs();

            // Choix des Pokémon pour Joueur2
            while (joueur2.getTerrain().size() < 3 && joueur2.getPioche().size() > 0) {
                joueur2.piocher();
                choisirCartePokemonPourJoueur2();
            }

            if (numeroTour >= 2) {
                joueurAdverse.jouerTour(this);
            }
            if (joueur1.getTerrain().size() == 0) {
                if (joueur1.getPioche().size() == 0 && joueur1.getMain().size() == 0 && joueur1.getPioche().size() == 0) {
                    numeroTour++;
                    System.out.println(joueur1 + " a perdu.");
                    break;
                }
            }

            if (joueur2.getTerrain().size() == 0) {
                if (joueur2.getPioche().size() == 0 && joueur2.getMain().size() == 0 && joueur2.getPioche().size() == 0) {
                    numeroTour++;
                    System.out.println(joueur2 + " a perdu.");
                    break;
                }
            }

            if (joueur2.getTerrain().size() != 0 || joueur1.getTerrain().size() != 0) {
                utilisationDesPouvoirs();
                tousLesPokemonsOntAttaque();
                numeroTour++;
            }
        }
    }

    private void utilisationDesPouvoirs() {
        afficherCartesSurTerrain();
        for (CartePokemon carte : this.joueur2.getTerrain()) {
            if (carte.getPouvoir() != null && carte.getPouvoir().getUtilisation() > 0) {
                System.out.println("Le pokemon " + carte.getNom() + " peut encore utiliser : " + carte.getPouvoir().toString() + "\n");
                System.out.println("Voulez-vous utiliser le pouvoir de ce pokemon ? (oui/non) \n");

                String choix = scanner.nextLine();
                if (choix.equalsIgnoreCase("oui")) {
                    System.out.println("Vous voulez utiliser le pouvoir sur quel pokemon? \n");
                    String choixCible = scanner.nextLine();
                    CartePokemon carteCible = trouverCarteDansTerrain(choixCible);
                    if (carteCible != null) {
                        Pouvoir pouvoir = carte.getPouvoir();
                        boolean peutUtiliserPouvoir = false;

                        switch (pouvoir.getType()) {
                            case ALLIE:
                                if (joueur2.getTerrain().contains(carteCible)) {
                                    peutUtiliserPouvoir = true;
                                }
                                break;
                            case ENNEMI:
                                if (getJoueurAdverse(joueur2).getTerrain().contains(carteCible)) {
                                    peutUtiliserPouvoir = true;
                                }
                                break;
                            case TOUS:
                                peutUtiliserPouvoir = true;
                                break;
                        }

                        if (peutUtiliserPouvoir) {
                            if (pouvoir.toString() == "Kamikaze") {
                                pouvoir.utiliserPouvoir(carteCible,carte);
                                System.out.println("Les deux pokemons sont mort exploser ");
                                joueur1.defausserPokemon(carteCible);
                                joueur2.defausserPokemon(carte);
                                break;
                            }

                        pouvoir.utiliserPouvoir(carteCible);
                        System.out.println("Le pouvoir a été utilisé avec succès sur " + carteCible.getNom() + ".");
                    } else {
                        System.out.println("Le pouvoir ne peut pas être utilisé sur ce Pokémon.");
                    }
                } else {
                    System.out.println("Le pokemon cible n'a pas été trouvé.");
                }
            }
        }
    }
}

    private CartePokemon trouverCarteDansTerrain(String nomCarte) {
        for (CartePokemon carte : joueur1.getTerrain()) {
            if (carte.getNom().equalsIgnoreCase(nomCarte)) {
                return carte;
            }
        }
        for (CartePokemon carte : joueur2.getTerrain()) {
            if (carte.getNom().equalsIgnoreCase(nomCarte)) {
                return carte;
            }
        }
        return null;
    }


    private void placerPokemonsSurTerrainPourJoueur1() {
        while (joueur1.getTerrain().size() < 3 && !joueur1.getPioche().isEmpty()) {
            CartePokemon carte = joueur1.getPioche().remove(0);
            joueur1.getTerrain().add(carte);
            joueur1.getMain().add(carte);
            System.out.println("Le pokemon " + carte.getNom() + " a été placé sur le terrain .");
        }
    }


    private void afficherCartesSurTerrain() {
        System.out.println("Voici les cartes sur le terrain :");
        System.out.println();
        System.out.println("cartes du joueur 1 : ");
        joueur1.afficherPioche();
        joueur1.afficherDefausse();
        System.out.println();
        // Afficher les autres lignes
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < joueur1.getTerrain().size(); i++) {
                CartePokemon carte = joueur1.getTerrain().get(i);
                System.out.print("      ");
                String info = "";
                switch (j) {
                    case 0:
                        info = "  *--------------------------*";
                        break;
                    case 1:
                        if(carte.getPouvoir()==null) {
                            info = "  | Pouvoir : None           |";
                        }
                        else {
                            if (carte.getPouvoir().toString()=="Berserk") {
                                info = String.format("  | Pouvoir : %-7s        |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="Kamikaze") {
                                info = String.format("  | Pouvoir : %-7s       |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="SoinTotal") {
                                info = String.format("  | Pouvoir : %-7s      |", carte.getPouvoir().toString());
                                break;
                            }
                            else{
                                info = String.format("  | Pouvoir : %-7s     |", carte.getPouvoir().toString());
                                break;
                            }
                        }
                        break;
                    case 2:
                        info = String.format("  | Affinite : %-6s        |", carte.getAffinite().getClass().getSimpleName());
                        break;
                    case 3:
                        if (carte.getVie()>=100) {
                            info = String.format("  | Vie: %-2d/%-3d             |", carte.getVie(), carte.getVieMax());
                        }else{
                            info = String.format("  | Vie: %-2d/%-3d              |", carte.getVie(), carte.getVieMax());
                        }

                        break;
                    case 4:
                        info = String.format("  | Attaque: %-2d              |", carte.getAttaque());
                        break;
                    case 5:
                        info = "  *--------------------------*";
                        break;

                }
                System.out.print(info);
                if (i < joueur1.getTerrain().size() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }


        // Afficher la ligne des noms
        for (int i = 0; i < joueur1.getTerrain().size(); i++) {
            System.out.printf("      ");
            CartePokemon carte = joueur1.getTerrain().get(i);
            System.out.printf("  |        %-10s        |", carte.getNom());

            if (i < joueur1.getTerrain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        for (int i = 0; i < joueur1.getTerrain().size(); i++) {
            System.out.printf("      ");
            System.out.print("  *--------------------------*");
            if (i < joueur1.getTerrain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        System.out.println();


        for (int i = 0; i < joueur2.getTerrain().size(); i++) {
            System.out.printf("      ");
            System.out.print("  *--------------------------*");
            if (i < joueur2.getTerrain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        // Afficher la ligne des noms
        for (int i = 0; i < joueur2.getTerrain().size(); i++) {
            System.out.printf("      ");
            CartePokemon carte = joueur2.getTerrain().get(i);
            System.out.printf("  |        %-10s        |", carte.getNom());

            if (i < joueur2.getTerrain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        // Afficher les autres lignes
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < joueur2.getTerrain().size(); i++) {
                CartePokemon carte = joueur2.getTerrain().get(i);
                System.out.print("      ");
                String info = "";
                switch (j) {
                    case 0:
                        info = "  *--------------------------*";
                        break;
                    case 1:
                        info = String.format("  | Attaque: %-2d              |", carte.getAttaque());
                        break;
                    case 2:
                        if (carte.getVie()>=100) {
                            info = String.format("  | Vie: %-2d/%-3d             |", carte.getVie(), carte.getVieMax());
                            break;
                        }else{
                            info = String.format("  | Vie: %-2d/%-3d              |", carte.getVie(), carte.getVieMax());
                            break;
                        }
                    case 3:
                        info = String.format("  | Affinite : %-6s        |", carte.getAffinite().getClass().getSimpleName());
                        break;
                    case 4:
                        if(carte.getPouvoir()==null) {
                            info = "  | Pouvoir : None           |";
                        }
                        else {
                            if (carte.getPouvoir().toString() == "Berserk") {
                                info = String.format("  | Pouvoir : %-7s        |", carte.getPouvoir().toString());

                            }
                            else if (carte.getPouvoir().toString()=="Kamikaze") {
                                info = String.format("  | Pouvoir : %-7s       |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="SoinTotal") {
                                info = String.format("  | Pouvoir : %-7s      |", carte.getPouvoir().toString());
                                break;
                            }
                            else{
                                info = String.format("  | Pouvoir : %-7s     |", carte.getPouvoir().toString());
                            }
                            break;
                        }
                        break;
                    case 5:
                        info = String.format("  *--------------------------*   ");
                        break;

                }
                System.out.print(info);
                if (i < joueur2.getTerrain().size() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();

        }
        System.out.println("cartes du joueur 2 : ");
        joueur2.afficherPioche();
        joueur2.afficherDefausse();
        System.out.println();
        System.out.println();
    }



    private void tousLesPokemonsOntAttaque() {
        boolean tousLesPokemonsOntAttaque = false;
        boolean carteChoisie = false;
        int compteurPokemonsAttaque = 0;
        while (!tousLesPokemonsOntAttaque) {
            while(!carteChoisie){
            for (CartePokemon carteAttaque : joueurActuel.getTerrain()) {
                if (!carteAttaque.getADejaAttaque()) {
                    afficherCartesSurTerrain();
                    System.out.println(joueurActuel.getNom() +" , veuillez choisir le nom du Pokémon à attaquer avec "+joueurActuel.getTerrain().get(compteurPokemonsAttaque).getNom() + ":");
                    String choixCible = scanner.nextLine();
                    CartePokemon carteCible = trouverCarteDansTerrainJoueurAdverse(choixCible, joueurActuel);
                    if (carteCible != null) {
                        int vieInitiale = carteCible.getVie(); // Stocke les points de vie initiaux
                        carteAttaque.attaquer(carteCible);
                        int vieRestante = carteCible.getVie();
                        int pointsDeViePerdus = vieInitiale - vieRestante; // Calcule la différence

                        System.out.println("Le pokemon " + carteCible.getNom() + " a été attaqué et il lui reste " + vieRestante + " points de vie.");
                        System.out.println("Il a perdu " + pointsDeViePerdus + " points de vie.");

                        compteurPokemonsAttaque += 1;
                        if (carteCible.getVie() == 0) {
                            System.out.println("Le pokemon " + carteCible.getNom() + " a été mis K.O. !");
                            joueur1.defausserPokemon(carteCible);
                            joueur1.getTerrain().remove(carteCible);
                        }
                        carteChoisie = true;
                    } else {
                        System.out.println("Le pokemon cible n'a pas été trouvé.");

                    }
                    }
                    if (joueurActuel.getPioche().size() != 0 && joueurActuel.getMain().size() != 0) {
                        tousLesPokemonsOntAttaque = true;
                    }
                    if (compteurPokemonsAttaque == 3) {
                        compteurPokemonsAttaque = 0;
                        tousLesPokemonsOntAttaque = true;
                    }
                }
            }
        }
    }



    private void afficherInformationsJoueurs() {
        System.out.println("********************************************************************************");
        System.out.println("Tour " + numeroTour + ":");

        afficherCartesSurTerrain();

    }

    private void choisirCartePokemonPourJoueur2() {
        Scanner scanner = new Scanner(System.in);
        boolean carteChoisie = false;

        while (!carteChoisie) {
            // Affiche la main du joueur2
            System.out.println("Votre main:");
            afficherMainJoueur2();

            // Demande au joueur de choisir une carte
            System.out.println("Quel pokemon souhaitez-vous placer sur le terrain? "+afficherCarteMainJoueur2()+": ");
            String choixJoueur2 = scanner.nextLine();

            CartePokemon carteChoisieMain = trouverCarteDansMainJoueur2(choixJoueur2);

            if (carteChoisieMain != null) {
                if (joueur2.placerPokemonSurTerrain(carteChoisieMain)) {
                    System.out.println("Le pokemon " + carteChoisieMain.getNom() + " a été placé sur le terrain.");
                    carteChoisie = true;
                } else {
                    System.out.println("Le terrain est plein. Vous ne pouvez pas placer plus de Pokémon.");
                    carteChoisie = true;
                }
            } else {
                System.out.println("Le pokemon choisi n'est pas dans votre main. Veuillez choisir un pokemon valide.");
            }
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

    private void afficherMainJoueur2() {
        StringBuilder mainJoueur2 = new StringBuilder();

        mainJoueur2.append("En main:\n");
        System.out.println();
        System.out.printf("    ");
        for (int i = 0; i < joueur2.getMain().size(); i++) {
            System.out.print("    *--------------------------------*      ");

        }
        System.out.println();
        // Afficher la ligne des noms
        for (int i = 0; i < joueur2.getMain().size(); i++) {
            System.out.printf("      ");
            CartePokemon carte = joueur2.getMain().get(i);
            System.out.printf("  |          %-10s            |", carte.getNom());

            if (i < joueur2.getMain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        // Afficher les autres lignes
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < joueur2.getMain().size(); i++) {
                CartePokemon carte = joueur2.getMain().get(i);
                System.out.print("      ");
                String info = "";
                switch (j) {
                    case 0:
                        info = "  *--------------------------------*";
                        break;
                    case 1:
                        info = String.format("  | Attaque: %-2d                    |", carte.getAttaque());
                        break;
                    case 2:
                        info = String.format("  | Vie: %-2d/%-3d                   |", carte.getVie(), carte.getVieMax());
                        break;
                    case 3:
                        info = String.format("  | Affinite : %-6s              |", carte.getAffinite().getClass().getSimpleName());
                        break;
                    case 4:
                        if (carte.getPouvoir() == null) {
                            info = "  | Pouvoir : None                 |";
                        }else {
                            if (carte.getPouvoir().toString()=="Berserk") {
                                info = String.format("  | Pouvoir : %-7s              |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="Kamikaze") {
                                info = String.format("  | Pouvoir : %-7s             |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="SoinTotal") {
                                info = String.format("  | Pouvoir : %-7s            |", carte.getPouvoir().toString());
                                break;
                            }
                            else{
                                info = String.format("  | Pouvoir : %-7s           |", carte.getPouvoir().toString());
                                break;
                            }
                        }
                        break;
                    case 5:
                        info = String.format("  *--------------------------------*");
                        break;

                }
                System.out.print(info);
                if (i < joueur2.getMain().size() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
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
        Pouvoir pouvoir = null;
        if((rand.nextInt(18))%3==0) {
            if (!pouvoirs.isEmpty()) {
                int indice = rand.nextInt(pouvoirs.size());
                pouvoir = pouvoirs.get(indice);
                pouvoirs.remove(indice);
            }
        }
        return new CartePokemon(nom, affinite, pouvoir, vie, vieMax, attaque);
    }

    public Dresseur getJoueurAdverse(Dresseur joueurActuel) {
        if (joueurActuel == joueur1) {
            return joueur2;
        } else {
            return joueur1;
        }
    }


}



