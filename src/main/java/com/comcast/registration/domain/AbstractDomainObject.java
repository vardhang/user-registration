package com.comcast.registration.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Version;

/**
 * Created by Vardhana Rao Gude on 7/13/2016.
 */
public abstract class AbstractDomainObject implements PersistentDomainObject<String>, Auditable<String, String> {
    protected String id;

    private String createdBy;
    private String lastModifiedBy;
    private DateTime lastModifiedDate;
    private DateTime createdDate;
    private Long version;

    @Column(name = "CREATED_BY", updatable = false)
    public String getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(String s) {
        this.createdBy = s;
    }

    @Column(name = "CREATED_DATE", updatable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    public DateTime getCreatedDate() {
        return createdDate;

    }

    public void setCreatedDate(DateTime dateTime) {
        this.createdDate = dateTime;
    }


    @Column(name = "LAST_CHANGED_BY")
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }


    public void setLastModifiedBy(String s) {
        lastModifiedBy = s;
    }


    @Column(name = "LAST_CHANGED_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime dateTime) {
        lastModifiedDate = dateTime;
    }

    @Version
    @Column(name = "OBJECT_VERSION")
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNew() {
        return getId() == null;
    }

}
