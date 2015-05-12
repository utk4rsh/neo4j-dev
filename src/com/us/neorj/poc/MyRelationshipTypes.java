package com.us.neorj.poc;

import org.neo4j.graphdb.RelationshipType;

public enum MyRelationshipTypes implements RelationshipType {
	IS_FRIEND_OF,
	HAS_SEEN;
}
