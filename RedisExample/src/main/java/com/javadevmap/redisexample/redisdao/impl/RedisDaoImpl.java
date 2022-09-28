package com.javadevmap.redisexample.redisdao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

import com.javadevmap.redisexample.redisdao.RedisDao;

@Repository
public class RedisDaoImpl implements RedisDao{
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void testRedisString() {
		redisTemplate.opsForValue().set("String:stringredis", "string redis value");
		String redisString = redisTemplate.opsForValue().get("String:stringredis");
		System.out.println(redisString);
		redisString = redisTemplate.opsForValue().getAndSet("String:stringredis", "string redis value 2");
		System.out.println(redisString);
		redisString = redisTemplate.opsForValue().get("String:stringredis");
		System.out.println(redisString);
		Long size = redisTemplate.opsForValue().size("String:stringredis");
		System.out.println("size is " + size);
		
		redisTemplate.opsForValue().append("String:stringappend", "stringappend%");
		redisString = redisTemplate.opsForValue().get("String:stringappend");
		System.out.println(redisString);
		redisTemplate.opsForValue().append("String:stringappend", "stringappend");
		redisString = redisTemplate.opsForValue().get("String:stringappend");
		System.out.println(redisString);
		
		redisTemplate.opsForValue().set("String:stringredistimeout", "stringredis timeout 10 seconds",10,TimeUnit.SECONDS);
		redisString = redisTemplate.opsForValue().get("String:stringredistimeout");
		System.out.println(redisString);
		
		boolean settype = redisTemplate.opsForValue().setIfAbsent("String:stringredisifabsent", "string redis ifabsent");
		System.out.println("set type is " + settype);
		settype = redisTemplate.opsForValue().setIfAbsent("String:stringredis", "string redis ifabsent");
		System.out.println("set type is " + settype);
		redisTemplate.delete("String:stringredis");
		settype = redisTemplate.opsForValue().setIfAbsent("String:stringredis", "string redis ifabsent");
		System.out.println("set type is " + settype);
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("String:strings1", "strings1");
		map.put("String:strings2", "strings2");
		map.put("String:strings3", "strings3");
		redisTemplate.opsForValue().multiSet(map);
		List<String> keys = new ArrayList<String>();
		keys.add("String:strings1");
		keys.add("String:strings2");
		keys.add("String:strings3");
		List<String> values = redisTemplate.opsForValue().multiGet(keys);
		System.out.println(values.toString());
		
		redisTemplate.opsForValue().increment("String:num", 1);
		redisString = redisTemplate.opsForValue().get("String:num");
		System.out.println("num is " + redisString);
		redisTemplate.opsForValue().increment("String:num", 1);
		redisString = redisTemplate.opsForValue().get("String:num");
		System.out.println("num is " + redisString);
		
		Set<String> stringkeys = redisTemplate.keys("String*");
		redisTemplate.delete(stringkeys);
	}

	@Override
	public void testRedisList() {
		List<String> list = new ArrayList<String>();
		list.add("listString1");
		list.add("listString2");
		list.add("listString3");
		list.add("listString4");
		redisTemplate.opsForList().rightPushAll("List:list", list);
		List<String> retlist = redisTemplate.opsForList().range("List:list", 0, 2);
		System.out.println(retlist.toString());
		retlist = redisTemplate.opsForList().range("List:list", 0, -1);
		System.out.println(retlist.toString());
		
		redisTemplate.opsForList().leftPush("List:list", "listString0");
		redisTemplate.opsForList().rightPushAll("List:list", "listString5","listString6","listString8");
		redisTemplate.opsForList().leftPush("List:list", "listString8", "listString7");
		retlist = redisTemplate.opsForList().range("List:list", 0, -1);
		System.out.println(retlist.toString());
		long size = redisTemplate.opsForList().size("List:list");
		System.out.println("size is " + size);
		
		redisTemplate.opsForList().leftPop("List:list");
		redisTemplate.opsForList().rightPopAndLeftPush("List:list", "List:list");
		retlist = redisTemplate.opsForList().range("List:list", 0, -1);
		System.out.println(retlist.toString());
		
		redisTemplate.opsForList().set("List:list", 0, "listString0");
		retlist = redisTemplate.opsForList().range("List:list", 0, -1);
		System.out.println(retlist.toString());
		redisTemplate.opsForList().trim("List:list", 1, 3);
		retlist = redisTemplate.opsForList().range("List:list", 0, -1);
		System.out.println(retlist.toString());
		
		Set<String> listkeys = redisTemplate.keys("List*");
		redisTemplate.delete(listkeys);
	}

	@Override
	public void testRedisHash() {
		Map<String, String> map = new HashMap<>();
		map.put("key1", "hash value 1");
		map.put("key2", "hash value 2");
		map.put("key3", "hash value 3");
		map.put("key4", "hash value 4");
		redisTemplate.opsForHash().putAll("Hash:map", map);
		System.out.println("size is " + redisTemplate.opsForHash().size("Hash:map"));
		Set<Object> keys = redisTemplate.opsForHash().keys("Hash:map");
		System.out.println(keys.toString());
		List<Object> values = redisTemplate.opsForHash().values("Hash:map");
		System.out.println(values.toString());
		Map<Object, Object> retmap = redisTemplate.opsForHash().entries("Hash:map");
		System.out.println(retmap.toString());
		
		System.out.println("has key4 = " + redisTemplate.opsForHash().hasKey("Hash:map", "key4"));
		String value = (String) redisTemplate.opsForHash().get("Hash:map", "key4");
		System.out.println("key4 value is " + value);
		redisTemplate.opsForHash().delete("Hash:map", "key4");
		retmap = redisTemplate.opsForHash().entries("Hash:map");
		System.out.println(retmap.toString());
		redisTemplate.opsForHash().putIfAbsent("Hash:map", "key4", "hash value 4");
		redisTemplate.opsForHash().put("Hash:map", "key5", "hash value 5");
		retmap = redisTemplate.opsForHash().entries("Hash:map");
		System.out.println(retmap.toString());
		
		Set<String> mapkeys = redisTemplate.keys("Hash*");
		redisTemplate.delete(mapkeys);
	}

	@Override
	public void testRedisSet() {
		redisTemplate.opsForSet().add("Set:set1", "set value 1","set value 2","set value 3");
		Set<String> set = redisTemplate.opsForSet().members("Set:set1");
		System.out.println(set.toString());
		redisTemplate.opsForSet().add("Set:set1", "set value 1");
		redisTemplate.opsForSet().remove("Set:set1", "set value 3");
		set = redisTemplate.opsForSet().members("Set:set1");
		System.out.println(set.toString());
		
		redisTemplate.opsForSet().add("Set:set2", "set value 2","set value 3","set value 4");
		System.out.println("set 2 size is " + redisTemplate.opsForSet().size("Set:set2"));
		System.out.println("set value 4 is member " + redisTemplate.opsForSet().isMember("Set:set2", "set value 4"));
		
		set = redisTemplate.opsForSet().intersect("Set:set1", "Set:set2");
		System.out.println(set.toString());
		
		set = redisTemplate.opsForSet().union("Set:set1", "Set:set2");
		System.out.println(set.toString());
		
		redisTemplate.opsForSet().unionAndStore("Set:set1", "Set:set2","Set:set3");
		set = redisTemplate.opsForSet().members("Set:set3");
		System.out.println(set.toString());
		
		set = redisTemplate.opsForSet().difference("Set:set1", "Set:set2");
		System.out.println(set.toString());
		
		for(int i = 0; i<3; i++) {
			String member = redisTemplate.opsForSet().randomMember("Set:set2");
			System.out.println("random member is " + member);
		}
		
		Set<String> setkeys = redisTemplate.keys("Set*");
		redisTemplate.delete(setkeys);
	}

	@Override
	public void testRedisZSet() {
		redisTemplate.opsForZSet().add("ZSet:set1", "set value 1", 1.0);
		redisTemplate.opsForZSet().add("ZSet:set1", "set value 2", 2.0);
		redisTemplate.opsForZSet().add("ZSet:set1", "set value 4", 4.0);
		Cursor<TypedTuple<String>> cursor = redisTemplate.opsForZSet().scan("ZSet:set1", ScanOptions.NONE);
		while (cursor.hasNext()) {
			TypedTuple<String> item = cursor.next();
			System.out.println(item.getValue());
		}
		redisTemplate.opsForZSet().add("ZSet:set1", "set value 3", 3.0);
		Set<String> retSet = redisTemplate.opsForZSet().range("ZSet:set1", 0, -1);
		System.out.println(retSet.toString());
		
		redisTemplate.opsForZSet().incrementScore("ZSet:set1", "set value 1", 1.0);
		redisTemplate.opsForZSet().incrementScore("ZSet:set1", "set value 2", -1.0);
		retSet = redisTemplate.opsForZSet().range("ZSet:set1", 0, -1);
		System.out.println(retSet.toString());
		
		Long index = redisTemplate.opsForZSet().rank("ZSet:set1", "set value 1");
		System.out.println("set value 1 index is " + index);
		
		retSet = redisTemplate.opsForZSet().rangeByScore("ZSet:set1", 2.0, 4.0);
		System.out.println(retSet.toString());
		
		Long count = redisTemplate.opsForZSet().count("ZSet:set1", 2.0, 4.0);
		System.out.println("count is " + count);
		
		redisTemplate.opsForZSet().removeRangeByScore("ZSet:set1", 3.0, 4.0);
		retSet = redisTemplate.opsForZSet().range("ZSet:set1", 0, -1);
		System.out.println(retSet.toString());
		
		Set<String> zsetkeys = redisTemplate.keys("ZSet*");
		redisTemplate.delete(zsetkeys);
	}

	@Override
	public void testRedisMulti() {
		long starttime = System.currentTimeMillis();
		for(int i=0;i<1000;i++) {
			redisTemplate.opsForValue().set("String:Strings A:" + i, "strings " + i);
		}
		long endtime = System.currentTimeMillis();
		System.out.println("duration is " + (endtime-starttime));
		
		starttime = System.currentTimeMillis();
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0;i<1000;i++) {
			map.put("String:Strings B:" + i, "strings " + i);
		}
		redisTemplate.opsForValue().multiSet(map);
		endtime = System.currentTimeMillis();
		System.out.println("duration is " + (endtime-starttime));
		
		starttime = System.currentTimeMillis();
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.multi();
		for(int i=0;i<1000;i++) {
			redisTemplate.opsForValue().set("String:Strings C:" + i, "strings " + i);
		}
		redisTemplate.exec();
		endtime = System.currentTimeMillis();
		System.out.println("duration is " + (endtime-starttime));
		
		Set<String> stringkeys = redisTemplate.keys("String*");
		redisTemplate.delete(stringkeys);
	}

	@Override
	public void testRedisTransaction() {
		redisTemplate.opsForValue().set("watchvalue", "1");
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.watch("watchvalue");
		redisTemplate.multi();
		redisTemplate.opsForValue().getAndSet("watchvalue", "3");
		List<Object> list = redisTemplate.exec();
		System.out.println(list.toString());
	}
	
}
