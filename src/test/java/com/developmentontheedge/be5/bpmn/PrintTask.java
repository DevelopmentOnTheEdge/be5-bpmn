package com.developmentontheedge.be5.bpmn;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PrintTask implements JavaDelegate 
{
	public void execute(DelegateExecution execution) throws Exception 
    {
		System.out.println("Print task, variables: ");
		System.out.println(execution.getVariables());
	}

}
