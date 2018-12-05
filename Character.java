public class Character extends LingvoObject {

    public Character() { super(Type.Unkown);}

    public Character(Type charType) {
        super(charType);
    }

    @Override
    public String toString() {
        return new String() + m_Value;
    }

    public Character(Type charType, char value) {
        super(charType);
        m_Value = value;
    }

    public String read(String str, BooleanWrapper endOfSentense) {
        setValue(str.charAt(0));
        return str.substring(1);
    }

    public void setValue(char ch) {
        m_Value = ch;
    }

    public char getValue() {
        return m_Value;
    }

    private char m_Value;
}
