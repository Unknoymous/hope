import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.datasets import make_blobs
from sklearn.cluster import KMeans

# Generate data
data = make_blobs(n_samples=300, n_features=2, centers=5, cluster_std=1.8, random_state=101)

# Extract data and labels
X, y = data

# Plot the original data
plt.scatter(X[:, 0], X[:, 1], c=y, cmap='brg')

# Perform KMeans clustering
kmeans = KMeans(n_clusters=5)
kmeans.fit(X)

# Extract cluster centers and labels
centers = kmeans.cluster_centers_
labels = kmeans.labels_

# Plot KMeans results and original data
fig, (ax1, ax2) = plt.subplots(1, 2, sharey=True, figsize=(12, 6))
ax1.set_title("KMeans")
ax1.scatter(X[:, 0], X[:, 1], c=labels, cmap='brg')
ax1.scatter(centers[:, 0], centers[:, 1], s=300, c='black', marker='x', label='Centroids')
ax1.legend()

ax2.set_title("Original Data")
ax2.scatter(X[:, 0], X[:, 1], c=y, cmap='brg')

plt.show()
