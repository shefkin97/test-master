public class Punctuation extends Character {

    Punctuation() {
        super(Type.Punct);
    }

    Punctuation(char value) {
        super(Type.Punct, value);

        if (!checkValue(value))
            throw new IllegalArgumentException("It`s not sentense!");
    }

    @Override
    public  String  read(String str, BooleanWrapper endOfSentense){
        char ch = str.charAt(0);

        if (checkValue(ch)) {
            setValue(ch);

            switch (ch) {
                case '.':
                case '!':
                case '?':
                    endOfSentense.value = true;
            }

            return str.substring(1);
        }

        return str;
    }

    private boolean checkValue(char value) {
        return Utility.isPunct(value);
    }
}
