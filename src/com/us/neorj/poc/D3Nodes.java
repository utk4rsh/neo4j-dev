package com.us.neorj.poc;

public class D3Nodes {
	private String term;
	private int nodeId;
	private int clusterId;
	private int clusterWeight;

	public D3Nodes(String term, int nodeId, int clusterId, int weight) {
		super();
		this.term = term;
		this.clusterId = clusterId;
		this.clusterWeight = weight;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public int getWeight() {
		return clusterWeight;
	}

	public void setWeight(int weight) {
		this.clusterWeight = weight;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		D3Nodes other = (D3Nodes) obj;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "D3Nodes [term=" + term + ", nodeId=" + nodeId + ", clusterId="
				+ clusterId + ", clusterWeight=" + clusterWeight + "]";
	}

}
