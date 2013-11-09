package com.gekkotta.productions.unit.player;

import com.gekkotta.productions.unit.server.ServerData;

public class Player {
	String name, email, ign;

	public Player(String name, String email, String ign) {
		this.ign = ign;
		this.name = name;
		this.email = email;
	}

	public boolean isUnique(String ign) {
		// add url later
		String url = ("");
		String raw = ServerData.readContents(url);
		if (raw.equals("false")) {
			return false;
		} else {
			return true;
		}
	}

	public void sendToServer(String name, String email, String ign) {
		// do later
	}
}
