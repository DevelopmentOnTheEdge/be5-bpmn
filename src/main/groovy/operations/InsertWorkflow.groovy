package operations

import com.developmentontheedge.be5.bpmn.BpmnService
import com.developmentontheedge.be5.databasemodel.util.DpsUtils
import com.developmentontheedge.be5.server.model.Base64File
import com.developmentontheedge.be5.server.operations.support.GOperationSupport

import groovy.transform.TypeChecked

import javax.inject.Inject

class InsertWorkflow extends GOperationSupport
{
	@Inject
	BpmnService bpmnService
	
	@Override
    Object getParameters(Map<String, Object> presetValues) throws Exception
    {
        params.add("title", "Название")
        params.add("description", "Описание") { CAN_BE_NULL = true }
		params.add("comment", "Комментарий") { CAN_BE_NULL = true }
        params.add("file", "Файл") { TYPE = Base64File }
		
        return DpsUtils.setValues(params, presetValues)
    }

    @Override
    void invoke(Object params) throws Exception
    {
        def file = (Base64File) params.$file

		String model = new String(file.data)
		String modelId = bpmnService.deployModel((String)params.$title, model)
		
	    database.workflows << [
            title       : params.$title,
            description : params.$description,
            comment     : params.$comment,
            data        : model,
			camundaId   : modelId
        ]
            
    }
	
}
