## Build 

change directory to your repo, then run

```
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/ && ./gradlew assemble
```

## OpenStreetMaps

### main branch

/usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/bin/javac -cp /disk1/lucene/repo/lucene/lucene/core/build/classes/java/main:/disk1/lucene/repo/lucene/lucene/sandbox/build/classes/java/main /disk1/lucene/repo/luceneutil/src/main/perf/IndexAndSearchOpenStreetMaps1D.java

rm -f /disk1/lucene/bkdtest1d/* && /usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/bin/java -cp /disk1/lucene/repo/lucene/lucene/core/build/classes/java/main:/disk1/lucene/repo/lucene/lucene/core/build/resources/main:/disk1/lucene/repo/lucene/lucene/sandbox/build/classes/java/main:/disk1/lucene/repo/luceneutil/src/main/perf IndexAndSearchOpenStreetMaps1D

### Optimized PR branch

/usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/bin/javac -cp /disk1/lucene/repo/lucene-me/lucene/core/build/classes/java/main:/disk1/lucene/repo/lucene-me/lucene/sandbox/build/classes/java/main /disk1/lucene/repo/luceneutil/src/main/perf/IndexAndSearchOpenStreetMaps1D.java

rm -f /disk1/lucene/bkdtest1d/* && /usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/bin/java -cp /disk1/lucene/repo/lucene-me/lucene/core/build/classes/java/main:/disk1/lucene/repo/lucene-me/lucene/core/build/resources/main:/disk1/lucene/repo/lucene-me/lucene/sandbox/build/classes/java/main:/disk1/lucene/repo/luceneutil/src/main/perf IndexAndSearchOpenStreetMaps1D


## TPC-H LINEITEM

### main branch

/usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/bin/javac -cp /disk1/lucene/repo/lucene/lucene/core/build/classes/java/main:/disk1/lucene/repo/lucene/lucene/sandbox/build/classes/java/main /disk1/lucene/repo/luceneutil/src/main/perf/IndexAndSearchTpcHLineItem.java

rm -f /disk1/lucene/bkd-tpch/* && /usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/bin/java -cp /disk1/lucene/repo/lucene/lucene/core/build/classes/java/main:/disk1/lucene/repo/lucene/lucene/core/build/resources/main:/disk1/lucene/repo/lucene/lucene/sandbox/build/classes/java/main:/disk1/lucene/repo/luceneutil/src/main/perf IndexAndSearchTpcHLineItem

### Optimized PR branch

/usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/bin/javac -cp /disk1/lucene/repo/lucene-me/lucene/core/build/classes/java/main:/disk1/lucene/repo/lucene-me/lucene/sandbox/build/classes/java/main /disk1/lucene/repo/luceneutil/src/main/perf/IndexAndSearchTpcHLineItem.java

 rm -f /disk1/lucene/bkd-tpch/* && /usr/lib/jvm/java-11-openjdk-11.0.11.0.9-1.1.al7.x86_64/bin/java -cp /disk1/lucene/repo/lucene-me/lucene/core/build/classes/java/main:/disk1/lucene/repo/lucene-me/lucene/core/build/resources/main:/disk1/lucene/repo/lucene-me/lucene/sandbox/build/classes/java/main:/disk1/lucene/repo/luceneutil/src/main/perf IndexAndSearchTpcHLineItem