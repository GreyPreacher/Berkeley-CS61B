public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque <Character> dq = new LinkedListDeque <>();
        int len = word.length();
        for (int i = 0 ; i < len; i += 1){
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word){
        if (word == null || word.length() <= 1){
            return true;
        }
        int len = word.length();
        for (int i = 0 ; i < len/2; i++){
            if (word.charAt(i) != word.charAt(len-i-1)){
                return false;
            }
        }
        return  true;
    }

    //Add a new method that overloads isPalindrome.
    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word == null || word.length() <= 1){
            return true;
        }
        int len = word.length();
        for (int i = 0 ; i < len/2; i++) {
            if (!cc.equalChars((word.charAt(i)),word.charAt(len - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
