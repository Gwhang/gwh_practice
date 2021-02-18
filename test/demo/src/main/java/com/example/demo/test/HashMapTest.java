package com.example.demo.test;

/**
 * 为什么HashMap的长度是2的整数次幂？
 *
 * 为了加快哈希计算以及减少哈希冲突
 *
 * 为什么可以加快计算？
 * 要找到 KEY 的位置在哈希表的哪个槽里面，需要计算   hash(KEY) % 数组长度
 * 但是 %(取模) 计算比 &(与) 慢很多
 * 所以用 & 代替 %，为了保证 & 的计算结果等于 % 的结果需要把 length 减 1 也就是 hash(KEY) & (length - 1)
 * 下面是hashMap 计算索引存放位置的方法
 *
 * 为什么可以减少冲突？
 * 假设现在数组的长度 length 可能是偶数也可能是奇数
 * length 为偶数时，length-1 为奇数，奇数的二进制最后一位是 1，这样便保证了 hash &(length-1) 的最后一位可能为 0，
 * 也可能为 1（这取决于 h 的值），即 & 运算后的结果可能为偶数，也可能为奇数，这样便可以保证散列的均匀性。
 *
 * 而如果 length 为奇数的话，很明显 length-1 为偶数，它的最后一位是 0，这样 hash & (length-1) 的最后一位肯定为 0，即只能为偶数，
 * 这样任何 hash 值都只会被散列到数组的偶数下标位置上，这便浪费了近一半的空间，最后一位都为0，而0001，0011，0101，1001，1011，0111，1101这几个位置永远都不能存放元素了，空间浪费相当大，
 * 更糟的是这种情况中，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率
 *
 *  HashMap的容量为16转化成二进制为10000，length-1得出的二进制为1111 ，5的二进制为 101 进行与运算 （有对应的两个二进位都为1时，结果位才为1否则为0）
 *        1 0 1
 *  &   1 1 1 1
 *  -------------
 *      0 1 0 1
 *  结果是 5
 *  假设HashMap的容量为15 转换为2进制 1111, length-1得出的二进制为1110 ，进行与运算
 *     1 1 1 1
 *   & 1 1 1 0
 *   -----------
 *     1 1 1 0
 *
 *     1 1 1 0
 *   & 1 1 1 0
 *   -----------
 *     1 1 1 0
 *
 *    现在两个索引的位置都是14，就会造成分布不均匀，增加了碰撞的几率，减慢了查询的效率，造成空间的浪费
 *
 *
 *
 */
public class HashMapTest {

    public static void main(String[] args) {
        // 与计算 hash(KEY) & (length - 1)
        System.out.println(indexFor(158,16));
        // 取模计算 hash(KEY) % 数组长度
        System.out.println(158 % 16);

    }

    /**
     * HashMap通过哈希算法得出哈希值之后，将键值对放入哪个索引的方法
     * @param h
     * @param length
     * @return
     */
    public static int indexFor(int h, int length) {

    // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        return h & (length - 1);
    }

}
