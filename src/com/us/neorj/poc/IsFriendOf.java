package com.us.neorj.poc;

import org.neo4j.graphdb.RelationshipType;

public class IsFriendOf implements RelationshipType {

	@Override
	public String name() {
		return "IS_FRIEND_OF";
	}

}
