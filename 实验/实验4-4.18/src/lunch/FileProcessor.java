package lunch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {
    public static void main(String[] args) {
        // 获取当前目录
        Path currentPath = Paths.get("").toAbsolutePath();
        // 构建Lunch.java的完整路径
        String lunchFilePath = "src/lunch/Lunch.java";
        
        // 读取文件内容并存储到 List 中
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(lunchFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            System.out.println("成功读取文件: " + lunchFilePath);
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
            System.err.println("当前工作目录: " + currentPath);
            return; // 如果文件读取失败，终止程序
        }

        // 使用自定义 Comparator 比较字符串（将小写字母转为大写后比较）
        Comparator<String> customComparator = (s1, s2) -> {
            String upperS1 = s1.toUpperCase();
            String upperS2 = s2.toUpperCase();
            return upperS2.compareTo(upperS1); // 按从大到小排序
        };

        // 排序
        lines.sort(customComparator);

        // 将排序后的结果存入 LinkedList
        LinkedList<String> sortedLines = new LinkedList<>(lines);

        // 将 LinkedList 中的字符串按相反顺序输出到 inverse.txt 文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inverse.txt"))) {
            for (int i = sortedLines.size() - 1; i >= 0; i--) {
                writer.write(sortedLines.get(i));
                writer.newLine();
            }
            System.out.println("逆序输出完成，已保存到 inverse.txt 文件中。");
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
        }
    }
}
