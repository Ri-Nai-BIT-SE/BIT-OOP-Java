import java.io.*;
import java.util.*;

public class KMeans {
    private final int k;  // 聚类数量
    private static final int MAX_ITERATIONS = 100;  // 最大迭代次数
    private static final double CONVERGENCE_THRESHOLD = 0.0001;  // 收敛阈值

    private final List<Point> points;  // 所有数据点
    private final List<Point> centroids;  // 聚类中心
    private List<Point> previousCentroids;  // 上一次迭代的中心点

    public KMeans(int k) {
        this.k = k;
        points = new ArrayList<>();
        centroids = new ArrayList<>();
        previousCentroids = new ArrayList<>();
    }

    // 加载数据
    public void loadData(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                String[] values = line.split(",");
                if (values.length >= 5) {  // 确保有足够的值
                    double[] features = new double[4];
                    for (int i = 0; i < 4; i++) {
                        features[i] = Double.parseDouble(values[i]);
                    }
                    Point point = new Point(features, values[4]);
                    points.add(point);
                }
            }
            for (int i = 0; i < 4; i++) {
                int finalI = i;
                double max = points.stream().mapToDouble(p -> p.getFeatures()[finalI]).max().orElse(0);
                double min = points.stream().mapToDouble(p -> p.getFeatures()[finalI]).min().orElse(0);
                double range = max - min;
                for (Point point : points) {
                    point.setNormalizedFeatures(i, (point.getFeatures()[i] - min) / range);
                }
            }
        }
    }

    // 初始化聚类中心：随机选择k个不同的点作为初始中心
    private void initializeCentroids() {
        Random random = new Random();
        Set<Integer> used = new HashSet<>();
        
        // 清空现有的中心点
        centroids.clear();
        previousCentroids.clear();
        
        // 随机选择k个不同的点作为初始中心
        while (centroids.size() < k) {
            int index = random.nextInt(points.size());
            if (!used.contains(index)) {
                centroids.add(new Point(points.get(index)));
                used.add(index);
            }
        }

        // 初始化上一次的中心点
        for (Point centroid : centroids) {
            previousCentroids.add(new Point(centroid));
        }
    }

    // 计算两个点之间的欧氏距离
    private double calculateDistance(Point p1, Point p2) {
        return p1.distanceTo(p2);
    }

    // 为每个点分配最近的聚类中心
    private void assignClusters() {
        for (Point point : points) {
            double minDistance = Double.MAX_VALUE;
            int newCluster = 0;

            // 找到距离最近的中心点
            for (int i = 0; i < centroids.size(); i++) {
                double distance = calculateDistance(point, centroids.get(i));
                if (distance < minDistance) {
                    minDistance = distance;
                    newCluster = i;
                }
            }
            point.setCluster(newCluster);
        }
    }

    // 更新聚类中心：计算每个簇中所有点的平均值
    private void updateCentroids() {
        // 保存当前中心点到previousCentroids
        previousCentroids.clear();
        for (Point centroid : centroids) {
            previousCentroids.add(new Point(centroid));
        }

        // 对每个簇
        for (int i = 0; i < k; i++) {
            double[] sumOriginal = new double[4];
            double[] sumNormalized = new double[4];
            int count = 0;

            // 计算该簇中所有点的和
            for (Point point : points) {
                if (point.getCluster() == i) {
                    double[] originalFeatures = point.getFeatures();
                    double[] normalizedFeatures = point.getNormalizedFeatures();
                    for (int j = 0; j < originalFeatures.length; j++) {
                        sumOriginal[j] += originalFeatures[j];
                        sumNormalized[j] += normalizedFeatures[j];
                    }
                    count++;
                }
            }

            // 如果该簇非空，更新中心点
            if (count > 0) {
                double[] newOriginalCentroid = new double[4];
                double[] newNormalizedCentroid = new double[4];
                for (int j = 0; j < 4; j++) {
                    newOriginalCentroid[j] = sumOriginal[j] / count;
                    newNormalizedCentroid[j] = sumNormalized[j] / count;
                }
                centroids.get(i).setFeatures(newOriginalCentroid);
                centroids.get(i).setNormalizedFeatures(newNormalizedCentroid);
            }
        }
    }

    // 检查是否收敛
    private boolean hasConverged() {
        double maxMove = 0.0;
        for (int i = 0; i < k; i++) {
            double distance = calculateDistance(centroids.get(i), previousCentroids.get(i));
            maxMove = Math.max(maxMove, distance);
        }
        return maxMove < CONVERGENCE_THRESHOLD;
    }

    // 执行聚类算法
    public void cluster() {
        // 1. 初始化k个聚类中心
        initializeCentroids();
        
        // 2. 迭代直到收敛或达到最大迭代次数
        int iterations = 0;
        boolean converged = false;
        
        while (!converged && iterations < MAX_ITERATIONS) {
            // 3. 将每个点分配到最近的中心点所在的簇
            assignClusters();
            
            // 4. 重新计算每个簇的中心点
            updateCentroids();
            
            // 5. 检查是否收敛
            converged = hasConverged();
            
            iterations++;
        }
        
        System.out.println("聚类迭代次数: " + iterations);
        if (!converged) {
            System.out.println("警告：算法在达到最大迭代次数后仍未收敛");
        }
    }

    // 将结果写入文件
    public void writeResults(String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // 写入表头
            writer.write("sepal length,sepal width,petal length,petal width,cluster label,original label\n");
            // sort points by cluster
            points.sort(Comparator.comparingInt(Point::getCluster));
            // 写入每个点的特征和所属簇
            for (Point point : points) {
                double[] features = point.getFeatures();
                writer.write(String.format("%.1f,%.1f,%.1f,%.1f,%d,%s\n",
                    features[0], features[1], features[2], features[3], 
                    point.getCluster(), point.getOriginalLabel()));
            }
            
            // 写入每个簇的中心点
            writer.write("\n聚类中心点:\n");
            for (int i = 0; i < centroids.size(); i++) {
                double[] features = centroids.get(i).getFeatures();
                writer.write(String.format("簇 %d 中心点: %.2f,%.2f,%.2f,%.2f,%s\n",
                    i, features[0], features[1], features[2], features[3], centroids.get(i).getOriginalLabel()));
            }
        }
    }

    public static void main(String[] args) {
        try {
            // 从命令行参数获取k值，默认为3
            int k = args.length > 0 ? Integer.parseInt(args[0]) : 3;
            
            KMeans kmeans = new KMeans(k);
            kmeans.loadData("resources/iris.data");
            kmeans.cluster();
            kmeans.writeResults("cluster.txt");
            System.out.println("聚类完成，结果已写入cluster.txt文件");
        } catch (IOException e) {
            System.err.println("发生错误: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("K值必须是一个有效的整数");
            e.printStackTrace();
        }
    }
} 
