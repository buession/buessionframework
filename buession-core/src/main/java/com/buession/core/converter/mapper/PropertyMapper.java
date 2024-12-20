/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.converter.mapper;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 可用于将值从提供的源映射到目标，拷贝自：org.springframework.boot.context.properties.PropertyMapper
 *
 * @author Yong.Teng
 * @since 2.0.1
 */
public class PropertyMapper {

	private static final Predicate<?> ALWAYS = (t)->true;

	private static final PropertyMapper INSTANCE = new PropertyMapper(null, null);

	private final PropertyMapper parent;

	private final SourceOperator sourceOperator;

	private PropertyMapper(final PropertyMapper parent, final SourceOperator sourceOperator) {
		this.parent = parent;
		this.sourceOperator = sourceOperator;
	}

	/**
	 * Return a new {@link PropertyMapper} instance that applies
	 * {@link Source#whenNonNull() whenNonNull} to every source.
	 *
	 * @return a new property mapper instance
	 */
	public PropertyMapper alwaysApplyingWhenNonNull() {
		return alwaysApplying(this::whenNonNull);
	}

	/**
	 * Return a new {@link PropertyMapper} instance that applies
	 * {@link Source#whenNull() whenNull} to every source.
	 *
	 * @return a new property mapper instance
	 *
	 * @since 2.3.3
	 */
	public PropertyMapper alwaysApplyingWhenNull() {
		return alwaysApplying(this::whenNull);
	}

	/**
	 * Return a new {@link PropertyMapper} instance that applies
	 * {@link Source#whenHasText() whenHasText} to every source.
	 *
	 * @return a new property mapper instance
	 */
	public PropertyMapper alwaysApplyingWhenHasText() {
		return alwaysApplying(this::whenHasText);
	}

	/**
	 * Return a new {@link PropertyMapper} instance that applies
	 * {@link Source#whenNonText() whenHasText} to every source.
	 *
	 * @return a new property mapper instance
	 *
	 * @since 2.3.3
	 */
	public PropertyMapper alwaysApplyingWhenNonText() {
		return alwaysApplying(this::whenNonText);
	}

	/**
	 * Return a new {@link PropertyMapper} instance that applies
	 * {@link Source#whenPositiveNumber() whenHasText} to every source.
	 *
	 * @return a new property mapper instance
	 *
	 * @since 3.0.0
	 */
	public PropertyMapper alwaysApplyingWhenPositiveNumber() {
		return alwaysApplying(this::whenPositiveNumber);
	}

	/**
	 * Return a new {@link PropertyMapper} instance that applies the given
	 * {@link SourceOperator} to every source.
	 *
	 * @param operator
	 * 		the source operator to apply
	 *
	 * @return a new property mapper instance
	 */
	public PropertyMapper alwaysApplying(SourceOperator operator) {
		Assert.isNull(operator, "Operator cloud not be null");
		return new PropertyMapper(this, operator);
	}

	/**
	 * Return a new {@link Source} from the specified value supplier that can be used to
	 * perform the mapping.
	 *
	 * @param <T>
	 * 		the source type
	 * @param supplier
	 * 		the value supplier
	 *
	 * @return a {@link Source} that can be used to complete the mapping
	 *
	 * @see #from(Object)
	 */
	public <T> Source<T> from(Supplier<T> supplier) {
		Assert.isNull(supplier, "Supplier cloud not be null");

		Source<T> source = getSource(supplier);
		if(sourceOperator != null){
			source = sourceOperator.apply(source);
		}

		return source;
	}

	/**
	 * Return a new {@link Source} from the specified value that can be used to perform the mapping.
	 *
	 * @param <T>
	 * 		the source type
	 * @param value
	 * 		the value
	 *
	 * @return a {@link Source} that can be used to complete the mapping
	 */
	public <T> Source<T> from(T value) {
		return from(()->value);
	}

	@SuppressWarnings("unchecked")
	private <T> Source<T> getSource(Supplier<T> supplier) {
		if(parent != null){
			return parent.from(supplier);
		}

		return new Source<>(new CachingSupplier<>(supplier), (Predicate<T>) ALWAYS);
	}

	/**
	 * Return the property mapper.
	 *
	 * @return the property mapper
	 */
	public static PropertyMapper get() {
		return INSTANCE;
	}

	private <T> Source<T> whenNonNull(Source<T> source) {
		return source.whenNonNull();
	}

	private <T> Source<T> whenNull(Source<T> source) {
		return source.whenNull();
	}

	private <T> Source<T> whenHasText(Source<T> source) {
		return source.whenHasText();
	}

	private <T> Source<T> whenNonText(Source<T> source) {
		return source.whenNonText();
	}

	private <T> Source<T> whenPositiveNumber(Source<T> source) {
		return source.whenPositiveNumber();
	}

	private <T> Source<T> whenTrue(Source<T> source) {
		return source.whenTrue();
	}

	private <T> Source<T> whenFalse(Source<T> source) {
		return source.whenFalse();
	}

	/**
	 * An operation that can be applied to a {@link Source}.
	 */
	@FunctionalInterface
	public interface SourceOperator {

		/**
		 * Apply the operation to the given source.
		 *
		 * @param <T>
		 * 		the source type
		 * @param source
		 * 		the source to operate on
		 *
		 * @return the updated source
		 */
		<T> Source<T> apply(Source<T> source);

	}

	/**
	 * A source that is in the process of being mapped.
	 *
	 * @param <T>
	 * 		the source type
	 */
	public final static class Source<T> {

		private final Supplier<T> supplier;

		private final Predicate<T> predicate;

		private Source(final Supplier<T> supplier, final Predicate<T> predicate) {
			Assert.isNull(predicate, "Predicate cloud not be null");
			this.supplier = supplier;
			this.predicate = predicate;
		}

		/**
		 * Return an adapted version of the source with {@link Byte} type.
		 *
		 * @param adapter
		 * 		an adapter to convert the current value to a number.
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return a new adapted source instance
		 *
		 * @since 2.3.1
		 */
		public <R extends Number> Source<Byte> asByte(Function<T, R> adapter) {
			return as(adapter).as(Number::byteValue);
		}

		/**
		 * Return an adapted version of the source with {@link Short} type.
		 *
		 * @param adapter
		 * 		an adapter to convert the current value to a number.
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return a new adapted source instance
		 *
		 * @since 2.3.1
		 */
		public <R extends Number> Source<Short> asShort(Function<T, R> adapter) {
			return as(adapter).as(Number::shortValue);
		}

		/**
		 * Return an adapted version of the source with {@link Integer} type.
		 *
		 * @param adapter
		 * 		an adapter to convert the current value to a number.
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return a new adapted source instance
		 */
		public <R extends Number> Source<Integer> asInt(Function<T, R> adapter) {
			return as(adapter).as(Number::intValue);
		}

		/**
		 * Return an adapted version of the source with {@link Long} type.
		 *
		 * @param adapter
		 * 		an adapter to convert the current value to a number.
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return a new adapted source instance
		 *
		 * @since 2.3.1
		 */
		public <R extends Number> Source<Long> asLong(Function<T, R> adapter) {
			return as(adapter).as(Number::longValue);
		}

		/**
		 * Return an adapted version of the source with {@link Float} type.
		 *
		 * @param adapter
		 * 		an adapter to convert the current value to a number.
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return a new adapted source instance
		 *
		 * @since 2.3.1
		 */
		public <R extends Number> Source<Float> asFloat(Function<T, R> adapter) {
			return as(adapter).as(Number::floatValue);
		}

		/**
		 * Return an adapted version of the source with {@link Double} type.
		 *
		 * @param adapter
		 * 		an adapter to convert the current value to a number.
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return a new adapted source instance
		 *
		 * @since 2.3.1
		 */
		public <R extends Number> Source<Double> asDouble(Function<T, R> adapter) {
			return as(adapter).as(Number::doubleValue);
		}

		/**
		 * Return an adapted version of the source with {@link Boolean} type.
		 *
		 * @param adapter
		 * 		an adapter to convert the current value to a boolean.
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return a new adapted source instance
		 *
		 * @since 2.3.1
		 */
		public <R extends Boolean> Source<Boolean> asBoolean(Function<T, R> adapter) {
			return as(adapter).as(Boolean::booleanValue);
		}

		/**
		 * Return an adapted version of the source changed via the given adapter function.
		 *
		 * @param adapter
		 * 		the adapter to apply
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return a new adapted source instance
		 */
		public <R> Source<R> as(Function<T, R> adapter) {
			Assert.isNull(adapter, "Adapter cloud not be null");

			Supplier<Boolean> test = ()->this.predicate.test(this.supplier.get());
			Predicate<R> predicate = (t)->test.get();
			Supplier<R> supplier = ()->test.get() ? adapter.apply(this.supplier.get()) : null;

			return new Source<>(supplier, predicate);
		}

		/**
		 * Return a filtered version of the source that won't map non-null values or
		 * suppliers that throw a {@link NullPointerException}.
		 *
		 * @return a new filtered source instance
		 */
		public Source<T> whenNonNull() {
			return new Source<>(new NullPointerExceptionSafeSupplier<>(this.supplier), Objects::nonNull);
		}

		/**
		 * Return a filtered version of the source that won't map null values.
		 *
		 * @return a new filtered source instance
		 *
		 * @since 2.3.3
		 */
		public Source<T> whenNull() {
			return new Source<>(new NullPointerExceptionSafeSupplier<>(this.supplier), Objects::isNull);
		}

		/**
		 * Return a filtered version of the source that will only map values that are {@code true}.
		 *
		 * @return a new filtered source instance
		 */
		public Source<T> whenTrue() {
			return when(Boolean.TRUE::equals);
		}

		/**
		 * Return a filtered version of the source that will only map values that are {@code false}.
		 *
		 * @return a new filtered source instance
		 */
		public Source<T> whenFalse() {
			return when(Boolean.FALSE::equals);
		}

		/**
		 * Return a filtered version of the source that will only map values that have a
		 * {@code toString()} containing actual text.
		 *
		 * @return a new filtered source instance
		 */
		public Source<T> whenHasText() {
			return when((value)->Validate.isNotBlank(Objects.toString(value, null)));
		}

		/**
		 * Return a filtered version of the source that will only map values that have a
		 * {@code toString()} containing actual text.
		 *
		 * @return a new filtered source instance
		 *
		 * @since 2.3.3
		 */
		public Source<T> whenNonText() {
			return when((value)->value == null || Validate.isBlank(Objects.toString(value, null)));
		}

		/**
		 * Return a filtered version of the source that will only map values that have a positive number.
		 *
		 * @return a new filtered source instance
		 *
		 * @since 3.0.0
		 */
		public Source<T> whenPositiveNumber() {
			return when((value)->value instanceof Number && ((Number) value).doubleValue() > 0);
		}

		/**
		 * Return a filtered version of the source that will only map values equal to the specified {@code object}.
		 *
		 * @param object
		 * 		the object to match
		 *
		 * @return a new filtered source instance
		 */
		public Source<T> whenEqualTo(Object object) {
			return when(object::equals);
		}

		/**
		 * Return a filtered version of the source that will only map values that are an
		 * instance of the given type.
		 *
		 * @param <R>
		 * 		the target type
		 * @param target
		 * 		the target type to match
		 *
		 * @return a new filtered source instance
		 */
		public <R extends T> Source<R> whenInstanceOf(Class<R> target) {
			return when(target::isInstance).as(target::cast);
		}

		/**
		 * Return a filtered version of the source that won't map values that match the given predicate.
		 *
		 * @param predicate
		 * 		the predicate used to filter values
		 *
		 * @return a new filtered source instance
		 */
		public Source<T> whenNot(Predicate<T> predicate) {
			Assert.isNull(predicate, "Predicate cloud not be null");
			return when(predicate.negate());
		}

		/**
		 * Return a filtered version of the source that won't map values that don't match the given predicate.
		 *
		 * @param predicate
		 * 		the predicate used to filter values
		 *
		 * @return a new filtered source instance
		 */
		public Source<T> when(Predicate<T> predicate) {
			Assert.isNull(predicate, "Predicate cloud not be null");
			return new Source<>(supplier, this.predicate != null ? predicate.and(predicate) : predicate);
		}

		/**
		 * Complete the mapping by passing any non-filtered value to the specified consumer.
		 *
		 * @param consumer
		 * 		the consumer that should accept the value if it's not been
		 * 		filtered
		 */
		public void to(Consumer<T> consumer) {
			Assert.isNull(consumer, "Consumer cloud not be null");

			T value = supplier.get();
			if(predicate.test(value)){
				consumer.accept(value);
			}
		}

		/**
		 * Complete the mapping by creating a new instance from the non-filtered value.
		 *
		 * @param factory
		 * 		the factory used to create the instance
		 * @param <R>
		 * 		the resulting type
		 *
		 * @return the instance
		 *
		 * @throws NoSuchElementException
		 * 		if the value has been filtered
		 */
		public <R> R toInstance(Function<T, R> factory) {
			Assert.isNull(factory, "Factory cloud not be null");

			T value = supplier.get();
			if(predicate.test(value)){
				return factory.apply(value);
			}

			throw new NoSuchElementException("No value present");
		}

	}

	/**
	 * Supplier that caches the value to prevent multiple calls.
	 */
	private final static class CachingSupplier<T> implements Supplier<T> {

		private final Supplier<T> supplier;

		private boolean hasResult;

		private T result;

		CachingSupplier(final Supplier<T> supplier) {
			this.supplier = supplier;
		}

		@Override
		public T get() {
			if(hasResult == false){
				result = this.supplier.get();
				hasResult = true;
			}

			return result;
		}

	}

	/**
	 * Supplier that will catch and ignore any {@link NullPointerException}.
	 */
	private final static class NullPointerExceptionSafeSupplier<T> implements Supplier<T> {

		private final Supplier<T> supplier;

		NullPointerExceptionSafeSupplier(final Supplier<T> supplier) {
			this.supplier = supplier;
		}

		@Override
		public T get() {
			try{
				return this.supplier.get();
			}catch(NullPointerException ex){
				return null;
			}
		}

	}

}
