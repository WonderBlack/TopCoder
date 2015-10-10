package chap5;

import java.util.Arrays;

public class CrazyBot2 {

	// comb様の大域変数
	static String[] way = { "E", "W", "S", "N" };
	static String[] ss;// 文字列を保持
	static String[] pattern;
	static int cnt = 0;
	static int n;// ステップ数
	static String s = "";

	public static void main(String[] args) {

		int[] move = { 4, 25, 25, 25, 25 };

		// grid[4][2] = true;
		//
		// for (int i = 0; i < grid.length; i++) {
		// System.out.println("x = " + i);
		// for (int j = 0; j < grid.length; j++) {
		// System.out.println(grid[i][j]);
		// }
		// }

		n = 7;
		ss = new String[n];// 文字列の長さはnステップ分
		System.out.println("ステップn = " + n);
		pattern = new String[(int) Math.pow(way.length, n)];
		System.out.println("pattern = " + pattern.length);
		comb(0);// 全パターンを生成
		// System.out.println(Arrays.toString(pattern));
		
		double ans = getProbability(n, 50, 50, 0, 0);
		System.out.println("ans = " + ans);
	}

	public static double getProbability(int n, int east, int west, int south,
			int north) {
		
		double sum = 0;// 成功パターンの％を合計するための変数s
		double per = 0;// 各パターン毎の％を格納
		double[] pers = new double[pattern.length];// 全パターンの％を格納
		int x;// グリッドのｘ座標
		int y;// グリッドのｙ座標
		boolean[][] grid = new boolean[29][29];// マス目となる配列
		double ea = east * 0.01;
		double we = west * 0.01;
		double so = south * 0.01;
		double no = north * 0.01;

		for (int i = 0; i < pattern.length; i++) {// 全パターンを試す

			for (int j = 0; j < grid.length; j++) {
				Arrays.fill(grid[j], false);// グリッドを初期化
			}
			x = 15;// x座標を初期化
			y = 15;// y座標を初期化
			grid[x][y] = true;// スタート地点をチェック
			per = 1;// このパターンの％を算出する変数を初期化

			for (int j = 0; j < n; j++) {// ステップ数分繰り返す
				char c = pattern[i].charAt(j);// j文字目を取り出してcに格納
				if (c == 'E') {// 東なら
					if (grid[x + 1][y] == false) {// 未訪問なら
						x++;// 東に１動く
						grid[x][y] = true;// 訪問済みにする
						per *= ea;// 東の％をかける
					} else {// 訪問済みなら
						per *= 0;// 失敗パターンなので０をかけて％を０にしておく
						break;// このパターンは駄目なので探索中止
					}
				} else if (c == 'W') {// 西なら
					if (grid[x - 1][y] == false) {// 未訪問なら
						x--;// 西に１動く
						grid[x][y] = true;// 訪問済みにする
						per *= we;// 西の％をかける
					} else {// 訪問済みなら
						per *= 0;// 失敗パターンなので０をかけて％を０にしておく
						break;// このパターンは駄目なので探索中止
					}
				} else if (c == 'S') {// 南なら
					if (grid[x][y + 1] == false) {// 未訪問なら
						y++;// 南に１動く
						grid[x][y] = true;// 訪問済みにする
						per *= so;// 南の％をかける
					} else {// 訪問済みなら
						per *= 0;// 失敗パターンなので０をかけて％を０にしておく
						break;// このパターンは駄目なので探索中止
					}
				} else if (c == 'N') {// 北なら
					if (grid[x][y - 1] == false) {// 未訪問なら
						y--;// 北に１動く
						grid[x][y] = true;// 訪問済みにする
						per *= no;// 北の％をかける
					} else {// 訪問済みなら
						per *= 0;// 失敗パターンなので０をかけて％を０にしておく
						break;// このパターンは駄目なので探索中止
					}
				}
			}
			System.out.println(per);
			// 今回の％を配列に格納
			pers[i] = per;
		}
		for (int i = 0; i < pers.length; i++) {
			sum += pers[i];
		}

		return sum;
	}

	public static void comb(int idx) {

		for (int i = 0; i < way.length; i++) {
			if (idx < n) {
				ss[idx] = way[i];// 自桁に文字を入力
				comb(idx + 1);// 桁を１つずらして再帰
			} else {// 末尾桁に達したら
				for (int j = 0; j < ss.length; j++) {// 文字列を生成
					s += ss[j];
				}
				pattern[cnt] = s;// 生成した文字列を保存
				s = "";// 文字列をリセット
				cnt++;
				return;
			}
		}

	}
	
	

}
