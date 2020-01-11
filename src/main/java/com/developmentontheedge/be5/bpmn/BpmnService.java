package com.developmentontheedge.be5.bpmn;

public interface BpmnService
{
	/**
	 * Deploys BPMN model.
	 * 
	 * @return id for deployed model. 
	 */
	public String deployModel(String name, String model);
	

}