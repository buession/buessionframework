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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args.timeseries;

import com.buession.redis.utils.ArgStringBuilder;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class TSMRangeArgument {

	private Boolean latest;

	private long[] filterByTimestamps;

	private MinMax filterByValues;

	private Labels labels;

	private Aggregation aggregation;

	private GroupBy groupBy;

	public Boolean isLatest() {
		return getLatest();
	}

	public Boolean getLatest() {
		return latest;
	}

	public TSMRangeArgument latest() {
		return setLatest(true);
	}

	public TSMRangeArgument setLatest(Boolean latest) {
		this.latest = latest;
		return this;
	}

	public long[] getFilterByTimestamps() {
		return filterByTimestamps;
	}

	public TSMRangeArgument setFilterByTimestamps(long[] filterByTimestamps) {
		this.filterByTimestamps = filterByTimestamps;
		return this;
	}

	public MinMax getFilterByValues() {
		return filterByValues;
	}

	public TSMRangeArgument setFilterByValues(MinMax filterByValues) {
		this.filterByValues = filterByValues;
		return this;
	}

	public Labels getLabels() {
		return labels;
	}

	public TSMRangeArgument setLabels(Labels labels) {
		this.labels = labels;
		return this;
	}

	public Aggregation getAggregation() {
		return aggregation;
	}

	public TSMRangeArgument setAggregation(Aggregation aggregation) {
		this.aggregation = aggregation;
		return this;
	}

	public GroupBy getGroupBy() {
		return groupBy;
	}

	public TSMRangeArgument setGroupBy(GroupBy groupBy) {
		this.groupBy = groupBy;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(Boolean.TRUE.equals(getLatest()) ? "LATEST" : "")
				.add("FILTER_BY_TS", getFilterByTimestamps()).add("FILTER_BY_VALUE", getFilterByValues())
				.append(getLabels()).append(getAggregation()).append(getGroupBy()).build();
	}

	public interface Labels {

	}

	public final static class WithLabels implements Labels {

		@Override
		public String toString() {
			return "WITHLABELS";
		}
	}

	public final static class SelectedLabels implements Labels {

		private final String[] values;

		public SelectedLabels(final String[] values) {
			this.values = values;
		}

		public String[] getValues() {
			return values;
		}

		@Override
		public String toString() {
			return ArgStringBuilder.create().append("SELECTED_LABELS").append(getValues()).build();
		}

	}

	public final static class GroupBy {

		private final String label;

		private final String reduce;

		public GroupBy(final String label, final String reduce) {
			this.label = label;
			this.reduce = reduce;
		}

		public String getLabel() {
			return label;
		}

		public String getReduce() {
			return reduce;
		}

		@Override
		public String toString() {
			return ArgStringBuilder.create().add("GROUPBY", getLabel()).add("REDUCE", getReduce()).build();
		}

	}

}
