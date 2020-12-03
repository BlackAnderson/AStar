package astar.simple.model;

/**
 * A*寻路地图
 *
 * @author CHU
 * @date 2020年6月30日
 *
 */
public class AStarGrid {
	/** 地图 */
	private AStarNode[][] grid = new AStarNode[0][0];

	/** 列 */
	private int colums;

	/** 行 */
	private int rows;

	/** 开始节点 */
	private AStarNode startNode;

	/** 目标节点 */
	private AStarNode endNode;

	/**
	 * 构造寻路地图
	 * 
	 * @param colums
	 * @param rows
	 */
	public AStarGrid(int colums, int rows) {
		this.colums = colums;
		this.rows = rows;
		grid = new AStarNode[colums][rows]; 
		for (int i = 0; i < colums; i++) {
			if (grid[i] == null) {
				grid[i] = new AStarNode[rows];
			}
			for (int j = 0; j < rows; j++) {
				grid[i][j] = createNode(i, j);
			}
		}
	}

	/**
	 * 构建周围的节点
	 */
	public void findAroundLinks() {
		for (int i = 0; i < colums; i++) {
			for (int j = 0; j < rows; j++) {
				AStarNode node = grid[i][j];
				int startX = Math.max(0, node.getX() - 1);
				int endX = Math.min(colums - 1, node.getX() + 1);
				int startY = Math.max(0, node.getY() - 1);
				int endY = Math.min(rows - 1, node.getY() + 1);
				for (int m = startX; m <= endX; m++) {
					for (int n = startY; n <= endY; n++) {
						AStarNode target = grid[m][n];
						if (target == node || !target.isWalkable() || !this.grid[node.getX()][target.getY()].isWalkable() || !this.grid[target.getX()][node.getY()].isWalkable()) {
							continue;
						}
						int cost = AStar.STRAIGHT_COST;
						if (!((node.getX() == target.getX()) || (node.getY() == target.getY()))) {
							cost = AStar.DIAG_COST;
						}
						AStarLink link = new AStarLink(target, cost);
						node.getAroundLinks().add(link);
					}
				}
			}
		}
	}

	public AStarNode createNode(int x, int y) {
		AStarNode node = new AStarNode(x, y, true, 1);
		return node;
	}

	public AStarNode getNode(int x, int y) {
		return this.grid[x][y];
	}

	public void setGrid(AStarNode[][] grid) {
		this.grid = grid;
	}

	public int getColums() {
		return colums;
	}

	public void setColums(int colums) {
		this.colums = colums;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public AStarNode getStartNode() {
		return startNode;
	}

	public void setStartNode(AStarNode startNode) {
		this.startNode = startNode;
	}

	public AStarNode getEndNode() {
		return endNode;
	}

	public void setEndNode(AStarNode endNode) {
		this.endNode = endNode;
	}
	
	public void setWalkable(int x, int y,boolean walkable) {
        this.grid[x][y].setWalkable(walkable);
    }

}
