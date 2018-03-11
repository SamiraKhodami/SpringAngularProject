package com.smr.domain.user;

import javax.persistence.*;
import com.smr.domain.BaseModel;

@Entity
@Table(name = "USER", uniqueConstraints = {
		@UniqueConstraint(columnNames = "USERNAME")})
public class User extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;

    @Column(name = "USERNAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;    
	
	@Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String userName, String password, String firstName, String surname) {
        this.id = id;
        this.userName = userName;
        this.password = password;        
		this.firstName = firstName;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}