package com.simlab.frontlinesms.domains;

public class Fmessage {
	private String source;
	private String text;
	private boolean inbound;

	// > GETTERS
	public String getSource() {
		return this.source;
	}

	public String getText() {
		return this.text;
	}

	public boolean isInbound() {
		return this.inbound;
	}

	// >SETTERS
	public void setSource(String s) {
		this.source = s;
	}

	public void setText(String t) {
		this.text = t;
	}

	public void setInbound(boolean b) {
		this.inbound = b;
	}
}
