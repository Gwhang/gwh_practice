package com.example.demo.map;

import sun.misc.JavaLangAccess;
import sun.misc.SharedSecrets;
import sun.misc.VM;

import java.util.HashMap;
import java.util.Random;

/**
 * 实现HashMap1.7
 */
public class TestHashMap<K,V> implements TestMap<K,V> {

    /**
     * 表示hashmap数组的最大容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 默认初始化容量
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * 默认加载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 数组长度
     */
    transient int size;

    transient boolean useAltHashing;

    /**
     * 数组容量
     */
    int threshold;

    /**
     * 加载因子
     */
    final float loadFactor;

    /**
     * 存储的entity对象
     */
    transient Entry<K,V>[] table;

    static final int ALTERNATIVE_HASHING_THRESHOLD_DEFAULT = Integer.MAX_VALUE;

    transient final int hashSeed = Hashing.randomHashSeed(this);


    /**
     * hashMap键值对
     * @param <K> key
     * @param <V> value
     */
    static class Entry<K,V> implements TestMap.Entry<K,V>{

        /**
         * key
         */
        final K key;
        /**
         * value
         */
        V value;
        /**
         * 如果产生了hashcode冲突，使用链表存储 头插法
         */
        Entry<K,V> next;
        /**
         * hash值
         */
        int hash;

        /**
         * Creates new entry.
         */
        Entry(int h, K k, V v, Entry<K,V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void recordAccess(TestHashMap<K, V> kvTestHashMap) {
        }
    }


    /**
     * 无参数构造方法
     */
    public TestHashMap(){
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * 有参数构造方法
     * @param initialCapacity  初始化容量
     * @param loadFactor 加载因子 0.75
     */
    public TestHashMap(int initialCapacity, float loadFactor){
        // 检查我们的数组初始容量是否小于0
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        }
        //检查我们的数组最大容量是否越界  如果超过就等于最大容量
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        //判断加载因子是否小于等于0
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        }

        this.loadFactor = loadFactor;
        // 计算容量
        // Find a power of 2 >= initialCapacity  大于2倍的情况下产生
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }
        // capacity * 0.75 于 hashMap的最大容量进行比较，取最小的数 作为hashMap的数组容量
        threshold = (int)Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
        //初始化 table 默认容量是16 加载因子是12 如果达到12则进行扩容
        table = new Entry[capacity];
        useAltHashing = sun.misc.VM.isBooted() &&
                (capacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
        // 方便后期扩展
        init();

    }

    void init() {
    }

    private static class Holder {

        // Unsafe mechanics
        /**
         * Unsafe utilities
         */
        static final sun.misc.Unsafe UNSAFE;

        /**
         * Offset of "final" hashSeed field we must set in readObject() method.
         */
        static final long HASHSEED_OFFSET;

        /**
         * Table capacity above which to switch to use alternative hashing.
         */
        static final int ALTERNATIVE_HASHING_THRESHOLD;

        static {
            String altThreshold = java.security.AccessController.doPrivileged(
                    new sun.security.action.GetPropertyAction(
                            "jdk.map.althashing.threshold"));

            int threshold;
            try {
                threshold = (null != altThreshold)
                        ? Integer.parseInt(altThreshold)
                        : ALTERNATIVE_HASHING_THRESHOLD_DEFAULT;

                // disable alternative hashing if -1
                if (threshold == -1) {
                    threshold = Integer.MAX_VALUE;
                }

                if (threshold < 0) {
                    throw new IllegalArgumentException("value must be positive integer.");
                }
            } catch(IllegalArgumentException failed) {
                throw new Error("Illegal value for 'jdk.map.althashing.threshold'", failed);
            }
            ALTERNATIVE_HASHING_THRESHOLD = threshold;

            try {
                UNSAFE = sun.misc.Unsafe.getUnsafe();
                HASHSEED_OFFSET = UNSAFE.objectFieldOffset(
                        HashMap.class.getDeclaredField("hashSeed"));
            } catch (NoSuchFieldException | SecurityException e) {
                throw new Error("Failed to record hashSeed offset", e);
            }
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public V get(K key) {
        // 如果key为null则取table[0]的值
        if (key == null) {
            return getForNullKey();
        }
        //循环取值
        Entry<K,V> entry = getEntry(key);

        return null == entry ? null : entry.getValue();
    }

    /**
     * 循环根据key 取 value
     * @param key
     * @return
     */
    final Entry<K,V> getEntry(Object key) {
        int hash = (key == null) ? 0 : hash(key);
        for (Entry<K,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))){
                return e;
            }
        }
        return null;
    }

    /**
     * 如果key为null的时候取 table[0]的值
     * @return
     */
    private V getForNullKey() {
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                return e.value;
            }
        }
        return null;
    }
    /**
     * put 方法
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        // hashMap的值允许为空
        if (key == null) {
            //如果为空则放在第一个位置
            return putForNullKey(value);
        }
        // 计算hash值
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        //循环添加键值对
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
            Object k;
            //如果key存在 则值相同则进行修改
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        return null;
    }

    /**
     * Returns index for hash code h.
     */
    static int indexFor(int h, int length) {
        //使用二进制的计算减少index冲突
        return h & (length-1);
    }

    final int hash(Object k) {
        int h = 0;
        if (useAltHashing) {
            if (k instanceof String) {
                return Hashing.stringHash32((String) k);
            }
            h = hashSeed;
        }

        h ^= k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    /**
     *每当条目中的值被HashMap中已经存在的键k的put（k，v）的调用覆盖时，
     *  就会调用他的方法。
     */
    void recordAccess(HashMap<K,V> m) {
    }
    /**
     * put 方法如果hashMap的key为空则放置到第一个位置
     */
    private V putForNullKey(V value) {
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        modCount++;
        addEntry(0, null, value, 0);
        return null;
    }




    /**
     * 此哈希映射在结构上被修改的次数
     */
    transient int modCount;

    /**
     * Adds a new entry with the specified key, value and hash code to
     * the specified bucket.  It is the responsibility of this
     * method to resize the table if appropriate.
     *
     * Subclass overrides this to alter the behavior of put method.
     */
    void addEntry(int hash, K key, V value, int bucketIndex) {
        // 一旦size 大于加载容量 的时候 进行扩容
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    /**
     * 创建 entry 对象
     * @param hash
     * @param key
     * @param value
     * @param bucketIndex
     */
    void createEntry(int hash, K key, V value, int bucketIndex) {
        //获取原来的链表
        Entry<K,V> e = table[bucketIndex];
        //创建新的entity对象 如果产生冲突会将新进入的数据插入到最前面
        table[bucketIndex] = new Entry<>(hash, key, value, e);
        size++;
    }

    /**
     *
     * 将此映射的内容重新合并到一个容量更大的新数组中。
     * 当此映射中的键数达到其阈值时，将自动调用此方法。
     * 如果当前容量是最大容量，则此方法不会调整映射的大小，
     * 但会将阈值设置为Integer.MAX\u值。
     * @param newCapacity
     */
    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        boolean oldAltHashing = useAltHashing;
        useAltHashing |= sun.misc.VM.isBooted() &&
                (newCapacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
        boolean rehash = oldAltHashing ^ useAltHashing;
        transfer(newTable, rehash);
        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }
    /**
     * 将当前表中的所有条目转移到新的Table中
     */
    void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
        for (Entry<K,V> e : table) {
            while(null != e) {
                Entry<K,V> next = e.next;
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }


}

class Hashing {
    private Hashing() {
        throw new Error("No instances");
    }

    public static int murmur3_32(byte[] var0) {
        return murmur3_32(0, (byte[])var0, 0, var0.length);
    }

    public static int murmur3_32(int var0, byte[] var1) {
        return murmur3_32(var0, (byte[])var1, 0, var1.length);
    }

    public static int murmur3_32(int var0, byte[] var1, int var2, int var3) {
        int var4 = var0;

        int var5;
        int var6;
        for(var5 = var3; var5 >= 4; var4 = var4 * 5 + -430675100) {
            var6 = var1[var2] & 255 | (var1[var2 + 1] & 255) << 8 | (var1[var2 + 2] & 255) << 16 | var1[var2 + 3] << 24;
            var5 -= 4;
            var2 += 4;
            var6 *= -862048943;
            var6 = Integer.rotateLeft(var6, 15);
            var6 *= 461845907;
            var4 ^= var6;
            var4 = Integer.rotateLeft(var4, 13);
        }

        if (var5 > 0) {
            var6 = 0;
            switch(var5) {
                case 3:
                    var6 ^= (var1[var2 + 2] & 255) << 16;
                case 2:
                    var6 ^= (var1[var2 + 1] & 255) << 8;
                case 1:
                    var6 ^= var1[var2] & 255;
                default:
                    var6 *= -862048943;
                    var6 = Integer.rotateLeft(var6, 15);
                    var6 *= 461845907;
                    var4 ^= var6;
            }
        }

        var4 ^= var3;
        var4 ^= var4 >>> 16;
        var4 *= -2048144789;
        var4 ^= var4 >>> 13;
        var4 *= -1028477387;
        var4 ^= var4 >>> 16;
        return var4;
    }

    public static int murmur3_32(char[] var0) {
        return murmur3_32(0, (char[])var0, 0, var0.length);
    }

    public static int murmur3_32(int var0, char[] var1) {
        return murmur3_32(var0, (char[])var1, 0, var1.length);
    }

    public static int murmur3_32(int var0, char[] var1, int var2, int var3) {
        int var4 = var0;
        int var5 = var2;

        int var6;
        int var7;
        for(var6 = var3; var6 >= 2; var4 = var4 * 5 + -430675100) {
            var7 = var1[var5++] & '\uffff' | var1[var5++] << 16;
            var6 -= 2;
            var7 *= -862048943;
            var7 = Integer.rotateLeft(var7, 15);
            var7 *= 461845907;
            var4 ^= var7;
            var4 = Integer.rotateLeft(var4, 13);
        }

        if (var6 > 0) {
            char var8 = var1[var5];
            var7 = var8 * -862048943;
            var7 = Integer.rotateLeft(var7, 15);
            var7 *= 461845907;
            var4 ^= var7;
        }

        var4 ^= var3 * 2;
        var4 ^= var4 >>> 16;
        var4 *= -2048144789;
        var4 ^= var4 >>> 13;
        var4 *= -1028477387;
        var4 ^= var4 >>> 16;
        return var4;
    }

    public static int murmur3_32(int[] var0) {
        return murmur3_32(0, (int[])var0, 0, var0.length);
    }

    public static int murmur3_32(int var0, int[] var1) {
        return murmur3_32(var0, (int[])var1, 0, var1.length);
    }

    public static int murmur3_32(int var0, int[] var1, int var2, int var3) {
        int var4 = var0;
        int var5 = var2;

        for(int var6 = var2 + var3; var5 < var6; var4 = var4 * 5 + -430675100) {
            int var7 = var1[var5++];
            var7 *= -862048943;
            var7 = Integer.rotateLeft(var7, 15);
            var7 *= 461845907;
            var4 ^= var7;
            var4 = Integer.rotateLeft(var4, 13);
        }

        var4 ^= var3 * 4;
        var4 ^= var4 >>> 16;
        var4 *= -2048144789;
        var4 ^= var4 >>> 13;
        var4 *= -1028477387;
        var4 ^= var4 >>> 16;
        return var4;
    }

    public static int stringHash32(String var0) {
       // return Holder.LANG_ACCESS.getStringHash32(var0); // TODO jdk1.7 方法
      return 0;
    }

    public static int randomHashSeed(Object var0) {
        int var1;
        if (VM.isBooted()) {
            var1 = Hashing.Holder.SEED_MAKER.nextInt();
        } else {
            int[] var2 = new int[]{System.identityHashCode(Hashing.class), System.identityHashCode(var0), System.identityHashCode(Thread.currentThread()), (int)Thread.currentThread().getId(), (int)(System.currentTimeMillis() >>> 2), (int)(System.nanoTime() >>> 5), (int)(Runtime.getRuntime().freeMemory() >>> 4)};
            var1 = murmur3_32(var2);
        }

        return 0 != var1 ? var1 : 1;
    }

    private static class Holder {
        static final Random SEED_MAKER = new Random(Double.doubleToRawLongBits(Math.random()) ^ (long)System.identityHashCode(Hashing.class) ^ System.currentTimeMillis() ^ System.nanoTime() ^ Runtime.getRuntime().freeMemory());
        static final JavaLangAccess LANG_ACCESS = SharedSecrets.getJavaLangAccess();

        private Holder() {
        }

        static {
            if (null == LANG_ACCESS) {
                throw new Error("Shared secrets not initialized");
            }
        }
    }
}
