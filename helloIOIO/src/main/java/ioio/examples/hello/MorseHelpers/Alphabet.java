package ioio.examples.hello.MorseHelpers;


import java.util.HashMap;

public class Alphabet {

    Letter[] alphabetArray;
    HashMap<String, Integer> keys;

    public Alphabet(){

        this.alphabetArray = new Letter[]{
                new Letter(".-","a"),
                new Letter("-...","b"),
                new Letter("-.-.","c"),
                new Letter("-..","d"),
                new Letter(".","e"),
                new Letter("..-.","f"),
                new Letter("--.","g"),
                new Letter("....","h"),
                new Letter("..","i"),
                new Letter(".---","j"),
                new Letter("-.-","k"),
                new Letter(".-..","l"),
                new Letter("--","m"),
                new Letter("-.","n"),
                new Letter("---","o"),
                new Letter(".--.","p"),
                new Letter("--.-","q"),
                new Letter(".-.","r"),
                new Letter("...","s"),
                new Letter("-","t"),
                new Letter("..-","u"),
                new Letter("...-","v"),
                new Letter(".--","w"),
                new Letter("-..-","x"),
                new Letter("-.--","y"),
                new Letter("--..","z"),
        };
        this.keys = new HashMap<>();

        for (Integer i=0; i< alphabetArray.length; i++){
            keys.put(alphabetArray[i].imageName, i);
        }

    }

    public Letter[] getAlphabet(){
        return alphabetArray;
    }

    public HashMap<String, Integer> getKeys(){
        return keys;
    }

    public Integer getPositionOfLetter(String letter){
        return keys.get(letter);
    }

    public String getCode(String letra){
        int posicion = getPositionOfLetter(letra);
        return alphabetArray[posicion].morseCode;
    }

}
