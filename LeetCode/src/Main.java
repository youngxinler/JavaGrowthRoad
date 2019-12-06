import java.nio.ByteBuffer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{1, 2, 3};
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        bytes[1] = (byte) 3;
        System.out.println(buf.get());
        System.out.println(Arrays.toString(buf.array()));
    }
}