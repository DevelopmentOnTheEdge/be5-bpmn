package com.developmentontheedge.be5.bpmn;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class CamundaBmpnServiceImpl implements BpmnService
{
	public static String BPMN_SUFFIX = ".bpmn";
	
	static ProcessEngine processEngine;

	public CamundaBmpnServiceImpl()
	{
		InputStream cfg = this.getClass().getClassLoader().getResourceAsStream("camunda.cfg.xml");

		ProcessEngineConfiguration pec = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(cfg)
				.setJdbcDriver("org.postgresql.Driver");

		processEngine = pec.buildProcessEngine();
	}
	
	/**
	 * Deploys BPMN model.
	 * 
	 * Model name should end with ".bpmn".
	 * The method checks it and adds  ".bpmn" if needed.
	 *  
	 * @return id for deployed model. 
	 */
	public String deployModel(String name, String model)
	{
		if( !name.endsWith(BPMN_SUFFIX) )
			name += BPMN_SUFFIX;
				
		Deployment deployment = processEngine.getRepositoryService()
			     .createDeployment()
			     .addString(name, model)
			     .deploy();			
		
		return deployment.getId();
	}

	/**
	 * Delete BPMN model with the specified id and all related resources
	 * (cascade deletion to process instances, history process instances and jobs).
	 */
	public void deleteModel(String id)
	{
		processEngine.getRepositoryService()
			.deleteDeployment(id, true);
	}
	
	/**
	 * Starts BPMN model with the specified id and variables.
	 * 
	 * @return id for started process instance. 
	 */
	public String startProcess(String id, Map<String,Object> variables)
	{
	    List<ProcessDefinition> definitions = processEngine.getRepositoryService()
	    		.createProcessDefinitionQuery()
	    		.deploymentId(id)
	    		.list();
		
		ProcessInstance pi = processEngine.getRuntimeService()
	    		.startProcessInstanceById(definitions.get(0).getId(), variables);
		
		return pi.getProcessInstanceId();
	}
}
