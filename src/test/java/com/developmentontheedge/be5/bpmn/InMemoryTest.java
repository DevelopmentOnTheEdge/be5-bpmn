package com.developmentontheedge.be5.bpmn;

import java.util.Hashtable;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

public class InMemoryTest 
{
	  @Rule
	  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

	  @Test
	  @Deployment(resources = { "printVariables.bpmn" })
	  public void testServiceInvocationSuccessful() 
	  {

	    final RuntimeService runtimeService = processEngineRule.getRuntimeService();

	    // Specify vriables
	    Map<String, Object> variables = new Hashtable <String, Object>();
	    variables.put("Название услуги", "Выплата ЕДВ");
	    //variables.put("Регион", ...)
	    //variables.put("Период", ...)

	    // start the process instance
	    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_printVariables", variables);
	    
	  }  

}
