package jbolo.core;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * @author Mike Heath
 */
public class Alliance {

	private final String name;

	private final Set<Player> players = new HashSet<Player>();

	private final Set<Player> unmodifiablePlayers = Collections.unmodifiableSet(players);

	public Alliance(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Player> getPlayers() {
		return unmodifiablePlayers;
	}

	public boolean addPlayer(Player player) {
		return players.add(player);
	}

	public boolean removePlayer(Player player) {
		return players.remove(player);
	}

	public void clear() {
		players.clear();
	}

}
