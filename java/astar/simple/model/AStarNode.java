package astar.simple.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 格子节点
 *
 * @author CHU
 * @date 2020年6月30日
 *
 */
public class AStarNode {

	private int x;

	private int y;

	/** 是否可通过 */
	private boolean walkable = true;

	/** 通过代价 */
	private int costMultiplier;

	/** 总代价 */
	private int f;

	/** 起点到当前节点的代价 */
	private int g;

	/** 当前节点到终点的代价预估值 */
	private int h;

	/** 父亲节点 */
	private AStarNode parent;

	/** 当前是第几次寻路 */
	private int checkNum;

	/** 周围可到达的节点 */
	private List<AStarLink> aroundLinks = new ArrayList<>();

	public AStarNode(int x, int y, boolean walkable, int costMultiplier) {
		super();
		this.x = x;
		this.y = y;
		this.walkable = walkable;
		this.costMultiplier = costMultiplier;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public int getCostMultiplier() {
		return costMultiplier;
	}

	public void setCostMultiplier(int costMultiplier) {
		this.costMultiplier = costMultiplier;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public AStarNode getParent() {
		return parent;
	}

	public void setParent(AStarNode parent) {
		this.parent = parent;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public List<AStarLink> getAroundLinks() {
		return aroundLinks;
	}

	public void setAroundLinks(List<AStarLink> aroundLinks) {
		this.aroundLinks = aroundLinks;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AStarNode other = (AStarNode) obj;
		if (other.x == this.x && other.y == this.y) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 15;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}

}
