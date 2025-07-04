package assign05.entities;

public class Candidate {
    private int id;
    private String name;
    private String party;
    private int votes;
    
    public Candidate() {
        this.votes = 0;
    }
    
    public Candidate(String name, String party) {
        this();
        this.name = name;
        this.party = party;
    }
    
    // Getters and Setters
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
    
    public String getParty() {
        return party;
    }
    
    public void setParty(String party) {
        this.party = party;
    }
    
    public int getVotes() {
        return votes;
    }
    
    public void setVotes(int votes) {
        this.votes = votes;
    }
    
    @Override
    public String toString() {
        return "Candidate [id=" + id + ", name=" + name + ", party=" + party + ", votes=" + votes + "]";
    }
}

