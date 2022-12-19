package ru.netology.domaine.dataClass.game;

import ru.netology.domaine.dataClass.Player;
import ru.netology.domaine.dataClass.exception.NotRegisteredException;

import java.util.ArrayList;
import java.util.Collection;

public class Game {

    private Collection<Player> players = new ArrayList<>(); //  использую лист для хранения игроков

    public Collection<Player> getPlayers() {
        return players;
    }

    public Game(Collection<Player> players) {
        this.players = players;
    }

    public Game() {
    }

    public void register(Player player) {
        this.players.add(player);
    }

    public Player findById(int id) { // поиск по имени для получения всей остальной  информации  об игроке
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        throw new NotRegisteredException("Игрок с ID " + id + " не зарегистрирован !");
    }

    public int round(int playerId1, int playerId2) {
        Player gamePlayer1 = findById(playerId1);
        Player gamePlayer2 = findById(playerId2);

        if (gamePlayer1.getStrength() > gamePlayer2.getStrength()){
            return 1;
        } else if (gamePlayer2.getStrength() > gamePlayer1.getStrength()) {
            return 2;
        }
        else {
            return 0;
        }
    }
}


