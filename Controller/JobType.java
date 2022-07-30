package Controller;

public class JobType {
    private String jobType;
    private int idJobType;
    private float salary;

    public JobType() {
         this.jobType = "";
        this.idJobType = 0;
        this.salary = 0;
    }

    public JobType(String jobType, int idJobType, float salary) {
        this.jobType = jobType;
        this.idJobType = idJobType;
        this.salary = salary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getIdJobType() {
        return idJobType;
    }

    public void setIdJobType(int idJobType) {
        this.idJobType = idJobType;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return jobType;
    }
    
    
    
}
