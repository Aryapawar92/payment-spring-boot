package payment_backend.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;

// MappedSuperclass - means JPA includes these fields in child tables
// * but does NOT create a separate table for BaseEntity itself
// @EnableJpaAuditing — without it, @CreatedDate and @LastModifiedDate won't auto populate.


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private Instant updatedAt;

    @Column(name = "is_deleted",nullable = false)
    private boolean deleted;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    public void softDelete(){
        this.deleted = true;
        this.deletedAt = Instant.now();

    }
}
