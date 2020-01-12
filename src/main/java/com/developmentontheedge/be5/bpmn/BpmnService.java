package com.developmentontheedge.be5.bpmn;

import java.util.Map;

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
	
	/**
	 * Starts BPMN model with the specified id and variables.
	 * 
	 * @return id for started process instance. 
	 */
	public String startProcess(String id, Map<String,Object> variables);
}