package chap5;

public class GridWalk2 {

	static boolean[][] grid = new boolean[4][4];
	static int vx[] = { 1, -1, 0, 0 };
	static int vy[] = { 0, 0, 1, -1 };
	static int gx = grid.length - 1;// ゴールのマスのx座標
	static int gy = grid[0].length - 1;// ゴールのマスのy座標
	static int min = 50;// 最短距離
	static int max = 0;// 最長距離
	static int[] dists = new int[10000];
	static int discnt = 0;
	static int dist = 0;

	public static void main(String[] args) {

		walk(0, 0);// 引数はスタート地点のx,y座標

		System.out.println("最長距離は" + max);
		System.out.println("最短距離は" + min);

		int x = 0;
		for (int i = 0; i < dists.length; i++) {
			if (dists[i] != 0) {
				x++;
			}
		}
		System.out.println("ゴールまで辿り着けるルートは " + x + " 通り");
	}

	public static void walk(int x, int y) {

		grid[x][y] = true;// 現在地をtrueにする

		System.out.println("現在地はgrid[" + x + "][" + y + "]");
		System.out.println("ここまでの移動距離は" + dist);

		if (x == gx && y == gy) {// ゴールに着いたら

			min = Math.min(min, dist);
			max = Math.max(max, dist);
			System.out.println("ゴールしました。");
			System.out.println("ここまでの移動距離は" + dist);

			grid[x][y] = false;// 現在地をfalseにする
			dists[discnt] = dist;
			discnt++;
			dist--;// 移動距離も一つ前のステップに戻す
			System.out.println("距離を一つ前の状態に戻します　dist = " + dist);

			return;
		}

		for (int i = 0; i < vx.length; i++) {// 全方向を試し終わるまで繰り返す（東西南北の順）
			int a = x + vx[i];
			int b = y + vy[i];
			if (0 <= a && a < grid.length && 0 <= b && b < grid[0].length) {// エリア内であれば
				if (grid[a][b] == false) {// その場所が空いていれば：埋まっていれば方向を変える（i++）
					switch (i) {
					case 0:
						System.out.println("東に進む");
						break;
					case 1:
						System.out.println("西に進む");
						break;
					case 2:
						System.out.println("南に進む");
						break;
					case 3:
						System.out.println("北に進む");
						break;
					}
					dist++;// 移動距離を一つ足す
					walk(a, b);
				}
			}
		}
		// 全方向埋まっていてこれ以上進めなければ
		if (dist > 0) {
			grid[x][y] = false;// 現在地をfalseにする
			dist--;// 移動距離も一つ前のステップに戻す
			System.out.println("どの方向にも行けないので、マス目と距離を一つ前の状態に戻します　dist = " + dist);
		}

	}
}
