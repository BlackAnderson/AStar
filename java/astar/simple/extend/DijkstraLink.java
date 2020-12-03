package astar.simple.extend;

/**
 * 可移动到的格子的关系
 *
 * @author CHU
 * @date 2020年6月30日
 *
 */
public class DijkstraLink {

	/** 联系的格子 */
	private DijkstraNode node;

	/** 消耗 */
	private int cost;

	public DijkstraNode getNode() {
		return node;
	}

	public void setNode(DijkstraNode node) {
		this.node = node;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public DijkstraLink(DijkstraNode node, int cost) {
		this.node = node;
		this.cost = cost;
	}

}
