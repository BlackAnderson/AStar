package astar.simple.model;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.collections.buffer.PriorityBuffer;

/**
 * A*寻路
 *
 * @author CHU
 * @date 2020年6月30日
 *
 */
public abstract class AStar {

	/**
	 * 上下左右的移动成本
	 */
	public static int STRAIGHT_COST = 10;

	/**
	 * 斜角的移动成本
	 */
	public static int DIAG_COST = 14;

	/** 移动地图 */
	protected AStarGrid grid;

	/** 开始节点 */
	protected AStarNode startNode;

	/** 结束节点 */
	protected AStarNode endNode;

	/** 第几次寻路 */
	protected int nowCheckNum = 1;

	/** 二叉堆(完全二叉树 按照每个节点的F值排序) */
	protected PriorityBuffer binaryHeaps;

	/** 默认排序规则 根据节点的F值排序(F值最小的在树的最顶端,直接获取F值最小的节点) */
	private Comparator<AStarNode> comparator = new Comparator<AStarNode>() {

		@Override
		public int compare(AStarNode o1, AStarNode o2) {
			return o1.getF() - o2.getF();
		}
	};

	/** 路径 */
	protected LinkedList<AStarNode> path;

	public AStar() {
		this.binaryHeaps = new PriorityBuffer(comparator);
	}

	/**
	 * 寻路
	 * 
	 * @return
	 */
	public boolean find(AStarGrid grid) {
		// 初始化寻路地图
		if (this.grid != null) {
			this.clear();
		}
		this.grid = grid;
		this.startNode = this.grid.getStartNode();
		this.endNode = this.grid.getEndNode();
		this.startNode.setG(0);
		this.startNode.setH(this.heuristic(startNode, endNode));
		this.startNode.setF(this.startNode.getG() + this.startNode.getH());
		return this.search();
	}

	/**
	 * 寻路
	 * 
	 * @return
	 */
	public boolean search() {
		AStarNode node = this.startNode;
		while (node != this.endNode) {
			Iterator<AStarLink> iterator = node.getAroundLinks().iterator();
			while (iterator.hasNext()) {
				AStarLink next = iterator.next();
				AStarNode test = next.getNode();
				int g = node.getG() + next.getCost();
				int h = heuristic(next.getNode(), this.endNode);
				int f = g + h;
				if (test.getCheckNum() == this.nowCheckNum) {
					if (test.getF() > f) {
						test.setF(f);
						test.setG(g);
						test.setH(h);
						test.setParent(node);
						if (this.binaryHeaps.contains(test)) {
							this.binaryHeaps.remove(test);
						}
						this.binaryHeaps.add(test);
					}
				} else {
					test.setF(f);
					test.setG(g);
					test.setH(h);
					test.setParent(node);
					this.binaryHeaps.add(test);
					test.setCheckNum(this.nowCheckNum);
				}
			}
			node.setCheckNum(this.nowCheckNum);
			if (this.binaryHeaps.size() <= 0) {
				this.nowCheckNum++;
				return false;
			}
			node = (AStarNode) binaryHeaps.remove();
		}
		this.buildPath();
		this.nowCheckNum++;
		return true;
	}

	/**
	 * 构建路径
	 */
	protected void buildPath() {
		this.path = new LinkedList<>();
		AStarNode node = this.endNode;
		this.path.addFirst(node);
		while (node != this.startNode) {
			node = node.getParent();
			this.path.addFirst(node);
		}
	}

	public void clear() {
		this.grid = null;
		this.startNode = null;
		this.endNode = null;
		this.binaryHeaps.clear();
		this.path = new LinkedList<>();
	}

	/**
	 * 曼哈顿启发函数
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	public static int manhattan(AStarNode node1, AStarNode node2) {
		int dx = node1.getX() > node2.getX() ? node1.getX() - node2.getX() : node2.getX() - node1.getX();
		int dy = node1.getY() > node2.getY() ? node1.getY() - node2.getY() : node2.getY() - node1.getY();
		return (dx + dy) * AStar.STRAIGHT_COST;
	}

	/**
	 * 欧式启发函数
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	public static int euclidian(AStarNode node1, AStarNode node2) {
		int dx = node1.getX() > node2.getX() ? node1.getX() - node2.getX() : node2.getX() - node1.getX();
		int dy = node1.getY() > node2.getY() ? node1.getY() - node2.getY() : node2.getY() - node1.getY();
		return ((Double) ((Math.sqrt((dx * dx + dy * dy)) * AStar.STRAIGHT_COST))).intValue();
	}

	/**
	 * 对角启发函数
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	public static int diagonal(AStarNode node1, AStarNode node2) {
		int dx = node1.getX() > node2.getX() ? node1.getX() - node2.getX() : node2.getX() - node1.getX();
		int dy = node1.getY() > node2.getY() ? node1.getY() - node2.getY() : node2.getY() - node1.getY();
		return dx > dy ? AStar.DIAG_COST * dy + AStar.STRAIGHT_COST * (dx - dy) : AStar.DIAG_COST * dx + AStar.STRAIGHT_COST * (dy - dx);
	}

	/** 启发函数 */
	protected abstract int heuristic(AStarNode node1, AStarNode node2);

}
