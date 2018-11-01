package modules.containers.processor;

import behaviour.Consumer;
import behaviour.Pluggable;

public interface Processor extends Pluggable, Consumer{

    int process(int duration); //Duration in ms
}
