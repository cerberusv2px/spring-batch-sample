package com.sujin.spring.core;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@TypeDefs({
    @TypeDef(name = "json", typeClass = JsonStringType.class),
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public abstract class BaseEntity<T extends Serializable> extends AbstractPersistable<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private T id;

    @Version
    private int version;

    @Column(name = "created_at", insertable = true, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @Column(name = "created_by", insertable = true, updatable = false)
    @CreatedBy
    private Long createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private Long modifiedBy;

    @Override
    public T getId() {
        return id;
    }

    @Override
    public void setId(T id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) o;
        return getVersion() == that.getVersion() &&
            getId().equals(that.getId()) &&
            getCreatedAt().equals(that.getCreatedAt()) &&
            Objects.equals(getLastModifiedAt(), that.getLastModifiedAt()) &&
            getCreatedBy().equals(that.getCreatedBy()) &&
            Objects.equals(getModifiedBy(), that.getModifiedBy());
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(getId(), getVersion(), getCreatedAt(), getLastModifiedAt(), getCreatedBy(),
                getModifiedBy());
    }
}
