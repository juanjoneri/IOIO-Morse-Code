package ioio.examples.hello.MorseHelpers;

import java.util.HashMap;
import ioio.lib.api.DigitalOutput;

public class CharHelper {
    //Este objeto almacena una coleccion de Time Helpers: dot, dash, spaceOne, spaceTwo, spaceTree.
    //Debido a que una combinacion de pulsos en estos intervalos generan cualquier oracion en Morse
    
    HashMap<String, TimeHelper> primitives = new HashMap<String, TimeHelper>(); //Hashmap permite asignare un nombre a cada Time Helper
    DigitalOutput led; //Lugar en donde se manifiesta el objeto

    public CharHelper(DigitalOutput led){
        //Metodo constructor de la coleccion de time Helpers
        this.led=led;
        primitives.put("dot", new TimeHelper(true, 1, led));//prendido, una unidad
        primitives.put("dash", new TimeHelper(true, 3, led));//prendido, tres unidades
        primitives.put("spaceOne", new TimeHelper(false, 1, led));//apagado, unda unidad (uso interno de la letra)
        primitives.put("spaceTwo", new TimeHelper(false, 2, led));//2+1=3 (uso entre letra y letra)
        primitives.put("spaceThree", new TimeHelper(false, 4, led));//apagado, 4 unidades
                                        // 3+2+1=7, simpre viene despues de space one y two

    }

    public TimeHelper getPrimitive(String key) {
        return primitives.get(key);
    }
    //Este metodo de usa para acceder a el primitivo sabiendo su valor key
}





