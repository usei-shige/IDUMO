package com.hixi_hyi.idumo.console.exec;

import com.hixi_hyi.idumo.core.IdumoException;
import com.hixi_hyi.idumo.core.exec.AbstractExecutionComponent;

public abstract class AbstractConsoleMain {

	private ConsoleExecution execution;

	public void setExecutionWithComponent(AbstractExecutionComponent component){
		this.execution = new ConsoleExecution(component);
	}

	public void setExecution(ConsoleExecution execution) {
		this.execution = execution;
	}

	abstract public void init();

	public void exec(){
		init();
		try {
			execution.onIdumoCreated();
		} catch (IdumoException e) {
			e.printStackTrace();
		}
		execution.onIdumoStart();
		execution.onIdumoExec();
		execution.onIdumoStop();
	}
}
