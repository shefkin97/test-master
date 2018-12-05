public class Utility {

    public static boolean isAlpha(char ch) {
        return (ch >= 'A' && ch <= 'Z' ||
                ch >= 'a' && ch <= 'z' ||
                ch >= 'А' && ch <= 'Я' ||
                ch >= 'а' && ch <= 'я'
        );
    }

    public static boolean isLoudAlpha(char ch) {
        switch (ch)
        {
            case 'A': case 'a':
            case 'E': case 'e':
            case 'I': case 'i':
            case 'O': case 'o':
            case 'U': case 'u':
            case 'Y': case 'y':
            return true;
        }

        return false;
    }

    public static boolean isSpace(char ch)
    {
        return (ch == ' ') || (ch == '\t');
    }

    public static boolean isPunct(char ch) {
        switch (ch)
        {
            case ',':
            case '.':
            case ':':
            case '-':
            case ';':
            case '!':
            case '?':
                return true;
        }
        return false;
    }

}
