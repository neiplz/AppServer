package com.github.neiplz.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "app_pref")
public class AppPref {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@NotNull
	@Size(min = 3, max = 100)
    private String email;
    private int stride;
    private int goal;
    private float sensitivity;

    public AppPref() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppPref(long id) {
		super();
		this.id = id;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStride() {
        return stride;
    }

    public void setStride(int stride) {
        this.stride = stride;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public float getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(float sensitivity) {
        this.sensitivity = sensitivity;
    }

}
