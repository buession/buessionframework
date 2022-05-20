package com.buession.core.builder;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Set 构建器
 *
 * @param <V>
 * 		Value 类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class SetBuilder<V> {

	private final Set<V> data;

	private final static Logger logger = LoggerFactory.getLogger(SetBuilder.class);

	/**
	 * 构造函数
	 *
	 * @param data
	 * 		Set 数据
	 */
	private SetBuilder(final Set<V> data){
		this.data = data;
	}

	/**
	 * 创建默认为 {@link HashSet} 类型的 {@link SetBuilder} 实例
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link SetBuilder} 实例
	 */
	public static <V> SetBuilder<V> create(){
		return new SetBuilder<>(new HashSet<>(16));
	}

	/**
	 * 创建实例
	 *
	 * @param clazz
	 * 		Set Class
	 * @param <V>
	 * 		Value 类型
	 * @param <S>
	 * 		Set 类型
	 *
	 * @return {@link SetBuilder} 实例
	 */
	public static <V, S extends Set<V>> SetBuilder<V> create(Class<S> clazz){
		Assert.isNull(clazz, "java.util.Set class cloud not be null.");

		Set<V> data;
		try{
			data = clazz.newInstance();
		}catch(InstantiationException e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new HashSet<>(16);
		}catch(IllegalAccessException e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new HashSet<>(16);
		}

		return new SetBuilder<>(data);
	}

	/**
	 * 添加元素
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link SetBuilder} 实例
	 */
	public SetBuilder<V> add(final V value){
		data.add(value);
		return this;
	}

	/**
	 * 添加元素，仅当 value 不为 null 时
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link SetBuilder} 实例
	 */
	public SetBuilder<V> addIfPresent(final V value){
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
	 * @return {@link SetBuilder} 实例
	 */
	public SetBuilder<V> addAll(final Collection<V> data){
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
	 * @return {@link SetBuilder} 实例
	 */
	public SetBuilder<V> remove(final V value){
		data.remove(value);
		return this;
	}

	/**
	 * 清空元素
	 *
	 * @return {@link SetBuilder} 实例
	 */
	public SetBuilder<V> clear(){
		data.clear();
		return this;
	}

	/**
	 * 构建 Set 数据
	 *
	 * @return Set 数据
	 */
	public Set<V> build(){
		return data;
	}

	/**
	 * 创建空 Set
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> Set<V> empty(){
		return SetBuilder.<V>create().build();
	}

	/**
	 * 创建空 Set
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> Set<V> of(){
		return empty();
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
	public static <V> Set<V> of(final V value){
		return SetBuilder.<V>create().add(value).build();
	}

}

