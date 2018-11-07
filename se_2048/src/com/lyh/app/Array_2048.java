package com.lyh.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Array_2048 {

	private static final int[] NUM = { 2, 4 };
	private int[][] arr;
	private boolean[] flag;

	// 初始化界面
	public void init() {
		flag = new boolean[5];
		for (int i = 0; i < flag.length; i++) {
			flag[i] = true;
		}
		arr = new int[4][4];
		initRandom_2(arr);
		// show(arr);
	}

	// 开始游戏
	public void startGame() {
		while (flag[0]) {
			System.out.print("wasd输入按键:");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
			if (input.equalsIgnoreCase("w")) {
				checkUp();
				// show(arr);
				checkOver();
			} else if (input.equalsIgnoreCase("s")) {
				checkDown();
				// show(arr);
				checkOver();
			} else if (input.equalsIgnoreCase("a")) {
				checkLeft();
				// show(arr);
				checkOver();
			} else if (input.equalsIgnoreCase("d")) {
				checkRight();
				// show(arr);
				checkOver();
			} else {
				System.out.println("请重新输入:");
			}
		}
	}

	// 往上移动
	public void checkUp() {
		flag[1] = true;
		boolean moveFlag = false;
		int[][] tempArr = copy2Arr(arr);

		for (int i = 0; i < 4; i++) {
			int level = 0;
			for (int j = 0; j < 4; j++) {
				if (arr[j][i] != 0) {
					int temp = arr[j][i];
					arr[j][i] = 0;
					arr[level++][i] = temp;
				}
			}
		}
		// 从上往下相邻的两个相同相加
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[j][i] == arr[j + 1][i]) {
					arr[j][i] += arr[j][i];
					arr[j + 1][i] = 0;
					int jLv = j + 1;
					for (int k = j + 2; k < 4; k++) {
						if (arr[k][i] != 0) {
							int temp = arr[k][i];
							arr[k][i] = 0;
							arr[jLv++][i] = temp;
						}
					}
				}
			}
		}
		moveFlag = checkArrIsEqual(arr, tempArr);
		// 判断是否有改变，如果有改变则增加一个新的
		if (!moveFlag)
			setRandomCoordiante(arr);
		else
			flag[1] = false;

	}

	// 往下移动
	public void checkDown() {
		flag[2] = true;
		boolean moveFlag = false;
		int[][] tempArr = copy2Arr(arr);

		for (int i = 0; i < 4; i++) {
			int level = 3;
			for (int j = 3; j >= 0; j--) {
				if (arr[j][i] != 0) {
					int temp = arr[j][i];
					arr[j][i] = 0;
					arr[level--][i] = temp;
				}
			}
		}
		// 从下往上相邻的两个相同相加
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j > 0; j--) {
				if (arr[j][i] == arr[j - 1][i]) {
					arr[j][i] += arr[j][i];
					arr[j - 1][i] = 0;
					int jLv = j - 1;
					for (int k = j - 2; k >= 0; k--) {
						if (arr[k][i] !=  0) {
							int temp = arr[k][i];
							arr[k][i] = 0;
							arr[jLv--][i] = temp;
						}
					}
				}
			}
		}
		moveFlag = checkArrIsEqual(arr, tempArr);
		// 判断是否有改变，如果有改变则增加一个新的
		if (!moveFlag)
			setRandomCoordiante(arr);
		else
			flag[2] = false;

	}

	// 往左移动
	public void checkLeft() {
		flag[3] = true;
		boolean moveFlag = false;
		int[][] tempArr = copy2Arr(arr);

		for (int i = 0; i < 4; i++) {
			int level = 0;
			for (int j = 0; j < 4; j++) {
				if (arr[i][j] != 0) {
					int temp = arr[i][j];
					arr[i][j] = 0;
					arr[i][level++] = temp;
				}
			}
		}
		// 从左往右相邻的两个相同相加
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] == arr[i][j + 1]) {
					arr[i][j] += arr[i][j];
					arr[i][j + 1] = 0;
					int jLv = j + 1;
					for (int k = j + 2; k < 4; k++) {
						if (arr[i][k] != 0) {
							int temp = arr[i][k];
							arr[i][k] = 0;
							arr[i][jLv++] = temp;
						}
					}
				}
			}
		}
		moveFlag = checkArrIsEqual(arr, tempArr);
		// 判断是否有改变，如果有改变则增加一个新的
		if (!moveFlag)
			setRandomCoordiante(arr);
		else
			flag[3] = false;
	}

	// 往右移动
	public void checkRight() {
		flag[4] = true;
		boolean moveFlag = false;
		int[][] tempArr = copy2Arr(arr);

		for (int i = 0; i < 4; i++) {
			int level = 3;
			for (int j = 3; j >= 0; j--) {
				if (arr[i][j] != 0) {
					int temp = arr[i][j];
					arr[i][j] = 0;
					arr[i][level--] = temp;
				}
			}
		}
		// 从右往左相邻的两个相同相加
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j > 0; j--) {
				if (arr[i][j] == arr[i][j - 1]) {
					arr[i][j] += arr[i][j];
					arr[i][j - 1] = 0;
					int jLv = j - 1;
					for (int k = j - 2; k >= 0; k--) {
						if (arr[i][k] != 0) {
							int temp = arr[i][k];
							arr[i][k] = 0;
							arr[i][jLv--] = temp;
						}
					}
				}
			}
		}
		moveFlag = checkArrIsEqual(arr, tempArr);
		// 判断是否有改变，如果有改变则增加一个新的
		if (!moveFlag)
			setRandomCoordiante(arr);
		else
			flag[4] = false;
	}

	public void checkOver() {
		System.out.println();
		if (!(flag[1] || flag[2] || flag[3] || flag[4])) {
			flag[0] = false;
			System.out.println("游戏结束");
		}
	}

	// 初始界面的随机两个坐标显示2
	public void initRandom_2(int[][] arr) {
		List<Coordinate> cds = getCoordinate(arr);
		Coordinate cd = cds.get((int) (Math.random() * cds.size()));
		arr[cd.getX()][cd.getY()] = 2;
		cds = getCoordinate(arr);
		cd = cds.get((int) (Math.random() * cds.size()));
		arr[cd.getX()][cd.getY()] = 2;
	}

	// 显示界面
	public void show(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 复制二维数组
	public int[][] copy2Arr(int[][] arr) {
		int[][] arr2 = new int[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr2[i][j] = arr[i][j];
			}
		}
		return arr2;
	}

	// 判断俩个数组是否相同
	public boolean checkArrIsEqual(int[][] arr1, int[][] arr2) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr1[i][j] != arr2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	// 获取所有空白坐标
	private List<Coordinate> getCoordinate(int[][] arr) {
		List<Coordinate> cds = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == 0) {
					cds.add(new Coordinate(i, j));
				}
			}
		}
		return cds;
	}

	// 设置空白位置上的随机一个坐标值为2或4
	private void setRandomCoordiante(int[][] arr) {
		List<Coordinate> cds = getCoordinate(arr);
		// 随机坐标
		if (cds.size() > 0) {
			Coordinate cd = cds.get((int) (Math.random() * cds.size()));
			arr[cd.getX()][cd.getY()] = NUM[(int) (Math.random() * 2)];
		}

	}

	public int[][] getArr() {
		return arr;
	}

}
