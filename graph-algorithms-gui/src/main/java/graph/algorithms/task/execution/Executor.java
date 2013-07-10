package graph.algorithms.task.execution;

public class Executor {
    
    private ExecutionBatch currentBatch;
    private ExecutionSignal signal;
    private boolean stop;

    public Executor(ExecutionSignal signal) {
        this.signal = signal;
    }
    
    public void executeBatch(final ExecutionBatch batch){
        if(currentBatch != null)
            throw new RuntimeException("Executor Busy");
        
        new Thread(new Runnable() {            
            @Override
            public void run() {
                try{
                    currentBatch = batch;
                    stop = false;
                    execute();
                }finally{
                    currentBatch = null;
                }
            }
        }, "Executor").start();
    }
    
    private void execute() {
        mainLoop:
        for(Execution<?> execution: currentBatch.getExecutions()){
            while(!execution.finished()){
                if(stop) 
                    break mainLoop;
                execution.step();
                signal.sendStep(execution);
            }
            signal.sendFinished(execution);
        }
    }
    
    public void stop(){
        stop = true;
    }
}
