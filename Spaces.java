public class Spaces extends Character {

    public Spaces() {
        super(Type.Spaces);
    }

    public Spaces(char value) {
        super(Type.Spaces, value);

        if (!checkValue(value))
            throw new IllegalArgumentException("It`s not spaces!");
    }

    @Override
    public String read(String str, BooleanWrapper endOfSentense) {
        int i = 0;

        while (i < str.length() && checkValue(str.charAt(i)))
            setValue(str.charAt(i++));

        return str.substring(i);
    }


    private boolean checkValue(char value) { return Utility.isSpace(value); }
}
