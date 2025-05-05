1、使用Lambda表达式比较器Comparator给List对象排序，分别按Name、Age（倒序）、Grade排序。List对象内容如下表：
ID	Name	Age	Grade
1	ZhangSan	28	98
2	LiSi	21	100
3	KangKang	27	89
4	LiMing	19	92
5	WangGang	22	66
6	ZhaoXin	24	85
7	LiuWei	20	78
8	BaiZhanTang	16	99

2、打开中的lunch.java文件，一次读取其中的一行，令每行形成一个String对象。然后利用java.util.Comparator接口重新定义String对象间的比较方法：将每个String中的小写字母转为大写后再进行比较。使用该比较法对这些String进行排序，按从大到小的顺序存入一个LinkedList。最后将LinkedList中的String按存入的相反顺序输出到另一个文件inverse.txt中。
