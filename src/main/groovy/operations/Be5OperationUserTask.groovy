package operations

import javax.inject.Inject

import com.developmentontheedge.be5.bpmn.Be5Facade
import com.developmentontheedge.be5.bpmn.BpmnService
import com.developmentontheedge.be5.groovy.GDynamicPropertySetSupport
import com.developmentontheedge.be5.server.operations.support.GOperationSupport
import com.developmentontheedge.be5.operation.Operation
import com.developmentontheedge.be5.operation.OperationResult

import groovy.transform.TypeChecked

//@TypeChecked
class Be5OperationUserTask extends GOperationSupport
{
	public static final String ENTITY_NAME_PARAM    = "entityName";
	public static final String OPERATION_NAME_PARAM = "operationName";
	public static final String TASK_ID_PARAM        = "bpmnTaskId";
	public static final String EXECUTION_ID_PARAM   = "executionId";
	
	@Inject
	BpmnService bpmnService

	@Override
	Object getParameters(Map<String, Object> presetValues) throws Exception
	{
		String taskId      = getParam(presetValues, TASK_ID_PARAM)
		String executionId = getParam(presetValues, EXECUTION_ID_PARAM)

		Map<String, Object> taskParams = bpmnService.getVariables(executionId)
		String entityName    = getParam(taskParams, ENTITY_NAME_PARAM)
		String operationName = getParam(taskParams, OPERATION_NAME_PARAM)
		
		Operation operation = Be5Facade.getOperation(entityName, operationName)

		Object delegateParams = Be5Facade.getParameters(operation, presetValues);
		if( delegateParams == null )
			delegateParams = new GDynamicPropertySetSupport()
		
		delegateParams.add(ENTITY_NAME_PARAM)    { HIDDEN = true; value = entityName }		
		delegateParams.add(OPERATION_NAME_PARAM) { HIDDEN = true; value = operationName }
		delegateParams.add(TASK_ID_PARAM)        { HIDDEN = true; value = taskId }
		delegateParams.add(EXECUTION_ID_PARAM)   { HIDDEN = true; value = executionId }
		
		delegateParams.add("TASK_INFO", " Task info") { 
			READ_ONLY = true 
            value = generateTaskInfo(executionId, entityName, operationName) 
		}

		return delegateParams
	}

	protected String generateTaskInfo(String executionId, String entityName, String operationName)
	{
		return "Task: " + entityName + "." + operationName
	}
	
	
	void invoke(Object params) throws Exception
	{
		Map<String, Object> paramsAsMap = params.asMap()
		String taskId  = getParam(paramsAsMap, TASK_ID_PARAM)
		Operation operation = Be5Facade.getOperation( getParam(paramsAsMap, ENTITY_NAME_PARAM),
													  getParam(paramsAsMap, OPERATION_NAME_PARAM) );

		Be5Facade.execute(operation, (Map<String, Object>)paramsAsMap)
		bpmnService.completeTask(taskId, paramsAsMap)

		setResult(operation.getResult())
	}

	protected String getParam(Map<String, Object> values, String param)
	{
		Object value = values.get(param);
		
		if( value == null )
			throw new IllegalArgumentException("Parameter " + param + " should be specified for Be5UserTask.");
		
		if( ! (value instanceof String) )
			throw new IllegalArgumentException("Value for parameter " + param + " should be string in Be5UserTask.");
		
		return (String) value;
	}

}
