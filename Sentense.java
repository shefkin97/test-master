import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sentense {

    enum Type
    {
        Simple,
        Ask
    }

    public Sentense() {
        m_Type = Type.Simple;
        m_Objects = new ArrayList<>();
    }

    public Sentense(ArrayList<Word> words, Type type) {
        m_Type = Type.Simple;
        m_Objects = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getType() == LingvoObject.Type.Word) {
                m_Objects.add(words.get(i));
                if (i < words.size() - 1)
                    m_Objects.add(new Spaces(' '));
            }
        }
        if (type == Type.Ask)
            m_Objects.add(new Punctuation('?'));
        else
            m_Objects.add(new Punctuation('.'));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i =0; i < m_Objects.size(); ++i)
            builder.append(m_Objects.get(i).toString());
        return builder.toString();
    }

    public Type getSentenseType() {
        return m_Type;
    }

    public void append(LingvoObject obj){
        m_Objects.add(obj);
    }

    public int getCountInclude(LingvoObject obj) {
        int counter = 0;
        for (int i = 0; i < m_Objects.size(); ++i) {
            if (m_Objects.get(i).equals(obj))
                counter += 1;
        }
        return counter;
    }

    public ArrayList<LingvoObject> getLingvoObject() {
        return m_Objects;
    }

    public ArrayList<Word> getWords() {
        ArrayList<Word> words = new ArrayList<>();

        for (int i = 0; i < m_Objects.size(); ++i)
            if (m_Objects.get(i).getType() == LingvoObject.Type.Word)
                words.add((Word) m_Objects.get(i));

        return words;
    }

    public int getWordsCount() {
        return getWords().size();
    }

    public String read(String sentense, BooleanWrapper endOfRead) {

        while (sentense.length() > 0 &&
                (Utility.isSpace(sentense.charAt(0)) || sentense.charAt(0) == '\n'))
            sentense = sentense.substring(1);

        while (sentense.length() > 0 && !endOfRead.value) {
            LingvoObject obj = getFromChar(sentense.charAt(0));
            sentense = obj.read(sentense, endOfRead);
            m_Objects.add(obj);

            if (obj.getType() == LingvoObject.Type.Punct &&
                    ((Punctuation) obj).getValue() == '?')
                m_Type = Type.Ask;

            while (sentense.length() > 0 && sentense.charAt(0) == '\n')
                sentense = sentense.substring(1);
        }

        return sentense;
    }

    public static Comparator<Sentense> getCountWordsComparator() {
        return new Comparator<Sentense>() {
            @Override
            public int compare(Sentense o1, Sentense o2) {
                if (o1.getWordsCount() < o2.getWordsCount())
                    return -1;

                else if (o1.getWordsCount() > o2.getWordsCount())
                    return 1;

                else
                    return 0;
            }
        };
    }

    private LingvoObject getFromChar(char ch) {
        if (Utility.isAlpha(ch))
            return new Word();

        if (Utility.isSpace(ch))
            return new Spaces();

        if (Utility.isPunct(ch))
            return new Punctuation();

        return new Character();
    }

    private Type                    m_Type;
    private ArrayList<LingvoObject>  m_Objects;
}
