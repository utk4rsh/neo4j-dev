package com.us.neorj.poc;

import java.util.LinkedHashSet;

public class D3JsonObject {

	private LinkedHashSet<D3Nodes> nodes;
	private LinkedHashSet<D3Links> links;

	public D3JsonObject(LinkedHashSet<D3Nodes> nodes, LinkedHashSet<D3Links> links) {
		super();
		this.nodes = nodes;
		this.links = links;
	}

	public LinkedHashSet<D3Nodes> getNodes() {
		return nodes;
	}

	public void setNodes(LinkedHashSet<D3Nodes> nodes) {
		this.nodes = nodes;
	}

	public LinkedHashSet<D3Links> getLinks() {
		return links;
	}

	public void setLinks(LinkedHashSet<D3Links> links) {
		this.links = links;
	}

}
