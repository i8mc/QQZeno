package me.funapi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class WallPostThread extends Thread {
    /**
     * 暂时没有用
     * 因为timertask中post方法无法调用robot的键盘操作
     */

    //TODO 更换写法
    private Queue<Wall> queues = new LinkedList<Wall>();

    public WallPostThread() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Demo.CQ.logDebug("[Wall-PostThread]", "开始检查队列");
                if (queues.size() == 0) {
                    return;
                }
                Demo.CQ.logDebug("[Wall-PostThread]", "队列不为空");
                Wall wall = queues.element();
                Demo.getUtil().post(wall);
                queues.poll();
                Demo.CQ.logDebug("[Wall-PostThread]", "队列提交任务");
            }
        };
    }

    private TimerTask timerTask;
    private Timer timer = new Timer();

    @Override
    public void run() {
        timer.schedule(timerTask, 1000 * 10, 1000 * 10);
    }

    public void add(Wall wall) {
        queues.offer(wall);
    }

    @Override
    public void interrupt() {
        super.interrupt();
        timer.cancel();
    }
}
