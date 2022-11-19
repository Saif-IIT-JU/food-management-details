package com.saif.foodmanagement.model;

import com.saif.foodmanagement.utils.Constants;
import com.saif.foodmanagement.validation.ValidationGroups;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author saifuzzaman
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL_USERS, query = "FROM User WHERE role != 'ADMIN' ORDER BY firstName, lastName"),
        @NamedQuery(name = User.FIND_BY_USERNAME, query = "FROM User WHERE username = :username"),
        @NamedQuery(name = User.FIND_BY_EMAIL, query = "FROM User WHERE email = :email")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_USERS = "User.findAllUsers";
    public static final String FIND_BY_USERNAME = "User.findByUsername";
    public static final String FIND_BY_EMAIL = "User.findByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupOne.class})
    @Size(max = Constants.MAX_NAME_LENGTH, message = "{max.name.length}", groups = {ValidationGroups.ValidationGroupOne.class})
    @Column(length = Constants.MAX_NAME_LENGTH, nullable = false)
    private String firstName;

    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupOne.class})
    @Size(max = Constants.MAX_NAME_LENGTH, message = "{max.name.length}", groups = {ValidationGroups.ValidationGroupOne.class})
    @Column(length = Constants.MAX_NAME_LENGTH, nullable = false)
    private String lastName;

    @Email
    @Size(max = Constants.MAX_EMAIL_LENGTH, message = "{max.email.length}", groups = {ValidationGroups.ValidationGroupOne.class})
    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupOne.class})
    @Column(length = Constants.MAX_EMAIL_LENGTH, nullable = false, unique = true)
    private String email;

    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupTwo.class})
    @Size(max = Constants.MAX_NAME_LENGTH, message = "{max.name.length}", groups = {ValidationGroups.ValidationGroupTwo.class})
    @Column(length = Constants.MAX_NAME_LENGTH, unique = true, nullable = false)
    private String username;

    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupTwo.class})
    @Size(max = Constants.MAX_PASSWORD_LENGTH, message = "{password.length}", groups = {ValidationGroups.ValidationGroupTwo.class})
    @Column(length = Constants.MAX_PASSWORD_LENGTH, nullable = false)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedOn;

    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupTwo.class})
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean isBlocked;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addedBy")
    private List<Food> foods;

    public User() {
        reviews = new ArrayList<>();
        foods = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public boolean isNew() {
        return this.getId() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
