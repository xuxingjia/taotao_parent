package com.study.demo.taotao_common.common.utils;

import java.util.List;
import java.util.Map;

public interface JedisInterface {

    /**
     * Redis Setnx（SET if Not eXists） 命令在指定的 key 不存在时，为 key 设置指定的值。
     * @param key 健
     * @param value 值
     * @return 设置成功，返回 1 。 设置失败，返回 0 。
     */
    long setnx(String key, String value);

    /**
     * Redis Getrange 命令用于获取存储在指定 key 中字符串的子字符串。字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * @param key 健
     * @param start 开启位置
     * @param end 结束位置
     * @return 截取得到的子字符串。
     */
    String getrange(String key, int start, int end);

    /**
     * Redis Mset 命令用于同时设置一个或多个 key-value 对。
     * @param keyAndValue 多个key value
     * @return 总是返回 OK 。
     */
    String mSet(String... keyAndValue);

    /**
     * Redis Setex 命令为指定的 key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值。
     * @param key 健
     * @param time 过期时间
     * @param value 值
     * @return 总是返回 OK 。
     */
    String setex(String key, int time, String value);

    /**
     * Redis SET 命令用于设置给定 key 的值。如果 key 已经存储其他值， SET 就覆写旧值，且无视类型。
     * @param key 健
     * @param value 值
     * @return 在 Redis 2.6.12 以前版本， SET 命令总是返回 OK 。
     * 从 Redis 2.6.12 版本开始， SET 在设置操作成功完成时，才返回 OK 。
     */
    String set(String key, String value);

    /**
     * Redis Get 命令用于获取指定 key 的值。如果 key 不存在，返回 nil 。如果key 储存的值不是字符串类型，返回一个错误。
     * @param key 值
     * @return  返回 key 的值，如果 key 不存在时，返回 nil。 如果 key 不是字符串类型，那么返回一个错误。
     */
    String get(String key);

    /**
     * Redis Strlen 命令用于获取指定 key 所储存的字符串值的长度。当 key 储存的不是字符串值时，返回一个错误。
     * @param key 值
     * @return 字符串值的长度。 当 key 不存在时，返回 0。
     */
    long strLen(String key);

    /**
     * Redis Hmset 命令用于同时将多个 field-value (字段-值)对设置到哈希表中。
     * @param group_key 组名
     * @param vaule 键值对信息
     * @return 如果命令执行成功，返回 OK 。
     */
    String hmset(String group_key, Map<String, String> vaule);

    /**
     * Redis Hmget 命令用于返回哈希表中，一个或多个给定字段的值。
     * @param group_key 组名
     * @param key 值
     * @return 一个包含多个给定字段关联值的表，表值的排列顺序和指定字段的请求顺序一样。
     */
    List<String> hmget(String group_key, String... key);

    /**
     * Redis Hset 命令用于为哈希表中的字段赋值 。
     * @param group_key 组名
     * @param key 健
     * @param value 值
     * @return 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 1 。 如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 0 。
     */
    long hset(String group_key, String key, String value);

    /**
     * Redis Hgetall 命令用于返回哈希表中，所有的字段和值。
     * @param group_key 组名
     * @return 以列表形式返回哈希表的字段及字段值。 若 key 不存在，返回空列表。
     */
    Map<String,String> hGetAll(String group_key);

    /**
     * Redis Hget 命令用于返回哈希表中指定字段的值。
     * @param group_key 组名
     * @param key 健
     * @return 返回给定字段的值。如果给定的字段或 key 不存在时，返回 nil 。
     */
    String hget(String group_key, String key);

    /**
     * Redis Hexists 命令用于查看哈希表的指定字段是否存在。
     * @param group_key 组名
     * @param key 健
     * @return 如果哈希表含有给定字段，返回 1 。 如果哈希表不含有给定字段，或 key 不存在，返回 0 。
     */
    boolean hExists(String group_key, String key);

    /**
     * Redis Hexists 命令用于查看的指定字段是否存在。
     * @param key 健
     * @return 如果含有给定字段，返回 1 。 如果不含有给定字段，或 key 不存在，返回 0 。
     */
    boolean exists(String key);

    /**
     * Redis DEL 命令用于删除已存在的键。不存在的 key 会被忽略。
     * @param key 健
     * @return  被删除 key 的数量。
     */
    long del(String key);

    /**
     * Redis Hdel 命令用于删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略。
     * @param group_key 组名
     * @param key 值
     * @return 被成功删除字段的数量，不包括被忽略的字段。
     */
    long hDel(String group_key, String key);

    /**
     * 获取过期时间
     * @param key 查询的key
     * @return 返回还有多久过期
     */
    long pTtl(String key);
}