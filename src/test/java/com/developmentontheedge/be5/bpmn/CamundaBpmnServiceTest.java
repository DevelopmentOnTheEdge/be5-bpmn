package com.developmentontheedge.be5.bpmn;

import com.developmentontheedge.be5.bpmn.CamundaBpmnServiceImpl;

import java.util.Map;
import java.util.Hashtable;
import java.util.List;

import org.camunda.bpm.engine.task.Task;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CamundaBpmnServiceTest 
{
	static BpmnService bpmnService;
	static String processDefinitionKey;
	static String processInstanceId;
	
	static String WORKFLOW = "userTask.bpmn";

	@Test
	public void _1_init()
	{
		bpmnService = new CamundaBpmnServiceImpl();
		processDefinitionKey = bpmnService.deployModel(WORKFLOW, 
				getClass().getClassLoader().getResourceAsStream(WORKFLOW));
	}

	@Test
	public void _2_startWorkflow()
	{
		// init variables
	    Map<String, Object> variables = new Hashtable<String, Object>();
	    variables.put("e-mail", "test@test.com");
	    
	    processInstanceId = bpmnService.startProcess(processDefinitionKey, variables);
	}
	
	@Test
	public void _3_userTask() throws Exception
	{
		List<Task> active = bpmnService.getActiveTasks();
		
		Task t = active.get(0);

		Map<String, Object> vars =  bpmnService.getVariables(t.getExecutionId());
		
		bpmnService.setVariable(t.getExecutionId(), "e-mail", "new@test.com");
		
		Map<String, Object> vars2 =  new Hashtable<String, Object>();
		vars2.put("user_var2", "completed");
		bpmnService.completeTask(t.getId(), vars2);
		
		return;
	}

	@Test
	public void _9_removeWorkflow()
	{

		bpmnService.deleteModel(processDefinitionKey);
		bpmnService.deleteModel("Process_printVariables");		
	}
	
}
