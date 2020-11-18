package com.example.datastructure.demo.algorithm;

import java.util.Arrays;

/**
 * 归并排序
 *  举个例子有2个有序的数组
 *  1 3 5 7       2 4 6 8 10 12
 *  对他们进行排序
 *  新建1个数组 array
 *   先拿1和2比较 1小于2 将1放置新建的数组array中,然后拿2和3比较 2小于3，然后就将2放置新建的数组中，然后拿3和4比较3小于4，将3也
 *   放置array中，哪4和我比较，4小于5将4也放置array中，然后拿5和6 比较 5小于6，将5放置在array中，拿6和7比较，6小于7，将6也放置在
 *   array中 拿7和8比较7小于8，将7放置array中 然后左边的集合中已经没有数据了，就将右边的搬下来就可以了
 *   最终结果 1 2 3 4 5 6 7 8 10 12
 *   这就是归并排序的核心思想：将2个有序的数组进行互相比较 然后将比较结果放置新数组中，这里提到了有序，如果是2个无序的数组呢
 *    2 4 6 1 4 12 7 6 9 8 10
 *    这个时候就将他们切分成2部分，  2 4 6 1 4 12      7 6 9 8 10
 *    然后再来将剩下的切分成2部分
 *    2 4 6 | 1 4 12      7 6 9 | 8 10
 *    然后接着切
 *    2 4 | 6 | 1 4 | 12       7 6 | 9 | 8 10
 *    一直拆到不能拆为止
 *    2 | 4 | 6 | 1 | 4 | 12     7 | 6 | 9| 8 | 10
 *    还是按照刚才的比较方法 拿2和4进行比较 2小于4，将2放置新加的数组中,2后面没有了，就将4放到新数组中
 *    现在表示就为 2 4|6|1|4|12  7|6|9|8|10
 *    拿2 4 和 6 比较
 *    2小于6放到新数组中，4小于6放到新数组中 数组中没有了，就将6放置到新数组
 *    2 4 6 和1 比较 一样的比较方法
 *    1 2 4 6 和4比较 一样的比较方法
 *    1 2 4 4 6 和12比较
 *    1 2 4 4 6 12  另外一边也按照上面的方法进行比较 得到的结果为 6 7 8 9 10
 *    这样就拿的了2个有序的数组了，然后就可以将他们进行归并排序合并为1个有序的数组了
 *   实际上是沿用了递归的思想
 *
 */
public class MergerSort {

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,4,6,8,10};
        System.out.println(Arrays.toString(arr));
        mergerSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 递归排序
     * @param arr 数组
     * @param low 从哪里开始
     * @param high 从哪里结束
     */
    public static void mergerSort(int[] arr,int low,int high){
        //取中间值
        int middle =(high+low)/2;
        if(low<high){
            //处理左边
            mergerSort(arr, low, middle);
            //处理右边
            mergerSort(arr, middle+1, high);
            //归并
            merger(arr,low,middle,high);
        }

    }


    /**
     * 合并的方法
     * @param arr 给定的数组
     * @param low 从哪里开始
     * @param middle 从哪里分割
     * @param high 从哪里结束
     */
    public static void merger(int[] arr,int low,int middle,int high){
        //创建1个临时的数组用于存储归并后的临时数组 长度怎么来的，列如 2 4 6   6对应的下标是2 2对应的下标是0 拿这个数组的长度就是 2-0+1=3
        int[] temp = new int[high-low+1];
        //用于记录第一个数组中需要遍历的下标
        int i = low;
        //用于记录第二个数组中需要遍历的下标
        int j = middle+1;
        //用于记录在临时数组中存放的下标
        int index = 0;
        //遍历2个数组 取出小的数字，放入临时的数组中
        while (i<=middle && j<=high){
            //第一个数组的数据更小
            if (arr[i] <= arr[j]){
                //把小的数据放入临时数组中
                temp[index] = arr[i];
                //让下标向后移
                i++;
            }else{
                temp[index] = arr[j];
                //下标需要移动
                j++;
            }
            index++;
        }
        //处理多余的数据
        while (j<=high){
            temp[index] = arr[j];
            j++;
            index++;
        }
        while (i<=middle){
            temp[index] = arr[i];
            i++;
            index++;
        }
        //把临时数组中的数据重新存储原数组
        for (int k = 0;k< temp.length;k++){
            arr[k+low] = temp[k];
        }


    }


}
