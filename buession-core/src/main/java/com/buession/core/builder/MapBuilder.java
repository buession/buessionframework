package com.buession.core.builder;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Map 构建器
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		Value 类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class MapBuilder<K, V> {

	private final Map<K, V> data;

	private final static Logger logger = LoggerFactory.getLogger(MapBuilder.class);

	/**
	 * 构造函数
	 *
	 * @param data
	 * 		Map 数据
	 */
	private MapBuilder(final Map<K, V> data){
		this.data = data;
	}

	/**
	 * 创建默认为 {@link HashMap} 类型的 {@link MapBuilder} 实例
	 *
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public static <K, V> MapBuilder<K, V> create(){
		return new MapBuilder<>(new HashMap<>(16));
	}

	/**
	 * 创建实例
	 *
	 * @param clazz
	 * 		Map Class
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 * @param <M>
	 * 		Map 类型
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public static <K, V, M extends Map<K, V>> MapBuilder<K, V> create(Class<M> clazz){
		Assert.isNull(clazz, "java.util.Map class cloud not be null.");

		Map<K, V> data;
		try{
			data = clazz.newInstance();
		}catch(InstantiationException e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new HashMap<>(16);
		}catch(IllegalAccessException e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new HashMap<>(16);
		}

		return new MapBuilder<>(data);
	}

	/**
	 * 添加元素
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public MapBuilder<K, V> put(final K key, final V value){
		data.put(key, value);
		return this;
	}

	/**
	 * 批量添加元素
	 *
	 * @param data
	 * 		Map
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public MapBuilder<K, V> putAll(final Map<K, V> data){
		if(Validate.isNotEmpty(data)){
			this.data.putAll(data);
		}
		return this;
	}

	/**
	 * 移除元素
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public MapBuilder<K, V> remove(final K key){
		data.remove(key);
		return this;
	}

	/**
	 * 清空元素
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public MapBuilder<K, V> clear(){
		data.clear();
		return this;
	}

	/**
	 * 构建 Map 数据
	 *
	 * @return Map 数据
	 */
	public Map<K, V> build(){
		return data;
	}

	/**
	 * 创建空 Map
	 *
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Map
	 */
	public static <K, V> Map<K, V> empty(){
		return MapBuilder.<K, V>create().build();
	}

	/**
	 * 创建空 Map
	 *
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Map
	 */
	public static <K, V> Map<K, V> of(){
		return empty();
	}

	/**
	 * 创建单一元素 Map
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 单一元素 Map
	 */
	public static <K, V> Map<K, V> of(final K key, final V value){
		return MapBuilder.<K, V>create().put(key, value).build();
	}

}

