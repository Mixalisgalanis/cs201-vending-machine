package modules.containers.processor;

import interfaces.Pluggable;

public interface Processor extends Pluggable{

    int process(int duration); //Duration in ms
}
