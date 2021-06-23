package com.sujin.spring.domain.auth.resetpassword;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sujin.spring.domain.auth.AuthUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "password_reset_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@EntityListeners(AuditingEntityListener.class)
public class PasswordResetToken extends AbstractPersistable<Long> {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AuthUser user;

    private String token;

    private Date expiryDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @Transient
    public boolean isExpired() {
        Calendar cal = Calendar.getInstance();
        return expiryDate.getTime() - cal.getTime().getTime() <= 0;
    }
}
