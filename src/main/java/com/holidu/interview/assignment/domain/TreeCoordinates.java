package com.holidu.interview.assignment.domain;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TreeCoordinates {
    @NonNull
    @JsonProperty("tree_id")
    private String treeId;
    @NonNull
    @JsonProperty("spc_common")
    private String treeName;
    @JsonProperty("x_sp")
    private Double x;
    @JsonProperty("y_sp")
    private Double y;
    
    public TreeCoordinates() {
		super();
	}
    
	public TreeCoordinates(String treeId, String treeName, Double x, Double y) {
		super();
		this.treeId = treeId;
		this.treeName = treeName;
		this.x = x;
		this.y = y;
	}
	
	public String getTreeId() {
		return treeId;
	}
	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
	public String getTreeName() {
		return treeName;
	}
	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
    
    
}
