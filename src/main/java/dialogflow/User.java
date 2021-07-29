package dialogflow;

public class User {
    private String name;
    private String job;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public String toString(){
        return this.name + " :: " + this.job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
