# 数组

## 数组的特点
1. 数组是相同数据类型的元素的集合。
2. 数组中的各元素是有先后顺序的。它们在内存中按照这个顺序连续存放在一起。
3. 每个数组元素用整个数组的名字和它自己在数组中的位置表达（此位置被叫做下标）。

Java中的数组是**对象**，因此属于引用类型，数组对象需要使用new关键字来创建。

## 数组的声明

```java
// 声明数组
int[] a = new int[10];
int a[] = new int[10];
// 在声明数组的同时进行初始化
int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
// 匿名数组
new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
```
## 数组的使用

### 数组的引用

所有的数组都有一个属性`length`，这个属性存储了数组元素的个数。
```java
a.length
```
#### 数组下标越界

Java系统能自动检查是否有数组下标越界的情况

如果在程序中使用`intArray[10]`，就会发生数组下标越界，此时Java系统会自动终止当前的流程，并产生一个名为`ArrayIndexOutOfBoundsException`的异常，通知使用者出现了数组下标越界。

避免越界发生的有效方法是利用`length`属性作为数组下标的上界。

### 不定长参数

不定长参数是Java 1.5引入的一个新特性，允许方法接受可变数量的参数。

```java
public void print(String... args) {
    for (String arg : args) {
        System.out.println(arg);
    }
}
```
### 多维数组

多维数组是数组中的数组，即数组的数组。

```java
int[][] a = new int[3][4];
int[][] array = new int[][]{{1,2,3},{4,5,6}};
```

## util.Arrays
| 方法 | 描述 | 示例|
| --- | --- | --- |
| `Arrays.copyOf(array, length)` | 拷贝数组 | `int [] newArray = Arrays.copyOf(array, length)` |
| `Arrays.sort(array)` | 排序数组 | `Arrays.sort(array)` |
| `Arrays.toString(array)` | 将数组转换为字符串 | `String str = Arrays.toString(array)` |
| `Arrays.asList(array)` | 将数组转换为列表 | `List<Integer> list = Arrays.asList(array); ArrayList<Integer> arrayList = new ArrayList<>(list);` |


## util.ArrayList

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);
list.add(3);
```

### 数组转列表

```java
ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(array));
```

### 列表转数组

```java
int[] array = arrayList.stream().mapToInt(i -> i).toArray();
```



## for / for-each

### for

```java

for (int i = 0; i < array.length; i++) {
    System.out.println(array[i]);
}
```

### for-each

```java
for (int x : array) {
    System.out.println(x);
}
```

### stream

| 方法 | 描述 | 示例 |   
| --- | --- | --- |
| `forEach` | 遍历数组 | `Arrays.stream(array).forEach(System.out::println);` <=> `Arrays.asList(array).forEach(System.out::println);` |
| `map` | 映射数组 | `Arrays.stream(array).map(i -> i * 2).toArray();` |
| `filter` | 过滤数组 | `Arrays.stream(array).filter(i -> i % 2 == 0).toArray();` |
| `reduce` | 归约数组 | `Arrays.stream(array).reduce((a, b) -> a + b).get();` | 
| `collect` | 收集数组 | `Arrays.stream(array).collect(Collectors.toList());` |
