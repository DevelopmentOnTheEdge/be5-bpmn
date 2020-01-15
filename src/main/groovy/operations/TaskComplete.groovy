package operations

import com.developmentontheedge.beans.DynamicPropertySet

import com.developmentontheedge.be5.bpmn.BpmnService
import com.developmentontheedge.be5.databasemodel.util.DpsUtils
import com.developmentontheedge.be5.operation.OperationResult
import com.developmentontheedge.be5.server.operations.support.GOperationSupport
import com.developmentontheedge.be5.util.Utils

import javax.inject.Inject

import org.camunda.bpm.engine.runtime.ProcessInstance

import groovy.transform.TypeChecked

//@TypeChecked
class TaskComplete extends GOperationSupport
{
	@Inject
	BpmnService bpmnService
	
	@Override
    Object getParameters(Map<String, Object> presetValues) throws Exception
    {
		params.add("task", "Task ID")
		
        return DpsUtils.setValues(params, presetValues)
    }

    @Override
    void invoke(Object params) throws Exception
    {
		bpmnService.completeTask(params.$task, null)
    }
	
}
