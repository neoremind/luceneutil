
BASE_DIR = '/path/to/workspace'
BENCH_BASE_DIR = '/path/to/workspace/fork_luceneutil'

import os
os.environ["JAVA_HOME"] = "/path/to/java_home"
os.environ["PATH"] = os.environ["JAVA_HOME"] + "/bin:" + os.environ.get("PATH", "")
_java_bin = "/path/to/java_home/bin/"
JAVA_EXE = f"{_java_bin}java"
JAVA_COMMAND = "%s -server -Xms16g -Xmx16g --add-modules jdk.incubator.vector -XX:+HeapDumpOnOutOfMemoryError -XX:+UseParallelGC" % JAVA_EXE

# Cohere v3 vectors (1024d, normalized, DOT_PRODUCT) — matches nightly site since Dec 2025
# Using first1M subset downloaded by initial_setup.py (1M doc vectors)
VECTORS_DOCS_FILE = "%s/data/cohere-v3-wikipedia-en-scattered-1024d.docs.first1M.vec" % BASE_DIR
VECTORS_DIMENSIONS = 1024
VECTORS_TYPE = "FLOAT32"
