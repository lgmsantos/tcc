package graph.algorithms.task.execution;

import graph.algorithms.gui.ExecutionBatchControl;
import graph.algorithms.task.GraphInput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.picocontainer.Startable;

import com.thoughtworks.xstream.XStream;

public class ExecutionLoader implements Startable{

    private ExecutionBatchControl batchControl;
    private XStream xstream;

    public ExecutionLoader(final ExecutionBatchControl batchControl, ExecutionSignal signal) {
        this.batchControl = batchControl;
        xstream = new XStream();
        xstream.setMode(XStream.ID_REFERENCES);
        xstream.omitField(GraphInput.class, "graph");
        

        signal.addExecutionListener(new ExecutionListener() {

            @Override
            public void executionStepped(Execution<?> execution) {
            }

            @Override
            public void executionFinished(Execution<?> execution) {
                save();
            }

        });
    }

    private synchronized void save() {
        try (FileOutputStream out = new FileOutputStream("data")) {
            xstream.toXML(batchControl.getExecutionBatches(), out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void load() {
        File file = new File("data");
        if(!file.exists())
            return;
        
        @SuppressWarnings("unchecked")
        List<ExecutionBatch> batches = (List<ExecutionBatch>) xstream.fromXML(file);
        batchControl.setExecutionBatches(batches);
    }

    @Override
    public void start() {
        load();
    }

    @Override
    public void stop() {
        
    }
}
