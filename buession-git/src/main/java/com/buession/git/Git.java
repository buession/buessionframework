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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.git;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Optional;
import java.util.Set;

/**
 * Git 信息
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
public class Git {

	/**
	 * 分支
	 */
	private String branch;

	/**
	 * Build 信息
	 */
	private Build build;

	/**
	 * 远程信息
	 */
	private Remote remote;

	/**
	 * 本地信息
	 */
	private Local local;

	/**
	 * Commit
	 */
	private Commit commit;

	/**
	 * 仓库脏，为仓库脏时，返回 true；为干净仓库时, 返回 false
	 */
	private Boolean dirty;

	/**
	 * Closest
	 */
	private Closest closest;

	/**
	 * Tags
	 */
	private Set<String> tags;

	/**
	 * 统计
	 */
	private Total total;

	/**
	 * 构造函数
	 */
	public Git(){
	}

	/**
	 * 构造函数
	 *
	 * @param branch
	 * 		分支
	 * @param build
	 * 		Build 信息
	 * @param remote
	 * 		远程信息
	 * @param local
	 * 		本地信息
	 * @param commit
	 * 		Commit
	 * @param dirty
	 * 		仓库脏
	 * @param closest
	 * 		Closest
	 * @param tags
	 * 		Tags
	 * @param total
	 * 		统计
	 */
	public Git(String branch, Build build, Remote remote, Local local, Commit commit, boolean dirty,
			   Closest closest, Set<String> tags, Total total){
		this.branch = branch;
		this.build = build;
		this.remote = remote;
		this.local = local;
		this.commit = commit;
		this.dirty = dirty;
		this.closest = closest;
		this.tags = tags;
		this.total = total;
	}

	/**
	 * 返回分支
	 *
	 * @return 分支
	 */
	public String getBranch(){
		return branch;
	}

	/**
	 * 设置分支
	 *
	 * @param branch
	 * 		分支
	 */
	public void setBranch(String branch){
		this.branch = branch;
	}

	/**
	 * 返回 Build 信息
	 *
	 * @return Build 信息
	 */
	public Build getBuild(){
		return build;
	}

	/**
	 * 设置 Build 信息
	 *
	 * @param build
	 * 		Build 信息
	 */
	public void setBuild(Build build){
		this.build = build;
	}

	/**
	 * 返回远程信息
	 *
	 * @return 远程信息
	 */
	public Remote getRemote(){
		return remote;
	}

	/**
	 * 设置远程信息
	 *
	 * @param remote
	 * 		远程信息
	 */
	public void setRemote(Remote remote){
		this.remote = remote;
	}

	/**
	 * 返回本地信息
	 *
	 * @return 本地信息
	 */
	public Local getLocal(){
		return local;
	}

	/**
	 * 设置本地信息
	 *
	 * @param local
	 * 		本地信息
	 */
	public void setLocal(Local local){
		this.local = local;
	}

	/**
	 * 返回 Commit
	 *
	 * @return Commit
	 */
	public Commit getCommit(){
		return commit;
	}

	/**
	 * 设置 Commit
	 *
	 * @param commit
	 * 		Commit
	 */
	public void setCommit(Commit commit){
		this.commit = commit;
	}

	/**
	 * 仓库脏，为仓库脏时，返回 true；为干净仓库时, 返回 false
	 *
	 * @return 仓库脏，为仓库脏时，返回 true；为干净仓库时, 返回 false
	 */
	public Boolean isDirty(){
		return dirty;
	}

	/**
	 * 设置仓库脏
	 *
	 * @param dirty
	 * 		仓库脏
	 */
	public void setDirty(Boolean dirty){
		this.dirty = dirty;
	}

	/**
	 * 返回 Closest
	 *
	 * @return Closest
	 */
	public Closest getClosest(){
		return closest;
	}

	/**
	 * 设置 Closest
	 *
	 * @param closest
	 * 		Closest
	 */
	public void setClosest(Closest closest){
		this.closest = closest;
	}

	/**
	 * 返回 Tags
	 *
	 * @return Tags
	 */
	public Set<String> getTags(){
		return tags;
	}

	/**
	 * 设置 Tags
	 *
	 * @param tags
	 * 		Tags
	 */
	public void setTags(Set<String> tags){
		this.tags = tags;
	}

	/**
	 * 返回统计
	 *
	 * @return 统计
	 */
	public Total getTotal(){
		return total;
	}

	/**
	 * 设置统计
	 *
	 * @param total
	 * 		统计
	 */
	public void setTotal(Total total){
		this.total = total;
	}

	/**
	 * 返回 Commit Id
	 *
	 * @return Commit Id
	 */
	@JsonIgnore
	public String getCommitId(){
		return getCommit() != null ? getCommit().getId().getValue() : null;
	}

	/**
	 * 返回短 Commit Id
	 *
	 * @return 短 Commit Id
	 */
	@JsonIgnore
	public String getShortCommitId(){
		return getCommit() != null ? getCommit().getId().getAbbrev() : null;
	}

	@Override
	public String toString(){
		final GitInfoBuilder builder = GitInfoBuilder.getInstance()
				.append("branch", branch)
				.append("build", Optional.ofNullable(build).orElse(new Build()))
				.append("remote", Optional.ofNullable(remote).orElse(new Remote()))
				.append("local", Optional.ofNullable(local).orElse(new Local()))
				.append("commit", Optional.ofNullable(commit).orElse(new Commit()))
				.append("dirty", dirty)
				.append("closest", Optional.ofNullable(closest).orElse(new Closest()))
				.append("tags", tags)
				.append("total", Optional.ofNullable(total).orElse(new Total()));

		return builder.build();
	}

}
