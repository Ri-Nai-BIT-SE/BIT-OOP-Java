package hw1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class ArithmeticOperations {
    public static void main(String[] args) {
        // 基本类型运算
        primitiveOperations();

        // 大数类型运算
        bigNumberOperations();

        // 大数类型与基本类型运算对比
        compareOperations();

        // 交叉类型运算
        crossTypeOperations();

    }

    private static void primitiveOperations() {
        System.out.println("----- 基本类型运算 -----");

        // int 类型运算
        int a = 10;
        int b = 3;
        System.out.println("[int类型]");
        System.out.println("加法: " + (a + b));    // 13
        System.out.println("减法: " + (a - b));    // 7
        System.out.println("乘法: " + (a * b));    // 30
        System.out.println("除法: " + (a / b));    // 3（整数除法）

        // float 类型运算
        float x = 10.5f;
        float y = 3.2f;
        System.out.println("\n[float类型]");
        System.out.println("加法: " + (x + y));   // 13.7
        System.out.println("减法: " + (x - y));   // 7.3
        System.out.println("乘法: " + (x * y));   // 33.6
        System.out.println("除法: " + (x / y));   // 3.28125
    }

    private static void bigNumberOperations() {
        System.out.println("\n----- 大数类型运算 -----");

        // BigInteger 运算
        BigInteger bigIntA = new BigInteger("100000000000000000000");
        BigInteger bigIntB = new BigInteger("33333333333333333333");
        System.out.println("[BigInteger]");
        System.out.println("加法: " + bigIntA.add(bigIntB));
        System.out.println("减法: " + bigIntA.subtract(bigIntB));
        System.out.println("乘法: " + bigIntA.multiply(bigIntB));
        System.out.println("除法: " + bigIntA.divide(bigIntB));

        // BigDecimal 运算
        BigDecimal bigDecA = new BigDecimal("100.123456789");
        BigDecimal bigDecB = new BigDecimal("3.333333333");
        System.out.println("\n[BigDecimal]");
        System.out.println("加法: " + bigDecA.add(bigDecB));
        System.out.println("减法: " + bigDecA.subtract(bigDecB));
        System.out.println("乘法: " + bigDecA.multiply(bigDecB));
        System.out.println("除法: " + bigDecA.divide(bigDecB, 6, RoundingMode.HALF_UP));
    }

    private static void compareOperations() {
        System.out.println("\n----- 大数类型与基本类型运算对比 -----");

        // BigInteger 运算 vs int类型
        BigInteger bigIntA = new BigInteger("1000000000");
        BigInteger bigIntB = new BigInteger("3");
        int intA = 1_000_000_000;
        int intB = 3;

        System.out.println("[BigInteger vs int对比]");
        System.out.println("加法对比：");
        System.out.println("BigInteger: " + bigIntA.add(bigIntB)); // 1000000003
        System.out.println("int       : " + (intA + intB));       // 1000000003

        // 展示int溢出问题
        BigInteger overflowBigInt = new BigInteger("2147483647").add(BigInteger.ONE);
        int overflowInt = Integer.MAX_VALUE + 1;
        System.out.println("\n溢出对比：");
        System.out.println("BigInteger: " + overflowBigInt); // 2147483648
        System.out.println("int       : " + overflowInt);    // -2147483648

        // BigDecimal 运算 vs float类型
        BigDecimal bigDecA = new BigDecimal("0.1");
        BigDecimal bigDecB = new BigDecimal("0.2");
        float floatA = 0.1f;
        float floatB = 0.2f;

        System.out.println("\n[BigDecimal vs float对比]");
        System.out.println("加法精度对比：");

        // 注意：float类型的精度问题
        System.out.println("BigDecimal: " + bigDecA.add(bigDecB).setScale(10, RoundingMode.HALF_UP)); // 0.3
        System.out.println("float     : " + String.format("%.10f", floatA + floatB)); // 0.3000
        // 累计运算精度对比
        BigDecimal accBigDec = BigDecimal.ZERO;
        float accFloat = 0.0f;
        for (int i = 0; i < 10; i++) {
            accBigDec = accBigDec.add(new BigDecimal("0.1"));
            accFloat += 0.1f;
        }
        System.out.println("\n累加10次0.1：");
        System.out.println("BigDecimal: " + accBigDec.setScale(10, RoundingMode.HALF_UP)); // 1.0
        System.out.println("float     : " + String.format("%.10f", accFloat)); // 0.9999999994
    }

    private static void crossTypeOperations() {
        System.out.println("\n----- 交叉类型运算 -----");

        // 场景1：基本类型之间的交叉运算（int + float）
        int intVal = 10;
        float floatVal = 3.5f;
        System.out.println("[int + float]");
        System.out.println("运算结果类型: " + getType(intVal + floatVal)); // float
        System.out.println("10 + 3.5 = " + (intVal + floatVal)); // 13.5

        // 场景2：基本类型与大数类型的转换运算
        System.out.println("\n[基本类型转大数类型]");
        BigInteger fromInt = BigInteger.valueOf(100); // int转BigInteger
        BigDecimal fromFloat = BigDecimal.valueOf(3.14f); // float转BigDecimal
        System.out.println("BigInteger计算: " + fromInt.add(BigInteger.TEN));
        System.out.println("BigDecimal计算: " + fromFloat.multiply(new BigDecimal("2")));

        // 场景3：大数类型转基本类型（可能丢失精度）
        System.out.println("\n[大数类型转基本类型]");
        BigInteger bigInt = new BigInteger("123456789");
        BigDecimal bigDec = new BigDecimal("3.1415926535");

        // 转换演示（注意精度处理）
        System.out.println("BigInteger转int: " + bigInt.intValue());
        System.out.println("BigDecimal转float: " + bigDec.floatValue());
    }

    private static String getType(Object num) {
        return num.getClass().getSimpleName();
    }
}
