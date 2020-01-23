package com.developmentontheedge.be5.bpmn.tasks;

import java.util.Hashtable;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.developmentontheedge.be5.bpmn.Be5Facade;
import com.developmentontheedge.be5.operation.Operation;

/**
 * Utility tasks to print workflow variables.
 */
public class Be5OperationServiceTask implements JavaDelegate 
{
	public static final String ENTITY_NAME_PARAM    = "entityName";
	public static final String OPERATION_NAME_PARAM = "operationName";
	
	public void execute(DelegateExecution execution) throws Exception
	{
		Operation operation = Be5Facade.getOperation( getParam(execution, ENTITY_NAME_PARAM), 
				                                      getParam(execution, OPERATION_NAME_PARAM) );

		Map<String, Object> params = new Hashtable<String, Object>(execution.getVariables());
		Be5Facade.execute(operation, params);		
	}
	
	protected String getParam(DelegateExecution execution, String param)
	{
		Object value = execution.getVariables().get(param);
		
		if( value == null )
			throw new IllegalArgumentException("Parameter " + param + "should be specified for Be5OperationServiceTask.");
		
		if( ! (value instanceof String) )
			throw new IllegalArgumentException("Value for parameter " + param + "should be string in Be5OperationServiceTask.");
		
		return (String) value;
	}
	
}
