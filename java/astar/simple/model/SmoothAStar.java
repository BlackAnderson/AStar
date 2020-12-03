package astar.simple.model;

import java.util.LinkedList;

/**
 * 平滑路径的A*算法
 *
 * @author CHU
 * @date 2020年7月1日
 *
 */
public class SmoothAStar extends AStar {

	/** 平滑路径 */
	private LinkedList<AStarNode> smoothPath;

	@Override
	protected void buildPath() {
		super.buildPath();
		buildKeyPoint();
	}

	/**
	 * 获取寻路的每次转折点
	 */
	private void buildKeyPoint() {
		if (this.path.size() < 3) {
			this.smoothPath = this.path;
		} else {
			this.smoothPath = new LinkedList<>();
			LinkedList<AStarNode> path = this.path;
			smoothPath.add(path.getFirst());
			int offsetX = path.getFirst().getX() - path.get(1).getX();
			int offsetY = path.getFirst().getY() - path.get(1).getY();
			int length = path.size() - 1;
			for (int i = 1; i < length; i++) {
				int ox = path.get(i).getX() - path.get(i + 1).getX();
				int oy = path.get(i).getY() - path.get(i + 1).getY();
				if (ox != offsetX || oy != offsetY) {
					smoothPath.addLast(path.get(i));
					offsetX = ox;
					offsetY = oy;
				}
			}
			smoothPath.addLast(path.get(length));
//			this.buildSmoothPoint();
		}
	}

//	private void buildSmoothPoint() {
//		if (smoothPath.size() > 2) {
//			LinkedList<AStarNode> smooth = new LinkedList<>();
//			smooth.add(smoothPath.getFirst());
//			int length = smoothPath.size();
//			int nowIndex = 0;
//			int testIndex = 2;
//			while (testIndex < length) {
//				if (!canPass(smoothPath.get(nowIndex), smoothPath.get(testIndex))) {
//					smooth.add(smoothPath.get(testIndex - 1));
//					nowIndex = testIndex - 1;
//					testIndex++;
//				} else {
//					testIndex++;
//				}
//			}
//			smooth.add(smoothPath.get(length - 1));
//		}
//	}

//	/**
//	 * 可否通过
//	 * 
//	 * @param start
//	 * @param end
//	 * @return
//	 */
//	private boolean canPass(AStarNode start, AStarNode end) {
//		int x = start.getX();
//		int y = start.getY();
//		int w = end.getX() - start.getX();
//		int h = end.getY() - start.getY();
//		int dx1 = w < 0 ? -1 : (w > 0 ? 1 : 0);
//		int dy1 = h < 0 ? -1 : (h > 0 ? 1 : 0);
//		int dx2 = w < 0 ? -1 : (w > 0 ? 1 : 0);
//		int dy2 = 0;
//		int fastStep = Math.abs(w);
//		int slowStep = Math.abs(h);
//		if (fastStep <= slowStep) {
//			fastStep = Math.abs(h);
//			slowStep = Math.abs(w);
//			dx2 = 0;
//			dy2 = h < 0 ? -1 : (h > 0 ? 1 : 0);
//		}
//		int numerator = fastStep >> 1;
//		for (int i = 0; i <= fastStep; i++) {
//			if (x != start.getX() || y != start.getY()) {
//				if (!this.grid.getNode(x, y).isWalkable()) {
//					return false;
//				}
//			}
//			numerator += slowStep;
//			if (numerator >= fastStep) {
//				numerator -= fastStep;
//				x += dx1;
//				y += dy1;
//			} else {
//				x += dx2;
//				y += dy2;
//			}
//		}
//		return true;
//	}

	public LinkedList<AStarNode> getSmoothPath() {
		return smoothPath;
	}

	public void setSmoothPath(LinkedList<AStarNode> smoothPath) {
		this.smoothPath = smoothPath;
	}

	@Override
	protected int heuristic(AStarNode node1, AStarNode node2) {
		return super.euclidian(node1, node2);
	}

}
