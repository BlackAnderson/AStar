package astar.simple.model;

/**
 * 可移动到的格子的关系
 *
 * @author CHU
 * @date 2020年6月30日
 *
 */
public class AStarLink {

	/** 联系的格子 */
	private AStarNode node;

	/** 消耗 */
	private int cost;

	public AStarNode getNode() {
		return node;
	}

	public void setNode(AStarNode node) {
		this.node = node;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public AStarLink(AStarNode node, int cost) {
		this.node = node;
		this.cost = cost;
	}

}
