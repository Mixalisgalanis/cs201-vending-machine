package modules.containers.processor;

import behaviour.Consumer;

public interface Processor extends Consumer {

    void process(int duration); //Duration in ms
}
