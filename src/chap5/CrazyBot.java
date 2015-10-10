package chap5;

public class CrazyBot {

	static boolean[][] grid = new boolean[100][100];
	static int vx[] = { 1, -1, 0, 0 };
	static int vy[] = { 0, 0, 1, -1 };

	static double[] prob = new double[4];

	public static void main(String[] args) {
		double per = getProbability(3, 25, 25, 25, 25);
		System.out.println(per);
		
		int x = 0;
		
		for (int i = 0; i < 36; i++) {
			x += 0.015625;
		}
		System.out.println(x);
	}

	public static double getProbability(int n, int east, int west, int south, int north) {

		prob[0] = east / 100.0;
		prob[1] = west / 100.0;
		prob[2] = south / 100.0;
		prob[3] = north / 100.0;

		return dfs(50, 50, n);// グリッドの中央からスタート
	}

	static double dfs(int x, int y, int n) {
		// n - 1を繰り返しステップの末端まで辿っていき、末端に達した場合のみ、確率の計算を開始する。末端に達するとretの指数として1がリターンされる。
		//その次からはその回のretが計算された結果がリターンされて、
		//それが呼び出し元のメソッドの計算式にまた入る。（ret += dfs(x + vx[i], y + vy[i], n - 1) * prob[i];）
		// 末端に達しなければ0がリターンされて、確率は必ず０になる。

		if (grid[x][y]) {// マス目が埋まっていたら０をかけてこのパターンの％を０にする
			return 0;
		}
		if (n == 0) {// 最後のステップの場合
			return 1;
		}
		grid[x][y] = true;// 訪問済みにする
		double ret = 0;// %を保持する変数

		for (int i = 0; i < 4; i++) {
			// east,west,south,northの順にロボットを動かす
			ret += dfs(x + vx[i], y + vy[i], n - 1) * prob[i];// 今回の％に次回の％をかける
		}
		grid[x][y] = false;// 現ステップで東西南北全ての探索が終わったら、癌在地のフラグを未訪問にする、一旦戻って方向を変えるため

		return ret;

	}

}
