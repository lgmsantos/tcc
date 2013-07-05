package graph.algorithms.task.execution;

enum State {
    NEW {
        @Override
        public State run(ExecutionBatch executionBatch) {
            executionBatch.newRun();
            return RUNNING;
        }
    },
    RUNNING {
        @Override
        public State stop(ExecutionBatch executionBatch) { return PAUSED; }

        @Override
        public boolean isRunning() { return true; }
    },
    PAUSED {
        @Override
        public State run(ExecutionBatch executionBatch) {
            return RUNNING;
        }
    },
    FINISHED {
        @Override
        public boolean isFinished() { return true; }
    };

    public State run(ExecutionBatch executionBatch) { return this; }

    public State stop(ExecutionBatch executionBatch) { return this; }

    public boolean isRunning() { return false; }

    public boolean isFinished() { return false; }
}