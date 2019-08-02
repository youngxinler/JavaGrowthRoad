import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
    }

    public static class SimulateCAS{
        private volatile int value;

        public synchronized int get(){
            return value;
        }

        public synchronized int compareAndSwap(int exceptedValue, int newValue){
            int oldValue = value;
            if (oldValue == exceptedValue)
                value = newValue;
            return oldValue;
        }

        public synchronized boolean compareAndSet(int exceptedValue, int newValue){
            return (exceptedValue == compareAndSwap(exceptedValue, newValue));
        }
    }
}
