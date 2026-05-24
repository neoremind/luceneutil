package perf;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.lucene.store.IndexOutput;

/**
 * A delegating IndexOutput that collects write operation statistics.
 */
public class StatsIndexOutput extends IndexOutput {

  private final IndexOutput delegate;
  private final WriteStats stats = new WriteStats();

  public StatsIndexOutput(IndexOutput delegate) {
    super(delegate.toString(), delegate.getName());
    this.delegate = delegate;
  }

  public WriteStats getStats() { return stats; }

  @Override public void writeByte(byte b) throws IOException {
    stats.writeByteCount.incrementAndGet();
    delegate.writeByte(b);
  }

  @Override public void writeBytes(byte[] b, int offset, int length) throws IOException {
    stats.writeBytesCount.incrementAndGet();
    stats.writeBytesTotalLen.addAndGet(length);
    if (length <= 16) stats.writeBytesLen1_16.incrementAndGet();
    else if (length <= 128) stats.writeBytesLen17_128.incrementAndGet();
    else if (length <= 1024) stats.writeBytesLen129_1024.incrementAndGet();
    else if (length <= 8192) stats.writeBytesLen1025_8192.incrementAndGet();
    else stats.writeBytesLenOver8192.incrementAndGet();
    delegate.writeBytes(b, offset, length);
  }

  @Override public void writeInt(int i) throws IOException {
    stats.writeIntCount.incrementAndGet();
    delegate.writeInt(i);
  }

  @Override public void writeLong(long i) throws IOException {
    stats.writeLongCount.incrementAndGet();
    delegate.writeLong(i);
  }

  @Override public void writeShort(short i) throws IOException {
    stats.writeShortCount.incrementAndGet();
    delegate.writeShort(i);
  }

  @Override public void close() throws IOException { delegate.close(); }
  @Override public long getFilePointer() { return delegate.getFilePointer(); }
  @Override public long getChecksum() throws IOException { return delegate.getChecksum(); }

  public static final class WriteStats {
    public final AtomicLong writeByteCount = new AtomicLong();
    public final AtomicLong writeBytesCount = new AtomicLong();
    public final AtomicLong writeBytesTotalLen = new AtomicLong();
    public final AtomicLong writeIntCount = new AtomicLong();
    public final AtomicLong writeLongCount = new AtomicLong();
    public final AtomicLong writeShortCount = new AtomicLong();
    public final AtomicLong writeBytesLen1_16 = new AtomicLong();
    public final AtomicLong writeBytesLen17_128 = new AtomicLong();
    public final AtomicLong writeBytesLen129_1024 = new AtomicLong();
    public final AtomicLong writeBytesLen1025_8192 = new AtomicLong();
    public final AtomicLong writeBytesLenOver8192 = new AtomicLong();

    public void merge(WriteStats o) {
      writeByteCount.addAndGet(o.writeByteCount.get());
      writeBytesCount.addAndGet(o.writeBytesCount.get());
      writeBytesTotalLen.addAndGet(o.writeBytesTotalLen.get());
      writeIntCount.addAndGet(o.writeIntCount.get());
      writeLongCount.addAndGet(o.writeLongCount.get());
      writeShortCount.addAndGet(o.writeShortCount.get());
      writeBytesLen1_16.addAndGet(o.writeBytesLen1_16.get());
      writeBytesLen17_128.addAndGet(o.writeBytesLen17_128.get());
      writeBytesLen129_1024.addAndGet(o.writeBytesLen129_1024.get());
      writeBytesLen1025_8192.addAndGet(o.writeBytesLen1025_8192.get());
      writeBytesLenOver8192.addAndGet(o.writeBytesLenOver8192.get());
    }

    public long totalCalls() {
      return writeByteCount.get() + writeBytesCount.get() + writeIntCount.get()
          + writeLongCount.get() + writeShortCount.get();
    }

    public long totalBytes() {
      return writeByteCount.get() + writeBytesTotalLen.get()
          + writeIntCount.get() * 4L + writeLongCount.get() * 8L + writeShortCount.get() * 2L;
    }

    @Override public String toString() {
      long total = totalCalls();
      if (total == 0) return "WriteStats: no operations";
      long totalB = totalBytes();
      return String.format(
          "WriteStats: totalOps=%,d totalBytes=%,d%n"
              + "  writeByte:  %,10d (%5.1f%% calls, %5.1f%% bytes)%n"
              + "  writeBytes: %,10d (%5.1f%% calls, %5.1f%% bytes, avgLen=%.0f)%n"
              + "  writeInt:   %,10d (%5.1f%% calls, %5.1f%% bytes)%n"
              + "  writeLong:  %,10d (%5.1f%% calls, %5.1f%% bytes)%n"
              + "  writeShort: %,10d (%5.1f%% calls, %5.1f%% bytes)%n"
              + "  writeBytes size distribution:%n"
              + "    [1-16]:    %,d%n"
              + "    [17-128]:  %,d%n"
              + "    [129-1K]:  %,d%n"
              + "    [1K-8K]:   %,d%n"
              + "    [>8K]:     %,d",
          total, totalB,
          writeByteCount.get(), pct(writeByteCount.get(), total), pct(writeByteCount.get(), totalB),
          writeBytesCount.get(), pct(writeBytesCount.get(), total), pct(writeBytesTotalLen.get(), totalB),
          writeBytesCount.get() > 0 ? (double) writeBytesTotalLen.get() / writeBytesCount.get() : 0,
          writeIntCount.get(), pct(writeIntCount.get(), total), pct(writeIntCount.get() * 4L, totalB),
          writeLongCount.get(), pct(writeLongCount.get(), total), pct(writeLongCount.get() * 8L, totalB),
          writeShortCount.get(), pct(writeShortCount.get(), total), pct(writeShortCount.get() * 2L, totalB),
          writeBytesLen1_16.get(), writeBytesLen17_128.get(), writeBytesLen129_1024.get(),
          writeBytesLen1025_8192.get(), writeBytesLenOver8192.get());
    }

    private static double pct(long part, long whole) {
      return whole == 0 ? 0 : 100.0 * part / whole;
    }
  }
}
