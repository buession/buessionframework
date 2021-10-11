package com.buession.core.utils;

import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * List 构建器
 *
 * @param <V>
 * 		Value 类型
 *
 * @author Yong.Teng
 * @since 1.3.1
 */
public class ListBuilder<V> {

	private final List<V> data;

	private final static Logger logger = LoggerFactory.getLogger(ListBuilder.class);

	/**
	 * 构造函数
	 */
	private ListBuilder(final List<V> data){
		this.data = data;
	}

	/**
	 * 创建实例
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public static <V> ListBuilder<V> create(){
		return new ListBuilder<>(new ArrayList<>(16));
	}

	/**
	 * 创建实例
	 *
	 * @param <V>
	 * 		Value 类型
	 * @param <S>
	 * 		Set 类型
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public static <V, S extends List<V>> ListBuilder<V> create(Class<S> clazz){
		Assert.isNull(clazz, "java.util.List class cloud not be null.");

		List<V> data;
		try{
			data = clazz.newInstance();
		}catch(InstantiationException e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new ArrayList<>(16);
		}catch(IllegalAccessException e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new ArrayList<>(16);
		}

		return new ListBuilder<>(data);
	}

	/**
	 * 添加元素
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public ListBuilder<V> add(final V value){
		data.add(value);
		return this;
	}

	/**
	 * 批量添加元素
	 *
	 * @param data
	 * 		Map
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public ListBuilder<V> addAll(final Collection<V> data){
		if(Validate.isNotEmpty(data)){
			this.data.addAll(data);
		}
		return this;
	}

	/**
	 * 移除元素
	 *
	 * @param value
	 * 		Value
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public ListBuilder<V> remove(final V value){
		data.remove(value);
		return this;
	}

	/**
	 * 清空元素
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public ListBuilder<V> clear(){
		data.clear();
		return this;
	}

	/**
	 * 构建 Set 数据
	 *
	 * @return Map 数据
	 */
	public List<V> build(){
		return data;
	}

	/**
	 * 创建空 Map
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> List<V> of(){
		return ListBuilder.<V>create().build();
	}

	/**
	 * 创建单一元素 Set
	 *
	 * @param value
	 * 		值
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 单一元素 Set
	 */
	public static <V> List<V> of(final V value){
		return ListBuilder.<V>create().add(value).build();
	}

}

