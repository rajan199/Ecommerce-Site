package com.application.Infibeam.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	 
	    //@Enumerated(EnumType.STRING)
	    
	    @Column(length = 60)
	    private String name;
		 
	    // private RoleName name;
	    
	       @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
		   @JoinTable(name = "user_roles", 
		    joinColumns = @JoinColumn(name = "roleid"), 
		    inverseJoinColumns = @JoinColumn(name = "userid"))
	    	@JsonIgnore
		    private Set<Users> user = new HashSet();
		
	    
	 
	    public Role() {}
	 
	    
	  
	 
	    public Role(String name) {
			super();
			this.name = name;
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




		public Set<Users> getUser() {
			return user;
		}




		public void setUser(Set<Users> user) {
			this.user = user;
		}
		

		
	

}
