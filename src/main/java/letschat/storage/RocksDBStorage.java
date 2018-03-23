package letschat.storage;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDBStorage extends Storage<String, String> {
    private RocksDB rocksDB = null;
    private String directory = "";

    public RocksDBStorage(String directory) {
        this.directory = directory;
    }

    public boolean initialize() {
        RocksDB.loadLibrary();
        try {
            Options options = new Options().setCreateIfMissing(true);
            this.rocksDB = RocksDB.open(options, this.directory);
            return true;
        } catch (RocksDBException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean put(String key, String value) {
        try {
            byte[] byteKey = key.getBytes("utf-8");
            byte[] byteValue = value.getBytes("utf-8");

            this.rocksDB.put(byteKey, byteValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(String key) {
        try {
            byte[] byteKey = key.getBytes("utf-8");
            byte[] byteValue = this.rocksDB.get(byteKey);

            if (byteValue != null) {
                this.rocksDB.delete(byteKey);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean contains(String key) {
        try {
            byte[] byteKey = key.getBytes("utf-8");
            byte[] byteValue = this.rocksDB.get(byteKey);

            return byteValue != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String get(String key) {
        try {
            byte[] byteKey = key.getBytes("utf-8");
            byte[] byteValue = this.rocksDB.get(byteKey);

            if (byteValue != null) {
                return new String(byteValue, "utf-8");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
