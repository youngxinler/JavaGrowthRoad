package com.graphic.future;

/*
 * futureData 是data的取货单, 如果realData没有准备好, 那么就会使用wait()方法阻塞getContent方法.
 * 切记, setContent()中一定要调用 notifyAll(), 否则,调用futureData()的方法的进程会始终阻塞在getContent()方法中!
 *
 * 个人理解, workerThread pattern相比于threadPerMessage pattern(最初版本的做法, 不使用java.util.concurrent包)
 * 避免了线程启动和销毁的开销, 但是这两种pattern虽然不会阻塞方法的进行,但不能获得处理的返回值.
 *
 * 但future pattern既不会发生"阻塞", (这里的意思不是wait()方法, 也就是realData的生成过程中, 调用getContent()之前, 线程是自由.)
 * 其实这里也可以理解future 实现了"异步", 它将值生成的过程, 与获取值的过程 进行了分离, 以往值生成的过程, 线程只能傻乎乎在那里等着, 但现在该线程可以去
 * 做其他的事情了, 做完其他事情后, 再来利用完成的值, 完成最后的活动.
 * 举个例子: 我们想吃泡面的时候, 先把泡面泡上, 然后泡面泡好之前, 我们可以去看会书, 刷刷微博,知乎, 或者去楼下买杯饮品, 我们可以不傻乎乎的站在泡面前等着它泡好,
 *          只要在最后它泡好之后, 记得回来吃了它就行.
 */

public class FutureData implements Data {
    private boolean ready = false;
    private RealData realData;

    public synchronized void setContent(RealData realData) {
        if (ready) {
            return;
        }
        this.realData = realData;
        ready = true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
