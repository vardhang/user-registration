package com.comcast.registration.domain;


import java.io.Serializable;

/**
 * Interface for all persistable domain objects. This interface must be parameterized with the type of the ID property.
 *
 * @author Vardhana Rao Gude on 7/13/2016.
 */
public interface PersistentDomainObject<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID id);
}
