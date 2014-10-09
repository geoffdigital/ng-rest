package com.geoffdigital.ngrest.domain.events.students;

import java.util.UUID;

public class StudentDetails {

  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
  private String major;

  public StudentDetails() {
    id = null;
  }

  public StudentDetails(UUID id) {
    this.id = id;
  }
  
  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
  
  public void setFirstName(String firstName) {
	  this.firstName = firstName;
  }
  
  public String getFirstName() {
	  return firstName;
  }

  public void setLastName(String lastName) {
	  this.lastName = lastName;
  }
  
  public String getLastName() {
	  return lastName;
  }

  public void setEmail(String email) {
	  this.email = email;
  }
  
  public String getEmail() {
	  return email;
  }

  public void setMajor(String major) {
	  this.major = major;
  }
  
  public String getMajor() {
	  return major;
  }

}
