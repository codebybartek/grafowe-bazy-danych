package com.neo4j.contest;

import org.neo4j.ogm.session.Session;

import java.util.HashMap;
import java.util.Map;

class PlayerService extends GenericService<Player> {

    public PlayerService(Session session) {
		super(session);
	}
    
	@Override
	Class<Player> getEntityType() {
		return Player.class;
	}


	public void update(Long id, String newName) {
		String query = "MATCH (player:Player) " +
				"WHERE ID(player) = $playerId " +
				"SET player.name = $name";
		Map<String, Object> map = new HashMap<>();
		map.put("playerId", id);
		map.put("name", newName);
		session.queryForObject(Player.class, query, map);
	}

	public void moveUp(Long id) {
		String query = "MATCH (player:Player) " +
				"WHERE ID(player) = $playerId " +
				"SET player.position = (player.position + 1) ";
		Map<String, Object> map = new HashMap<>();
		map.put("playerId", id);
		session.queryForObject(Player.class, query, map);
	}

	public void moveDown(Long id) {
		String query = "MATCH (player:Player) " +
				"WHERE ID(player) = $playerId " +
				"SET player.position = (player.position - 1) ";
		Map<String, Object> map = new HashMap<>();
		map.put("playerId", id);
		session.queryForObject(Player.class, query, map);
	}

	public Iterable<Player> getPlayers(int age) {
		String query = "MATCH (player:Player) " +
				"WHERE player.age >= $playerAge " +
				"RETURN player";

		Map<String, Object> map = new HashMap<>();
		map.put("playerAge", age);
		return session.query(Player.class, query, map);
	}

	public Iterable<Player> getALLPlayers() {
		String query = "MATCH (player:Player) " +
				"RETURN player";
		Map<String, Object> map = new HashMap<>();
		return session.query(Player.class, query, map);
	}

}
