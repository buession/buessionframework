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
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.dao;

import com.buession.core.Pagination;
import com.buession.core.exception.OperationException;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Yong.Teng
 */
public abstract class AbstractMongoDBDao<P, E> extends AbstractDao<P, E> {

    public final static String PRIMARY_FIELD = "_id";

    @Resource
    private MongoTemplate masterMongoTemplate;

    @Resource
    private List<MongoTemplate> slaveMongoTemplates;

    private final static Logger logger = LoggerFactory.getLogger(AbstractMongoDBDao.class);

    public MongoTemplate getMasterMongoTemplate(){
        return masterMongoTemplate;
    }

    public void setMasterMongoTemplate(MongoTemplate masterMongoTemplate){
        this.masterMongoTemplate = masterMongoTemplate;
    }

    public List<MongoTemplate> getSlaveMongoTemplates(){
        return slaveMongoTemplates;
    }

    public void setSlaveMongoTemplates(List<MongoTemplate> slaveMongoTemplates){
        this.slaveMongoTemplates = slaveMongoTemplates;
    }

    @Override
    public int insert(E e){
        try{
            masterMongoTemplate.insert(e);
            return 1;
        }catch(Exception ex){
            return 0;
        }
    }

    @Override
    public int replace(E e){
        return 0;
    }

    @Override
    public int update(E e, Map<String, Object> conditions){
        final Query query = new Query();
        final Criteria criteria = buildCriteria(conditions);
        final Update update = new Update();

        if(criteria != null){
            query.addCriteria(criteria);
        }

        if(e != null){
            BasicDBObject data = toDbObject(e);

            for(String key : data.keySet()){
                update.set(key, data.get(key));
            }
        }

        UpdateResult writeResult = masterMongoTemplate.updateFirst(query, update, getStatement());

        return (int) writeResult.getModifiedCount();
    }

    @Override
    public int updateByPrimary(P primary, E e){
        final Query query = new Query();
        final Criteria criteria = Criteria.where(PRIMARY_FIELD).is(primary);
        final Update update = new Update();

        query.addCriteria(criteria);

        if(e != null){
            BasicDBObject data = toDbObject(e);

            for(String key : data.keySet()){
                update.set(key, data.get(key));
            }
        }

        UpdateResult writeResult = masterMongoTemplate.updateFirst(query, update, getStatement());

        return (int) writeResult.getModifiedCount();
    }

    @Override
    public E getByPrimary(P primary){
        final Query query = new Query();
        final Criteria criteria = Criteria.where(PRIMARY_FIELD).is(primary);

        query.addCriteria(criteria);

        try{
            return getSlaveMongoTemplate().findOne(query, getStatement());
        }catch(OperationException e){
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public E selectOne(Map<String, Object> conditions, int offset, Map<String, Order> orders){
        final Query query = new Query();
        final Criteria criteria = buildCriteria(conditions);

        if(criteria != null){
            query.addCriteria(criteria);
        }

        query.skip(offset);

        try{
            return getSlaveMongoTemplate().findOne(query, getStatement());
        }catch(OperationException e){
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public List<E> select(Map<String, Object> conditions, Map<String, Order> orders){
        final Query query = new Query();
        final Criteria criteria = buildCriteria(conditions);

        if(criteria != null){
            query.addCriteria(criteria);
        }

        try{
            return getSlaveMongoTemplate().find(query, getStatement());
        }catch(OperationException e){
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public List<E> select(Map<String, Object> conditions, int offset, int size, Map<String, Order> orders){
        final Query query = new Query();
        final Criteria criteria = buildCriteria(conditions);

        if(criteria != null){
            query.addCriteria(criteria);
        }

        return select(query, offset, size, orders);
    }

    public List<E> select(Query query, int offset, int size, Map<String, Order> orders){
        Assert.isNull(query, "Query Argument could not be null");

        if(Validate.isEmpty(orders) == false){
            final List<Sort.Order> sortOrders = new ArrayList<>(orders.size());

            orders.forEach((field, order)->{
                if(order == Order.ASC){
                    sortOrders.add(new Sort.Order(Sort.Direction.ASC, field));
                }else if(order == Order.DESC){
                    sortOrders.add(new Sort.Order(Sort.Direction.DESC, field));
                }
            });

            query.with(Sort.by(sortOrders));
        }

        query.limit(size);
        query.skip(offset);

        try{
            return getSlaveMongoTemplate().find(query, getStatement());
        }catch(OperationException e){
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public Pagination<E> paging(Map<String, Object> conditions, int page, int pagesize, Map<String, Order> orders){
        final Query query = new Query();
        final Criteria criteria = buildCriteria(conditions);

        if(criteria != null){
            query.addCriteria(criteria);
        }

        return paging(query, page, pagesize, orders);
    }

    public Pagination<E> paging(Query query, int page, int pagesize, Map<String, Order> orders){
        if(page < 1){
            throw new IllegalArgumentException("Page argument value must be positive integer");
        }

        if(pagesize < 1){
            throw new IllegalArgumentException("Pagesize argument value must be positive integer");
        }

        long totalRecords = count(query);

        com.buession.dao.Pagination<E> pagination = new com.buession.dao.Pagination<>(page, pagesize, totalRecords);

        if(totalRecords > 0){
            List<E> result = select(query, pagination.getOffset(), pagination.getPagesize(), orders);
            pagination.setData(result);
        }

        return pagination;
    }

    @Override
    public List<E> getAll(){
        try{
            return getSlaveMongoTemplate().findAll(getStatement());
        }catch(OperationException e){
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public long count(Map<String, Object> conditions){
        final Query query = new Query();
        final Criteria criteria = buildCriteria(conditions);

        if(criteria != null){
            query.addCriteria(criteria);
        }

        return count(query);
    }

    public long count(Query query){
        Assert.isNull(query, "Query Argument could not be null");

        try{
            return getSlaveMongoTemplate().count(query, getStatement());
        }catch(OperationException e){
            logger.error(e.getMessage());
        }

        return 0;
    }

    @Override
    public int delete(Map<String, Object> conditions){
        final Query query = new Query();
        final Criteria criteria = buildCriteria(conditions);

        if(criteria != null){
            query.addCriteria(criteria);
        }

        DeleteResult writeResult = getMasterMongoTemplate().remove(query, getStatement());

        return (int) writeResult.getDeletedCount();
    }

    @Override
    public int deleteByPrimary(P primary){
        final Query query = new Query();
        final Criteria criteria = Criteria.where(PRIMARY_FIELD).is(primary);

        query.addCriteria(criteria);

        DeleteResult writeResult = getMasterMongoTemplate().remove(query, getStatement());

        return (int) writeResult.getDeletedCount();
    }

    @Override
    public int clear(){
        return 0;
    }

    @Override
    public int truncate(){
        return 0;
    }

    protected final MongoTemplate getSlaveMongoTemplate(final int index) throws OperationException{
        if(Validate.isEmpty(slaveMongoTemplates)){
            return getMasterMongoTemplate();
        }else{
            MongoTemplate mongoTemplate = slaveMongoTemplates.get(index);

            if(mongoTemplate == null){
                throw new OperationException("Could not find the \"" + index + "\" slave MongoTemplate.");
            }

            return mongoTemplate;
        }
    }

    protected final MongoTemplate getSlaveMongoTemplate() throws OperationException{
        if(Validate.isEmpty(slaveMongoTemplates)){
            return getMasterMongoTemplate();
        }else{
            Random random = new Random();
            int index = random.nextInt(slaveMongoTemplates.size());

            return getSlaveMongoTemplate(index);
        }
    }

    @SuppressWarnings("unchecked")
    protected Class<E> getStatement(){
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected static Criteria buildCriteria(Map<String, Object> conditions){
        if(conditions == null){
            return null;
        }

        Criteria criteria = new Criteria();

        conditions.forEach((field, value)->{
            if(value instanceof MongoOperation){
                MongoOperation mongoOperation = (MongoOperation) value;
                MongoOperation.Operator operator = mongoOperation.getOperator();

                /* 等于 */
                if(MongoOperation.Operator.EQUAL.equals(operator)){
                    criteria.and(field).is(mongoOperation.getValue());
                    /* 不等于 */
                }else if(MongoOperation.Operator.NOT_EQUAL.equals(operator)){
                    criteria.and(field).ne(mongoOperation.getValue());
                    /* 小于 */
                }else if(MongoOperation.Operator.LT.equals(operator)){
                    criteria.and(field).lt(mongoOperation.getValue());
                    /* 小于等于 */
                }else if(MongoOperation.Operator.LTE.equals(operator)){
                    criteria.and(field).lte(mongoOperation.getValue());
                    /* 大于 */
                }else if(MongoOperation.Operator.GT.equals(operator)){
                    criteria.and(field).gt(mongoOperation.getValue());
                    /* 大于等于 */
                }else if(MongoOperation.Operator.GTE.equals(operator)){
                    criteria.and(field).gte(mongoOperation.getValue());
                    /* IN */
                }else if(MongoOperation.Operator.IN.equals(operator) == true){
                    criteria.and(field).in(mongoOperation.getValue());
                    /* NOT IN */
                }else if(MongoOperation.Operator.NIN.equals(operator)){
                    criteria.and(field).nin(mongoOperation.getValue());
                    /* 正则 */
                }else if(MongoOperation.Operator.LIKE.equals(operator)){
                    criteria.and(field).regex((String) mongoOperation.getValue());
                }
            }else{
                criteria.and(field).is(value);
            }
        });

        return criteria;
    }

    private <E> BasicDBObject toDbObject(E e){
        BasicDBObject doc = new BasicDBObject();

        masterMongoTemplate.getConverter().write(e, doc);

        return doc;
    }

}