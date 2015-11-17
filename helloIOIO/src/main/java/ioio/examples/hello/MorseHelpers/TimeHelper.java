package ioio.examples.hello.MorseHelpers;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;

public class TimeHelper {

    final static int N=250; //Unidad Patron de medida del tiempo en mS
    //final y estatico porque es una constante que no debe ser alterada

    int period; //La cantidad de N que dura el gesto (dot, dash, etc)
    boolean onOf; //Estara prendido o apagado durante este tiempo
    DigitalOutput led; //Lugar donde se manifiesta este objeto


    public TimeHelper(boolean onOf, int period, DigitalOutput led){
        //Metodo constructor "time helper". Segun los parametros establecidos
        this.onOf=onOf;
        this.period=period;
        this.led=led;
    }

    public void work() throws ConnectionLostException {
        //Cuando es llamado pone a ejecutar el "time helper"

        long tInicio = System.currentTimeMillis();
        while(System.currentTimeMillis() < (tInicio + (period*N))){
            led.write(!onOf);
        }

        //Al terminar de ejecutar apaga el LED
        led.write(true);
    }

}
