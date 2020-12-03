package astar.simple.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 切面测试
 *
 * @author CHU
 * @date 2020年7月7日
 *
 */
public class AStarInvocationHandler implements InvocationHandler {

	// 被动态代理的对象
	private Object target;

	public AStarInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("开始记录日志，方法开始执行...");
		method.invoke(target, args);
		System.out.println("结束记录日志，方法结束执行...");
		return null;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		AStarInterface aStarInterface = (AStarInterface) Proxy.newProxyInstance(AStarInterface.class.getClassLoader(), new Class<?>[] { AStarInterface.class }, new AStarInvocationHandler(new AStar()));
		aStarInterface.find();
//		CoordinateInfo coordinateInfo = new CoordinateInfo();
//		coordinateInfo.setCoordinate(new Coordinate(1, 1));
//		coordinateInfo.setCoordinateType(CoordinateType.NORMAL);
//		coordinateInfo.setFiveElementsProp(FiveElementsProp.EARTH);
//		
//		PlayerUnit playerUnit = new PlayerUnit();
//		playerUnit.setBattleReportId(11111L);
//		playerUnit.setFactionId(11111L);
//		playerUnit.setPlayerId(11111L);
//		playerUnit.setPreStatus(new StatusModel(PieceStatus.NORMAL, 11111L, 11111L));
//		playerUnit.setStatusModel(playerUnit.getPreStatus());
//		playerUnit.setTarget(new Coordinate(1, 1));
//		playerUnit.setTragetId(11111L);
//		
//		coordinateInfo.setPlayer(playerUnit);
//		
//		
//		long now = System.currentTimeMillis();
//		
//		
//		CoordinateInfo copy = coordinateInfo.clone();
//		coordinateInfo.setCoordinateType(CoordinateType.BRIDGE);
//		System.out.println(coordinateInfo.getPlayer());
//		System.out.println(copy.getPlayer());
//		System.out.println(copy.getCoordinateType());
//		
//		
//		long after = System.currentTimeMillis();
//		System.out.println("耗时：" + (after - now));
	}

}
