package com.buession.core.builder;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue 构建器
 *
 * @param <V>
 * 		Value 类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class QueueBuilder<V> {

	private final Queue<V> data;

	private final static Logger logger = LoggerFactory.getLogger(QueueBuilder.class);

	/**
	 * 构造函数
	 *
	 * @param data
	 * 		Queue 数据
	 */
	private QueueBuilder(final Queue<V> data){
		this.data = data;
	}

	/**
	 * 创建默认为 {@link LinkedList} 类型的 {@link QueueBuilder} 实例
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public static <V> QueueBuilder<V> create(){
		return new QueueBuilder<>(new LinkedList<>());
	}

	/**
	 * 创建实例
	 *
	 * @param clazz
	 * 		Queue Class
	 * @param <V>
	 * 		Value 类型
	 * @param <S>
	 * 		Queue 类型
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public static <V, S extends Queue<V>> QueueBuilder<V> create(Class<S> clazz){
		Assert.isNull(clazz, "java.util.Queue class cloud not be null.");

		Queue<V> data;
		try{
			data = clazz.newInstance();
		}catch(InstantiationException e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new LinkedList<>();
		}catch(IllegalAccessException e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new LinkedList<>();
		}

		return new QueueBuilder<>(data);
	}

	/**
	 * 添加元素
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> add(final V value){
		data.add(value);
		return this;
	}

	/**
	 * 添加元素，仅当 value 不为 null 时
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> addIfPresent(final V value){
		if(value != null){
			data.add(value);
		}
		return this;
	}

	/**
	 * 批量添加元素
	 *
	 * @param data
	 * 		Collection
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> addAll(final Collection<V> data){
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
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> remove(final V value){
		data.remove(value);
		return this;
	}

	/**
	 * 清空元素
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> clear(){
		data.clear();
		return this;
	}

	/**
	 * 构建 Queue 数据
	 *
	 * @return Queue 数据
	 */
	public Queue<V> build(){
		return data;
	}

	/**
	 * 创建空 Queue
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> Queue<V> empty(){
		return QueueBuilder.<V>create().build();
	}

	/**
	 * 创建空 Queue
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> Queue<V> of(){
		return empty();
	}

	/**
	 * 创建单一元素 Queue
	 *
	 * @param value
	 * 		值
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 单一元素 Queue
	 */
	public static <V> Queue<V> of(final V value){
		return QueueBuilder.<V>create().add(value).build();
	}

}

