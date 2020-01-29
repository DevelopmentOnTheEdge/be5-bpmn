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
class TestServiceOperation extends GOperationSupport
{
	@Override
    Object getParameters(Map<String, Object> presetValues) throws Exception
    {
        return null;
    }

    @Override
    void invoke(Object params) throws Exception
    {
		String[] processDefinitionKeys = db.stringArray("SELECT processDefinitionKey FROM workflows");
    }

}
