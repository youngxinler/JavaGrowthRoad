>在编码过程中, 我们难免会遇见对共享变量的读取或者更新操作, 而这些共享变量是可以被多个线程同时访问, 我们可以使用锁来保证这些操作的有序地进行串行的访问, 但是这样性能和可伸缩性将大大降低, 这就需要我们今天的主角, Java中的原子变量.
### Java原子变量的主要机制
- volatile保证其内部字段的可见性, 有序性.
- 通过CAS机器级指令实现原子性的更新操作.
如果你对CAS机制不了解, 推荐看下这篇文章[Java CAS原理分析](https://github.com/youngxinler/JavaGrowthRoad/blob/master/Eassy/basics/Java%20CAS%E5%8E%9F%E7%90%86%E5%88%86%E6%9E%90.md)
### 分析源码 (AtomicInteger)
```java
public class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int value;
```
>valueOffset是value字段的偏移量, 这里的offset,处于不同的操作系统是有差别的, 不是value的准确内存地址, 首先它是一个静态变量就确定它不可能是准确地址, 其次static的执行位序也确定了他不会和实例的地址有关.
这里我们找一个方法来看下.
```java
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
```
这里通过this传入了实例的引用, 与偏移量配合, 完成这个CAS操作.
至于具体的unsafe的代码, 这里的compareAndSwapInt()是一个JNI方法, 我在其他文章说过.

### 其他原子变量
至于其他原子变量的更新操作, 和AtomicInteger的处理思路是近乎一致的, 通过Unsafe直接或者间接提供的CAS操作来完成
AtomicReference的一个举例
```java
    public final void set(V newValue) {
        value = newValue;
    }
    public final boolean compareAndSet(V expect, V update) {
        return unsafe.compareAndSwapObject(this, valueOffset, expect, update);
    }
```
这里不免有一个疑问, 可能会觉得有了set(), 还要compareAndSet()方法有什么用呢?
*我的理解是:set()方法是无状态的, 由于 value本身就是volitile, 保证了每次更新的可见性, 但是仅仅拥有可见性是不行的, 想一下, 其实跟volitile i进行i++会造成错误一样, 如果你的逻辑操作是有状态依赖的话, 就要用compareAndSet(), 如果没有状态依赖, 那么直接set()即可*
