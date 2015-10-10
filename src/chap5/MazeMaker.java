package chap5;

// 幅優先探索で解いたもの

import java.util.LinkedList;
import java.util.Queue;

public class MazeMaker {

	public static void main(String[] args) {

		String[] maze = { "X.X", "...", "XXX", "X.X" };
		int startRow = 0;
		int startCol = 1;
		int[] moveRow = { 1, 0, -1, 0 };
		int[] moveCol = { 0, 1, 0, -1 };

		int ans = longestPath(maze, startRow, startCol, moveRow, moveCol);
		System.out.println("ans = " + ans);

	}

	/*
	 * maze １〜５０の配列（行を表す） 各要素は１〜５０文字（列とマス目を表す） xなら通れないマス　.なら動けるマス
	 * startRow、startColは開始マス moveRowとmoveColはジムの現在地からの相対的な動き方を表す
	 */

	// 移動可能なマスの中で最長の物（一番遠くにあるゴール）を返す。移動不可能なマスが存在すれば-1を返す。
	public static int longestPath(String[] maze, int startRow, int startCol,
			int[] moveRow, int[] moveCol) {

		int[][] grid = new int[maze.length][maze[0].length()];// 各マスまでの移動距離を保持
		boolean[][] grid2 = new boolean[maze.length][maze[0].length()];// 各マスの訪問、未訪問をチェック
		Queue<Integer> queueX1 = new LinkedList<Integer>();
		Queue<Integer> queueY1 = new LinkedList<Integer>();
		Queue<Integer> queueX2 = new LinkedList<Integer>();
		Queue<Integer> queueY2 = new LinkedList<Integer>();

		int num = maze.length * maze[0].length();
		System.out.println("行数 = " + maze.length);
		System.out.println("列数 = " + maze[0].length());
		System.out.println("マス目の数 = " + num);
		System.out.println("移動パターンの数 = " + moveRow.length);
		System.out.println("パターンの数（計算回数） = " + (num * moveRow.length));

		queueX1.add(startRow);
		queueY1.add(startCol);
		grid2[startRow][startCol] = true;// 出発地点を訪問済みにする
		grid[startRow][startCol] = 0;// 移動距離をセット
		int cnt = 1;
		boolean flg = true;

		while (cnt < num) {

			if (flg) {
				// キューに入っている全ての現在地の次の現在地を移動パターンを元に算出
				while (!queueX1.isEmpty()) {
					int x1 = queueX1.remove();// キューに入っている現在地ｘを取り出す
					int y1 = queueY1.remove();// キューに入っている現在地ｙを取り出す
					for (int i = 0; i < moveRow.length; i++) {
						int x2 = x1 + moveRow[i];// 移動後のｘ座標
						int y2 = y1 + moveCol[i];// 移動後のｙ座標
						// 計算結果のマスがエリア内なら
						if (0 <= x2 && x2 < maze.length && 0 <= y2
								&& y2 < maze[0].length()) {
							// 計算結果のマスが通行可能で、なおかつ未訪問であれば
							if (maze[x2].charAt(y2) != 'X'
									&& grid2[x2][y2] != true) {
								queueX2.add(x2);// 計算結果ｘをキューに追加する
								queueY2.add(y2);// 計算結果ｙをキューに追加する
								grid2[x2][y2] = true;// そのマスを訪問済みにする
								grid[x2][y2] = cnt;// 移動距離をセット
							}
						}
					}
				}
				cnt++;
				flg = false;
			} else {
				// キューに入っている全ての現在地の次の現在地を移動パターンを元に算出
				while (!queueX2.isEmpty()) {
					int x1 = queueX2.remove();// キューに入っている現在地ｘを取り出す
					int y1 = queueY2.remove();// キューに入っている現在地ｙを取り出す
					for (int i = 0; i < moveRow.length; i++) {
						int x2 = x1 + moveRow[i];// 移動後のｘ座標
						int y2 = y1 + moveCol[i];// 移動後のｙ座標
						// 計算結果のマスがエリア内なら
						if (0 <= x2 && x2 < maze.length && 0 <= y2
								&& y2 < maze[0].length()) {
							// 計算結果のマスが通行可能で、なおかつ未訪問であれば
							if (maze[x2].charAt(y2) != 'X'
									&& grid2[x2][y2] != true) {
								queueX1.add(x2);// 計算結果ｘをキューに追加する
								queueY1.add(y2);// 計算結果ｙをキューに追加する
								grid2[x2][y2] = true;// そのマスを訪問済みにする
								grid[x2][y2] = cnt;// 移動距離をセット
							}
						}
					}
				}
				cnt++;
				flg = true;
			}

		}

		// 各マスまでの移動距離を表示
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (maze[i].charAt(j) == 'X') {
					System.out.print("_  ");
				} else if (i == startRow && j == startCol) {// スタート地点なら
					System.out.print("S" + grid[i][j] + " ");
				} else {
					System.out.print(grid[i][j] + "  ");
				}
			}
			System.out.println();
		}

		// 一番遠い場所にあるゴールを算出
		int max = 0;// 移動距離の最大値を格納
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				max = Math.max(max, grid[i][j]);// 走査しながら最大値を更新
				if (grid[i][j] == 0) {// 未訪問のマスがあったら
					// そのマスはスタート地点でなく、障害物もない（辿りつけない）
					if (!(i == startRow && j == startCol)
							&& maze[i].charAt(j) != 'X') {
						return -1;
					}
				}
			}
		}

		return max;

	}

}
