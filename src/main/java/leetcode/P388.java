package leetcode;

public class P388 {
    int max = 0;
    int i = 0;
    public void parse(int parentLen, int level, String s) {
        int bufferLen = 0;
        boolean isFile = false;
        while(i < s.length()) {
            if (s.charAt(i) == '\n') {
                int currentPathLen = parentLen + bufferLen;
                if (parentLen > 0) {
                    currentPathLen++;
                }
                if (isFile) {
                    if (currentPathLen > max) {
                        max = currentPathLen;
                    }
                }
                i++;
                int depth = 0;
                while (s.charAt(i) == '\t')  {
                    depth++;
                    i++;
                }
                if (depth == level) {
                    bufferLen = 0;
                    isFile = false;
                } else if (depth == level + 1) {
                    if (isFile) {
                        throw new IllegalStateException();
                    }
                    parse(currentPathLen, level + 1, s);
                } else if (depth == level - 1) {

                    return;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                if (s.charAt(i) == '.') {
                    isFile = true;
                }
                bufferLen++;
                i++;
            }
        }
        int currentPathLen = parentLen + bufferLen + 1;
        if (isFile) {
            if (currentPathLen > max) {
                max = currentPathLen;
            }
        }
    }
    public static void main(String[] args) {
        P388 p = new P388();
        p.parse(0,0,"dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
        System.out.println(p.max);
    }
}
