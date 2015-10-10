package chap7;

public class Knapsack2 {

	int[] ws = { 3, 4, 1, 2, 3 };
	int[] ps = { 2, 3, 2, 3, 6 };
	int maxw = 10;
	int[][] dp = new int[6][11];
	int ret = 0;// 最大金額

	// n = 詰め込む商品を変えるカウンタ　w = 現在の総重量
	int knapsack(int n, int w) {

		// 総重量を超えたら
		if (w > maxw) {
			return -1;
		}
		// 全ての商品を試したら
		if (n >= ws.length) {
			return 0;
		}
		return Math.max(knapsack(n + 1, w), knapsack(n + 1, w + ws[n]) + ps[n]);
	}

	void knapsack2() {

		for (int i = 0; i <= ws.length; i++) {
			for (int j = 0; j <= maxw; j++) {
				if (j + ws[i] <= maxw) { 
					dp[i + 1][j + ws[i]] = Math.max(dp[i + 1][j + ws[i]],dp[i][j] + ps[i]);
					ret = Math.max(dp[i + 1][j + ws[i]], ret);
				}
			}
		}
	}
}
