class Solution {

    int[][] factor = {
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {2, 0, 0, 0},
        {0, 0, 1, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 1},
        {3, 0, 0, 0},
        {0, 2, 0, 0}
    };

    public String smallestNumber(String num, long t) {
        int[] need = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                need[i]++;
                t /= primes[i];
            }
        }

        if (t != 1) return "-1";

        int n = num.length();
        int[][] pref = new int[n + 1][4];
        boolean[] zero = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';

            for (int j = 0; j < 4; j++) {
                pref[i + 1][j] = pref[i][j];
            }

            zero[i + 1] = zero[i] || d == 0;

            if (d != 0) {
                for (int j = 0; j < 4; j++) {
                    pref[i + 1][j] += factor[d][j];
                }
            }
        }

        if (!zero[n] && enough(pref[n], need)) {
            return num;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (zero[i]) continue;

            int cur = num.charAt(i) - '0';

            for (int d = Math.max(1, cur + 1); d <= 9; d++) {
                int[] left = new int[4];

                for (int j = 0; j < 4; j++) {
                    left[j] = Math.max(
                        0,
                        need[j] - pref[i][j] - factor[d][j]
                    );
                }

                int len = n - i - 1;

                if (minDigits(left) <= len) {
                    String suffix = build(left, len);

                    if (suffix != null) {
                        return num.substring(0, i) + d + suffix;
                    }
                }
            }
        }

        int len = Math.max(n + 1, minDigits(need));

        while (true) {
            String ans = build(need, len);

            if (ans != null) {
                return ans;
            }

            len++;
        }
    }

    private boolean enough(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) {
                return false;
            }
        }

        return true;
    }

    private String build(int[] need, int len) {
        int[] left = need.clone();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int remain = len - i - 1;
            boolean found = false;

            for (int d = 1; d <= 9; d++) {
                int[] next = new int[4];

                for (int j = 0; j < 4; j++) {
                    next[j] = Math.max(
                        0,
                        left[j] - factor[d][j]
                    );
                }

                if (minDigits(next) <= remain) {
                    ans.append(d);
                    left = next;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return null;
            }
        }

        return enough(new int[]{0, 0, 0, 0}, left)
            ? ans.toString()
            : null;
    }

    private int minDigits(int[] need) {
        int a = need[0];
        int b = need[1];
        int count = need[2] + need[3];

        int best = Integer.MAX_VALUE;

        for (int six = 0; six <= Math.min(a, b); six++) {
            int twos = a - six;
            int threes = b - six;

            int cur = six;

            cur += twos / 3;

            if (twos % 3 != 0) {
                cur++;
            }

            cur += threes / 2;

            if (threes % 2 != 0) {
                cur++;
            }

            best = Math.min(best, cur);
        }

        return count + best;
    }
}