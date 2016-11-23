package ioio.examples.hello.MorseHelpers;


import ioio.lib.api.exception.ConnectionLostException;

public class MorseHelper {
//Este objeto hace el trabajo definitivo de traduccion de letras en formato Char a DictionaryHelper
    DictionaryHelper dictionaryHelper;

    public MorseHelper(DictionaryHelper dictionaryHelper){
        //Su metodo constructor solo hace uso de los un DictionaryHelper, usa sus metodos para mostrar las letras con el .work()
        this.dictionaryHelper=dictionaryHelper;
    }

    public void convert(String text) throws ConnectionLostException {

        for (int i = 0; i < text.length(); i++) {
            String c = String.valueOf(text.charAt(i));
            if (c.equals(" ")){
                dictionaryHelper.ejecutarEspacio();
            }
            else {
                dictionaryHelper.ejecutarLetra(c);
            }
        }
    }
}
