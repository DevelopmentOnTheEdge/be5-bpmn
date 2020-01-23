package com.developmentontheedge.be5.bpmn;

import java.util.Collections;
import java.util.Map;

import com.developmentontheedge.be5.meta.Meta;
import com.developmentontheedge.be5.operation.Operation;
import com.developmentontheedge.be5.operation.OperationInfo;
import com.developmentontheedge.be5.operation.services.OperationExecutor;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Facade to get access to BE5 services to init and execute operations. 
 */
public class Be5Facade 
{
	@Inject
	static Injector injector;

	@Inject
	static Meta meta;
	
	@Inject
	static OperationExecutor operationExecutor;

	public static Operation getOperation(String entityName, String operationName)
	{
		OperationInfo operationInfo = new OperationInfo(meta.getOperation(entityName, operationName));
		
        Map<String, Object> operationParams = Collections.emptyMap();
        return operationExecutor.create(operationInfo, null, operationParams);
	}
	
	public static Object execute(Operation operation, Map<String, Object> presetValues)
	{
		return operationExecutor.execute(operation, presetValues);
	}
	
}
