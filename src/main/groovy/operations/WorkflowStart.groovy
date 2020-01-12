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

@TypeChecked
class WorkflowStart extends GOperationSupport
{
	@Inject
	BpmnService bpmnService
	
	@Override
    Object getParameters(Map<String, Object> presetValues) throws Exception
    {
        params.add("var1 ", "Var 1") { CAN_BE_NULL = true }
		
        return DpsUtils.setValues(params, presetValues)
    }

    @Override
    void invoke(Object params) throws Exception
    {
		String[] deployId = db.stringArray("SELECT camundaId FROM workflows WHERE id=?", context.records[0]);

		try
		{
			String processId = bpmnService.startProcess(deployId[0], 
				DpsUtils.toLinkedHashMap((DynamicPropertySet)params) );

			setResult(OperationResult.finished("Workflow started, process instance id=" + processId))
		}
		catch(Throwable t)
		{
			String msg = ("Workflow error, deployId =" + deployId[0] + ", error: " + t.getMessage())
			setResult(OperationResult.error(msg))
			
			System.err.println(msg)
			t.printStackTrace(System.err)
		}

    }
	
}
