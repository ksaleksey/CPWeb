package ru.aleksey.cp.model;




import javax.persistence.*;

/**
 * Created by Алексей on 29.09.2016.
 */
@Entity
@Table(name="ACCOUNTS")
public class Account {

    @Id
    @Column(name="ID")
    @SequenceGenerator(name="ACCOUNTS_ID_SEQ", sequenceName="ACCOUNTS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNTS_ID_SEQ")
    private Integer id;


//    @Column(name="PERSON_ID")
//    private Integer personId;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID")
    private Person person;
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }





    @Column(name="ACCNUM", insertable = false)
    //@SequenceGenerator(name="ACCNUM_SEQ", sequenceName="ACCNUM_SEQ", allocationSize = 1)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCNUM_SEQ")
    private Integer accnum;





    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getPersonId() { return personId; }
//
//    public void setPersonId(Integer personId) { this.personId = personId; }

    public Integer getAccnum() {
        return accnum;
    }

    public void setAccnum(Integer accnum) {
        this.accnum = accnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
//                ", personId=" + personId +
                ", accnum=" + accnum +
                ", address='" + address + '\'' +
                '}';
    }
}
