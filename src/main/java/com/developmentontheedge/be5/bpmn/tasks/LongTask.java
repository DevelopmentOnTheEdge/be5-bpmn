package com.developmentontheedge.be5.bpmn.tasks;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Utility tasks to emulate long task.
 */
public class LongTask implements JavaDelegate 
{
	/** Variable to specify task duration (ms). 	 */
	public static String VAR_DURATION = "duration";

	/** Variable to specify task duration. 	 */
	public int DEFAULT_DURATION = 300000;
	
	public void execute(DelegateExecution execution) throws Exception 
    {
		int duration = DEFAULT_DURATION;
		
		if( execution.hasVariable(VAR_DURATION) )
		{
			Object val = execution.getVariable(VAR_DURATION);
			if( val instanceof String )
				duration = Integer.parseInt((String)val);
			if( val instanceof Integer )
				duration = ((Integer)val).intValue();
		}
		
		Thread.sleep(duration); 		
	}
}
