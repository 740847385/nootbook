package com.mendas.notebook.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.LRUMap;

/**
 * 幂等性判断
 *
 * @author Administrator
 */
@Slf4j
public class IdempotentUtils {
    private IdempotentUtils() {

    }
    /**
     * 根据 LRU(Least Recently Used，最近最少使用)算法淘汰数据的 Map 集合，最大容量 100 个
     */
    private static final LRUMap<String, Integer> REQ_CACHE = new LRUMap<>(100);


    public static boolean judge(String id, Object lockClass) {
        synchronized (lockClass) {
            if (REQ_CACHE.containsKey(id)) {
                // 重复请求
                log.info("请勿重复提交！！！" + id);
                return false;
            }

            REQ_CACHE.put(id, 1);
        }
        return true;
    }
}
