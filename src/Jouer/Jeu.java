package Jouer;
import Element.*;
import Joueur.*;
import Pouvoir.*;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Jeu {
    private final Scanner scanner;
    private Dresseur m_joueur1;
    private Dresseur m_joueur2;
    private Dresseur m_joueurActuel;
    private int m_numeroTour;
    private List<String> m_nomsPokemon;
    private List<Pouvoir> m_pouvoirs;
    JoueurOrdinateur m_joueurAdverse = new JoueurOrdinateur("Ordinateur");
    private Clip m_clip;

    public Jeu(Dresseur joueur1, Dresseur joueur2) {
        this.m_joueur1 = joueur1;
        m_joueurAdverse = (JoueurOrdinateur) joueur1;
        this.m_joueur2 = joueur2;
        this.m_joueurActuel = joueur2;
        this.m_numeroTour = 1;
        this.nomsPokemon = new ArrayList<>(Arrays.asList("Herbizarre", "Florizarre", "Celebi", "Dracaufeu", "Bulbizarre", "Pikachu",
                "Arceus", "Keunotor", "Entei", "Sharpedo", "Lippoutou", "Fulguris",
                "Carchacrok", "Elektor", "Philaly", "Raiku", "Zeraora", "Poussacha",
                "Simiabraz", "Roigada", "Tengalice", "Metamorph", "Malamandre", "Altaria",
                "Galifeu", "Magmar", "Cacnea", "Laggron", "Flambusard", "Suicune",
                "Psykokwak", "Tiplouf", "Taupiqueur", "Jungko", "Roucool", "Manaphy",
                "Scorplane", "Cizayox", "Yveltal", "Mewtwo","Giratina"));
//        this.m_nomsPokemon = new ArrayList<>(Arrays.asList("He", "Flo", "Sal", "Dra", "Bul", "Pi",
//                "Arc", "Keu", "Entei", "Shar", "Lip", "Ful",
//                "Car", "Elek", "Phi", "Raiku", "Zer", "Pou",
//                "Si", "Roi", "Tengo", "Meta", "Mala", "Alta",
//                "Gal", "Must", "Cac", "Lag", "Flame", "Sui",
//                "Psy", "Ti", "Taup", "Ping", "Ryx", "Mana",
//                "Sco", "Ciz", "Yve", "Mew", "Gir"));
        m_pouvoirs = new ArrayList<>(Arrays.asList( new Kamikaze(), new Resistance(),new SoinTotal(),new SoinSimple(), new AffiniteEther(), new AffinitePlomb(),new Peur(),new FerveurGuerriere()));
        Collections.shuffle(m_nomsPokemon);
        this.scanner = new Scanner(System.in);
        playBackgroundMusic("src/son/Pokemon Heart Gold & Soul Silver Musique - Combat ： Champion Arène de Kanto.wav");
        setupKeyListener();
    }

    public void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            m_clip = AudioSystem.getClip();
            m_clip.open(audioStream);
            m_clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("Musique est en route. Appuyez sur flèche du haut pour arrêter la musique.");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (m_clip != null && m_clip.isRunning()) {
            m_clip.stop();
            m_clip.close();
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
            while (m_joueur2.getTerrain().size() < 3 && m_joueur2.getPioche().size() > 0) {
                m_joueur2.piocher();
                choisirCartePokemonPourJoueur2();
            }

            if (m_numeroTour >= 2) {
                m_joueurAdverse.jouerTour(this);
            }
            if (m_joueur1.getTerrain().size() == 0) {
                if (m_joueur1.getPioche().size() == 0 && m_joueur1.getMain().size() == 0 && m_joueur1.getPioche().size() == 0) {
                    m_numeroTour++;
                    System.out.println(m_joueur1 + " a perdu.");
                    break;
                }
            }

            if (m_joueur2.getTerrain().size() == 0) {
                if (m_joueur2.getPioche().size() == 0 && m_joueur2.getMain().size() == 0 && m_joueur2.getPioche().size() == 0) {
                    m_numeroTour++;
                    System.out.println(m_joueur2 + " a perdu.");
                    break;
                }
            }

            if (m_joueur2.getTerrain().size() != 0 || m_joueur1.getTerrain().size() != 0) {
                utilisationDesPouvoirs();
                tousLesPokemonsOntAttaque();
                m_numeroTour++;
            }
        }
    }

    private void utilisationDesPouvoirs() {
        afficherCartesSurTerrain();
        Iterator<CartePokemon> iterator = this.m_joueur2.getTerrain().iterator();
        while (iterator.hasNext()) {
            CartePokemon carte = iterator.next();
            if (carte.getPouvoir() != null && carte.getPouvoir().nbrUtilisation() > 0) {
                System.out.println("Le pokemon " + carte.getNom() + " peut encore utiliser : " + carte.getPouvoir().toString() + "\n");

                boolean decisionPrise = false;
                while (!decisionPrise) {
                    System.out.println("Voulez-vous utiliser le pouvoir de ce pokemon ? (oui/non/description) \n");
                    String choix = scanner.nextLine();

                    if (choix.equalsIgnoreCase("description")) {
                        System.out.println();
                        System.out.println("Description du pouvoir: " + carte.getPouvoir().getDescription());
                    } else if (choix.equalsIgnoreCase("oui")) {
                        decisionPrise = true;
                        System.out.println("Vous voulez utiliser le pouvoir sur quel pokemon? \n");
                        String choixCible = scanner.nextLine();
                        CartePokemon carteCible = trouverCarteDansTerrain(choixCible);

                        if (carteCible != null) {
                            Pouvoir pouvoir = carte.getPouvoir();
                            boolean peutUtiliserPouvoir = false;

                            switch (pouvoir.getTypePouvoir()) {
                                case ALLIE:
                                    if (m_joueur2.getTerrain().contains(carteCible)) {
                                        peutUtiliserPouvoir = true;
                                    }
                                    break;
                                case ENNEMI:
                                    if (getJoueurAdverse(m_joueur2).getTerrain().contains(carteCible)) {
                                        peutUtiliserPouvoir = true;
                                    }
                                    break;
                                case TOUTCAMP:
                                case TOUS:
                                    peutUtiliserPouvoir = true;
                                    break;
                            }

                            if (peutUtiliserPouvoir) {
                                if (pouvoir.toString().equals("Kamikaze")) {
                                    pouvoir.utiliserPouvoir(carte, carteCible);
                                    System.out.println("Les deux pokemons sont morts exploser ");
                                    m_joueur1.defausserPokemon(carteCible);
                                    iterator.remove(); // Supprimer le Pokémon kamikaze de la liste du joueur2
                                    break;
                                } else {
                                    pouvoir.utiliserPouvoir(carteCible);
                                    System.out.println("Le pouvoir a été utilisé avec succès sur " + carteCible.getNom() + ".");
                                }
                            } else {
                                System.out.println("Le pouvoir ne peut pas être utilisé sur ce Pokémon.");
                            }
                        } else {
                            System.out.println("Le pokemon cible n'a pas été trouvé.");
                        }
                    } else if (choix.equalsIgnoreCase("non")) {
                        decisionPrise = true;
                    } else {
                        System.out.println("Choix invalide. Veuillez entrer 'oui', 'non' ou 'description'.");
                    }
                }
            }
        }
    }




    private CartePokemon trouverCarteDansTerrain(String nomCarte) {
        for (CartePokemon carte : m_joueur1.getTerrain()) {
            if (carte.getNom().equalsIgnoreCase(nomCarte)) {
                return carte;
            }
        }
        for (CartePokemon carte : m_joueur2.getTerrain()) {
            if (carte.getNom().equalsIgnoreCase(nomCarte)) {
                return carte;
            }
        }
        return null;
    }


    private void placerPokemonsSurTerrainPourJoueur1() {
        while (m_joueur1.getTerrain().size() < 3 && !m_joueur1.getPioche().isEmpty()) {
            CartePokemon carte = m_joueur1.getPioche().remove(0);
            m_joueur1.getTerrain().add(carte);
            m_joueur1.getMain().add(carte);
            System.out.println("Le pokemon " + carte.getNom() + " a été placé sur le terrain .");
        }
    }


    private void afficherCartesSurTerrain() {
        System.out.println("Voici les cartes sur le terrain :");
        System.out.println();
        System.out.println("cartes du joueur 1 : ");
        m_joueur1.afficherPioche();
        m_joueur1.afficherDefausse();
        System.out.println();
        // Afficher les autres lignes
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < m_joueur1.getTerrain().size(); i++) {
                CartePokemon carte = m_joueur1.getTerrain().get(i);
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
                            if (carte.getPouvoir().toString()=="Kamikaze") {
                                info = String.format("  | Pouvoir : %-7s       |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="Peur") {
                                info = String.format("  | Pouvoir : %-7s        |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="FerveurGuerriere") {
                                info = String.format("  | Pouvoir :%-7s|", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="AffinitePlomb" || carte.getPouvoir().toString()=="AffiniteEther") {
                                info = String.format("  | Pouvoir : %-7s  |", carte.getPouvoir().toString());
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
                            info = String.format("  | Vie: %-2d/%-3d             |", carte.getVie(), carte.getM_vieMax());
                        }else{
                            info = String.format("  | Vie: %-2d/%-3d              |", carte.getVie(), carte.getM_vieMax());
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
                if (i < m_joueur1.getTerrain().size() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }


        // Afficher la ligne des noms
        for (int i = 0; i < m_joueur1.getTerrain().size(); i++) {
            System.out.printf("      ");
            CartePokemon carte = m_joueur1.getTerrain().get(i);
            System.out.printf("  |        %-10s        |", carte.getNom());

            if (i < m_joueur1.getTerrain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        for (int i = 0; i < m_joueur1.getTerrain().size(); i++) {
            System.out.printf("      ");
            System.out.print("  *--------------------------*");
            if (i < m_joueur1.getTerrain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        System.out.println();


        for (int i = 0; i < m_joueur2.getTerrain().size(); i++) {
            System.out.printf("      ");
            System.out.print("  *--------------------------*");
            if (i < m_joueur2.getTerrain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        // Afficher la ligne des noms
        for (int i = 0; i < m_joueur2.getTerrain().size(); i++) {
            System.out.printf("      ");
            CartePokemon carte = m_joueur2.getTerrain().get(i);
            System.out.printf("  |        %-10s        |", carte.getNom());

            if (i < m_joueur2.getTerrain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        // Afficher les autres lignes
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < m_joueur2.getTerrain().size(); i++) {
                CartePokemon carte = m_joueur2.getTerrain().get(i);
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
                            info = String.format("  | Vie: %-2d/%-3d             |", carte.getVie(), carte.getM_vieMax());
                            break;
                        }else{
                            info = String.format("  | Vie: %-2d/%-3d              |", carte.getVie(), carte.getM_vieMax());
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
                            if (carte.getPouvoir().toString()=="Kamikaze") {
                                info = String.format("  | Pouvoir : %-7s       |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="Peur") {
                                info = String.format("  | Pouvoir : %-7s        |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="FerveurGuerriere") {
                                info = String.format("  | Pouvoir :%-7s|", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="AffinitePlomb" || carte.getPouvoir().toString()=="AffiniteEther") {
                                info = String.format("  | Pouvoir : %-7s  |", carte.getPouvoir().toString());
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
                if (i < m_joueur2.getTerrain().size() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();

        }
        System.out.println("cartes du joueur 2 : ");
        m_joueur2.afficherPioche();
        m_joueur2.afficherDefausse();
        System.out.println();
        System.out.println();
    }



    private void tousLesPokemonsOntAttaque() {
        boolean tousLesPokemonsOntAttaque = false;
        boolean carteChoisie = false;
        int compteurPokemonsAttaque = 0;
        while (!tousLesPokemonsOntAttaque) {
            while(!carteChoisie){
            for (CartePokemon carteAttaque : m_joueurActuel.getTerrain()) {
                if (!carteAttaque.getADejaAttaque()) {
                    afficherCartesSurTerrain();
                    System.out.println(m_joueurActuel.getNom() +" , veuillez choisir le nom du Pokémon à attaquer avec "+m_joueurActuel.getTerrain().get(compteurPokemonsAttaque).getNom() + ":");
                    String choixCible = scanner.nextLine();
                    CartePokemon carteCible = trouverCarteDansTerrainJoueurAdverse(choixCible, m_joueurActuel);
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
                            m_joueur1.defausserPokemon(carteCible);
                            m_joueur1.getTerrain().remove(carteCible);
                        }
                        carteChoisie = true;
                    } else {
                        System.out.println("Le pokemon cible n'a pas été trouvé.");

                    }
                    }
                    if (m_joueurActuel.getPioche().size() != 0 && m_joueurActuel.getMain().size() != 0) {
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
        System.out.println("Tour " + m_numeroTour + ":");

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
                if (m_joueur2.placerPokemonSurTerrain(carteChoisieMain)) {
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
        for (int i = 0; i < m_joueur2.getMain().size(); i++) {
            System.out.print("    *--------------------------------*      ");

        }
        System.out.println();
        // Afficher la ligne des noms
        for (int i = 0; i < m_joueur2.getMain().size(); i++) {
            System.out.printf("      ");
            CartePokemon carte = m_joueur2.getMain().get(i);
            System.out.printf("  |          %-10s            |", carte.getNom());

            if (i < m_joueur2.getMain().size() - 1) {
                System.out.print("\t");
            }
        }
        System.out.println();
        // Afficher les autres lignes
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < m_joueur2.getMain().size(); i++) {
                CartePokemon carte = m_joueur2.getMain().get(i);
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
                        info = String.format("  | Vie: %-2d/%-3d                   |", carte.getVie(), carte.getM_vieMax());
                        break;
                    case 3:
                        info = String.format("  | Affinite : %-6s              |", carte.getAffinite().getClass().getSimpleName());
                        break;
                    case 4:
                        if (carte.getPouvoir() == null) {
                            info = "  | Pouvoir : None                 |";
                        }else {
                            if (carte.getPouvoir().toString()=="Peur") {
                                info = String.format("  | Pouvoir : %-7s              |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="FerveurGuerriere") {
                                info = String.format("  | Pouvoir :%-7s      |", carte.getPouvoir().toString());
                                break;
                            }
                            else if (carte.getPouvoir().toString()=="AffinitePlomb" || carte.getPouvoir().toString()=="AffiniteEther") {
                                info = String.format("  | Pouvoir : %-7s        |", carte.getPouvoir().toString());
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
                if (i < m_joueur2.getMain().size() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private String afficherCarteMainJoueur2() {
        StringBuilder mainJoueur2 = new StringBuilder();

        for (CartePokemon carte : m_joueur2.getMain()) {
            mainJoueur2.append(carte.getNom()).append("/");
        }

        // Supprimer le dernier "/"
        if (mainJoueur2.length() > 0) {
            mainJoueur2.setLength(mainJoueur2.length() - 1);
        }

        return mainJoueur2.toString();
    }
    private CartePokemon trouverCarteDansMainJoueur2(String nomCarte) {
        for (CartePokemon carte : m_joueur2.getMain()) {
            if (carte.getNom().equalsIgnoreCase(nomCarte)) {
                return carte;
            }
        }
        return null;
    }
    public CartePokemon genererPokemon() {
        if (m_nomsPokemon.isEmpty()) {
            throw new RuntimeException("Aucun nom de Pokémon disponible.");
        }

        List<Type> affinites = Arrays.asList(new Air(), new Feu(), new Eau(), new Terre());

        Random rand = new Random();

        String nom = m_nomsPokemon.get(0);
        m_nomsPokemon.remove(0); // Retirer le nom utilisé pour éviter les doublons

        int vieMax = rand.nextInt(11) * 10 + 100; // Nombre aléatoire entre 100 et 200, multiple de 10
        int vie = vieMax;
        int attaque = rand.nextInt(4) * 10 + 10; // Nombre aléatoire entre 10 et 40, multiple de 10

        Type affinite = affinites.get(rand.nextInt(affinites.size()));
        Pouvoir pouvoir = null;
        if((rand.nextInt(18))%3==0) {
            if (!m_pouvoirs.isEmpty()) {
                int indice = rand.nextInt(m_pouvoirs.size());
                pouvoir = m_pouvoirs.get(indice);
                m_pouvoirs.remove(indice);
            }
        }
        return new CartePokemon(nom, affinite, pouvoir, vie, vieMax, attaque);
    }

    public Dresseur getJoueurAdverse(Dresseur joueurActuel) {
        if (joueurActuel == m_joueur1) {
            return m_joueur2;
        } else {
            return m_joueur1;
        }
    }
    public Dresseur getJoueur(Dresseur joueurActuel) {
        if (joueurActuel == m_joueur1) {
            return m_joueur1;
        } else {
            return m_joueur2;
        }
    }


    public int getNumeroTour() {
        return m_numeroTour;
    }
    public boolean estTermine() {
        return false;
    }
}



