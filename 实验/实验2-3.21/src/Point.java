public class Point {
    private double[] features;
    private double[] normalizedFeatures;
    private int cluster;
    private String originalLabel;

    public Point(double[] features, String originalLabel) {
        this.features = features.clone();
        this.normalizedFeatures = features.clone();
        this.cluster = -1;
        this.originalLabel = originalLabel;
    }

    public Point(double[] features, String originalLabel, double[] normalizedFeatures) {
        this.features = features.clone();
        this.normalizedFeatures = normalizedFeatures.clone();
        this.originalLabel = originalLabel;
        this.cluster = -1;
    }
    public Point(Point other) {
        this.features = other.features.clone();
        this.normalizedFeatures = other.normalizedFeatures.clone();
        this.originalLabel = other.originalLabel;
        this.cluster = other.cluster;
    }

    public double[] getFeatures() {
        return features;
    }

    public void setFeatures(double[] features) {
        this.features = features.clone();
    }

    public double[] getNormalizedFeatures() {
        return normalizedFeatures;
    }

    public void setNormalizedFeatures(double[] normalizedFeatures) {
        this.normalizedFeatures = normalizedFeatures.clone();
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public String getOriginalLabel() {
        return originalLabel;
    }

    public void setOriginalLabel(String originalLabel) {
        this.originalLabel = originalLabel;
    }

    public double distanceTo(Point other) {
        double sum = 0.0;
        for (int i = 0; i < normalizedFeatures.length; i++) {
            sum += Math.pow(normalizedFeatures[i] - other.normalizedFeatures[i], 2);
        }
        return Math.sqrt(sum);
    }

    public void setNormalizedFeatures(int i, double v) {
        normalizedFeatures[i] = v;
    }
} 
