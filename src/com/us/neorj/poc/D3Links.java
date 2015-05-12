package com.us.neorj.poc;

public class D3Links {
	private int source;
	private int target;
	private int value;

	public D3Links(int source, int target, int value) {
		super();
		this.source = source;
		this.target = target;
		this.value = value;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
