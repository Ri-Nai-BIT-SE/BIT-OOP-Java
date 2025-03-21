以下是实现K-means算法对Iris数据集聚类的Java代码：

```java
import java.io.*;
import java.util.*;

public class KMeansIris {

    private static final int K = 3; // 设定k值为3
    private static final int MAX_ITERATIONS = 100;
    private static final double THRESHOLD = 0.0001;

    public static void main(String[] args) {
        try {
            List<double[]> data = loadIrisData("Iris.data");
            List<double[]> centers = initializeCenters(data, K);
            int[] clusterAssignments = new int[data.size()];
            boolean converged = false;
            int iterations = 0;

            while (!converged && iterations < MAX_ITERATIONS) {
                // 分配样本到最近的簇
                for (int i = 0; i < data.size(); i++) {
                    clusterAssignments[i] = findNearestCenter(data.get(i), centers);
                }

                // 计算新中心点
                double[][] newCenters = new double[K][4];
                int[] clusterCounts = new int[K];
                for (int i = 0; i < data.size(); i++) {
                    int cluster = clusterAssignments[i];
                    clusterCounts[cluster]++;
                    for (int j = 0; j < 4; j++) {
                        newCenters[cluster][j] += data.get(i)[j];
                    }
                }

                // 处理空簇并计算均值
                Random random = new Random();
                for (int j = 0; j < K; j++) {
                    if (clusterCounts[j] == 0) {
                        int randomIndex = random.nextInt(data.size());
                        newCenters[j] = data.get(randomIndex).clone();
                    } else {
                        for (int d = 0; d < 4; d++) {
                            newCenters[j][d] /= clusterCounts[j];
                        }
                    }
                }

                // 检查收敛
                converged = true;
                for (int j = 0; j < K; j++) {
                    double distance = calculateDistance(centers.get(j), newCenters[j]);
                    if (distance > THRESHOLD) {
                        converged = false;
                        break;
                    }
                }
                centers = new ArrayList<>(Arrays.asList(newCenters));
                iterations++;
            }

            // 写入结果文件
            BufferedWriter writer = new BufferedWriter(new FileWriter("cluster.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("Iris.data"));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String newLine = String.format("%s,%s,%s,%s,%d", 
                        parts[0], parts[1], parts[2], parts[3], clusterAssignments[index]);
                    writer.write(newLine);
                    writer.newLine();
                    index++;
                }
            }
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<double[]> loadIrisData(String filename) throws IOException {
        List<double[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                double[] features = new double[4];
                for (int i = 0; i < 4; i++) {
                    features[i] = Double.parseDouble(parts[i].trim());
                }
                data.add(features);
            }
        }
        br.close();
        return data;
    }

    private static List<double[]> initializeCenters(List<double[]> data, int k) {
        Random random = new Random();
        List<double[]> centers = new ArrayList<>();
        Set<Integer> selected = new HashSet<>();
        while (centers.size() < k) {
            int index = random.nextInt(data.size());
            if (!selected.contains(index)) {
                selected.add(index);
                centers.add(data.get(index).clone());
            }
        }
        return centers;
    }

    private static int findNearestCenter(double[] point, List<double[]> centers) {
        double minDistance = Double.MAX_VALUE;
        int cluster = 0;
        for (int i = 0; i < centers.size(); i++) {
            double distance = calculateDistance(point, centers.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                cluster = i;
            }
        }
        return cluster;
    }

    private static double calculateDistance(double[] a, double[] b) {
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }
}
```

代码说明：
1. 数据加载：从`Iris.data`文件中读取前四个属性值
2. 初始化中心点：随机选择k个样本作为初始聚类中心
3. 迭代过程：
   - 将每个样本分配到最近的中心点所在簇
   - 重新计算每个簇的中心点
   - 检查中心点变化是否小于阈值，决定是否停止迭代
4. 结果输出：将原始四个属性值与新的簇标签写入`cluster.txt`文件

注意：
1. 需要确保`Iris.data`文件与程序在同一目录下
2. 由于初始中心点随机选择，每次运行结果可能略有不同
3. 输出文件中的簇标签为0,1,2的整数形式
4. 空簇处理：当簇为空时会重新随机选择样本作为中心点
