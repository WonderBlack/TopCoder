package chap5;

import java.util.Arrays;

public class EightQueen {

	static boolean[][] grid = new boolean[17][17];// 8x8マスのグリッド
	static boolean[] row = new boolean[grid.length];// 行にすでにコマがあるかをチェック
	static boolean[] col = new boolean[grid[0].length];// 哲にすでにコマがあるかをチェック

	public static void main(String[] args) {

		tryQueen(0);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == true) {
					System.out.print(" O ");
				} else {
					System.out.print(" X ");
				}
			}
			System.out.println();
		}

	}

	public static boolean tryQueen(int row) {// 各列にコマがおけるか検証し置いていくメソッド

		for (int i = 0; i < grid[0].length; i++) {

			if (grid[row][i] != true && col[i] != true
					&& diagonal(row, i) != true) {// クイーンが被っていないか？
				System.out.println("grid[" + row +"][" + i + "]に駒を置きます。");
				grid[row][i] = true;// 駒を置く
				col[i] = true;// その列をチェックする

				if (row == grid.length - 1) {// 全てのクイーンが置けた
					return true;
				} else {
					if (tryQueen(row + 1)) {// 行a + 1以降の全てに駒が置けた
						return true;
					} else {
						// 失敗したので、駒を取り除く
						grid[row][i] = false;
						col[i] = false;
					}
				}
			} else {
				System.out.println("grid[" + row +"][" + i + "]には駒を置けません。");
			}
		}
		// 結局,行rowにはクイーンを置ける場所がなかった
		return false;

	}

	public static boolean diagonal(int a, int b) {// 斜めにかぶっていないか確認するメソッド

		int x = a;
		int y = b;

		boolean flg = false;

		// 右下探索（ｘもｙも増える）
		while (true) {
			// エリア外に出たら（端まで探索したら）
			if (x < 0 || grid.length <= x || y < 0 || grid.length <= y) {
				break;
			}
			// trueのマスを見つけたら
			if (grid[x][y] == true) {
				flg = true;
				break;
			}
			x++;
			y++;
		}
		if (flg == false) {
			// 右上探索（xが増えてｙが減る）
			x = a;
			y = b;
			while (true) {
				// エリア外に出たら（端まで探索したら）
				if (x < 0 || grid.length <= x || y < 0 || grid.length <= y) {
					break;
				}
				// trueのマスを見つけたら
				if (grid[x][y] == true) {
					flg = true;
					break;
				}
				x++;
				y--;
			}
		}
		if (flg == false) {
			// 左下探索（xが減ってｙが増える）
			x = a;
			y = b;
			while (true) {
				// エリア外に出たら（端まで探索したら）
				if (x < 0 || grid.length <= x || y < 0 || grid.length <= y) {
					break;
				}
				// trueのマスを見つけたら
				if (grid[x][y] == true) {
					flg = true;
					break;
				}
				x--;
				y++;
			}
		}
		if (flg == false) {
			// 左上探索（xもｙも減る）
			x = a;
			y = b;
			while (true) {
				// エリア外に出たら（端まで探索したら）
				if (x < 0 || grid.length <= x || y < 0 || grid.length <= y) {
					break;
				}
				// trueのマスを見つけたら
				if (grid[x][y] == true) {
					flg = true;
					break;
				}
				x--;
				y--;
			}
		}

		return flg;
	}

}