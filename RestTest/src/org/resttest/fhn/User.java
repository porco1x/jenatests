package org.resttest.fhn;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * Created by PoRcBook on 15-05-21.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User  {
 
	@XmlElement(name="name")
    private String name;
	
	@XmlElement(name="email")
    private String email;
	
	@XmlElement(name="organization")
    private String organization;
	
	@XmlElement(name="jobTitle")
    private String jobTitle;
	
	@XmlElement(name="birthday")
    private Date birthday;
	
	@XmlElement(name="relationshipStatus")
    private String relationshipStatus;
 
 
    public User() {
    }
 
    public User(String name, String email, String organization, String jobTitle, Date birthday, String relationshipStatus) {
        this.name = name;
        this.email = email;
        this.organization = organization;
        this.jobTitle = jobTitle;
        this.birthday = birthday;
        this.relationshipStatus = relationshipStatus;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getOrganization() {
        return organization;
    }
 
    public void setOrganization(String organization) {
        this.organization = organization;
    }
 
    public String getJobTitle() {
        return jobTitle;
    }
 
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
 
    public Date getBirthday() {
        return birthday;
    }
 
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
 
    public String getRelationshipStatus() {
        return relationshipStatus;
    }
 
    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }
 
    public int getAge(){
        Date birthday = this.getBirthday();
        Date todaysDate = getCurrentDate();
        int currentUserAge = getDiffYears(birthday,todaysDate);
        return currentUserAge;
    }
 
    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }
 
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
 
    private Date getCurrentDate() {
 
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        Date currentDate = convertToDate(formattedDate);
        return currentDate;
    }
 
    private Date convertToDate(String sDate) {
 
        Date date=null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(sDate);
            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
 
}