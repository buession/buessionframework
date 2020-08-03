/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.oss.model;

import com.buession.lang.Constants;
import com.buession.oss.core.BucketAcl;
import com.buession.oss.core.DataRedundancyType;
import com.buession.oss.core.Owner;
import com.buession.oss.core.StorageClass;

import java.io.Serializable;
import java.util.Date;

/**
 * Bucket
 *
 * @author Yong.Teng
 */
public class Bucket implements Serializable {

	private final static long serialVersionUID = 135856040687129744L;

	private String name;

	private Owner owner;

	private Date createdAt;

	private String description;

	private BucketAcl acl;

	private StorageClass storageClass;

	private DataRedundancyType dataRedundancyType;

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Owner getOwner(){
		return owner;
	}

	public void setOwner(Owner owner){
		this.owner = owner;
	}

	public Date getCreatedAt(){
		return createdAt;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public BucketAcl getAcl(){
		return acl;
	}

	public void setAcl(BucketAcl acl){
		this.acl = acl;
	}

	public StorageClass getStorageClass(){
		return storageClass;
	}

	public void setStorageClass(StorageClass storageClass){
		this.storageClass = storageClass;
	}

	public DataRedundancyType getDataRedundancyType(){
		return dataRedundancyType;
	}

	public void setDataRedundancyType(DataRedundancyType dataRedundancyType){
		this.dataRedundancyType = dataRedundancyType;
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder(128);

		sb.append("name: ").append(name).append(", ");
		sb.append("owner: ").append(owner == null ? Constants.EMPTY_STRING : owner.toString()).append(", ");
		sb.append("createdAt: ").append(createdAt).append(", ");
		sb.append("description: ").append(description).append(", ");
		sb.append("acl: ").append(acl).append(", ");
		sb.append("storageClass: ").append(storageClass).append(", ");
		sb.append("dataRedundancyType: ").append(dataRedundancyType).append(", ");

		return sb.toString();
	}

}
