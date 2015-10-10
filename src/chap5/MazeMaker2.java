package chap5;

// 深さ優先探索で解いたもの

public class MazeMaker2 {

	static int dist = 0;// 移動距離
	static int[][] grid;// 各マスまでの移動距離を保持
	static boolean[][] grid2;// 各マスの訪問、未訪問をチェック
	static String[] maze2;
	static int[] moveRow2;
	static int[] moveCol2;

	public static void main(String[] args) {

		String[] maze = { ".......", "X.X.X..", "XXX...X", "....X..",
				"X....X.", "......." };
		int startRow = 5;
		int startCol = 0;
		int[] moveRow = { 1, 0, -1, 0, -2, 1 };
		int[] moveCol = { 0, -1, 0, 1, 3, 0 };

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

		// 必要な変数を大域変数にする

		// grid[][]は移動距離と訪問、未訪問を管理
		grid = new int[maze.length][maze[0].length()];// グリッドの縦横を定義

		// grid2[][]はバックトラックの為の訪問、未訪問を管理
		grid2 = new boolean[maze.length][maze[0].length()];// グリッドの縦横を定義

		maze2 = new String[maze.length];
		System.arraycopy(maze, 0, maze2, 0, maze2.length);

		moveRow2 = new int[moveRow.length];
		System.arraycopy(moveRow, 0, moveRow2, 0, moveRow2.length);

		moveCol2 = new int[moveCol.length];
		System.arraycopy(moveCol, 0, moveCol2, 0, moveCol2.length);

		search(startRow, startCol);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (maze[i].charAt(j) == 'X') {
					System.out.print("_ ");
				} else {
					System.out.print(grid[i][j] + " ");
				}
			}
			System.out.println();
		}

		// 結果を集計する
		int max = 0;// 移動距離の最大値を格納
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				max = Math.max(max, grid[i][j]);// 走査しながら最大値を更新
				if (grid[i][j] == 0) {// 未訪問のマスがあったら
					// System.out.println("未訪問のマスを発見[" + i + "][" + j + "]" +
					// maze[i].charAt(j));
					if (!(i == startRow && j == startCol)
							&& maze[i].charAt(j) != 'X') {// スタート地点でなく、障害物もない
						// System.out.println("辿りつけないマスだ！");
						// 辿りつけないマスがあった

						return -1;
					}
				}
			}
		}

		return max;

	}

	public static void search(int x, int y) {// x,yは現在地の座標

		grid2[x][y] = true;// このマスを訪問済みにする
		// 今回がこのマスへの初訪問なら
		if (grid[x][y] == 0) {
			grid[x][y] = dist;// 現在地までの移動距離を入れる
		} else {// このマスへの再訪なら
			grid[x][y] = Math.min(grid[x][y], dist);// 移動距離が短い場合だけ値を更新する
		}

		// 移動パターンを選ぶ
		for (int i = 0; i < moveRow2.length; i++) {
			int a = x + moveRow2[i];// 移動した場合のx座標
			int b = y + moveCol2[i];// 移動した場合のy座標
			// 移動先がエリア外でなければ
			if (0 <= a && a < maze2.length && 0 <= b && b < maze2[0].length()) {
				// 移動先に障害物がなく、なおかつ未訪問であれば
				if (maze2[a].charAt(b) != 'X' && grid2[a][b] != true) {
					// 進む
					// System.out.println("パターン " + i + " で進みます。");
					dist++;// 移動距離を１増やす
					search(a, b);
				}
			}
			// 移動出来なければ他の移動パターンを選ぶ（i++）
			// System.out.println("パターン " + i + "　はＸ");
		}
		// 全パターンを試しても駄目だった
		// System.out.println("全パターンＸ");
		if (dist > 0) {// 最初のマスは除く
			dist--;// 移動距離を１減らす
			grid2[x][y] = false;// 現在地を未訪問にする
		}

	}

}
