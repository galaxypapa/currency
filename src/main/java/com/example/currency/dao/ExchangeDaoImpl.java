package com.example.currency.dao;

import com.example.currency.entity.Exchange;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ExchangeDaoImpl extends JpaBaseDao<Exchange> implements ExchangeDao {


    @Override
    public List<Exchange> list() {
        return null;
    }


    public Exchange findRateByToCurrency(@NotNull final String toCurrency) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Exchange> criteria = builder.createQuery(Exchange.class);
        Root<Exchange> from = criteria.from(Exchange.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get("toCurrency"), toCurrency));
        TypedQuery<Exchange> typed = em.createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }
}
