package ro.pub.cs.aipi.lab05.helper;

import java.io.Serializable;

public class Record implements Serializable {

	final public static long serialVersionUID = 20152015L;

	private String attribute;
	private Object value;

	public Record() {
		attribute = new String();
		value = new Object();
	}

	public Record(String attribute, Object value) {
		this.attribute = attribute;
		this.value = value;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getAttribute() {
		return attribute;
	}

	public Object getValue() {
		return value;
	}

}
