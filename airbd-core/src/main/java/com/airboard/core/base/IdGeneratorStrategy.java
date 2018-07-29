package com.airboard.core.base;

import com.airboard.core.util.IdWorker;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * @Description 自定义JPA entity ID 生成策略
 */
public class IdGeneratorStrategy implements Configurable, IdentifierGenerator {

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {

    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        IdWorker idWorker = new IdWorker(0, 0);
        return idWorker.nextId();
    }
}
