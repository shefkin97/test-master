import java.util.ArrayList;

public class Text {

    public Text(){
        m_Senteses = new ArrayList<>();
    }

    public void append(Sentense sentense) {
        m_Senteses.add(sentense);
    }
    public ArrayList<Sentense> getSenteses() {
        return (ArrayList<Sentense>) m_Senteses.clone();
    }

    public int sentenseCount() {
        return m_Senteses.size();
    }

    public ArrayList<Word> getAllWords() {
        ArrayList<Word> words = new ArrayList<>();

        for (int i = 0; i < m_Senteses.size(); ++i)
            words.addAll(m_Senteses.get(i).getWords());

        words.sort(Word.getStringComparator());
        for (int i = 0; i < words.size() - 1; ++i)
            while (words.get(i).equals(words.get(i+1)))
                words.remove(i+1);

        return  words;
    }

    public void read(String text) {
       while (!text.isEmpty())
       {
           Sentense sentense = new Sentense();
           text = sentense.read(text, new BooleanWrapper());

           if (sentense.getWordsCount() > 0)
               append(sentense);
       }
    }

    public boolean empty() {
        return  m_Senteses.isEmpty();
    }


    private ArrayList<Sentense> m_Senteses;

    @Override
    public  String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i =0; i < m_Senteses.size(); ++i) {
            builder.append(m_Senteses.get(i).toString());
            if (i < m_Senteses.size() - 1)
                builder.append(" ");
        }
        return builder.toString();
    }
}
