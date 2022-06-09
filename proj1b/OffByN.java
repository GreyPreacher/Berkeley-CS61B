public class OffByN implements CharacterComparator{
    private int n;

    //the constructor of offByN
    public OffByN(int N){
        n=N;
    }

    @Override
    public boolean equalChars(char x, char y){
        /*if ((x - y) == 1 || (x - y) == -1){
            return true;
        }else{
            return false;
        }*/
        return Math.abs(x - y) == n;
    }
}
