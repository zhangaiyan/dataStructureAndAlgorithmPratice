import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangchaoyue
 * @date 2019/10/31
 */
public class PraticeForArray {

    public static void main(String[] args) {
        CustomizeArrayList array = new CustomizeArrayList(100000000);
        for (int i = 0; i < 100000000; i++) {
            array.add(i);
        }
        long startTime = System.currentTimeMillis();
        array.find(new Random().nextInt(100000000));
        System.out.println(System.currentTimeMillis() - startTime);
    }
}

class CustomizeArrayList<E> {

    /**
     * 列表的容量，默认初始化容量为10
     */
    private static final int DEFAULT_CAPICITY = 10;

    /**
     * 用于空实例的共享数组实例
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 没有初始化的大小的共享数组实例，当第一个元素添加时扩容
     */
    private static final Object[] DEFAULTCAPICITY_EMPTY_ELEMENTDATA = {};

    /**
     * 数组允许分配的最大长度
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 存储elementData的数组对象
     */
    private Object[] elementData;

    /**
     * 列表的大小
     */
    private int size = 0;

    public CustomizeArrayList(int initialCapicity) {
        if (initialCapicity > 0) {
            this.elementData = new Object[initialCapicity];

        } else if (initialCapicity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capicity: " + initialCapicity);
        }
    }

    public CustomizeArrayList() {
        this.elementData = DEFAULTCAPICITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 在必要的时候进行数组的扩容
     *
     * @param minCapacity 要求可存储元素的最小容量
     */
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    /**
     * 计算存储元素所需的最小容量
     *
     * @param elementData
     * @param minCapacity
     * @return
     */
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPICITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPICITY, minCapacity);
        }
        return minCapacity;
    }

    /**
     * 当数组长度超过Integer.Max_Value时不再扩容
     *
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        //由于int是有符号的，当数组的长度超过Integer.Max_Value时，会出现符号位溢出，变为负数
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 数组扩容
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        // oldCapacity >> 1相当于除2操作
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //防止数组长度溢出
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        //数组扩容
        elementData = Arrays.copyOf(elementData, newCapacity);
    }


    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }


    /**
     * 添加元素
     *
     * @param e
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    /**
     * 在指定位置添加元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * 在指定位置删除元素
     *
     * @param index
     */
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 二分法查找有序数组中的某一个元素--只能应用于有序的数据
     *
     * @param searchKey
     */
    public int find(int searchKey) {
        int lowerBound = 0;
        int upperBound = size - 1;
        int curIndex;
        while (true) {
            curIndex = (lowerBound + upperBound) / 2;
            if (elementData[curIndex].equals(searchKey)) {
                //find it
                return curIndex;
            } else if (lowerBound > upperBound) {
                //not find
                return size;
            } else {
                if ((int) elementData[curIndex] < searchKey) {
                    //in upper half
                    lowerBound = curIndex + 1;
                } else {
                    //in lower half
                    upperBound = curIndex - 1;
                }
            }
        }
    }

    /**
     * 添加元素时范围检查
     *
     * @param index
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * 范围检查
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}

