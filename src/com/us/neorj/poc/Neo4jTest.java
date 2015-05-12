package com.us.neorj.poc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.logging.Logger;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.tooling.GlobalGraphOperations;

import com.google.gson.Gson;

public class Neo4jTest {
	static Logger logger = Logger.getLogger("Neo4jTest");

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		BufferedReader br = new BufferedReader(new FileReader("~/home/data/cluster_graph_full.json"));
		Gson gson = new Gson();
		D3JsonObject d3Json = gson.fromJson(br, D3JsonObject.class);
		LinkedHashSet<D3Links> finalD3Links = d3Json.getLinks();
		LinkedHashSet<D3Nodes> finalD3Nodes = d3Json.getNodes();
		System.out.println("Starting to write in graph DB");
		Map<Integer, Node> neo4NodeMap = new HashMap<Integer, Node>();
		GraphDatabaseService graphDb = new GraphDatabaseFactory()
				.newEmbeddedDatabase("~/home/data/neo4j_cluster.db");
		try (Transaction tx = graphDb.beginTx()) {
			for (D3Nodes d3Node : finalD3Nodes) {
				Node neo4JNode = graphDb.createNode();
				System.out.println("Created node with ID : " + neo4JNode.getId());
				try {
					int clusterId = Integer.parseInt(d3Node.getTerm());
					neo4JNode.setProperty("type", "cluster");
					neo4JNode.addLabel(MyLabels.CLUSTER);
					neo4JNode.setProperty("name", d3Node.getTerm());
				} catch (Exception e) {
					System.out.println("Caught Exception, therefore type is term");
					neo4JNode.setProperty("type", "term");
					neo4JNode.setProperty("name", d3Node.getTerm());
					neo4JNode.addLabel(MyLabels.TERM);
				}
				neo4NodeMap.put(d3Node.getNodeId(), neo4JNode);
			}
			for (D3Links d3Link : finalD3Links) {
				Node nSource = neo4NodeMap.get(d3Link.getSource());
				Node nTarget = neo4NodeMap.get(d3Link.getTarget());
				if (d3Link.getSource() == d3Link.getTarget())
					System.out.println("Self Loop");
				else {
					try {
						int clusterId = Integer.parseInt((String) neo4NodeMap.get(d3Link.getSource()).getProperty("name"));
						int clusterId2 = Integer.parseInt((String) neo4NodeMap.get(d3Link.getTarget()).getProperty("name"));
						Relationship rel2 = nSource.createRelationshipTo(nTarget, MyRelationshipTypes.IS_CLUSTER);
						System.out.println("Creating relationship from term " + d3Link.getSource() + " to " + d3Link.getTarget());
						rel2.setProperty("WT", d3Link.getValue());
					} catch (Exception e) {
						System.out.println("Caught Exception in relationship, therefore type is term");
						Relationship rel1 = nSource.createRelationshipTo(nTarget, MyRelationshipTypes.IS_TOP_TERM);
						rel1.setProperty("WT", d3Link.getValue());
					}
					
				}
			}
			tx.success();
		}
	}

	public static void iterateNodes() {
		System.out.println("Iterating nodes");
		GraphDatabaseService graphDb = new GraphDatabaseFactory()
				.newEmbeddedDatabase("~/home/data/neo4j_cluster.db");
		try (Transaction tx = graphDb.beginTx()) {
			ResourceIterable<Node> movies = GlobalGraphOperations.at(graphDb).getAllNodes();
			for (Node movie : movies) {
				System.out.println(movie.getProperty("TERM"));
			}
			tx.success();
		}
	}

	private static void setPropertiesForNeo4JNode(D3Nodes d3Node, Node neo4JNode) {
		neo4JNode.setProperty("name", d3Node.getTerm());
		try {
			int clusterId = Integer.parseInt(d3Node.getTerm());
			 System.out.println("cluster Id : " + clusterId);
			neo4JNode.setProperty("type", "cluster");
			neo4JNode.addLabel(MyLabels.CLUSTER);
		} catch (Exception e) {
			 System.out.println("Caught Exception, therefore type is term");
			neo4JNode.setProperty("type", "term");
			neo4JNode.addLabel(MyLabels.TERM);
		}

	}

	public enum MyRelationshipTypes implements RelationshipType {
		IS_TOP_TERM, IS_CLUSTER, HAS_SEEN;
	}

}
