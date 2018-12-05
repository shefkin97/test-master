import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class Tasks {

    public static void Task_01(Text text) {
        System.out.println("Task 01: ");
        ArrayList<Sentense> sentenses = text.getSenteses();

        for (int i = 0; i < sentenses.size(); ++i) {
            ArrayList<Word> words = sentenses.get(i).getWords();
            words.sort(Word.getStringComparator());

            for (int j = 0; j < words.size() - 1; ++j) {
                Word left = words.get(j);
                Word right = words.get(j+1);

                if (left.equals(right)) {
                    Println(sentenses.get(i).toString());
                    break;
                }
            }
        }

        System.out.println("");
    }

    public static void Task_02(Text text) {
        System.out.println("Task 02: ");
        ArrayList<Sentense> sentenses = text.getSenteses();
        sentenses.sort(Sentense.getCountWordsComparator());

        for (int i = 0; i < sentenses.size(); ++i)
            Println(sentenses.get(i).toString());
        Println("");
    }

    public static void Task_03(Text text) {
        System.out.println("Task 03: ");
        ArrayList<Sentense> sentenses = text.getSenteses();
        ArrayList<Word> wordsInFirst = sentenses.get(0).getWords();

        for (int i = 1; i < sentenses.size(); ++i)
        {
            ArrayList<Word> words = sentenses.get(i).getWords();

            wordsInFirst.removeIf(new Predicate<Word>() {
                @Override
                public boolean test(Word word) {
                    return words.contains(word);
                }
            });
        }

        for (int i = 0; i < wordsInFirst.size(); ++i)
            Println(wordsInFirst.get(i).toString());
        Println("");
    }

    public static void Task_04(Text text) {
        System.out.println("Task 04: ");
        ArrayList<Sentense> sentenses = text.getSenteses();
        sentenses.removeIf(new Predicate<Sentense>() {
            @Override
            public boolean test(Sentense sentense) {
                return sentense.getSentenseType() != Sentense.Type.Ask;
            }
        });

        ArrayList<Word> words = new ArrayList<>();
        for (int i = 0; i < sentenses.size(); ++i)
            words.addAll(sentenses.get(i).getWords());
        words.sort(Word.getStringComparator());

        for (int i = 0; i < words.size() - 1; ++i)
            while (words.get(i).equals(words.get(i+1)))
                words.remove(i+1);

        words.sort(Word.getStringLengthComparator());
        for (int i = 0; i < words.size(); ++i)
            Println(words.get(i).toString());
        Println("");
    }

    public static void Task_05(Text text) {
        System.out.println("Task 05: ");
        ArrayList<Sentense> sentenses = text.getSenteses();

        for (int i = 0; i < sentenses.size(); ++i) {
            ArrayList<LingvoObject> objects = sentenses.get(i).getLingvoObject();

            int firstID = objects.size();
            int lastID = 0;

            for (int j = 0; j < objects.size(); ++j)
                if (objects.get(j).getType() == LingvoObject.Type.Word) {
                    firstID = j;
                    break;
                }

            for (int j = objects.size()-1; j >= 0; --j)
                if (objects.get(j).getType() == LingvoObject.Type.Word) {
                    lastID = j;
                    break;
                }

            if (firstID < lastID) {
                String buf = objects.get(firstID).toString();
                objects.set(firstID, new Word(objects.get(lastID).toString()));
                objects.set(lastID, new Word(buf));
            }
        }

        for (int i = 0; i < sentenses.size(); ++i)
            Println(sentenses.get(i).toString());
        Println("");
    }

    public static void Task_06(Text text) {
        Println("Task 06:");
        ArrayList<Sentense> sentenses = text.getSenteses();
        ArrayList<Word> words = new ArrayList<>();

        for (int i = 0; i < sentenses.size(); ++i)
            words.addAll(sentenses.get(i).getWords());

        words.sort(Word.getStringComparator());

        char firstChar = words.get(0).toString().charAt(0);
        for (int i = 0; i < words.size(); ++i)
        {
            String word = words.get(i).toString();
            if (firstChar == word.charAt(0))
                Print(word + ' ');

            else
            {
                Println("");
                Print(word + ' ');
                firstChar = word.charAt(0);
            }
        }
        Println("\n");
    }

    public static void Task_07(Text text) {
        Println("Task 07:");
        ArrayList<Word> words = text.getAllWords();
        words.sort(Word.getLoudAlphaComparator());

        for (int i = 0; i < words.size(); ++i) {
            Word curr = words.get(i);
            Println(curr.toString() + " - " + (100.0f * curr.getLoudAlpha() / curr.length()) + "%");
        }
        Println("");
    }

    public static void Task_08(Text text) {
        Println("Task 08:");
        ArrayList<Word> words = text.getAllWords();

        words.removeIf(new Predicate<Word>() {
            @Override
            public boolean test(Word word) {
                return !Utility.isLoudAlpha(word.toString().charAt(0));
            }
        });
        words.sort(Word.getFirstConsonantAlphaComaparator());

        for (int i = 0; i < words.size(); ++i) {
            Word curr = words.get(i);
            Println(curr.toString());
        }
        Println("");
    }

    public static void Task_09(Text text) {
        Println("Task 09:");
        final char selectedAlpha = 'e';
        ArrayList<Word> words = text.getAllWords();

        words.removeIf(new Predicate<Word>() {
            @Override
            public boolean test(Word word) {
                return !word.haveAlpha(selectedAlpha);
            }
        });

        words.sort(Word.getConsistAlphaComparator(selectedAlpha));

        for (int i = 0; i < words.size(); ++i) {
            Word curr = words.get(i);
            Println(curr.toString());
        }
        Println("");
    }

    public static void Task_10(Text text) {
        Println("Task 10:");
        ArrayList<Sentense> sentenses = text.getSenteses();
        ArrayList<Word> wordsInFirst = sentenses.get(0).getWords();

        wordsInFirst.sort(Word.getStringComparator());
        for (int i = 0; i < wordsInFirst.size() - 1; ++i)
            while (wordsInFirst.get(i).equals(wordsInFirst.get(i+1)))
                wordsInFirst.remove(i+1);

        ArrayList<Pair<Word, Integer>> wordsCounts = new ArrayList<>();
        for (int i = 1; i < sentenses.size(); ++i) {
            for (int j = 0; j < wordsInFirst.size(); ++j) {
                Word word = wordsInFirst.get(j);
                int countInSentense = sentenses.get(i).getCountInclude(word);

                if (countInSentense > 0) {
                    wordsCounts.add(new Pair<>(word, countInSentense));
                    Println("Senteses id: " + i + "\tWord: \'" + word.toString() + "\'\tCount: " + countInSentense);
                }
            }
        }
        Println("");

        wordsCounts.sort(new Comparator<Pair<Word, Integer>>() {
            @Override
            public int compare(Pair<Word, Integer> o1, Pair<Word, Integer> o2) {
                return Word.getStringComparator().compare(o1.getKey(), o2.getKey());
            }
        });

        ArrayList<Pair<Word, Integer>> rezult = new ArrayList<>();
        for (int i = 0; i < wordsCounts.size();)
        {
            Word word = wordsCounts.get(i).getKey();
            int count = 0;

            while (i < wordsCounts.size() && wordsCounts.get(i).getKey().equals(word)) {
                count += wordsCounts.get(i).getValue();
                i += 1;
            }

            rezult.add(new Pair<>(word, count));
        }

        rezult.sort(new Comparator<Pair<Word, Integer>>() {
            @Override
            public int compare(Pair<Word, Integer> o1, Pair<Word, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < rezult.size(); ++i)
            Println("Word = \'" + rezult.get(i).getKey() + "\'\t Count in text = " + rezult.get(i).getValue());
        Println("");
    }

    public static void Task_11(Text text) {

    }

    public static void Task_12(Text text) {

    }

    public static void Task_13(Text text) {

    }

    public static void Task_14(Text text) {

    }

    public static void Task_15(Text text) {
        Println("Task 15:");

        Text resultText = new Text();
        ArrayList<Sentense> sentences = text.getSenteses();
        for (int i = 0; i < sentences.size(); i++) {
            ArrayList<Word> words = sentences.get(i).getWords();
            for (int k = 0; k < words.size(); k++) {
                // remove all repeats of first letter
                String word = words.get(k).toString();
                word = String.valueOf(word.charAt(0)) + word.replace(String.valueOf(word.charAt(0)), "");
                words.set(k, new Word(word));
            }
            resultText.append(new Sentense(words, sentences.get(i).getSentenseType()));
        }

        Println("Result text:\n" + resultText.toString());
        Println("");
    }

    public static Sentense Task_16(Sentense sentense, int length, String str) {
        Println("Task 16:");
        Println("Got sentence: " + sentense.toString());
        Println("Length of words to replace is " + length + ", str to insert is " + str);

        Sentense resultSentence = new Sentense();
        ArrayList<Word> words = sentense.getWords();
        for(int i = 0; i < words.size(); i++) {
            if (words.get(i).length() == length) {
                words.set(i, new Word(str));
            }
            resultSentence.append(words.get(i));
            if ( i < words.size() - 1)
                resultSentence.append(new Spaces(' '));
        }
        Println("Result sentence: " + resultSentence.toString());
        return resultSentence;
    }



    public static void Print(String str) {System.out.print(str);}
    public static void Println(String str) { System.out.println(str);}
}