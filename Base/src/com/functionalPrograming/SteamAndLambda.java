package com.functionalPrograming;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author youngxinler  19-6-30 上午10:04
 * <p>
 * java-8 stream
 * <p>
 * 流的执行方式:
 * 1.顺序执行
 * 2.并行执行
 * >一个流是否可以并行执行可以通过isParallel()方法来判断.
 * >使用方法sequential()或者parallel()来将流的执行方法设为顺序或者并行.
 * <p>
 * 流的相遇顺序:
 * 相遇顺序是流中元素被处理时的顺序.
 * 流的相遇顺序根据其特征可以进行判断.
 * 举例:
 * 1.从ArrayList创建的流有确定的顺序.
 * 2.从HashSet创建的流没有确定的顺序.
 * 可以使用sorted或者unsorted使流有序或者无序.
 * <p>
 * 流的有状态操作和无状态操作：
 * 有状态操作需要使用处理之前元素的信息, 如sorted, 需要根据前面的元素来判断当前元素的位置.
 * 如distinct, 需要判断之前的元素是否出现过来判断是否丢弃当前元素.
 * 无状态操作只涉及当前元素的状态, 如filter和map, filter过滤时只查看当前元素是否符合状态.
 * <p>
 * 流的流水线操作:
 * 具体的操作=源(source) + 0-N个中间操作(intermediate operation) + 终止操作(terminal operation)
 * 源: 源是流中元素的来源. java中的流提供对很多源的支持, 比如数组, 集合, 生成函数, I/O通道等.
 * 中间操作: 中间操作在一个流上进行操作, 返回结果是一个新的流. 操作是延迟性的.
 * 终止操作: 终止操作遍历流来产生一个结果或者副作用, 该流执行完终止操作之后, 即被消费掉, 无法被再次消费.
 * <p>
 * 流的中间操作:
 * map: 通过一个function将一个元素类型为T的流转化为元素类型为R的流, 或者可以理解为对流中的每个元素进行同一操作.
 * flatMap:通过一个 Function 把一个元素类型为 T 的流中的每个元素转换成一个元素类型为 R 的流，再把这些转换之后的流合并
 * filter: 过滤流中的元素, 只保留满足由Predicate所指定的条件的元素.
 * distinct: 使用equals方法来删除流中的重复元素.
 * limit: 截断流使其最多只包含指定数量的元素.
 * skip: 返回一个新的流, 并跳过原始流中的前N个元素.
 * sorted: 对流进行排序.
 * peek：返回的流与原始流相同。当原始流中的元素被消费时，会首先调用 peek 方法中指定的 Consumer 实现对元素进行处理.
 * dropWhile：从原始流起始位置开始删除满足指定 Predicate 的元素，直到遇到第一个不满足 Predicate 的元素. java-9
 * takeWhile：从原始流起始位置开始保留满足指定 Predicate 的元素，直到遇到第一个不满足 Predicate 的元素. java-9
 * <p>
 * 参考资源:https://www.ibm.com/developerworks/cn/java/j-understanding-functional-programming-3/index.html
 **/

public class SteamAndLambda {

    public static void main(String[] args) {

        //从数组中创建流
        Arrays.stream(new String[]{"hello", "world"})
                .forEach(System.out::println);

        int sum = Arrays.stream(new int[]{1, 2, 3})
                .reduce((v1, v2) -> v1 + v2)
                .getAsInt();
        System.out.println(sum);

        //从集合中创建流
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.stream()
                .forEach(System.out::println);

        //中间操作
        Stream.of(1, 2, 3).
                map(v -> v + 1)
                .flatMap(v -> Stream.of(v * 5, v * 10))
                .forEach(System.out::println);
        //输出 10，20，15，30，20，40


        //reduce操作示例
        Stream.of(1, 2, 3)
                .reduce((v1, v2) -> v1 + v2)
                .ifPresent(System.out::println);
        //6
        int result1 = Stream.of(1, 2, 3, 4, 5)
                .reduce(1, (v1, v2) -> v1 * v2);
        System.out.println(result1);
        //120
        int result2 = Stream.of(1, 2, 3, 4, 5)
                .parallel()
                .reduce(0, (v1, v2) -> v1 + v2, (v1, v2) -> v1 + v2);
        System.out.println(result2);
        //15

        //收集器groupingBy示例
        final Map<Character, List<String>> names =
                Stream.of("Alex", "Bob", "David", "Amy")
                        .collect(Collectors.groupingBy(v -> v.charAt(0)));
        System.out.println(names);
        //{A=[Alex, Amy], B=[Bob], D=[David]}

        //收集器joining示例
        String str = Stream.of("a", "b", "c")
                .collect(Collectors.joining(", "));
        System.out.println(str);
        //a, b, c
    }
}
