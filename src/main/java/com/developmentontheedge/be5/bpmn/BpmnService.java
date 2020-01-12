package com.developmentontheedge.be5.bpmn;

public interface BpmnService
{
	/**
	 * Deploys BPMN model.
	 * 
	 * @return id for deployed model. 
	 */
	public String deployModel(String name, String model);
	
	/**
	 * Delete BPMN model with the specified id and all related resources.
	 */
	public void deleteModel(String id);
}