package com.comcast.registration.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

/**
 * Created by Vardhana Rao Gude on 7/13/2016.
 */
@Entity
@Table(name = "USER")
public class User extends AbstractDomainObject {
    private String userName;

    private String email;

    private LocalDate registeredDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "REGISTERED_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (getUserName() != null ? !getUserName().equals(user.getUserName()) : user.getUserName() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) {
            return false;
        }
        return !(getRegisteredDate() != null ? !getRegisteredDate().equals(user.getRegisteredDate()) : user.getRegisteredDate() != null);

    }

    @Override
    public int hashCode() {
        int result = getUserName() != null ? getUserName().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getRegisteredDate() != null ? getRegisteredDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", registeredDate=" + registeredDate +
                '}';
    }
}
