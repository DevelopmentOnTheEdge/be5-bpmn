package operations

import javax.inject.Inject

import com.developmentontheedge.be5.bpmn.BpmnService
import com.developmentontheedge.be5.metadata.RoleType
import com.developmentontheedge.be5.operation.OperationResult
import com.developmentontheedge.be5.operation.TransactionalOperation
import com.developmentontheedge.be5.server.operations.DeleteOperation
import com.developmentontheedge.be5.util.Utils

class WorkflowDelete extends DeleteOperation implements TransactionalOperation
{
	@Inject
	BpmnService bpmnService

	@Override
	void invoke(Object parameters) throws Exception
	{
		String[] deployIds = db.stringArray("SELECT camundaId FROM workflows WHERE id IN "
              + Utils.inClause(context.records.size()), context.records);

		  for(String id in deployIds)
		  {
			  bpmnService.deleteModel(id)
		  }
 
		  super.invoke(parameters);
		  setResult(OperationResult.finished(out.toString()))
	}
	
}
