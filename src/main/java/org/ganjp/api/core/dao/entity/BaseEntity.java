package org.ganjp.api.core.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * Every Entity must implements the BaseEntity.
 * The entity include create info, update info, isActive.
 * The create date and update date is auto generated from system.
 * The create and update user name is auto generated from Authentication.
 * The isActive default value is 1
 *
 * @author Jianping
 * @date 20/06/2020
 * @version 1.0.0
 *
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable, Cloneable {

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "is_active")
    private int isActive = 1;


    /*
     * The method will run before insert data to dao.
     */
//    @PrePersist
//    public void preInsert() {
//        // Set the user name who create the record.
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (StringUtils.hasText(name)) {
//            this.createdBy = name;
//        } else if (!StringUtils.hasText(this.createdBy)) {
//            this.createdBy = "unknown";
//        }
//
//        // Set create date time
//        this.createdAt = new Date();
//
//        // Set default status to Active
//        if (this.isActive == null) {
//            this.isActive = 1;
//        }
//    }

    /*
     * The method will run before update data to dao.
     */
//    @PreUpdate()
//    void preUpdate() {
//        // Set the user name who update.
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (StringUtils.hasText(name)) {
//            this.updatedBy = name;
//        } else if (!StringUtils.hasText(this.updatedBy)) {
//            this.updatedBy = "unknown";
//        }
//
//        // set update date time
//        this.updatedAt = new Date();
//    }
}
