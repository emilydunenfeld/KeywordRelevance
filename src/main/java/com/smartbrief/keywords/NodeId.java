package com.smartbrief.keywords;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeId {
	private final UUID nodeId;

	@JsonCreator
	public NodeId(@JsonProperty("nodeId") UUID nodeId) {
		this.nodeId = nodeId;
	}

	public UUID getNodeId() {
		return nodeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		NodeId other = (NodeId) obj;
		return Objects.equals(nodeId, other.nodeId);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + nodeId + "]";
	}
}
