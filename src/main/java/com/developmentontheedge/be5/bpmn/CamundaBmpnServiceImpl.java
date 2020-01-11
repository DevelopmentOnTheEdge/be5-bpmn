package com.developmentontheedge.be5.bpmn;

import java.io.InputStream;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.repository.Deployment;

public class CamundaBmpnServiceImpl implements BpmnService
{
	static ProcessEngine processEngine;

	public CamundaBmpnServiceImpl()
	{
		InputStream cfg = this.getClass().getClassLoader().getResourceAsStream("camunda.cfg.xml");

		ProcessEngineConfiguration pec = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(cfg)
				.setJdbcDriver("org.postgresql.Driver");

System.out.println("###Camunda: " + pec.getJdbcUrl() + ", " + pec.getJdbcDriver());		
		
		processEngine = pec.buildProcessEngine();
		
/*
		processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(cfg)
				.buildProcessEngine();
*/				
	}
	
	/**
	 * Deploys BPMN model.
	 * @return id for deployed model. 
	 */
	public String deployModel(String name, String model)
	{
		Deployment deployment = processEngine.getRepositoryService()
			     .createDeployment()
			     .addString(name, model)
			     .deploy();			
		
		return deployment.getId();
	}

}
