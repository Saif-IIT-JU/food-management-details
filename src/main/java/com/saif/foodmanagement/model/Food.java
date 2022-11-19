package com.saif.foodmanagement.model;

import com.saif.foodmanagement.utils.Constants;
import com.saif.foodmanagement.validation.ValidationGroups;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Table(name = "foods")
@NamedQueries({
        @NamedQuery(name = Food.FIND_APPROVED_FOODS, query = "FROM Food WHERE approval = true ORDER BY name"),
        @NamedQuery(name = Food.FIND_PENDING_FOODS, query = "FROM Food WHERE approval = false ORDER BY name"),
        @NamedQuery(name = Food.FIND_BY_SERVE_DATE, query = "FROM Food WHERE serveDate = :date AND approval = true ORDER BY name"),
        @NamedQuery(name = Food.FIND_BY_CREATOR, query = "FROM Food WHERE added_by_id = :added_by_id ORDER BY name"),
        @NamedQuery(name = Food.FIND_BY_NAME, query = "FROM Food WHERE LOCATE(:name, name) != 0 AND approval = true ORDER BY name")
})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_APPROVED_FOODS = "Food.findAllApprovedFoods";
    public static final String FIND_PENDING_FOODS = "Food.findAllUnapprovedFoods";
    public static final String FIND_BY_SERVE_DATE = "Food.findByServeDate";
    public static final String FIND_BY_CREATOR = "Food.findByCreator";
    public static final String FIND_BY_NAME = "Food.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupOne.class})
    @Size(max = Constants.MAX_NAME_LENGTH, message = "{max.name.length}", groups = {ValidationGroups.ValidationGroupOne.class})
    @Column(length = Constants.MAX_NAME_LENGTH, nullable = false)
    private String name;

    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupThree.class})
    @Enumerated(EnumType.STRING)
    @Column(length = Constants.MAX_NAME_LENGTH, nullable = false)
    private Meal meal;

    @Column(nullable = false)
    private boolean approval;

    @NotNull(message = "{message.not.null}", groups = {ValidationGroups.ValidationGroupTwo.class})
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date serveDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    private User addedBy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food")
    private List<Review> reviews;

    public Food() {
        reviews = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
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

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public Date getServeDate() {
        return serveDate;
    }

    public void setServeDate(Date serveDate) {
        this.serveDate = serveDate;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public boolean isNew() {
        return this.getId() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        return id == food.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
