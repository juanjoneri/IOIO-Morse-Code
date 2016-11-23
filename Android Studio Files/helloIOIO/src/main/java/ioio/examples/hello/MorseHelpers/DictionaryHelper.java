package ioio.examples.hello.MorseHelpers;

import ioio.lib.api.exception.ConnectionLostException;

public class DictionaryHelper {
    //Utiliza los valores primitivos almacenados por Char Helper para generar el alfabeto
    //Tiene metodos que reproducen cada una de las letras en formato Morse

    CharHelper charHelper; //necesita de los valores primitivos almacenados en un charHelper
    Alphabet alphabet;
    final static String DOT = "dot"; //Aqui definimos los valores KEY para acceder al las los Time Helpers
    final static String DASH = "dash";
    final static String SPACEONE = "spaceOne";
    final static String SPACETWO = "spaceTwo";
    final static String SPACETHREE = "spaceThree";


    public DictionaryHelper(CharHelper charHelper, Alphabet alphabet){
        this.alphabet = alphabet;
        this.charHelper=charHelper;
    }
    //Metodo constructor

    public void ejecutarLetra(String letra) throws ConnectionLostException {
        String morseCode = alphabet.getCode(letra);

        for (int i=0; i<morseCode.length();i++){
            switch (morseCode.charAt(i)){
                case '.': charHelper.getPrimitive(DOT).work();
                    break;
                case '-': charHelper.getPrimitive(DASH).work();
                    break;
            }
            charHelper.getPrimitive(SPACEONE).work();//Entre char y char espacio simple
        }

        charHelper.getPrimitive(SPACETWO).work();//entre letra y letra espacio 2
    }

    public void ejecutarEspacio() throws ConnectionLostException {
        charHelper.getPrimitive(SPACETHREE).work();
    }

}
