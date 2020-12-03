package astar.simple.model;

/**
 * 测试用例
 *
 * @author CHU
 * @date 2020年7月1日
 *
 */
public class AStarMain {

	public static void main(String[] args) {
		// AStar aStar = new AStar() {
		//
		// @Override
		// protected int heuristic(AStarNode node1, AStarNode node2) {
		// return super.manhattan(node1, node2);
		// }
		// };
		SmoothAStar aStar = new SmoothAStar();
		AStarGrid grid = new AStarGrid(10, 10);
		grid.setStartNode(grid.getNode(1, 1));
		grid.setEndNode(grid.getNode(9, 9));
		grid.setWalkable(2, 1, false);
		grid.setWalkable(2, 2, false);
		grid.setWalkable(1, 2, false);
		grid.setWalkable(0, 5, false);
		grid.setWalkable(1, 5, false);
		grid.setWalkable(2, 4, false);
		grid.setWalkable(3, 3, false);
		grid.setWalkable(4, 2, false);
		grid.setWalkable(5, 1, false);
		long now = System.currentTimeMillis();
		grid.findAroundLinks();
		aStar.find(grid);
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - now));
		// for (AStarNode aStarNode : aStar.getSmoothPath()) {
		// System.out.println(aStarNode.getX() + "," + aStarNode.getY());
		// }
		for (AStarNode node : aStar.path) {
			System.out.println(node.getX() + "," + node.getY());
		}
	}
}
