package cn.daenx.myadmin.framework.common.utils;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 加锁Util类
 *
 * @author lijiayu
 * @date 2015年8月20日
 */
public class LockUtil {

    public static final String USER_LOCK = "User_Lock_";

    // 用于实现锁池效果
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(30);

    private LockUtil() {
    }

    private static class Single {

        private final static LockUtil lockutil = new LockUtil();
    }

    public static LockUtil getInstance() {
        return Single.lockutil;
    }

    private static Map<String, ReentrantLock> locks = new ConcurrentHashMap<String, ReentrantLock>();

    /**
     * 获取对象锁,每个字符串在缓存中有一个锁
     *
     * @param lockKey 唯一字符串
     * @return
     */
    private synchronized ReentrantLock getLock(String lockKey) {
        ReentrantLock lock = locks.get(USER_LOCK + lockKey);
        if (lock == null) {
            lock = new ReentrantLock();
            locks.put(USER_LOCK + lockKey, lock);
        }
        return lock;
    }

    /**
     * 加锁
     *
     * @param lockKey 唯一字符串
     */
    public void lock(String lockKey) {
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getLock(lockKey).lock();
    }

    /**
     * 解锁
     *
     * @param lockKey 唯一字符串
     */
    public void unLock(String lockKey) {
        getLock(lockKey).unlock();
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除锁缓存
     */
    public void cleanCacheLock(String className) {

        ReentrantLock lock = locks.get(USER_LOCK + className);

        if (lock == null) {

            return;
        }

        if (lock.isHeldByCurrentThread()) {
            lock.unlock();

        }

        if (!lock.hasQueuedThreads()) {
            locks.remove(USER_LOCK + className);
        }
    }
}