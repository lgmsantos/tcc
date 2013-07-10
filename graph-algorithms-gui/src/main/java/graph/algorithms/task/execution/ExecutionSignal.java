package graph.algorithms.task.execution;

import java.util.ArrayList;
import java.util.List;

public class ExecutionSignal {
    
    private List<ExecutionListener> listeners;

    public ExecutionSignal() {
        listeners = new ArrayList<>();
    }

    public void addExecutionListener(ExecutionListener executionListener){
        listeners.add(executionListener);
    }
    
    public void sendStep(Execution<?> execution) {
        for(ExecutionListener listener: listeners)
            listener.executionStepped(execution);
    }

    public void sendFinished(Execution<?> execution) {
        for(ExecutionListener listener: listeners)
            listener.executionFinished(execution);        
    }
}
