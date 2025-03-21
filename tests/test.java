import Element.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Joueur.CartePokemon;
import Pouvoir.*;



class AttaqueSimpleTest {

    @Test
    void testUtiliserAttaque() {
        CartePokemon pokemon1 = new CartePokemon("test1", new Feu(), null, 190, 200, 10);
        CartePokemon pokemon = new CartePokemon("test", new Eau(), null, 190, 200, 30);
        pokemon.attaquer(pokemon1);
        assertEquals(150, pokemon1.getVie());
    }

    @Test
    void testUtiliserAttaqueEnDessousDeZero() {
        CartePokemon pokemon1 = new CartePokemon("test1", new Feu(), null, 190, 200, 10);
        CartePokemon pokemon = new CartePokemon("test", new Eau(), null, 190, 200, 30);
        pokemon1.attaquer(pokemon);
        assertEquals(190, pokemon.getVie());
    }
}
class SoinSimpleTest {

    @Test
    void testUtiliserPouvoir() {
        SoinSimple soinSimple = new SoinSimple();
        CartePokemon pokemon = new CartePokemon("test", new Eau(), soinSimple, 150, 200, 10);
        soinSimple.utiliserPouvoir(pokemon);
        assertEquals(180, pokemon.getVie());
        assertEquals(999, soinSimple.nbrUtilisation());
    }

    @Test
    void testUtiliserPouvoirTropDeVie() {
        SoinSimple soinSimple = new SoinSimple();
        CartePokemon pokemon = new CartePokemon("test", new Eau(), soinSimple, 190, 200, 10);
        soinSimple.utiliserPouvoir(pokemon);
        assertEquals(200, pokemon.getVie());
        assertEquals(999, soinSimple.nbrUtilisation());
    }
}

class SoinTotalTest {

    @Test
    void testUtiliserPouvoir() {
        SoinTotal soinTotal = new SoinTotal();
        CartePokemon pokemon = new CartePokemon("test", new Eau(), soinTotal, 100, 200, 10);
        soinTotal.utiliserPouvoir(pokemon);
        assertEquals(200, pokemon.getVie());
        assertEquals(0, soinTotal.nbrUtilisation());
    }
}

class PeurTest {

    @Test
    void testUtiliserPouvoir() {
        Peur peur = new Peur();
        CartePokemon pokemon = new CartePokemon("test", new Eau(), peur, 200, 200, 10);
        peur.utiliserPouvoir(pokemon);
        assertEquals(0, pokemon.getAttaque());
        assertEquals(0, peur.nbrUtilisation());
    }
}

class KamikazeTest {

    @Test
    void testUtiliserPouvoir() {
        Kamikaze kamikaze = new Kamikaze();
        CartePokemon pokemon1 = new CartePokemon("test1", new Feu(), kamikaze, 150, 200, 10);
        CartePokemon pokemon2 = new CartePokemon("test2", new Eau(), null, 150, 200, 10);
        kamikaze.utiliserPouvoir(pokemon1, pokemon2);
        assertEquals(0, pokemon1.getVie());
        assertEquals(0, pokemon2.getVie());
        assertEquals(0, kamikaze.nbrUtilisation());
    }
}

class FerveurGuerriereTest {

    @Test
    void testUtiliserPouvoir() {
        FerveurGuerriere ferveurGuerriere = new FerveurGuerriere();
        CartePokemon pokemon = new CartePokemon("test", new Eau(), ferveurGuerriere, 150, 200, 10);
        ferveurGuerriere.utiliserPouvoir(pokemon);
        assertEquals(20, pokemon.getAttaque());
        assertEquals(0, ferveurGuerriere.nbrUtilisation());
    }
}

class AffinitePlombTest {

    @Test
    void testUtiliserPouvoir() {
        AffinitePlomb affinitePlomb = new AffinitePlomb();
        CartePokemon pokemon = new CartePokemon("test", new Eau(), affinitePlomb, 150, 200, 10);
        affinitePlomb.utiliserPouvoir(pokemon);
        assertEquals(new Plomb(), pokemon.getAffinite());
        assertEquals(0, affinitePlomb.nbrUtilisation());
    }
}

class AffiniteEtherTest {

    @Test
    void testUtiliserPouvoir() {
        AffiniteEther affiniteEther = new AffiniteEther();
        CartePokemon pokemon = new CartePokemon("test", new Eau(), affiniteEther, 150, 200, 10);
        affiniteEther.utiliserPouvoir(pokemon);
        assertEquals(new Ether(), pokemon.getAffinite());
        assertEquals(0, affiniteEther.nbrUtilisation());
    }
}
class ResistanceTest {

    @Test
    void testUtiliserPouvoir() {
        Resistance resistance = new Resistance();
        // Création des deux Pokémon
        CartePokemon pokemonA = new CartePokemon("Pokémon A", new Eau(), resistance, 150, 200, 10);
        CartePokemon pokemonB = new CartePokemon("Pokémon B", new Feu(), null, 150, 200, 30);

        // Utilisation du pouvoir de résistance par le premier Pokémon
        resistance.utiliserPouvoir(pokemonA);
        // L'attaque du deuxième Pokémon sur le premier
        pokemonB.attaquer(pokemonA);

        // La vie enlevée par l'attaque est supérieure de 10 à la résistance
        assertEquals(pokemonA.getVie(), 140);
    }
}

