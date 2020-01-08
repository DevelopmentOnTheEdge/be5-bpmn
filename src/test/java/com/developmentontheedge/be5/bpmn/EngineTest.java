package com.developmentontheedge.be5.bpmn;

import java.util.Map;
import java.util.Hashtable;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EngineTest 
{
	static ProcessEngine processEngine;
	static Deployment deployment;
	static ProcessInstance processInstance;

	@Test
	public void _1_initProcessEngine()
	{
		processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResourceDefault()
				.buildProcessEngine();
	}

	@Test
	public void _2_deployWorkflow()
	{
		deployment = processEngine.getRepositoryService()
				     .createDeployment()
				     .addInputStream("testWorkflow.bpmn", this.getClass().getClassLoader().getResourceAsStream("testWorkflow.bpmn"))
				     .deploy();				     
	}

	@Test
	public void _3_startWorkflow()
	{
		// init variables
	    Map<String, Object> variables = new Hashtable<String, Object>();

		// start the process instance
	    processInstance = processEngine.getRuntimeService()
	    		.startProcessInstanceByKey("testWorkflowId", variables);
	}
	
	@Test
	public void _9_removeWorkflow()
	{
//		processEngine.getRepositoryService()
//			.deleteDeployment("..", true);

//		processEngine.getRepositoryService()
//			.deleteDeployment(deployment.getId(), true);
	}
		
}
