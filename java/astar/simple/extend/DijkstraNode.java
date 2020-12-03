package astar.simple.extend;

import java.util.Map;

/**
 * Dijkstra节点
 *
 * @author CHU
 * @date 2020年7月1日
 *
 */
public class DijkstraNode {

	private String name;

	/** 关联的节点 */
	private Map<String, DijkstraLink> link;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, DijkstraLink> getLink() {
		return link;
	}

	public void setLink(Map<String, DijkstraLink> link) {
		this.link = link;
	}

}
