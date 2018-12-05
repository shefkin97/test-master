import java.util.Comparator;

public class Word extends LingvoObject {

    public Word() {
        super(Type.Word);
        m_Word = "";
    }

    public Word(String word) {
        super(Type.Word);
        if (checkValue(word))
            m_Word = word;
        else
            throw new IllegalArgumentException("It`s not word!");
    }

    public void setString(String str) {
        if (!checkValue(str))
            m_Word = str;
    }

    @Override
    public String toString() {
        return m_Word;
    }

    @Override
    public boolean equals(Object obj) {
        Word wrd = (Word)obj;
        return wrd.m_Word.toLowerCase().equals(m_Word.toLowerCase());
    }

    public boolean haveAlpha(char alpha) {
        for (int i = 0; i < m_Word.length(); ++i)
            if (m_Word.charAt(i) == alpha)
                return true;
        return false;
    }

    public int length() {
        return m_Word.length();
    }

    public int getLoudAlpha() {
        int count = 0;
        for (int i = 0; i < length(); ++i)
            if (Utility.isLoudAlpha(m_Word.charAt(i)))
                count += 1;
        return count;
    }

    public int getAlphaCount(char alpha) {
        int count = 0;
        for (int i = 0; i < length(); ++i)
            if (m_Word.charAt(i) == alpha)
                count += 1;
        return count;
    }

    @Override
    public String read(String str, BooleanWrapper endOfSentense) {
        int i = 0;

        StringBuilder builder = new StringBuilder();
        while (i < str.length() && checkChar(str.charAt(i)))
            builder.append(str.charAt(i++));
        m_Word = builder.toString();

        return str.substring(i);
    }

    private Boolean checkValue(String value) {
        for (int i = 0; i < value.length(); ++i) {
            if (!checkChar(value.charAt(i)))
                return false;
        }
        return true;
    }

    private Boolean checkChar(char ch)
    {
        return  Utility.isAlpha(ch) || ch == '-' || ch == '\'';
    }

    public static Comparator<Word> getStringComparator() {
        return new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.toString().toLowerCase().compareTo(o2.toString().toLowerCase());
            }
        };
    }

    public static Comparator<Word> getStringLengthComparator() {
        return new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if (o1.length() < o2.length())
                    return -1;

                else if (o1.length() > o2.length())
                    return 1;

                else
                    return 0;
            }
        };
    }

    public static Comparator<Word> getLoudAlphaComparator() {
        return new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                Double perLeft = (double) o1.length() / o1.getLoudAlpha();
                Double perSecond = (double) o2.length() / o2.getLoudAlpha();

                return perLeft.compareTo(perSecond);
            }
        };
    }

    public static Comparator<Word> getFirstConsonantAlphaComaparator() {
        return  new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                String left = o1.toString();
                String right = o2.toString();

                char lc = java.lang.Character.MAX_VALUE;
                char rc = java.lang.Character.MAX_VALUE;

                for (int i = 0; i < left.length(); ++i)
                    if (!Utility.isLoudAlpha(left.charAt(i))) {
                        lc = left.charAt(i);
                        break;
                    }

                for (int i = 0; i < right.length(); ++i)
                    if (!Utility.isLoudAlpha(right.charAt(i))) {
                        rc = right.charAt(i);
                        break;
                    }

                return java.lang.Character.compare(lc, rc);
            }
        };
    }

    public static Comparator<Word> getConsistAlphaComparator(char alpha) {
        return new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                int left = o1.getAlphaCount(alpha);
                int right = o2.getAlphaCount(alpha);

                if (left != right)
                    return Integer.compare(left, right);

                else
                    return getStringComparator().compare(o1, o2);
            }
        };
    }

    private String m_Word;

}
