public class Members {
    private String Id;
    private String Name;
    private String Department;
    private String Email;



    public Members(){}
    public Members(String id, String name, String department, String email )
{
    this.Id=id;
    this.Name=name;
    this.Department=department;
    this.Email=email;
}

public String getUId() {
    return Id;
}

public void setUId(String Id) {
    this.Id = Id;
}
public String getName() {
    return Name;
}

public void setName(String Name) {
    this.Name = Name;
}
public String getEmail() {
    return Email;
}

public void setEmail(String Email) {
    this.Email = Email;
}


public String getDepart() {
    return Department;
}

public void setDepart(String department) {
    this.Department = department;
}

}
