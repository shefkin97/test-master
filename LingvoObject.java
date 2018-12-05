public abstract class LingvoObject {

    public enum Type
    {
        Word,       // слово
        Punct,      // пунктуації
        Spaces,      // пропуску
        Unkown
    }

    public LingvoObject(Type type) {
        m_Type = type;
    }

    public abstract String read(String str, BooleanWrapper endOfSentense);

    public Type getType() {
        return m_Type;
    }

    private Type m_Type;
}