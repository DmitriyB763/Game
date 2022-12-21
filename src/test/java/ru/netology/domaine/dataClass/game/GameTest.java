package ru.netology.domaine.dataClass.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.domaine.dataClass.Player;
import ru.netology.domaine.dataClass.exception.NotRegisteredException;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    static Game game = new Game();

    static Player player = new Player(1, "tor", 12);
    static Player player1 = new Player(2, "im", 6);
    static Player player2 = new Player(3, "halk", 12);
    Player player3 = new Player(4, "spider", 9);

    @BeforeAll
    public static void setUp() {
        game.register(player);
        game.register(player1);
        game.register(player2);
    }


    @Test
    public void shouldIsRegistered() {

        game.register(player3);

        Collection<Player> expected = new ArrayList<>();
        expected.add(player);
        expected.add(player1);
        expected.add(player2);
        expected.add(player3);

        Collection<Player> actual = game.getPlayers();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void shouldFindBuId() {  //поиск по существующему id

        assertEquals(player, game.findById(1));
    }

    @Test
    public void shouldNotFindById() {//поиск по не существующему id


        assertThrows(NotRegisteredException.class, () -> {
            game.findById(1000);
        });
    }

    @Test
    public void shouldRoundWhenFirstPlayerStronger() {
        assertEquals(1, game.round(1, 2));
    }

    @Test
    public void shouldRoundWhenSecondPlayerStronger() {

        int expected = 2;
        int actual = game.round(2, 3);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldRoundWhenStrengthEquals() {


        int expected = 0;
        int actual = game.round(1, 3);

        assertEquals(expected, actual);
    }

    //негативные тесты
    @Test
    public void shouldRoundWhenStrengthEqualsAndPlayer2NotRegistered() {

        assertThrows(NotRegisteredException.class, () -> {
            game.round(1, 4);
        });
    }

    @Test
    public void shouldRoundWhenPlayer1NotRegistered() {

        assertThrows(NotRegisteredException.class, () -> {
            game.round(4, 3);
        });
    }

    @Test
    public void shouldRoundWhenPlayersNotRegistered() {



        assertThrows(NotRegisteredException.class, () -> {
            game.round(4, 5);
        });
    }

    @Test
    public void shouldRoundWhenIdEquals() {

        assertEquals(0, game.round(1, 1));
    }

    @Test
    public void shouldRoundWhenNotPlayers() {

        assertThrows(NotRegisteredException.class, () -> {
            game.round(null, null);
        });
    }
}

