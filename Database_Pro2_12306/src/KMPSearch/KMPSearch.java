package KMPSearch;

public class KMPSearch {
    //-----------------------------------------------------------------Question 01
    public static int[] computeNextArray(String pattern) {
        char[][] stringArray = new char[pattern.length()][pattern.length()];
        int[] next = new int[pattern.length()];
        int[] maxL = new int[pattern.length()];

        // write the String into an array
        for(int i=0; i<pattern.length(); i++){
            for(int j=0; j<i+1; j++){
                stringArray[i][j] = pattern.charAt(j);
            }
        }

        // test input
        /*for(int i=0; i<stringArray.length; i++){
            for(int j=0; j<stringArray[i].length; j++){
                System.out.print(stringArray[i][j] + " ");
            }
            System.out.println();
        }*/

        // get the maxL[]
        next[0] = 0;
        for(int i=1; i<stringArray.length; i++){
            for(int number=(i+1)/2; number>0; number--){
                boolean isRepeat = true;
                for(int j=0; j<number; j++){
                    if(stringArray[i][j] != stringArray[i][i-(number-j)+1]){
                        /*System.out.print(stringArray[i][j]);
                        System.out.print(stringArray[i][i-j+1]);*/
                        isRepeat = false;
                        break;
                    }
                }

                if(isRepeat){
                    next[i] = number;
                    break;
                }
            }
        }

        // get the next[]
        for(int i=next.length-1; i>0; i--){
            next[i] = next[i-1];
        }
        next[0] = -1;

        return next;
    }//-----------------------------------------------------------------Question 01


    //------------------------------------------------------------------Question 02
    public static int KMPSearchTimes(String text, String pattern) {
        // get the next[]
        int[] next;
        next = computeNextArray(pattern);
        // System.out.println(Arrays.toString(next));

        char[] textChar = text.toCharArray();
        char[] patternChar = pattern.toCharArray();

        int i = 0;
        int j = 0;
        int repeatTime = 0;
        // matching
        while(i<textChar.length){
            if(j==-1 || textChar[i]==patternChar[j]){
                i++;
                j++;
            }else{
                j=next[j];
            }

            if(j==patternChar.length){
                j=0;
                repeatTime++;
            }
        }

        return repeatTime;
    }//-----------------------------------------------------------------Question 02
}
